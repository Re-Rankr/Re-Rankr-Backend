package com.roczyno.userservice.service;

import com.roczyno.userservice.dto.AuthRequest;
import com.roczyno.userservice.dto.ResetPasswordRequest;
import com.roczyno.userservice.dto.UserDto;
import com.roczyno.userservice.dto.VerificationRequest;
import com.roczyno.userservice.entity.OTP;
import com.roczyno.userservice.enums.OtpType;
import com.roczyno.userservice.repository.UserRepository;
import com.roczyno.userservice.entity.User;
import com.roczyno.userservice.service.OtpService.OtpService;
import com.roczyno.userservice.service.mailservice.MailService;
import com.roczyno.userservice.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final OtpService otpService;
    private final UserMapper userMapper;
    private final MailService mailService;

    public UserDto signUp (AuthRequest request) {
        Optional<User> check = userRepository.findByEmail(request.email());
        if(check.isPresent()){
            throw new RuntimeException("User exists");
        }
        User user = User.builder()
                .email(request.email())
                .username(request.username())
                .password(encoder.encode(request.password()))
                .build();
        User createdUser = userRepository.save(user);
        mailService.sendVerificationMail(
                otpService.generateOTP(OtpType.VERIFY,user,true)
        );
        return UserDto.builder()
                .id(createdUser.getId())
                .email(createdUser.getEmail())
                .username(createdUser.getUsername())
                .build();
    }

    public String logIn(AuthRequest request) {
        User user = getUser(request.email());
        if(encoder.matches(request.password(),user.getPassword())){
            return jwtService.generateToken(
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(),request.password())),
                    user.getId().toString()
            );
        }
        return "Unauthorized";
    }

    public User getUser (String email) {
        return userRepository.findByEmail(email).orElseThrow(
                ()->new RuntimeException("User Does Not Exist"));
    }

    public UserDto validateToken (String token) {
        Map<String , Object> claims = jwtService.extractToken(token);
        return UserDto.builder()
                .id(UUID.fromString(claims.get("id").toString()))
                .email(claims.get("sub").toString())
                .build();
    }

    public UserDto verifyOTP(VerificationRequest request) {
        User user = otpService.verifyOTP(request.value(), request.email());
        user.setVerified(true);
        userRepository.save(user);
        return userMapper.mapToUserDto(user);
    }
    public UserDto resetPassword (UUID id,ResetPasswordRequest request) {
        if(request.otp() == null) {
            OTP otp = otpService.generateOTP(
                    OtpType.RESET,
                    userRepository.findByEmail(request.email()).orElseThrow(() ->new RuntimeException("User exists")),
                    true
                    );
            mailService.sendResetMail(otp);
            return null;
        }
        User user = otpService.verifyOTP(request.otp(),id);
        user.setPassword(encoder.encode(request.newPassword()));
        userRepository.save(user);
        return null;
    }
}
