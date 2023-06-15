package com.example.cookr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    Context context;
    List<Recipe> data;

    public RecipeAdapter(Context context, List<Recipe> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(context).inflate(R.layout.recipe_row_layout,parent,false);

        RecipeViewHolder holder = new RecipeViewHolder(root);
        holder.setIsRecyclable(false);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        holder.txt.setText(data.get(position).getName());

        CookrApp app = (CookrApp)((Activity)context).getApplication();

        holder.downloadImage(app.srv,data.get(position).getImageUrl());

        holder.row.setOnClickListener((v)->{

            Intent i = new Intent(context,DetailActivity.class);
            i.putExtra("name",data.get(position).getName());

            ((Activity)context).startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout row;
        ImageView img;
        TextView txt;
        Boolean imageDownloaded = false;


        Handler imageHandler = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message msg) {

                img.setImageBitmap((Bitmap) msg.obj);
                imageDownloaded = true;
                return true;
            }

        });


        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            row = itemView.findViewById(R.id.row_list);
            img = itemView.findViewById(R.id.imgList);
            txt = itemView.findViewById(R.id.txtListName);

        }

        public void downloadImage(ExecutorService srv,String path){


            if (!imageDownloaded){
                RecipeRepo repo = new RecipeRepo();
                repo.downloadImage(srv, imageHandler, path);
            }

        }
    }
}
