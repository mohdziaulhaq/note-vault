package com.notevault.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String email, String resetUrl) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reset Your Password - NoteVault");

        String emailBody = String.format(
                "Hi NoteVault user,\n\n" +
                        "We received a request to reset your password for your NoteVault account. " +
                        "If you made this request, please use the link below to reset your password:\n\n" +
                        "%s\n\n" +
                        "This link will expire in 15 minutes and can be used only once. " +
                        "For your security, if you did not request a password reset, please ignore this email or contact our support team.\n\n" +
                        "Thank you,\n" +
                        "The NoteVault Team",
                resetUrl
        );

        message.setText(emailBody);
        mailSender.send(message);
    }
}
