package com.example.taskmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LLMService {

    private final RestTemplate restTemplate;

    @Value("${gemini.api.key}")
    private String apiKey;

    private final String model = "gemini-2.5-flash-lite"; // or gemini-pro

    public LLMService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, String> generateEmailContent(String prompt) {

        String url = "https://generativelanguage.googleapis.com/v1/models/"
                + model + ":generateContent?key=" + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Build request body
        Map<String, Object> textPart = Map.of("text",
                "Generate a nice email with a clear subject line on the first line, then the body:\n" + prompt);

        Map<String, Object> content = Map.of(
                "parts", List.of(textPart)
        );

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(content)
        );

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url, entity, Map.class);

        // Extract generated text
        List<Map<String, Object>> candidates =
                (List<Map<String, Object>>) response.getBody().get("candidates");

        Map<String, Object> firstCandidate =
                (Map<String, Object>) candidates.get(0);

        Map<String, Object> contentMap =
                (Map<String, Object>) firstCandidate.get("content");

        List<Map<String, String>> parts =
                (List<Map<String, String>>) contentMap.get("parts");

        String fullText = parts.get(0).get("text");

        // Split subject and body
        String subject;
        String body;

        if (fullText.contains("\n")) {
            int idx = fullText.indexOf("\n");
            subject = fullText.substring(0, idx).trim();
            body = fullText.substring(idx + 1).trim();
        } else {
            subject = "AI Generated Email";
            body = fullText;
        }

        Map<String, String> result = new HashMap<>();
        result.put("subject", subject);
        result.put("body", body);

        return result;
    }
}