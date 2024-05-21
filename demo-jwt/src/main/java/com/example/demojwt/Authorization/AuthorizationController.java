package com.example.demojwt.Authorization;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthorizationController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse>  login( @RequestBody LoginRequest request) {
        var login = authService.login(request);   
        return ResponseEntity.ok(login);
    }
    
    @PostMapping("/register")
   public ResponseEntity<AuthResponse>  register( @RequestBody RegisterRequest request) {
        var register = authService.register(request);
        return ResponseEntity.ok(register);
    }
    


}
