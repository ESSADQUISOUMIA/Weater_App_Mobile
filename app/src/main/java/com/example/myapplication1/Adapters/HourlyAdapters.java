package com.example.myapplication1.Adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication1.R;

public class HourlyAdapters extends RecyclerView.Adapter<HourlyAdapters.viewHolder> {
    @NonNull
    @Override
    public HourlyAdapters.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate=layoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyAdapters.viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView hourTxt, tempTxt;
        ImageView pic;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            hourTxt = itemView.findViewById(R.id.);
        }
    }
}