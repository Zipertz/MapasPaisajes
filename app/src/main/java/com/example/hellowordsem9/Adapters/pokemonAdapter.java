package com.example.hellowordsem9.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellowordsem9.DetallePokemonActivity;
import com.example.hellowordsem9.R;
import com.example.hellowordsem9.models.Pokemon;
import com.example.hellowordsem9.servicios.ServicesWebPokemon;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pokemonAdapter extends RecyclerView.Adapter<pokemonAdapter.pokemonViewHolder>{
    List<Pokemon> pokemons;

    public pokemonAdapter(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
    @NonNull
    @Override
    public pokemonAdapter.pokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        pokemonAdapter.pokemonViewHolder vh = new pokemonAdapter.pokemonViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull pokemonAdapter.pokemonViewHolder holder, int position) {
        View vw = holder.itemView;

        Pokemon pokemon = pokemons.get(position);
        TextView itemNumero = holder.itemView.findViewById(R.id.tvNumeo);
        TextView itemNombre = holder.itemView.findViewById(R.id.tvNombre);
        TextView itemTipo = holder.itemView.findViewById(R.id.tvTipo);
        ImageView itemImg = holder.itemView.findViewById(R.id.ivAvatar);

        itemNumero.setText(pokemon.numero);
        itemNombre.setText(pokemon.nombre);
        itemTipo.setText(pokemon.tipo);
        Picasso.get()
                .load(pokemon.img) // Carga la imagen desde el enlace proporcionado en el objeto Libro
                .into(itemImg);

        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new  Retrofit.Builder()
                        .baseUrl("https://63746cd448dfab73a4df8801.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServicesWebPokemon services = retrofit.create(ServicesWebPokemon.class);
                Call<Pokemon> call=services.findContact(pokemon.id);

                call.enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        if (!response.isSuccessful()){
                            Log.e("asd1234", "error");
                        }else {

                            Log.i("asdasd12312", new Gson().toJson(response.body()));
                            Log.i("asd32", "Respuesta correcta por id");

                            Intent intent= new Intent(vw.getContext(), DetallePokemonActivity.class);


                            Log.i("asd32", "Respuesta correcta por id------------ ");
//
                            String pokemonJson = new Gson().toJson(pokemon);
                            intent.putExtra("Pokemons",pokemonJson);

                            vw.getContext().startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }
    public class pokemonViewHolder extends RecyclerView.ViewHolder {
        public pokemonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
