package com.example.taskmanagement.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender mailSender;
    // 👇 Manually written constructor
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }    

    public void sendSimpleEmail(String form, String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        //message.setFrom("your_email@gmail.com");
        message.setFrom(form);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);    
    }
}
