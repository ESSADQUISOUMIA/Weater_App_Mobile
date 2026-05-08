package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.Adapters.FutureAdapter;
import com.example.myapplication1.Domains.FutureDomain;

import java.util.ArrayList;

public class FutureActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterTomorrow;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future);

        initRecyclerView();
        setVariable();
    }

    private void setVariable() {
        ImageView backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish());
    }

    private void initRecyclerView() {
        ArrayList<FutureDomain> items = new ArrayList<>();

        items.add(new FutureDomain("Sat", "storm", "Storm", 21, 7));
        items.add(new FutureDomain("Sun", "cloudy", "Cloudy", 23, 8));
        items.add(new FutureDomain("Mon", "windy", "Windy", 24, 9));
        items.add(new FutureDomain("Tue", "cloudy_sunny", "Cloudy Sunny", 22, 7));
        items.add(new FutureDomain("Wen", "sunny", "Sunny", 27, 6));
        items.add(new FutureDomain("Thu", "rainy", "Rainy", 20, 9));

        recyclerView = findViewById(R.id.futureView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterTomorrow = new FutureAdapter(items);
        recyclerView.setAdapter(adapterTomorrow);
    }
}
