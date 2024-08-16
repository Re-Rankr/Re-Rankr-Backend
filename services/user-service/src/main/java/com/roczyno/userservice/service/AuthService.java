package com.roczyno.userservice.service;

import com.roczyno.userservice.dto.AuthRequest;
import com.roczyno.userservice.dto.UserDto;
import com.roczyno.userservice.dto.VerificationRequest;
import com.roczyno.userservice.repository.UserRepository;
import com.roczyno.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
        return UserDto.builder()
                .id(createdUser.getId().toString())
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
    public void verifyOTP(VerificationRequest request) {}
    public void resetPassword () {}


}
