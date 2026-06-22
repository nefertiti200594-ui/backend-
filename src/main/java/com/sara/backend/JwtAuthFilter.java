package com.sara.backend;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Collections;
import java.io.IOException;

@Component

public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthFilter(JwtService jwtService,
                         UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getServletPath();

        System.out.printf("PATH: " + path );
        if (
                path.equals("/auth/login") ||
                        path.equals("/auth/register") ||
                        path.startsWith("/h2-console")||
                        path.startsWith("/swagger-ui") ||
                                path.startsWith("/v3/api-docs")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        System.out.println("HEADER: " + authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            System.out.println("NO TOKEN OR WRONG FORMAT");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        return;
    }
        String token = authHeader.substring(7);
        System.out.println("TOKEN: " + token);

        try {

            String email = jwtService.extractEmail(token);
            User user = userRepository.findByEmail(email)
                    .orElseThrow();
            UsernamePasswordAuthenticationToken auth =

                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(

                                    new SimpleGrantedAuthority(

                                            "ROLE_" + user.getRole().name()

                                    )

                            )

                    );

            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println("TOKEN OK: " + email);

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            System.out.println("TOKEN ERROR: " + e.getMessage());

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        }

    }

}
