package com.example.videogamestore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.videogamestore.Activity.DetailsActivity;
import com.example.videogamestore.Domain.Games;
import com.example.videogamestore.R;

import java.util.ArrayList;

public class PopularGamesAdapter extends RecyclerView.Adapter<PopularGamesAdapter.ViewHolder> {

    ArrayList<Games> items;
    Context context;

    public PopularGamesAdapter(ArrayList<Games> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PopularGamesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular_games, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularGamesAdapter.ViewHolder holder, int position) {

        holder.titleText.setText(items.get(position).getTitle());
        holder.priceText.setText("£" + items.get(position).getPrice());
        holder.starText.setText("" + items.get(position).getStar());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("object", items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText, priceText, starText;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.titleText);
            priceText = itemView.findViewById(R.id.totalFee);
            starText = itemView.findViewById(R.id.starText);
            image = itemView.findViewById(R.id.image);

        }
    }
}
