package com.example.videogamestore.Adapter;

import android.content.Context;
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
import com.example.videogamestore.Domain.Games;
import com.example.videogamestore.Helper.ChangeNumberItemsListener;
import com.example.videogamestore.Helper.ManagmentCart;
import com.example.videogamestore.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<Games> list;
    private ManagmentCart managmentCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<Games> list, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        managmentCart = new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.totalItem.setText("£" + list.get(position).getPrice());
        holder.totalFee.setText((list.get(position).getNumberInCart() + " " + "x" + " " + "£" + (list.get(position).getNumberInCart()) * list.get(position).getPrice()));
        holder.num.setText(String.valueOf(list.get(position).getNumberInCart()));

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.image);

        holder.plusItem.setOnClickListener(v -> managmentCart.plusNumberItem(list, position, new ChangeNumberItemsListener() {
            public void change() {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            }
        }));

        holder.minusItem.setOnClickListener(v -> managmentCart.minusNumberItem(list, position, new ChangeNumberItemsListener() {
            public void change() {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, totalItem, plusItem, minusItem;
        ImageView image;
        TextView totalFee, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleText);
            totalItem = itemView.findViewById(R.id.totalItem);
            image = itemView.findViewById(R.id.image);
            totalFee = itemView.findViewById(R.id.totalFee);

            plusItem = itemView.findViewById(R.id.plusCartButton);
            minusItem = itemView.findViewById(R.id.minusCartButton);
            num = itemView.findViewById(R.id.amountText);
        }
    }
}
