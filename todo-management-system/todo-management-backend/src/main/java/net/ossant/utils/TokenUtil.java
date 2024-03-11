package net.ossant.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.StringJoiner;

import static net.ossant.constants.ApplicationConstants.*;

public class TokenUtil {

    public static String generateToken(Authentication authentication) {
        return Jwts.builder()
                .issuer(JWT_ISSUER)
                .subject(JWT_SUBJECT)
                .claim(USERNAME_OR_EMAIL_CLAIM, authentication.getName())
                .claim(AUTHORITIES_CLAIM, populateAuthorities(authentication.getAuthorities()))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + JWT_EXPIRATION))
                .signWith(getKey())
                .compact();
    }

    public static String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(USERNAME_OR_EMAIL_CLAIM)
                .toString();
    }

    public static String getRoles(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(AUTHORITIES_CLAIM)
                .toString();
    }

    public static boolean validateToken(String token) {
        Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token);
        return true;
    }

    public static SecretKey getKey() {
        return Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        StringJoiner joiner = new StringJoiner(",");
        collection.forEach(grantedAuthority -> joiner.add(grantedAuthority.getAuthority()));
        return joiner.toString();
    }

}
