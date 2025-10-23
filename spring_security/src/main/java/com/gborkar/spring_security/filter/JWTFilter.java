package com.gborkar.spring_security.filter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gborkar.spring_security.service.JWTService;
import com.gborkar.spring_security.service.UserService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
        String username = null;
        System.out.println(jwtToken);

        if (jwtToken != null) {
            try {
                username = jwtService.extractUsername(jwtToken);
            } catch (JwtException | IllegalArgumentException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(username);

            try {
                if (jwtService.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null,
                            userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (IllegalArgumentException | NoSuchAlgorithmException | JwtException e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
    
}
