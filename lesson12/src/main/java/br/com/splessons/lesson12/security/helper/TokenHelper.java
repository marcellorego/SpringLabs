package br.com.splessons.lesson12.security.helper;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class TokenHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenHelper.class);

    public static final String TOKEN_HEADER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    public static final String ROLES_CLAIM = "roles";
    public static final String GLOBAL_UNIQUE_ID_CLAIM = "guId";

    private TokenHelper() {
        // Private constructor for utility class
    }

    private static JwtBuilder initJwtClaimsToken(String subject, Date expiration) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(expiration);
    }

    public static String buildJwtClaimsToken(String subject,
                                             Map<String, Object> claims, Date expiration,
                                             byte[] signSecret) {
        return initJwtClaimsToken(subject, expiration)
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
        return Optional.of(claims);
    }
}
