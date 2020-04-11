package com.example.finalproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finalproject.Model.MoviesModel;
import com.example.finalproject.R;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private ArrayList<MoviesModel> moviesData = new ArrayList<>();
    private OnItemCallback onItemCallback;

    public void setMoviesData(ArrayList<MoviesModel> models){
        moviesData.clear();
        moviesData.addAll(models);
        notifyDataSetChanged();
    }

    public void setOnItemCallback(OnItemCallback onItemCallback) {
        this.onItemCallback = onItemCallback;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesViewHolder holder, int position) {
        final MoviesModel movies = moviesData.get(position);
        Glide.with(holder.itemView.getContext())
                .load(movies.getPoster())
                .apply(new RequestOptions().override(300,300))
                .into(holder.ivPoster);
        holder.tvTitle.setText(movies.getTitle());
        holder.tvDescription.setText(movies.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallback.onItemClicked(moviesData.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPoster;
        private TextView tvTitle,tvDescription;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }

    public interface OnItemCallback{
        void onItemClicked(MoviesModel model);
    }
}
