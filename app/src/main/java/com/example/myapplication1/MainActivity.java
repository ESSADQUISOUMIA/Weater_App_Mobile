package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.Adapters.HourlyAdapters;
import com.example.myapplication1.Domains.Hourly;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerViewHourly;

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

        initRecyclerView();
        setVariable();
    }

    private void setVariable() {
        TextView next7daysBtn = findViewById(R.id.textView7);
        next7daysBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FutureActivity.class));
        });
    }

    private void initRecyclerView() {
        ArrayList<Hourly> items = new ArrayList<>();

        items.add(new Hourly("9 PM", 28, "cloudy"));
        items.add(new Hourly("10 PM", 29, "sunny"));
        items.add(new Hourly("11 PM", 30, "windy"));
        items.add(new Hourly("12 AM", 31, "cloudy_sunny"));
        items.add(new Hourly("1 AM", 32, "sunny"));

        recyclerViewHourly = findViewById(R.id.view1);
        recyclerViewHourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterHourly = new HourlyAdapters(items);
        recyclerViewHourly.setAdapter(adapterHourly);
    }
}