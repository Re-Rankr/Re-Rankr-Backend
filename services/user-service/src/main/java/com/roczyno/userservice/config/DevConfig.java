package com.roczyno.userservice.config;

import com.roczyno.userservice.entity.User;
import com.roczyno.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DevConfig {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            userRepository.save(
              User.builder().email("admin@rankr.com").username("admin").password(encoder.encode("admin")).build()
            );
        };
    }
}
