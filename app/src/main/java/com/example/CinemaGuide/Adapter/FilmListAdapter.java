package com.example.CinemaGuide.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.CinemaGuide.Activity.DetailActivity;
import com.example.CinemaGuide.Domain.ListFilm;
import com.example.CinemaGuide.R;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {
    ListFilm item;
    Context context;

    public FilmListAdapter(ListFilm item) {
        this.item = item;
    }

    @NonNull
    @Override
    public FilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        context= parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmListAdapter.ViewHolder holder, int position) {
        holder.titletxt.setText(item.getData().get(position).getTitle());
        holder.scoretxt.setText(""+item.getData().get(position).getImdbRating());

        Glide.with(holder.itemView.getContext())
                .load(item.getData().get(position).getPoster())
                .into(holder.pic);
        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("id",item.getData().get(position).getId());
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return item.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titletxt, scoretxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titletxt=itemView.findViewById(R.id.titletext);
            scoretxt=itemView.findViewById(R.id.scoretext);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}
