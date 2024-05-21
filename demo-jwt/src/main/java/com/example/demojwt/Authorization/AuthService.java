package com.example.demojwt.Authorization;


import com.example.demojwt.User.Role;
import com.example.demojwt.User.User;
import com.example.demojwt.User.UserRepository;
import com.example.demojwt.jwt.JwtService;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableWebSecurity
public class AuthService {


    final private JwtService jwtService;
    final private UserRepository userRepository;

    public AuthResponse login(LoginRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .country(request.getCountry())
            .role(Role.USER) 
            .build();

            userRepository.save(user);

        var token = AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
        return token; 
    }


}
