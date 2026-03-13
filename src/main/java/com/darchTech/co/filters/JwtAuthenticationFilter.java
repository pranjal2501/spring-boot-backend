package com.darchTech.co.filters;

import com.darchTech.co.exception.JwtTokenException;
import com.darchTech.co.utilities.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private final HandlerExceptionResolver resolver;
    @Autowired
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            String username = null;
            String jwt = null;

            // Check for "Token is missing" scenario
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                // We don't throw an exception here because some APIs (Login/Register) are public.
                // SecurityConfig will handle the "unauthorized" block if the route is protected.
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            resolver.resolveException(request, response, null, new JwtTokenException("Session expired."));
        } catch (io.jsonwebtoken.security.SignatureException e) {
            resolver.resolveException(request, response, null, new JwtTokenException("Tampered token detected."));
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            resolver.resolveException(request, response, null, new JwtTokenException("Invalid token format."));
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            resolver.resolveException(request, response, null, new JwtTokenException("Unsupported token."));
        } catch (IllegalArgumentException e) {
            resolver.resolveException(request, response, null, new JwtTokenException("Token is missing or empty."));
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
    }
}