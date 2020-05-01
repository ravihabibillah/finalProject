package com.example.finalproject.ui.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Adapter.MoviesAdapter;
import com.example.finalproject.DetailMovies;
import com.example.finalproject.Model.MoviesModel;
import com.example.finalproject.R;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {

    private MoviesViewModel moviesViewModel;
    private ProgressBar progressBar;
    private RecyclerView rvMovies;
    private MoviesAdapter moviesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //inisialisasi
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        progressBar = root.findViewById(R.id.progress_bar_movies);
        rvMovies = root.findViewById(R.id.rv_movies);
        moviesAdapter = new MoviesAdapter();

        //koneksi ke viewModel
        moviesViewModel = new ViewModelProvider(this,
                new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);

        //proses data
        showLoading(true);
        viewModelCalled();
        recyclerView();

        return root;
    }

    private void viewModelCalled() {
        moviesViewModel.setMovies();

        moviesViewModel.getMovies().observe(this, new Observer<ArrayList<MoviesModel>>() {
            @Override
            public void onChanged(ArrayList<MoviesModel> moviesModels) {
                if (moviesModels!=null){
                    moviesAdapter.setMoviesData(moviesModels);
                    showLoading(false);
                }
            }
        });
    }

    private void recyclerView(){
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        moviesAdapter.notifyDataSetChanged();
        rvMovies.setAdapter(moviesAdapter);

        moviesAdapter.setOnItemCallback(new MoviesAdapter.OnItemCallback() {
            @Override
            public void onItemClicked(MoviesModel model) {
                Toast.makeText(getContext(), "Detail "+model.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DetailMovies.class);
                intent.putExtra(DetailMovies.EXTRA_DATA,model);
                startActivity(intent);
            }
        });
    }

    private void showLoading(boolean b) {
        if (b){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
