package com.example.taskmanagement.controller;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RestController;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import com.example.taskmanagement.model.UserPrincipal;

@RestController
public class info {
    private final Counter somethingCounter;
    private final Counter infoCounter;

    // MeterRegistry is auto-injected by Spring Boot
    public info(MeterRegistry registry) {

        this.somethingCounter = Counter.builder("api.something.requests")
            .description("Number of times /something endpoint was hit")
            .register(registry);
    
        this.infoCounter = Counter.builder("api.info.requests")
            .description("Number of times /info endpoint was hit")
            .register(registry);
        }

    @GetMapping("/info")
    public String getInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        
        System.out.println("Username: " + userPrincipal.getUsername());
        System.out.println("User ID: " + userPrincipal.getId());
        return "Username: " + userPrincipal.getUsername() +
               ", User ID: " + userPrincipal.getId();
    }

    @GetMapping("/something")
    public String getSomething() {
        somethingCounter.increment();

        return ResponseEntity.ok().body("All Okay").toString();
    }
//http://localhost:8080/actuator/prometheus            
        
}
