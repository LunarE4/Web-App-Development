package com.example.videogamestore.Activity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.videogamestore.Domain.Games;
import com.example.videogamestore.Helper.ManagmentCart;
import com.example.videogamestore.databinding.ActivityDetailsBinding;

public class DetailsActivity extends BaseActivity {

    ActivityDetailsBinding binding;
    private Games object;
    private int num = 1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        managmentCart = new ManagmentCart(this);

        binding.backButton.setOnClickListener(v -> finish());

        Glide.with(DetailsActivity.this)
                .load(object.getImagePath())
                .into(binding.pfpImage);

        binding.titleText.setText(object.getTitle());
        binding.priceText.setText("£" + object.getPrice());
        binding.descriptionText.setText(object.getDescription());
        binding.textRating.setText("" + object.getStar());
        binding.ratingBar.setRating((float) object.getStar());
        binding.totalText.setText(("£" + num * object.getPrice()));

        binding.plusButton.setOnClickListener(v -> {
            num = num + 1;
            binding.numberText.setText(num + " ");
            binding.totalText.setText("£" + (num * object.getPrice()));
        });

        binding.minusButton.setOnClickListener(v -> {
            if (num > 1) {
                num = num - 1;
                binding.numberText.setText(num + " ");
                binding.totalText.setText("£" + (num * object.getPrice()));
            }
        });

        binding.addButton.setOnClickListener(v -> {
            object.setNumberInCart(num);
            managmentCart.insertGame(object);
        });
    }

    private void getIntentExtra() {
        object = getIntent().getSerializableExtra("object", Games.class);
    }

}