package com.example.taskmanagement.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SendGridService {
    // constructor dependnecy injection. usine the object of SendGridMailSender created by spring framework
    private final SendGridMailSender mailSender;
    public SendGridService(SendGridMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void SendGridMailSender(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        // mannual object creation
       //SendGridMailSender mailSender = new SendGridMailSender();
       mailSender.sendEmail(from, to, subject, body);
    }
    
}
