package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailActivity extends AppCompatActivity {
    TextView titleTextView;
    TextView overViewText;
    ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        titleTextView = findViewById(R.id.movieDetailTitle);
        posterImageView = findViewById(R.id.movieDetailPoster);
        overViewText = findViewById(R.id.overView);



        Intent intent = getIntent();
        String movieTitle = intent.getStringExtra("movieTitle");
        String overView = intent.getStringExtra("overView");
        int movieImage = intent.getIntExtra("moviePosterImage", -1);

        if(movieTitle != null && movieImage != -1){
            titleTextView.setText(movieTitle);
            posterImageView.setImageResource(movieImage);
            overViewText.setText(overView);
        }

    }
}
