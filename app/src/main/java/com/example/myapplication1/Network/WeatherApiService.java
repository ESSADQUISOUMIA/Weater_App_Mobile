package com.example.myapplication1.Network;

import com.example.myapplication1.Models.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("v1/forecast")
    Call<WeatherResponse> getForecast(
        @Query("latitude") double lat,
        @Query("longitude") double lon,
        @Query("current") String current,
        @Query("hourly") String hourly,
        @Query("daily") String daily,
        @Query("timezone") String timezone,
        @Query("forecast_days") int days
    );
}
