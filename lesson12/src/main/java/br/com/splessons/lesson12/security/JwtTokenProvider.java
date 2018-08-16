package br.com.splessons.lesson12.security;

import br.com.splessons.lesson12.security.domain.UserPrincipal;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final byte[] jwtSecret;
    private final int jwtLifetime;
    private final int jwtRenewal;

    @Autowired
    public JwtTokenProvider(@Value("${app.jwtLifetime}") int jwtLifetime,
                            @Value("${app.jwtRenewal}") int jwtRenewal,
                            @Value("${app.jwtSecret}") String jwtSecret) {
        LOGGER.info("JWT tokens will be valid for {} seconds and renewed {} seconds before expiration", jwtLifetime, jwtRenewal);
        this.jwtLifetime = jwtLifetime;
        this.jwtRenewal = jwtRenewal;
        this.jwtSecret = jwtSecret.getBytes(StandardCharsets.UTF_8);
    }

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();


        .initJwtClaimsToken(username, new Date(System.currentTimeMillis() + jwtLifetime * 1000))
                .claim(TokenHelper.GLOBAL_UNIQUE_ID_CLAIM, user.getGlobalUniqueId())
                .claim(TokenHelper.ROLES_CLAIM, user.getComputedRoles().toArray())
                .compressWith(CompressionCodecs.GZIP)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

    }

}
