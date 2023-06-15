package com.example.cookr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class AllActivity extends AppCompatActivity {

    RecyclerView recView;
    ProgressBar prg;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Recipe> data = (List<Recipe>) msg.obj;
            RecipeAdapter adp = new RecipeAdapter(AllActivity.this, data);
            recView.setAdapter(adp);
            prg.setVisibility(View.INVISIBLE);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random);

        prg = findViewById(R.id.progressBarList);
        recView = findViewById(R.id.recyclerViewList);
        recView.setLayoutManager(new LinearLayoutManager(this));
        prg.setVisibility(View.VISIBLE);

        RecipeRepo repo = new RecipeRepo();
        repo.getAllRecipes(((CookrApp)getApplication()).srv,handler);
    }
}