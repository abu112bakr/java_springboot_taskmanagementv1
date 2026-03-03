package com.example.taskmanagement.dto;

import java.time.LocalDate;

public class EmailFilterRequest {
    ////String form, String to, String subject, String body
    private String form;
    private String to;
    private String subject;
    private String body;

    public String getForm() {
        return form;
    }
    public void setForm(String form) {
        this.form = form;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

      
    
}
