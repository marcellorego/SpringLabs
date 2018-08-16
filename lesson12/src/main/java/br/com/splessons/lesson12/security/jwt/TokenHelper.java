package br.com.splessons.lesson12.security.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TokenHelper {

    public static final String TOKEN_HEADER_PREFIX = "Bearer ";
    public static final String JWT_HEADER = "Authorization";

    static final String ROLES_CLAIM = "roles";
    static final String GLOBAL_UNIQUE_ID_CLAIM = "guid";

    private TokenHelper() {
        // Private constructor for utility class
    }

    static JwtBuilder initJwtClaimsToken(String subject, Date expiration) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expiration);
    }

    private static String buildJwtClaimsToken(String subject, Map<String, Object> claims, Date expiration, byte[] signSecret) {
        return initJwtClaimsToken(subject, expiration)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signSecret)
                .compact();
    }

    public static String buildInternalAuthenticationToken(String appName, String appVersion, String onBehalfUser, Set<String> roles) {

        Map<String, Object> claims = new HashMap<>(3);
        claims.put(APPNAME_CLAIM, appName);
        claims.put(APPVERSION_CLAIM, appVersion);
        if (onBehalfUser != null && !onBehalfUser.trim().isEmpty()) {
            claims.put(ONBEHALF_CLAIM, onBehalfUser);
        }
        if (roles != null && !roles.isEmpty()) {
            claims.put(ROLES_CLAIM, roles);
        }

        return buildJwtClaimsToken(RAD_INTERNAL_SUBJECT, claims, new Date(System.currentTimeMillis() + INTERNAL_TOKEN_LIFETIME * 1000), INTERNAL_SECRET);
    }

    public static String buildInternalAuthenticationToken(String appName, String appVersion) {
        return buildInternalAuthenticationToken(appName, appVersion, null, null);
    }
    
    // User is RAD_INTERNAL_SUBJECT and is not on behalf of other user
    public static boolean isStrictRadInternalUser(final String username) {
        return TokenHelper.RAD_INTERNAL_SUBJECT.equals(username);
    }

}
