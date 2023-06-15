package com.example.cookr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    ImageView img;
    TextView txtName;
    TextView txtDetails;
    TextView txtIngre;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            try {
                JSONArray arr = (JSONArray)msg.obj;
                JSONObject current = arr.getJSONObject(0);

                String name = current.getString("name");
                String diet = current.getString("diet");
                String cuisine = current.getString("cuisine");
                String introduction = current.getString("introduction");
                String imageUrl = current.getString("imageUrl");

                JSONArray tagsJsonArray = current.getJSONArray("tags");
                String[] tags = new String[tagsJsonArray.length()];
                for (int j = 0; j < tagsJsonArray.length(); j++) {
                    tags[j] = tagsJsonArray.getString(j);
                }

                JSONArray ingredientsJsonArray = current.getJSONArray("ingredients");
                String[] ingredients = new String[ingredientsJsonArray.length()];
                for (int j = 0; j < ingredientsJsonArray.length(); j++) {
                    ingredients[j] = ingredientsJsonArray.getString(j);
                }

                Recipe recipe = new Recipe(name, diet, cuisine, introduction, imageUrl, tags, ingredients);

                txtDetails.setText(recipe.getIntroduction());
                txtName.setText(recipe.getName());

                String[] ingredientsStr = recipe.getIngredients();
                StringBuilder ingredientsBuilder = new StringBuilder();

                for (String ingredient : ingredientsStr) {
                    ingredientsBuilder.append(ingredient).append("\n");
                }

                String ingredientsString = ingredientsBuilder.toString();
                txtIngre.setText(ingredientsString);

                RecipeRepo repo = new RecipeRepo();
                repo.downloadImage(((CookrApp)getApplication()).srv,imgHandler,recipe.getImageUrl());

                return true;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    });

    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            img.setImageBitmap((Bitmap) msg.obj);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img = findViewById(R.id.imgDetails);
        txtName = findViewById(R.id.txtNameDetail);
        txtDetails = findViewById(R.id.textIntro);
        txtIngre = findViewById(R.id.textIngredient);

        Button btn =findViewById(R.id.btnBack);
        btn.setOnClickListener((v)->{
            finish();
        });

        String name = getIntent().getExtras().getString("name");
        RecipeRepo repo = new RecipeRepo();
        repo.getDataByName(((CookrApp)getApplication()).srv,handler,name);


    }
}