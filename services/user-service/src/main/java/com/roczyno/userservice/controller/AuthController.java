package com.roczyno.userservice.controller;

import com.roczyno.userservice.dto.AuthRequest;
import com.roczyno.userservice.dto.UserDto;
import com.roczyno.userservice.service.AuthService;
import com.roczyno.userservice.service.JwtService;
import jakarta.ws.rs.HeaderParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    @PostMapping("/signup")
    public UserDto signUp (@RequestBody AuthRequest request) {
        return authService.signUp(request);
    }

    @PostMapping("/login")
    public String login (@RequestBody AuthRequest request) {
        return authService.logIn(request);
    }

    @GetMapping("/validate")
    public ResponseEntity<UserDto> verifyToken (@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(authService.validateToken(token), HttpStatus.OK);
    }

}
