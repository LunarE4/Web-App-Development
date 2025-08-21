package com.example.videogamestore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.videogamestore.Activity.GameListActivity;
import com.example.videogamestore.Domain.Category;
import com.example.videogamestore.Domain.Games;
import com.example.videogamestore.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        holder.titleText.setText(items.get(position).getName());

        if (position == 0) {
            holder.image.setBackgroundResource(R.drawable.cat_1_bg);
        } else if (position == 1) {
            holder.image.setBackgroundResource(R.drawable.cat_2_bg);
        } else if (position == 2) {
            holder.image.setBackgroundResource(R.drawable.cat_3_bg);
        } else if (position == 3) {
            holder.image.setBackgroundResource(R.drawable.cat_4_bg);
        } else if (position == 4) {
            holder.image.setBackgroundResource(R.drawable.cat_5_bg);
        } else if (position == 5) {
            holder.image.setBackgroundResource(R.drawable.cat_6_bg);
        } else if (position == 6) {
            holder.image.setBackgroundResource(R.drawable.cat_7_bg);
        } else if (position == 7) {
            holder.image.setBackgroundResource(R.drawable.cat_8_bg);
        }

        int drawableResourceID = context.getResources().getIdentifier(items.get(position).getImagePath()
                , "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResourceID)
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GameListActivity.class);
            intent.putExtra("CategoryId", items.get(position).getId());
            intent.putExtra("CategoryName", items.get(position).getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.catName);
            image = itemView.findViewById(R.id.imgCat);

        }
    }
}