package com.example.taskmanagement.service;
// Check the weather to see if you will able to comple the task on time or not,
// if not then you can reschedule the task to another day when the weather is good.

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.example.taskmanagement.dto.WeatherSummaryDTO;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    @Value("${openweather.api.key}")
    private String apiKey;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherSummaryDTO getWeather(String city) throws Exception {

        try {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        String jsonResponse = restTemplate.getForObject(url, String.class);
        
        // USE JACKSON. Mapping JSON directly to a map
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);

        String cityName = rootNode.path("name").asText();
        double temp = rootNode.path("main").path("temp").asDouble();
        double feels_like = rootNode.path("main").path("feels_like").asDouble();
        int humidity = rootNode.path("main").path("humidity").asInt();
        String description = rootNode.path("weather").get(0).path("description").asText();

        return new WeatherSummaryDTO(cityName, temp, feels_like ,humidity, description);
    
        } catch (Exception e) {
            System.out.println("Error fetching weather data: " + e.getMessage());
            throw new Exception("Failed to fetch weather data: " + e.getMessage());
        }
    }

    
}
