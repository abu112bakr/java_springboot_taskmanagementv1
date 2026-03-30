package com.example.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagement.service.EmailService;
import com.example.taskmanagement.service.LLMService;
import com.example.taskmanagement.service.SendGridService;
import com.example.taskmanagement.service.TaskService;
import com.example.taskmanagement.dto.EmailFilterRequest;
import com.example.taskmanagement.dto.TaskFilterRequest;
import com.example.taskmanagement.model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController // Indicate that this is a Spring-managed REST controller
public class TaskController {
//     private final TaskService taskService;
//     private final LLMService llmService;
//     private final EmailService emailService;
//     private final SendGridService sendGridService;

//     public TaskController(
//             TaskService taskService,
//             LLMService llmService,
//             EmailService emailService,
//             SendGridService sendGridService) {

//         this.taskService = taskService;
//         this.llmService = llmService;
//         this.emailService = emailService;
//         this.sendGridService = sendGridService;
//     }
// }    
    private final Task task;
    @Autowired // dependency injection of TaskService(service)
    TaskService taskService;

    @Autowired
    @Lazy
    LLMService LLMService;
    @Autowired
    EmailService emailService;
    @Autowired
    SendGridService sendGridService;

    TaskController(Task task) {
        this.task = task;
    }
    //TaskService.java have the following  methods
    //getTask(),getTaskById(),addTask(),updateTask,deleteTask
    @GetMapping("/task")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }
    @GetMapping("/task/{taskId}")
    public Task getTaskById(@PathVariable int taskId){
        return taskService.getTaskById(taskId);
    }
    @PostMapping("/task")
    public void addTask(@RequestBody Task task){
        taskService.addTask(task);
        System.out.println(task + " added successfully");
    }
    @PutMapping("/task")
    public void updateTask(@RequestBody Task task){
        taskService.updateTask(task);
        System.out.println(task + " updated successfully");
    }
    @DeleteMapping("/task/{taskId}")
    public void deleteTask(@PathVariable int taskId){
        taskService.deleteTask(taskId);
        System.out.println("Task with id " + taskId + " deleted successfully");
    }
    @PostMapping("/task/sql")
    public List<Task> getTasksByCreatedByAndDate(@RequestBody TaskFilterRequest request) {
        String createdBy = request.getCreatedBy();
        LocalDate date = request.getDate();
        return taskService.findByCreatedByAndDate(createdBy, date);
    }
    // {
    //     "createdBy": "cod",
    //     "date": "2026-02-25"
    // }
    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailFilterRequest emailRequest) {
        //String form, String to, String subject, String body
        String from = emailRequest.getForm();
        String to = emailRequest.getTo();
        String subject = emailRequest.getSubject();
        String body = emailRequest.getBody();
        //send email by javaMailSender
        emailService.sendSimpleEmail(from, to, subject, body);
        //send email by SendGrid
        //sendGridService.SendGridMailSender(from, to, subject, body);
        return "Email Send Successfully";
    }    
    // {
    //     "form": "abu.hasnath@gigatechltd.com",
    //     "to": "abu.hasnath@gigatechltd.com",
    //     "subject": "Test Email",
    //     "body": "Hello, this is a test email from Spring Boot."
    //   }
    @PostMapping("/ai-email")
    public String sendAiEmail(@RequestBody EmailFilterRequest emailRequest) {
        String from = emailRequest.getForm();
        String to = emailRequest.getTo();
    
        // Create prompt for LLM
        String prompt = "Write a inspiring good morning email, with subject and body. End with Warmly, Hasnath" 
                        + emailRequest.getBody();

        Map<String, String> emailContent;
        try {
            emailContent = LLMService.generateEmailContent(prompt);
        } catch (Exception e) {
            return "Failed to generate email content: " + e.getMessage();
        }
        String subject = emailContent.get("subject");
        String body = emailContent.get("body");
        
        //send email by javaMailSender
        emailService.sendSimpleEmail(from, to, subject, body);
        //send email by SendGrid
        sendGridService.SendGridMailSender(from, to, subject, body);


    
        return "Email Sent Successfully";
        // String generatedContent = LLMService.generateEmailContent(prompt);
    
        // // You can split subject/body if API returns structured response
        // String subject = "Generated by AI";
        // String body = generatedContent;
    
        // emailService.sendSimpleEmail(from, to, subject, body);
    
        // return "Email Sent Successfully";
    }    
}
