package com.example.cookr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;



public class RecipeRepo {

    public void getAllRecipes(ExecutorService srv, Handler uiHandler){

        srv.submit(()->{
            try {

                List<Recipe> data = new ArrayList<>();

                URL url = new URL("http://10.0.2.2:8080/cookr/recipes");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line= reader.readLine()) != null){

                    buffer.append(line);

                }

                JSONArray arr = new JSONArray(buffer.toString());

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject current = arr.getJSONObject(i);

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

                    data.add(recipe);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("DEV",e.getMessage());
            } catch (IOException e) {
                Log.e("DEV",e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV",e.getMessage());
            }
        });
    }

    public void getRandomRecipes(ExecutorService srv, Handler uiHandler){

        srv.submit(()->{
            try {

                List<Recipe> data = new ArrayList<>();

                URL url = new URL("http://10.0.2.2:8080/cookr/recipes/random");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line= reader.readLine()) != null){

                    buffer.append(line);

                }

                JSONArray arr = new JSONArray(buffer.toString());

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject current = arr.getJSONObject(i);

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

                    data.add(recipe);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("DEV",e.getMessage());
            } catch (IOException e) {
                Log.e("DEV",e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV",e.getMessage());
            }
        });
    }

    public void getDataByName(ExecutorService srv, Handler uiHandler,String name){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.0.2.2:8080/cookr/recipes/search?name=" + name);
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONArray arr = new JSONArray(buffer.toString());

                Message msg = new Message();
                msg.obj = arr;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });



    }

    public void getDataByDiet(ExecutorService srv, Handler uiHandler, String diet) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/cookr/diets/" + diet);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());

                Message msg = new Message();
                msg.obj = arr;
                uiHandler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public void getDataByCuisine(ExecutorService srv, Handler uiHandler, String cuisine) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/cookr/cuisines/" + cuisine);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());

                Message msg = new Message();
                msg.obj = arr;
                uiHandler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public void getDataByTags(ExecutorService srv, Handler uiHandler, String tags) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/cookr/tags/searchByTags?tags=" + tags);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());

                Message msg = new Message();
                msg.obj = arr;
                uiHandler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }


    public void sendData(ExecutorService srv, Handler uiHandler,Recipe recipe) {

        srv.execute(()->{
            try {
                URL url = new URL("http://10.0.2.2:8080/cookr/recipes");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/JSON");

                JSONObject recipeJson = convertRecipeToJSON(recipe);
                BufferedOutputStream writer =
                        new BufferedOutputStream(conn.getOutputStream());
                writer.write(recipeJson.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                int responseCode = conn.getResponseCode();
                boolean success = (responseCode == HttpURLConnection.HTTP_OK);

                conn.disconnect();

                if (success) {
                    // Recipe creation successful
                    uiHandler.sendEmptyMessage(1);
                } else {
                    // Recipe creation failed
                    uiHandler.sendEmptyMessage(0);
                }

            } catch (MalformedURLException e) {
                Log.e("DEV", e.getMessage());
            } catch (IOException e) {
                Log.e("DEV", e.getMessage());
            }
        });

    }


    private JSONObject convertRecipeToJSON(Recipe recipe) {
        JSONObject recipeJson = new JSONObject();

        try {
            recipeJson.put("name", recipe.getName());
            recipeJson.put("diet", recipe.getDiet());
            recipeJson.put("cuisine", recipe.getCuisine());
            recipeJson.put("introduction", recipe.getIntroduction());
            recipeJson.put("imageUrl", recipe.getImageUrl());

            JSONArray tagsJsonArray = new JSONArray();
            for (String tag : recipe.getTags()) {
                tagsJsonArray.put(tag);
            }
            recipeJson.put("tags", tagsJsonArray);

            JSONArray ingredientsJsonArray = new JSONArray();
            for (String ingredient : recipe.getIngredients()) {
                ingredientsJsonArray.put(ingredient);
            }
            recipeJson.put("ingredients", ingredientsJsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipeJson;
    }
    public void downloadImage(ExecutorService srv, Handler uiHandler, String path){

        srv.submit(()->{

            try {
                URL url = new URL(path);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                Bitmap bm =  BitmapFactory.decodeStream(conn.getInputStream());

                Message msg = new Message();
                msg.obj = bm;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("DEV",e.getMessage());
            } catch (IOException e) {
                Log.e("DEV",e.getMessage());
            }


        });
    }









}
