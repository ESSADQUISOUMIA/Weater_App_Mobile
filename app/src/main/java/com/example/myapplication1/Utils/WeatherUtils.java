package com.example.myapplication1.Utils;

import com.example.myapplication1.R;
import com.example.myapplication1.Models.WeatherCurrent;

public class WeatherUtils {
    public static String getDescription(int code) {
        if (code == 0)             return "Sunny";
        if (code <= 2)             return "Mostly Cloudy";
        if (code == 3)             return "Overcast";
        if (code <= 49)            return "Foggy";
        if (code <= 57)            return "Drizzle";
        if (code <= 67)            return "Rainy";
        if (code <= 77)            return "Snowy";
        if (code <= 82)            return "Rainy";
        if (code <= 86)            return "Snow Showers";
        if (code <= 99)            return "Storm";
        return "Unknown";
    }

    public static int getIconRes(int code) {
        if (code == 0)             return R.drawable.sunny;
        if (code <= 2)             return R.drawable.cloudy_sunny;
        if (code == 3)             return R.drawable.cloudy;
        if (code <= 49)            return R.drawable.cloudy;
        if (code <= 57)            return R.drawable.rainy;
        if (code <= 67)            return R.drawable.rainy;
        if (code <= 77)            return R.drawable.windy;
        if (code <= 82)            return R.drawable.rainy;
        if (code <= 86)            return R.drawable.windy;
        if (code <= 99)            return R.drawable.storm;
        return R.drawable.cloudy_sunny;
    }

    public static int getClothingAdviceResId(WeatherCurrent current) {
        if ((current.weatherCode >= 71 && current.weatherCode <= 77) || current.temperature <= -5) {
            return R.string.advice_snowy;
        }
        if (current.weatherCode >= 51 && current.weatherCode <= 67 || current.weatherCode >= 80 && current.weatherCode <= 82) {
            return R.string.advice_rainy;
        }
        if (current.temperature >= 30) {
            return R.string.advice_hot;
        }
        if (current.windSpeed > 20 && current.temperature < 15) {
            return R.string.advice_windy;
        }
        return R.string.advice_default;
    }

    public static int getActivitySuggestionResId(WeatherCurrent current) {
        if (current.weatherCode >= 95) {
            return R.string.activity_storm;
        }
        if (current.weatherCode >= 51 && current.weatherCode <= 82) {
            return R.string.activity_rainy;
        }
        if (current.temperature < 5) {
            return R.string.activity_cold;
        }
        if (current.weatherCode <= 2 && current.temperature >= 15) {
            return R.string.activity_sunny;
        }
        return R.string.activity_default;
    }

    // --- Interactive Logic (Vivant/Complice) ---

    public static int getLookAnswer(WeatherCurrent current) {
        if (current.temperature >= 25) return R.string.ans_look_hot;
        if (current.precipitationProb > 40) return R.string.ans_look_rain;
        return R.string.ans_look_default;
    }

    public static int getBikeAnswer(WeatherCurrent current) {
        if (current.precipitationProb < 20 && current.windSpeed < 25) {
            return R.string.ans_bike_ok;
        }
        return R.string.ans_bike_no;
    }

    public static int getLaundryAnswer(WeatherCurrent current) {
        if (current.weatherCode <= 2 && current.humidity < 60 && current.precipitationProb < 10) {
            return R.string.ans_laundry_ok;
        }
        return R.string.ans_laundry_no;
    }
    public static int getSportAnswer(WeatherCurrent current) {
        if (current.temperature >= 25 && current.weatherCode <= 2) {
            return R.string.sport_sunny;
        }
        if (current.weatherCode >= 51 && current.weatherCode <= 82) {
            return R.string.sport_rainy;
        }
        if (current.temperature < 5) {
            return R.string.sport_cold;
        }
        return R.string.sport_default;
    }

    public static int getCarWashAnswer(WeatherCurrent current) {
        if (current.precipitationProb > 30) {
            return R.string.carwash_rainy;
        }
        if (current.temperature < 0) {
            return R.string.carwash_cold;
        }
        return R.string.carwash_default;
    }

    public static int getHairAnswer(WeatherCurrent current) {
        if (current.humidity > 70 || (current.weatherCode >= 51 && current.weatherCode <= 82)) {
            return R.string.hair_humid;
        }
        if (current.temperature > 30) {
            return R.string.hair_hot;
        }
        return R.string.hair_default;
    }
}
