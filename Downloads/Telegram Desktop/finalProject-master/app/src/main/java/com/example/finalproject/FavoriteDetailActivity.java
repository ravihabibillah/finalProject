package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.finalproject.Database.AppDatabase;
import com.example.finalproject.Database.DataFavorite;

public class FavoriteDetailActivity extends AppCompatActivity {

    private ImageView ivPoster;
    private TextView tvTitle, tvDescription;
    private Button btnCatatan, btnHapus;
    private EditText etCatatan;
    String poster,title,description,catatan;
    int id;

    AppDatabase appDatabase;

    private DataFavorite dataFavorite;
//    public static String EXTRA_DATA = "extra_data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_detail);

        appDatabase = AppDatabase.initDb(getApplicationContext());

        ivPoster = findViewById(R.id.iv_poster_favorite_detail);
        tvTitle = findViewById(R.id.tv_title_favorite_detail);
        tvDescription = findViewById(R.id.tv_description_favorite_detail);
        etCatatan = findViewById(R.id.et_Catatan);
        btnHapus = findViewById(R.id.btn_delete_favorite);
        btnCatatan = findViewById(R.id.btn_update_catatan);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null) {
            poster = bundle.getString("poster");
            title = bundle.getString("judul");
            description = bundle.getString("deskripsi");
            id = bundle.getInt("id");
            catatan = bundle.getString("catatan");

            Glide.with(this).load(poster).fitCenter().into(ivPoster);
            tvTitle.setText(title);
            tvDescription.setText(description);
            etCatatan.setText(catatan);

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appDatabase.dao().deletebyId(id);
                    finish();
                }
            });

            btnCatatan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appDatabase.dao().update(etCatatan.getText().toString(),id);
                    finish();
                }
            });
        }

    }
}
