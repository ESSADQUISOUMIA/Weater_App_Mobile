package com.example.myapplication1.Models;

import java.io.Serializable;

public class WeatherCurrent implements Serializable {
    public double temperature;
    public double tempMax;
    public double tempMin;
    public int weatherCode;
    public double windSpeed;
    public int humidity;
    public int precipitationProb;
    public String cityName;
    public String dateTime;
}
