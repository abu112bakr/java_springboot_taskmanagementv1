package com.example.taskmanagement.dto;

public class WeatherSummaryDTO {
    private final String cityName;
    private final double temperature;
    private final double feels_like;
    private final int humidity;
    private final String description;
    
    

    public WeatherSummaryDTO(String cityName, double temperature, double feels_like, int humidity, String description) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.feels_like = feels_like;
        this.humidity = humidity;
        this.description = description;
    }

    public String getCityName() { return cityName; }
    public double getTemperature() { return temperature; }
    public double getFeels_like() {return feels_like; }
    public int getHumidity() { return humidity; }
    public String getDescription() { return description; }

}
