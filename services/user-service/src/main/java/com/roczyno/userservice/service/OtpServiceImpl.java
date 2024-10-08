package com.roczyno.userservice.service;

import com.roczyno.userservice.entity.OTP;
import com.roczyno.userservice.entity.User;
import com.roczyno.userservice.enums.OtpType;
import com.roczyno.userservice.repository.OtpRepository;
import com.roczyno.userservice.repository.UserRepository;
import com.roczyno.userservice.service.OtpService.OtpService;
import com.roczyno.userservice.service.mailservice.MailService;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final OtpRepository otpRepository;
    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public OTP generateOTP(OtpType type, User user, boolean save) {
        String otpValue = generate();
        OTP otp = OTP.builder()
                .type(type)
                .value(otpValue)
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        if(save)otpRepository.save(otp);
        return otp;
    }

    @Override
    public User verifyOTP(String value, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        OTP otp = otpRepository.findByValueAndUser(value, user).orElseThrow();
        otpRepository.delete(otp);
        return user;
    }

    public User verifyOTP(String value, UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        OTP otp = otpRepository.findByValueAndUser(value, user).orElseThrow();
        otpRepository.delete(otp);
        return user;
    }

    @Override
    public OTP getOTP(UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        OTP otp = otpRepository.findByUserAndType(user, OtpType.VERIFY ).orElseThrow();
        mailService.sendVerificationMail(otp);
        return otp;
    }

    private String generate () {
        CharacterRule numeric = new CharacterRule(EnglishCharacterData.Digit);
        CharacterRule alphabetical = new CharacterRule(EnglishCharacterData.Alphabetical);
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        return passwordGenerator.generatePassword(6,numeric,alphabetical);
    }

    @Scheduled(fixedRate = 1000)
    public void removeExpiredOtp() {
        List<OTP> expiredOtps = otpRepository.findByExpiresAtBefore(LocalDateTime.now());
        otpRepository.deleteAll(expiredOtps);
    }
}
