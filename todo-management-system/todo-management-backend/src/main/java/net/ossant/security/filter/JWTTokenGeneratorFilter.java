package net.ossant.security.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.StringJoiner;

import static net.ossant.constants.ApplicationConstants.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = BEARER + Jwts.builder()
                    .issuer(JWT_ISSUER)
                    .subject(JWT_SUBJECT)
                    .claim(USERNAME_OR_EMAIL_CLAIM, authentication.getName())
                    .claim(AUTHORITIES_CLAIM, populateAuthorities(authentication.getAuthorities()))
                    .issuedAt(new Date())
                    .expiration(new Date((new Date()).getTime() + JWT_EXPIRATION))
                    .signWith(key)
                    .compact();
            response.setHeader(AUTHORIZATION, jwt);
        }
        filterChain.doFilter(request, response);
    }

    /* We want to execute the filter only when the user logs in */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/api/auth");
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        StringJoiner joiner = new StringJoiner(",");
        collection.forEach(grantedAuthority -> joiner.add(grantedAuthority.getAuthority()));
        return joiner.toString();
    }
}
