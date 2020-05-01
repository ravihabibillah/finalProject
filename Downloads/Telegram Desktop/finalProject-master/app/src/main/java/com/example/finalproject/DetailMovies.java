package com.example.finalproject;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.finalproject.Model.MoviesModel;

import com.example.finalproject.Database.AppDatabase;
import com.example.finalproject.Database.DataFavorite;


public class  DetailMovies extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageView ivPoster;
    private TextView tvTitle, tvDescription;
    private Button btnDetail,btnFavorite;
    String poster,title,description;
    AppDatabase appDatabase;

    private MoviesModel model;
    public static String EXTRA_DATA = "extra_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);

        ivPoster = findViewById(R.id.iv_poster_movies_detail);
        tvTitle = findViewById(R.id.tv_title_movies_detail);
        tvDescription = findViewById(R.id.tv_description_movies_detail);
        btnFavorite = findViewById(R.id.btn_favorite_movies_detail);

        appDatabase = AppDatabase.initDb(getApplicationContext());

        model = getIntent().getParcelableExtra(EXTRA_DATA);

        if (model!=null){
            poster = model.getPoster();
            title = model.getTitle();
            description = model.getDescription();

            Glide.with(this).load(poster).fitCenter().into(ivPoster);
            tvTitle.setText(title);
            tvDescription.setText(description);

            btnFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insert(model);
                }
            });
        }


    }

    private void insert(MoviesModel model) {

            DataFavorite item = new DataFavorite();
            item.setId(model.getId());
            item.setPoster(model.getPoster());
            item.setJudul(model.getTitle());
            item.setDeskripsi(model.getDescription());

            Log.d("ISI",item.getJudul());
        try {
            appDatabase.dao().insert(item);
            Toast.makeText(getApplicationContext(),item.getJudul() + " ditambahkan ke daftar Favorit",Toast.LENGTH_LONG).show();
        } catch (SQLiteConstraintException E){
            Toast.makeText(getApplicationContext(),item.getJudul() + " Sudah ada di daftar Favorit",Toast.LENGTH_LONG).show();
        }


    }
}
