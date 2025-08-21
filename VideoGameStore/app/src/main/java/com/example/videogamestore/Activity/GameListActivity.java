package com.example.videogamestore.Activity;

import com.example.videogamestore.Adapter.GameListAdapter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.videogamestore.Domain.Games;
import com.example.videogamestore.R;
import com.example.videogamestore.databinding.ActivityGameListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GameListActivity extends BaseActivity {

    ActivityGameListBinding binding;
    private RecyclerView.Adapter adapterListGames;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;

    private boolean viewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        initList();
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Games");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Games> list = new ArrayList<>();

        Query query;
        if (isSearch) {
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText + "\uf8ff");
        } else {
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue: snapshot.getChildren()) {
                        list.add(issue.getValue(Games.class));
                    }
                    if (list.size() > 0) {
                        binding.gameListView.setLayoutManager(new GridLayoutManager(GameListActivity.this, 2));
                        adapterListGames = new GameListAdapter(list);
                        binding.gameListView.setAdapter(adapterListGames);
                    }
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getIntentExtra() {

        categoryId = getIntent().getIntExtra("CategoryId", 0);
        categoryName = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);
        viewAll = getIntent().getBooleanExtra("viewAll", false);

        binding.titleText.setText(categoryName);
        binding.backButton.setOnClickListener(v -> finish());
    }
}