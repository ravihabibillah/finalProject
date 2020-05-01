package com.example.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finalproject.R;

import java.util.List;

import com.example.finalproject.Database.DataFavorite;
import com.example.finalproject.Listener.FavoriteListener;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<DataFavorite> dataFavorites;
    private FavoriteListener favoriteListener;
    public Context context;
    private FavoriteAdapter.OnItemCallback onItemCallback;

    public void setOnItemCallback(FavoriteAdapter.OnItemCallback onItemCallback) {
        this.onItemCallback = onItemCallback;
    }

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public List<DataFavorite> getDataFavorites() {
        return dataFavorites;
    }

    public void setDataFavorites(List<DataFavorite> dataFavorites) {
        this.dataFavorites = dataFavorites;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row,parent,false);

        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteAdapter.ViewHolder holder, final int position){
        holder.tvJudul.setText(getDataFavorites().get(position).getJudul());
        holder.tvDeskripsi.setText(getDataFavorites().get(position).getDeskripsi());
        Glide.with(holder.itemView.getContext())
                .load(getDataFavorites().get(position).getPoster())
                .apply(new RequestOptions().override(300,300))
                .into(holder.ivPoster);
//        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, FavoriteDetailActivity.class);
//                intent.putExtra("title",getDataFavorites().get(position).getJudul());
//                intent.putExtra("deskripsi",getDataFavorites().get(position).getDeskripsi());
//                intent.putExtra("poster",getDataFavorites().get(position).getPoster())
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onItemCallback.onItemClicked(moviesData.get(holder.getAdapterPosition()));
                onItemCallback.onItemClicked(getDataFavorites().get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getDataFavorites().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJudul,tvDeskripsi;
        private ImageView ivPoster;
        private LinearLayout layoutItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_title);
            tvDeskripsi = itemView.findViewById(R.id.tv_description);
            ivPoster = itemView.findViewById(R.id.iv_poster);
        }
    }
    public interface OnItemCallback{
        void onItemClicked(DataFavorite dataFavorite);
    }
}
