package com.roczyno.userservice.config;

import com.roczyno.userservice.dto.AuthRequest;
import com.roczyno.userservice.entity.User;
import com.roczyno.userservice.repository.UserRepository;
import com.roczyno.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DevConfig {
    private final AuthService authService;
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            authService.signUp(
                    AuthRequest.builder().email("admin@rankr.com").username("admin").password("admin")
                    .build());
        };
    }
}
