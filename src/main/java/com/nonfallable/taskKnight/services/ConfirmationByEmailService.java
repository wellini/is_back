package com.nonfallable.taskKnight.services;

import com.nonfallable.taskKnight.models.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationByEmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${docker.env.mail.enable}")
    private boolean isMailingEnable;

    public void sendConfirmationCode(ConfirmationToken token, String to) {
        if(!isMailingEnable) return;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        switch (token.getType()) {
            case REGISTRATION:
                message.setSubject("Confirm your registration – Task Knight");
            case CHANGE_PASSWORD:
                message.setSubject("Password change – Task Knight");
            default:
                message.setSubject("Your confirmation code – Task Knight");
        }
        message.setText("Hello! Your confirmation code is: " + token.getCode());
        emailSender.send(message);
    }
}
