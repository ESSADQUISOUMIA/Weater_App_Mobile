package com.example.myapplication1.Utils;

import com.example.myapplication1.R;

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
        // Mapping WMO codes to existing or placeholder drawables
        if (code == 0)             return R.drawable.sunny;
        if (code <= 2)             return R.drawable.cloudy_sunny;
        if (code == 3)             return R.drawable.cloudy;
        if (code <= 49)            return R.drawable.cloudy; // Placeholder for fog
        if (code <= 57)            return R.drawable.rainy; // Placeholder for drizzle
        if (code <= 67)            return R.drawable.rainy;
        if (code <= 77)            return R.drawable.windy; // Placeholder for snow
        if (code <= 82)            return R.drawable.rainy;
        if (code <= 86)            return R.drawable.windy; // Placeholder for snow showers
        if (code <= 99)            return R.drawable.storm;
        return R.drawable.cloudy_sunny;
    }
}
