package br.com.splessons.lesson12.security;

import br.com.splessons.lesson12.security.domain.UserPrincipal;
import br.com.splessons.lesson12.security.helper.TokenHelper;
import br.com.splessons.lesson12.security.service.AuthenticationResolver;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final byte[] jwtSecret;
    private final int jwtLifetime;
    private final int jwtRenewal;

    @Autowired
    private AuthenticationResolver authenticationResolver;

    @Autowired
    public JwtTokenProvider(@Value("${app.jwtLifetime}") int jwtLifetime, @Value("${app.jwtRenewal}") int jwtRenewal,
                            @Value("${app.jwtSecret}") String jwtSecret) {
        LOGGER.info("JWT tokens will be valid for {} seconds and renewed {} seconds before expiration", jwtLifetime,
                jwtRenewal);
        this.jwtLifetime = jwtLifetime;
        this.jwtRenewal = jwtRenewal;
        this.jwtSecret = jwtSecret.getBytes(StandardCharsets.UTF_8);
    }

    protected Map<String, Object> createClaims(UserPrincipal userPrincipal) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(TokenHelper.GLOBAL_UNIQUE_ID_CLAIM, userPrincipal.getId());
        claims.put(TokenHelper.ROLES_CLAIM, userPrincipal.getRoles().toArray());

        return claims;
    }

    public String createAuthenticationToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Map<String, Object> claims = createClaims(userPrincipal);
        Date expiration = new Date(System.currentTimeMillis() + jwtLifetime * 1000);

        String result = TokenHelper.buildJwtClaimsToken(userPrincipal.getUsername(), claims, expiration,
                this.jwtSecret);

        return result;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(TokenHelper.AUTHORIZATION_HEADER_NAME);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TokenHelper.TOKEN_HEADER_PREFIX)) {
            return bearerToken.substring(TokenHelper.TOKEN_HEADER_PREFIX.length());
        }
        return null;
    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = getTokenFromRequest(request);
        if (StringUtils.hasText(token)) {
            // parse the token.
            try {
                Optional<Claims> claimsOptional = TokenHelper.getClaimsFromToken(token, this.jwtSecret);
                if (claimsOptional.isPresent()) {
                    final Claims claims = claimsOptional.get();
                    final Long userId = claims.get(TokenHelper.GLOBAL_UNIQUE_ID_CLAIM, Long.class);
                    if (userId != null && userId.longValue() > 0) {
                        Authentication auth = authenticationResolver.getAuthentication(request, userId);
                        if (shouldRenew(claims.getExpiration())) {
                            addResponseAuthentication(auth, response);
                        }
                        //
                        return auth;
                    }
                }
            } catch (JwtException e) {
                LOGGER.error(e.getMessage(), e);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                throw e;
            }
        }
        return null;
    }

    private boolean shouldRenew(Date expiration) {
        return LocalDateTime.ofInstant(expiration.toInstant().minus(this.jwtRenewal, ChronoUnit.SECONDS),
                ZoneId.systemDefault()).isBefore(LocalDateTime.now());
    }

    private void addResponseAuthentication(Authentication auth, HttpServletResponse response) {
        final String jwt = createAuthenticationToken(auth);
        response.addHeader(TokenHelper.TOKEN_HEADER_PREFIX, TokenHelper.TOKEN_HEADER_PREFIX + jwt);
    }
}
