package com.example.myapplication1.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String formatCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMMM dd | hh:mm a", Locale.ENGLISH);
        return sdf.format(new Date());
    }

    public static String formatHour(String isoTime) {
        try {
            // ISO format: 2024-06-17T10:00
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("h a", Locale.ENGLISH);
            Date date = inputFormat.parse(isoTime);
            return outputFormat.format(date);
        } catch (Exception e) {
            return "N/A";
        }
    }

    public static String formatDay(String isoDate) {
        try {
            // ISO format: 2024-06-17
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);
            Date date = inputFormat.parse(isoDate);
            return outputFormat.format(date);
        } catch (Exception e) {
            return "N/A";
        }
    }
}
