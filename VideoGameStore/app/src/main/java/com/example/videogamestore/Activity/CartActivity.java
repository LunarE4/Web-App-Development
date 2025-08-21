package com.example.videogamestore.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.videogamestore.Adapter.CartAdapter;
import com.example.videogamestore.Adapter.CategoryAdapter;
import com.example.videogamestore.Domain.Games;
import com.example.videogamestore.Helper.ChangeNumberItemsListener;
import com.example.videogamestore.Helper.ManagmentCart;
import com.example.videogamestore.R;
import com.example.videogamestore.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {

    ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);

        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if (managmentCart.getListCart().isEmpty()) {
            binding.emptyCart.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        } else {
            binding.emptyCart.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.cartView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });
        binding.cartView.setAdapter(adapter);
    }

    private void calculateCart() {
        double percentTax = 0.02; // 2% Tax
        double delivery = 2.5; // Delivery Cost £2.50

        tax = Math.round(managmentCart.getTotalFee() * percentTax * 100.0) / 100;

        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100;

        binding.totalFee.setText("£" + itemTotal);
        binding.totalTax.setText("£" + tax);
        binding.deliveryCost.setText("£" + delivery);
        binding.fullTotal.setText("£" + total);
    }

    private void setVariable() {
        binding.returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

}