package com.example.myapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.connection.RetrofitConnection;
import com.example.myapp.models.PokemonInfo;
import com.example.myapp.models.PokemonRespuesta;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonActivity extends AppCompatActivity {
    private Intent intent;
    private TextView textView;
    private ImageView imageView;
    private ProgressBar progressBarExp;
    //****
    private ProgressBar progressBarindex;
    private ProgressBar progressBarlevel;
    //level_learned_at
    private ProgressBar progressBarheight;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        this.progressBarExp = findViewById(R.id.progressExp);
        //:*********
        this.progressBarindex=findViewById(R.id.progressIndex);
        this.progressBarlevel=findViewById(R.id.progressLevel);
        this.progressBarheight=findViewById(R.id.progressheih);

        this.intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra("id"));
        this.textView = findViewById(R.id.textName);
        this.imageView = findViewById(R.id.imagePok);

        textView.setText(intent.getStringExtra("name"));

        Picasso.get().load(intent.getStringExtra("imagePok")).into(imageView);

        RetrofitConnection.getPokemonInfoCall(id).enqueue(new Callback<PokemonInfo>() {
            @Override
            public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {
                if(!response.isSuccessful()){
                    Log.i("POKEMON", "ERROR" + response.message());
                    return;
                }

                progressBarExp.setMax(200);
                ObjectAnimator.ofInt(progressBarExp, "progress", response.body().getExp()).setDuration(1000).start();

                progressBarindex.setMax(200);
                ObjectAnimator.ofInt(progressBarindex, "progress", response.body().getIndex()).setDuration(1000).start();

                progressBarlevel.setMax(200);
                ObjectAnimator.ofInt(progressBarlevel, "progress", response.body().getLevel()).setDuration(1000).start();

                progressBarheight.setMax(200);
                ObjectAnimator.ofInt(progressBarheight, "progress", response.body().getHeight()).setDuration(1000).start();




            }

            @Override
            public void onFailure(Call<PokemonInfo> call, Throwable t) {
                Log.i("POKEMON", "ERROR" + t.getMessage());
            }
        });
    }
}
