package com.example.taskmanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagement.dto.WeatherSummaryDTO;
import com.example.taskmanagement.service.WeatherService;

@RestController
public class WeatherController {
    //Constructor Injection
    private final WeatherService weatherService;

public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
}

    // Endpoint to get weather summary for a city
    // Example: GET /weather?city=London
    @PostMapping("/weather")
    public String getWeather(@RequestBody Map<String, String> request, Model model) {
        String city = request.get("city");
        try {
            WeatherSummaryDTO weatherSummary = weatherService.getWeather(city);
            return String.format("Weather in %s: %.1f°C, %d%% humidity, %s",
                    weatherSummary.getCityName(),
                    weatherSummary.getTemperature(),
                    weatherSummary.getHumidity(),
                    weatherSummary.getDescription());
        } catch (Exception e) {
            return "Error fetching weather data: " + e.getMessage();
        }

    }
    
}
