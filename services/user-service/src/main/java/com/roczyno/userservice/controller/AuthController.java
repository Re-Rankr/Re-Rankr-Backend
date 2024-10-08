package com.roczyno.userservice.controller;

import com.roczyno.userservice.dto.AuthRequest;
import com.roczyno.userservice.dto.ResetPasswordRequest;
import com.roczyno.userservice.dto.UserDto;
import com.roczyno.userservice.dto.VerificationRequest;
import com.roczyno.userservice.entity.OTP;
import com.roczyno.userservice.service.AuthService;
import com.roczyno.userservice.service.OtpService.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final OtpService otpService;
    @PostMapping("/signup")
    public UserDto signUp (@RequestBody @Valid AuthRequest request) {
        return authService.signUp(request);
    }

    @PostMapping("/login")
    public String login (@RequestBody AuthRequest request) {
        return authService.logIn(request);
    }

    @GetMapping("/validate")
    public ResponseEntity<UserDto> validateUser (@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return new ResponseEntity<>(authService.validateToken(token), HttpStatus.OK);
    }

    @GetMapping("/resend/{id}")
    public ResponseEntity<OTP> resendOTP (@PathVariable UUID id) {
        return new ResponseEntity<>(otpService.getOTP(id),HttpStatus.OK);
    }

    @PatchMapping("verify")
    public ResponseEntity<UserDto> verify(@RequestBody VerificationRequest request) {
        return new ResponseEntity<>(authService.verifyOTP(request),HttpStatus.OK);
    }

    @PostMapping("reset")
    public ResponseEntity<UserDto> requestReset (@RequestBody ResetPasswordRequest request) {
        return new ResponseEntity<>(authService.resetPassword(UUID.randomUUID(),request),HttpStatus.OK);
    }
    @PatchMapping("reset/{id}")
    public ResponseEntity<UserDto> resetPassword (@PathVariable UUID id, @RequestBody ResetPasswordRequest request) {
        return new ResponseEntity<>(authService.resetPassword(id,request),HttpStatus.OK);
    }
}
