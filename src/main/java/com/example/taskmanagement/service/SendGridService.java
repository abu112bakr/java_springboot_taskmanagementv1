package com.example.taskmanagement.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SendGridService {

    public void SendGridMailSender(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

  
       SendGridMailSender mailSender = new SendGridMailSender();
       mailSender.sendEmail(from, to, subject, body);
    }
    
}
