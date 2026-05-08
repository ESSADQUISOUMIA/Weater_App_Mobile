package com.example.myapplication1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.Domains.FutureDomain;
import com.example.myapplication1.R;

import java.util.ArrayList;

public class FutureAdapter extends RecyclerView.Adapter<FutureAdapter.viewHolder> {
    private ArrayList<FutureDomain> items;
    private Context context;

    public FutureAdapter(ArrayList<FutureDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FutureAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_holder_future, parent, false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FutureAdapter.viewHolder holder, int position) {
        FutureDomain item = items.get(position);
        holder.dayTxt.setText(item.getDay());
        holder.statusTxt.setText(item.getStatus());
        holder.highTxt.setText(item.getHighTemp() + "°");
        holder.lowTxt.setText(item.getLowTemp() + "°");

        int drawableResourceId = context.getResources().getIdentifier(item.getPicPath(), "drawable", context.getPackageName());
        if (drawableResourceId != 0) {
            holder.pic.setImageResource(drawableResourceId);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView dayTxt, statusTxt, lowTxt, highTxt;
        ImageView pic;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            dayTxt = itemView.findViewById(R.id.dayTxt);
            statusTxt = itemView.findViewById(R.id.statusTxt);
            lowTxt = itemView.findViewById(R.id.lowTxt);
            highTxt = itemView.findViewById(R.id.highTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
