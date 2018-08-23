package br.com.splessons.lesson12.security.helper;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.time.Clock;

public class TokenHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenHelper.class);

    public static final String TOKEN_HEADER_PREFIX = "Bearer ";

    public static final String ROLES_CLAIM = "roles";
    public static final String CLAIM_KEY_UNIQUE_ID = "guid";
    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "iat";

    private TokenHelper() {
        // Private constructor for utility class
    }

    private static JwtBuilder initJwtClaimsToken(String subject, Date expiration, Clock clock) {
        final Date issuedAt = Date.from(clock.instant());
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration);
    }

    public static String buildJwtClaimsToken(String subject,
                                             Map<String, Object> claims, Date expiration,
                                             byte[] signSecret,
                                             Clock clock) {
        return initJwtClaimsToken(subject, expiration, clock)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, signSecret)
                .compact();
    }

    public static Optional<Claims> getClaimsFromToken(String token, byte[] signSecret) throws JwtException {

        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(signSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature", ex);
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token", ex);
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token", ex);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.", ex);
        }
        return Optional.ofNullable(claims);
    }
}
