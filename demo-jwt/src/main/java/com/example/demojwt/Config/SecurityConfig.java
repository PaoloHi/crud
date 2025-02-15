package com.example.demojwt.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demojwt.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter; 
    private final AuthenticationProvider authProvider;

    
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        
        return http
            .csrf(csrf->
                csrf
                .disable())
            .authorizeHttpRequests(authRequest ->
            authRequest.anyRequest().permitAll()
            ).sessionManagement(sessionManager -> 
                    sessionManager.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(Customizer.withDefaults()) // disable this line to reproduce the CORS 401
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}




