package com.example.finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.finalproject.Model.MoviesModel;


public class DetailMovies extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageView ivPoster;
    private TextView tvTitle, tvDescription;
    private Button btnDetail;

    private MoviesModel model;
    public static String EXTRA_DATA = "extra_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        ivPoster = findViewById(R.id.iv_poster_movies_detail);
        tvTitle = findViewById(R.id.tv_title_movies_detail);
        tvDescription = findViewById(R.id.tv_description_movies_detail);

        model = getIntent().getParcelableExtra(EXTRA_DATA);

        if (model!=null){
            String poster = model.getPoster();
            String title = model.getTitle();
            String description = model.getDescription();

            Glide.with(this).load(poster).fitCenter().into(ivPoster);
            tvTitle.setText(title);
            tvDescription.setText(description);
        }
    }
}
