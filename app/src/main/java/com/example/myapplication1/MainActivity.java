package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.Adapters.HourlyAdapters;
import com.example.myapplication1.Domains.FutureDomain;
import com.example.myapplication1.Domains.Hourly;
import com.example.myapplication1.Models.WeatherCurrent;
import com.example.myapplication1.Repository.WeatherRepository;
import com.example.myapplication1.Utils.WeatherUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private HourlyAdapters adapterHourly;
    private RecyclerView recyclerViewHourly;
    private WeatherRepository weatherRepository;

    private TextView statusTxt, tempTxt, dateTxt, highLowTxt, rainTxt, windTxt, humidityTxt;
    private ImageView mainIcon, searchBtn;
    private EditText citySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        weatherRepository = new WeatherRepository();

        // Charger une ville par défaut
        loadWeatherData("Paris");

        setVariable();
    }

    private void initView() {
        statusTxt = findViewById(R.id.statusTxt);
        tempTxt = findViewById(R.id.tempTxt);
        dateTxt = findViewById(R.id.dateTxt);
        highLowTxt = findViewById(R.id.highLowTxt);
        rainTxt = findViewById(R.id.rainTxt);
        windTxt = findViewById(R.id.windTxt);
        humidityTxt = findViewById(R.id.humidityTxt);
        mainIcon = findViewById(R.id.mainIcon);
        recyclerViewHourly = findViewById(R.id.view1);

        citySearch = findViewById(R.id.citySearch);
        searchBtn = findViewById(R.id.searchBtn);
    }

    private void loadWeatherData(String cityName) {
        weatherRepository.fetchWeatherForCity(cityName, new WeatherRepository.WeatherCallback() {
            @Override
            public void onSuccess(WeatherCurrent current, List<Hourly> hourly, List<FutureDomain> daily) {
                runOnUiThread(() -> {
                    // Update Main UI using strings.xml to avoid warnings
                    statusTxt.setText(WeatherUtils.getDescription(current.weatherCode));
                    tempTxt.setText(getString(R.string.temp_format, (int) Math.round(current.temperature)));
                    dateTxt.setText(getString(R.string.date_city_format, current.cityName, current.dateTime));
                    highLowTxt.setText(getString(R.string.high_low_format, (int) Math.round(current.tempMax), (int) Math.round(current.tempMin)));
                    rainTxt.setText(getString(R.string.percentage_format, current.precipitationProb));
                    windTxt.setText(getString(R.string.wind_format, (int) Math.round(current.windSpeed)));
                    humidityTxt.setText(getString(R.string.percentage_format, current.humidity));
                    mainIcon.setImageResource(WeatherUtils.getIconRes(current.weatherCode));

                    // Update Hourly RecyclerView
                    recyclerViewHourly.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    adapterHourly = new HourlyAdapters(new ArrayList<>(hourly));
                    recyclerViewHourly.setAdapter(adapterHourly);
                });
            }

            @Override
            public void onError(String message) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void setVariable() {
        // Bouton vers la page suivante
        findViewById(R.id.nextDaysTxt).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FutureActivity.class)));

        // Recherche au clic sur l'icône
        searchBtn.setOnClickListener(v -> {
            String city = citySearch.getText().toString();
            if (!city.isEmpty()) loadWeatherData(city);
        });

        // Recherche avec la touche "Entrée" du clavier
        citySearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                String city = citySearch.getText().toString();
                if (!city.isEmpty()) loadWeatherData(city);
                return true;
            }
            return false;
        });
    }
}
