package com.sara.backend;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;


    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {

        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .headers(headers ->
                        headers.frameOptions(frame -> frame.disable())
                )

                .sessionManagement(session -> session

                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/auth/register", "/auth/login", "/h2-console/**", "/swagger-ui/**",

                                "/v3/api-docs/**")

                        .permitAll()
                        .requestMatchers("/auth/admin")
                        .hasRole("ADMIN")
                        .anyRequest()

                        .authenticated()

                )

                .httpBasic(httpBasic -> httpBasic.disable())

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean

    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {

        return username -> {

            throw new org.springframework.security.core.userdetails.UsernameNotFoundException(

                    "Default Spring user disabled"

            );
        };
    }
}

