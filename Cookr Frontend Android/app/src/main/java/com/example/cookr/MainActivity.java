package com.example.cookr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this,SearchActivity.class);
            startActivity(i);

        });

        ImageButton btnFilter = findViewById(R.id.btn_filter);
        btnFilter.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this,SendActivity.class);
            startActivity(i);

        });

        ImageButton btnRandom = findViewById(R.id.btn_random);
        btnRandom.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this,RandomActivity.class);
            startActivity(i);

        });

        ImageButton btnAll = findViewById(R.id.btn_all);
        btnAll.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this,AllActivity.class);
            startActivity(i);

        });

    }
}