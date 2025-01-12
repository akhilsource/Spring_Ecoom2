package com.example;

import com.example.Security.Jwtfilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private Jwtfilter jwtfilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Enable default CORS settings
                .csrf(AbstractHttpConfigurer::disable)    // Disable CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/signup", "/users/login","/products/intro_fetch").permitAll() // Public endpoints
                        .anyRequest().authenticated()                    // Secure all other endpoints
                )
                .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return http.build();
    }
}
