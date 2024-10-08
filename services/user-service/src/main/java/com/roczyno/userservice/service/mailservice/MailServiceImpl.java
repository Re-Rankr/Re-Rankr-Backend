package com.roczyno.userservice.service.mailservice;

import com.roczyno.userservice.entity.OTP;
import com.roczyno.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
    private final MailSender mailSender;
    private final UserRepository userRepository;

    @Override
    @Async
    public void sendVerificationMail(OTP otp) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("System");
        mail.setTo(otp.getUser().getEmail());
        mail.setSubject("Verification OTP");
        mail.setText(otp.getValue());
        mailSender.send(mail);
    }

    @Override
    @Async
    public void sendResetMail(OTP otp) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("System");
        mail.setTo(otp.getUser().getEmail());
        mail.setSubject("Reset Account");
        mail.setText(otp.getValue());
        mailSender.send(mail);
    }

}
