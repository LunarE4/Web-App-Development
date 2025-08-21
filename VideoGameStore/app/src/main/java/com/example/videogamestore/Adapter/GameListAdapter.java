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

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    ArrayList<Games> items;
    Context context;

    public GameListAdapter(ArrayList<Games> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public GameListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_game_list, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GameListAdapter.ViewHolder holder, int position) {

        holder.titleText.setText(items.get(position).getTitle());
        holder.priceText.setText("£" + items.get(position).getPrice());
        holder.textRating.setText("" + items.get(position).getStar());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText, priceText, textRating;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.titleText);
            priceText = itemView.findViewById(R.id.totalFee);
            textRating = itemView.findViewById(R.id.textRating);
            image = itemView.findViewById(R.id.image);

        }
    }
}
