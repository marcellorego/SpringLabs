package br.com.splessons.lesson12.security;

import br.com.splessons.lesson12.security.domain.UserPrincipal;
import br.com.splessons.lesson12.security.helper.TokenHelper;
import br.com.splessons.lesson12.security.service.AuthenticationResolver;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
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

    private Clock appClock;

    @Autowired
    public JwtTokenProvider(@Value("${app.jwtLifetime}") int jwtLifetime,
                            @Value("${app.jwtRenewal}") int jwtRenewal,
                            @Value("${app.jwtSecret}") String jwtSecret,
                            Clock appClock) {
        LOGGER.info("JWT tokens will be valid for {} seconds and renewed {} seconds before expiration", jwtLifetime,
                jwtRenewal);
        this.jwtLifetime = jwtLifetime;
        this.jwtRenewal = jwtRenewal;
        this.jwtSecret = jwtSecret.getBytes(StandardCharsets.UTF_8);
        this.appClock = appClock;
    }

    protected Map<String, Object> createClaims(UserPrincipal userPrincipal) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(TokenHelper.CLAIM_KEY_UNIQUE_ID, userPrincipal.getId());
        claims.put(TokenHelper.ROLES_CLAIM, userPrincipal.getRoles().toArray());

        return claims;
    }

    public String createAuthenticationToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Map<String, Object> claims = createClaims(userPrincipal);

        Date expiration = Date.from(appClock.instant().plusMillis(jwtLifetime * 1000));

        String result = TokenHelper.buildJwtClaimsToken(userPrincipal.getUsername(), claims, expiration,
                this.jwtSecret, appClock);

        return result;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
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
                    final Long userId = claims.get(TokenHelper.CLAIM_KEY_UNIQUE_ID, Long.class);
                    if (userId != null && userId.longValue() > 0) {
                        Authentication auth = authenticationResolver.getAuthentication(request, userId);
                        if (shouldRenew(claims.getExpiration())) {
                            addResponseAuthentication(auth, response);
                        }
                        return auth;
                    }
                }
            } catch (JwtException | AuthenticationException e) {
                LOGGER.error(e.getMessage(), e);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                throw e;
            }
        }
        return null;
    }

    private boolean shouldRenew(Date expiration) {

        Instant expiresAt = expiration.toInstant().minus(this.jwtRenewal, ChronoUnit.SECONDS);
        LocalDateTime expiresDateTime = LocalDateTime.ofInstant(expiresAt, ZoneId.systemDefault());
        return expiresDateTime.isBefore(LocalDateTime.now(appClock));
    }

    private void addResponseAuthentication(Authentication auth, HttpServletResponse response) {
        final String jwt = createAuthenticationToken(auth);
        response.addHeader(HttpHeaders.AUTHORIZATION, TokenHelper.TOKEN_HEADER_PREFIX + jwt);
    }
}
