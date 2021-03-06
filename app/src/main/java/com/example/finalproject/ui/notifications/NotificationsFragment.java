package com.example.finalproject.ui.notifications;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Adapter.FavoriteAdapter;
import com.example.finalproject.DetailMovies;
import com.example.finalproject.FavoriteDetailActivity;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

import Database.AppDatabase;
import Database.DataFavorite;
import Listener.FavoriteListener;

public class NotificationsFragment extends Fragment {
    private AppDatabase appDatabase;
    private RecyclerView rvFavorite;
    private ArrayList<DataFavorite> list = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View tampil =  inflater.inflate(R.layout.fragment_notifications, container, false);


        //init database
        appDatabase = AppDatabase.initDb(container.getContext());

        rvFavorite = tampil.findViewById(R.id.rv_movies_db);
        rvFavorite.setHasFixedSize(true);

        read();

        return tampil;
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        read();
    }

    private void read() {

        list.addAll(appDatabase.dao().getData());

        rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));

        FavoriteAdapter adapter = new FavoriteAdapter(getContext());

        adapter.setDataFavorites(list);
        rvFavorite.setAdapter(adapter);

        adapter.setOnItemCallback(new FavoriteAdapter.OnItemCallback() {
            @Override
            public void onItemClicked(DataFavorite dataFavorite) {
                Toast.makeText(getContext(), "Detail "+ dataFavorite.getJudul(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), FavoriteDetailActivity.class);
                intent.putExtra("judul",dataFavorite.getJudul());
                intent.putExtra("deskripsi",dataFavorite.getDeskripsi());
                intent.putExtra("poster",dataFavorite.getPoster());
                intent.putExtra("id", dataFavorite.getId());
                intent.putExtra("catatan",dataFavorite.getCatatan());
                startActivity(intent);

            }
        });
    }
}
