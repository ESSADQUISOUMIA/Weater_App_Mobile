package com.example.myapplication1.Repository;

import com.example.myapplication1.Domains.FutureDomain;
import com.example.myapplication1.Domains.Hourly;
import com.example.myapplication1.Models.GeocodingResponse;
import com.example.myapplication1.Models.WeatherCurrent;
import com.example.myapplication1.Models.WeatherResponse;
import com.example.myapplication1.Network.GeocodingApiService;
import com.example.myapplication1.Network.GeocodingClient;
import com.example.myapplication1.Network.RetrofitClient;
import com.example.myapplication1.Network.WeatherApiService;
import com.example.myapplication1.Utils.DateUtils;
import com.example.myapplication1.Utils.WeatherUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private WeatherApiService weatherService;
    private GeocodingApiService geoService;

    public WeatherRepository() {
        weatherService = RetrofitClient.getClient().create(WeatherApiService.class);
        geoService = GeocodingClient.getClient().create(GeocodingApiService.class);
    }

    public interface WeatherCallback {
        void onSuccess(WeatherCurrent current, List<Hourly> hourly, List<FutureDomain> daily);
        void onError(String message);
    }

    public void fetchWeatherForCity(String cityName, WeatherCallback callback) {
        geoService.searchCity(cityName, 1, "fr").enqueue(new Callback<GeocodingResponse>() {
            @Override
            public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().results != null && !response.body().results.isEmpty()) {
                    GeocodingResponse.GeoResult result = response.body().results.get(0);
                    fetchWeatherForCoords(result.latitude, result.longitude, result.name, callback);
                } else {
                    callback.onError("Ville introuvable");
                }
            }

            @Override
            public void onFailure(Call<GeocodingResponse> call, Throwable t) {
                callback.onError("Erreur lors de la recherche de la ville");
            }
        });
    }

    private void fetchWeatherForCoords(double lat, double lon, String cityName, WeatherCallback callback) {
        String currentParams = "temperature_2m,weathercode,windspeed_10m,relativehumidity_2m,precipitation_probability";
        String hourlyParams = "temperature_2m,weathercode";
        String dailyParams = "weathercode,temperature_2m_max,temperature_2m_min,precipitation_probability_max";

        weatherService.getForecast(lat, lon, currentParams, hourlyParams, dailyParams, "auto", 7)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            WeatherResponse weatherData = response.body();
                            
                            // Map Current
                            WeatherCurrent current = new WeatherCurrent();
                            current.temperature = weatherData.current.temperature;
                            current.weatherCode = weatherData.current.weatherCode;
                            current.windSpeed = weatherData.current.windSpeed;
                            current.humidity = weatherData.current.humidity;
                            current.precipitationProb = weatherData.current.precipitationProb;
                            current.tempMax = weatherData.daily.tempMax.get(0);
                            current.tempMin = weatherData.daily.tempMin.get(0);
                            current.cityName = cityName;
                            current.dateTime = DateUtils.formatCurrentDate();

                            // Map Hourly (next 24 hours)
                            List<Hourly> hourlyItems = new ArrayList<>();
                            for (int i = 0; i < 24; i++) {
                                String time = DateUtils.formatHour(weatherData.hourly.time.get(i));
                                int temp = (int) Math.round(weatherData.hourly.temperature.get(i));
                                String icon = "cloudy_sunny"; // This logic can be refined
                                hourlyItems.add(new Hourly(time, temp, "cloudy_sunny"));
                            }

                            // Map Daily
                            List<FutureDomain> dailyItems = new ArrayList<>();
                            for (int i = 1; i < weatherData.daily.time.size(); i++) {
                                String day = DateUtils.formatDay(weatherData.daily.time.get(i));
                                String status = WeatherUtils.getDescription(weatherData.daily.weatherCode.get(i));
                                int high = (int) Math.round(weatherData.daily.tempMax.get(i));
                                int low = (int) Math.round(weatherData.daily.tempMin.get(i));
                                dailyItems.add(new FutureDomain(day, "cloudy", status, high, low));
                            }

                            callback.onSuccess(current, hourlyItems, dailyItems);
                        } else {
                            callback.onError("Erreur lors de la récupération de la météo");
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        callback.onError("Erreur réseau");
                    }
                });
    }
}
