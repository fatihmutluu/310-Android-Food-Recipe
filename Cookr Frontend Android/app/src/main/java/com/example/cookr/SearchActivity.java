package com.example.cookr;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SearchActivity extends AppCompatActivity {
    EditText foodNameEditText,dietEditText,cuisineEditText,tagsEditText;
    Button searchButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        foodNameEditText = findViewById(R.id.food_name);
        dietEditText = findViewById(R.id.food_diet);
        cuisineEditText = findViewById(R.id.food_cuisine);
        tagsEditText = findViewById(R.id.food_tag);

        searchButton = findViewById(R.id.search_ny_name);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = foodNameEditText.getText().toString();
                String diet = dietEditText.getText().toString();
                String cuisine = cuisineEditText.getText().toString();
                String tags = tagsEditText.getText().toString();

                Intent intent = new Intent(SearchActivity.this, ListActivity.class);
                if (!foodName.isEmpty()) {
                    intent.putExtra("searchType", "name");
                    intent.putExtra("searchValue", foodName);
                } else if (!diet.isEmpty()) {
                    intent.putExtra("searchType", "diet");
                    intent.putExtra("searchValue", diet);
                } else if (!cuisine.isEmpty()) {
                    intent.putExtra("searchType", "cuisine");
                    intent.putExtra("searchValue", cuisine);
                } else if (!tags.isEmpty()) {
                    intent.putExtra("searchType", "tags");
                    intent.putExtra("searchValue", tags);
                }
                startActivity(intent);
            }
        });
    }

}