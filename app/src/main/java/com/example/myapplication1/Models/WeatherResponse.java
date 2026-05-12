package com.example.myapplication1.Models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {
    @SerializedName("current") public CurrentData current;
    @SerializedName("hourly") public HourlyData hourly;
    @SerializedName("daily") public DailyData daily;

    public static class CurrentData {
        @SerializedName("temperature_2m") public double temperature;
        @SerializedName("weathercode") public int weatherCode;
        @SerializedName("windspeed_10m") public double windSpeed;
        @SerializedName("relativehumidity_2m") public int humidity;
        @SerializedName("precipitation_probability") public int precipitationProb;
    }

    public static class HourlyData {
        public List<String> time;
        @SerializedName("temperature_2m") public List<Double> temperature;
        @SerializedName("weathercode") public List<Integer> weatherCode;
    }

    public static class DailyData {
        public List<String> time;
        @SerializedName("weathercode") public List<Integer> weatherCode;
        @SerializedName("temperature_2m_max") public List<Double> tempMax;
        @SerializedName("temperature_2m_min") public List<Double> tempMin;
        @SerializedName("precipitation_probability_max") public List<Integer> precipitationProb;
    }
}
