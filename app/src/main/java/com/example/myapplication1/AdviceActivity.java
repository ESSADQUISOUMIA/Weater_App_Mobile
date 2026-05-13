package com.example.myapplication1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.Models.WeatherCurrent;
import com.example.myapplication1.Utils.WeatherUtils;

public class AdviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        WeatherCurrent current = (WeatherCurrent) getIntent().getSerializableExtra("weather_data");

        initView(current);
    }

    private void initView(WeatherCurrent current) {
        ImageView backBtn = findViewById(R.id.backBtn);
        ImageView adviceIcon = findViewById(R.id.adviceIcon);
        TextView adviceContent = findViewById(R.id.adviceContent);
        TextView activityContent = findViewById(R.id.activityContent);
        
        // Chat elements
        TextView chatResponseTxt = findViewById(R.id.chatResponseTxt);
        TextView btnSport = findViewById(R.id.btnSport);
        TextView btnCar = findViewById(R.id.btnCar);
        TextView btnHair = findViewById(R.id.btnHair);

        backBtn.setOnClickListener(v -> finish());

        if (current != null) {
            // Mise à jour du conseil vestimentaire
            adviceContent.setText(getString(WeatherUtils.getClothingAdviceResId(current)));
            
            // Mise à jour de la suggestion d'activité
            activityContent.setText(getString(WeatherUtils.getActivitySuggestionResId(current)));
            
            // Mise à jour de l'icône
            adviceIcon.setImageResource(WeatherUtils.getIconRes(current.weatherCode));

            // --- Logique du Chat Interactif ---
            
            btnSport.setOnClickListener(v -> {
                chatResponseTxt.setText(getString(WeatherUtils.getSportAnswer(current)));
            });

            btnCar.setOnClickListener(v -> {
                chatResponseTxt.setText(getString(WeatherUtils.getCarWashAnswer(current)));
            });

            btnHair.setOnClickListener(v -> {
                chatResponseTxt.setText(getString(WeatherUtils.getHairAnswer(current)));
            });
        }
    }
}
