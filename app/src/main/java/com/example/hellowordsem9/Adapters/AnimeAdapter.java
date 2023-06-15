package com.example.hellowordsem9.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellowordsem9.R;
import com.example.hellowordsem9.models.Animes;
import com.example.hellowordsem9.models.Contacto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimeAdapter extends  RecyclerView.Adapter{


    private Context context;
    private List<Animes> animes;

    public AnimeAdapter(Context context, List<Animes> animes) {
        this.context = context;
        this.animes = animes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_anime, parent, false);
        AnimeAdapter.AnimeViewHolder viewHolder = new AnimeAdapter.AnimeViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Animes item = animes.get(position);
        Log.i("Validar Contacto", "Information message: " + item.toString());
        View view = holder.itemView;

        TextView tvName = view.findViewById(R.id.name_anime);
        TextView tvDescripcion = view.findViewById(R.id.descripcion);
        ImageView imageView = view.findViewById(R.id.picture_contact);
        tvName.setText(item.getNombre());
        tvDescripcion.setText(item.getDescripcion());
        Picasso.get().load(item.getFoto()).into(imageView);
        ImageView starImageView = view.findViewById(R.id.fabvoritaEstrella);
        if (item.isFavorite()) {
            starImageView.setImageResource(R.drawable.star_black);
        } else {
            starImageView.setImageResource(R.drawable.star_white);
        }

        starImageView.setOnClickListener(vista -> {
            item.setFavorite(!item.isFavorite());
            if (item.isFavorite()) {
                starImageView.setImageResource(R.drawable.star_black);
            } else {
                starImageView.setImageResource(R.drawable.star_white);
            }
        });
    }




    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class  AnimeViewHolder extends RecyclerView.ViewHolder {

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
