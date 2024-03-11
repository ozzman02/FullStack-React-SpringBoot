package net.ossant.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import net.ossant.utils.TokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static net.ossant.constants.ApplicationConstants.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader(AUTHORIZATION);

        if (bearerToken != null && bearerToken.startsWith(BEARER)) {
            String token = bearerToken.substring(BEARER_PREFIX_LENGTH);

            Claims claims = Jwts.parser()
                    .verifyWith(TokenUtil.getKey() )
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String usernameOrEmail = String.valueOf(claims.get(USERNAME_OR_EMAIL_CLAIM));
            String authorities = (String) claims.get(AUTHORITIES_CLAIM);

            Authentication auth = new UsernamePasswordAuthenticationToken(usernameOrEmail, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    /* We need to execute this for all api calls except for login
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/api/auth");
    }*/

}
