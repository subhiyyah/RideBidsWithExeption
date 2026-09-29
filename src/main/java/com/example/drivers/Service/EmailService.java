package com.example.drivers.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    // جلب بريد المرسل تلقائياً من application.properties
    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            System.out.println("تم إرسال الإيميل بنجاح إلى: " + to);
        } catch (Exception e) {
            System.err.println("فشل إرسال الإيميل: " + e.getMessage());
            e.printStackTrace();
        }
    }
}