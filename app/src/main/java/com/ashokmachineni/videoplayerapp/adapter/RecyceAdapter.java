package com.ashokmachineni.videoplayerapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashokmachineni.videoplayerapp.R;
import com.ashokmachineni.videoplayerapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyceAdapter extends RecyclerView.Adapter<RecyceAdapter.MiViewHolder> {
     Context context;
    ArrayList<Movie> movies;

    public RecyceAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.names.setText(movie.getTitle());
        String imageUrl = movie.getThumbnail();
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.thums);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder{
        TextView names;
        ImageView thums;
        RelativeLayout relativeLayout;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            names = itemView.findViewById(R.id.titleview);
            thums = itemView.findViewById(R.id.imgView);
            relativeLayout = itemView.findViewById(R.id.rel);
        }
    }
}
