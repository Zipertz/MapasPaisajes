package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.hellowordsem9.Adapters.pokemonAdapter;

import com.example.hellowordsem9.models.Pokemon;
import com.example.hellowordsem9.servicios.ServicesWebPokemon;
import com.example.hellowordsem9.servicios.servicesWeb;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallePokemonActivity extends AppCompatActivity {

    ImageView imgA;
    Button btnEliminar;
    private pokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pokemon);

        String pokemonJson = getIntent().getStringExtra("Pokemons");
        Pokemon pokemon = new Gson().fromJson(pokemonJson, Pokemon.class);


        imgA = findViewById(R.id.imgAvatarr);
        EditText tvnumero = findViewById(R.id.etNumeroo);
        EditText tvnombre = findViewById(R.id.tvNombree);
        EditText tvtipo = findViewById(R.id.tvTipoo);
        EditText tvmUrll = findViewById(R.id.tvUrll);
        btnEliminar = findViewById(R.id.btnEliminar);

        Picasso.get()
                .load(pokemon.img) // Carga la imagen desde el enlace proporcionado en el objeto Libro
                .into(imgA);

        tvnumero.setText(pokemon.numero);
        tvnombre.setText(pokemon.nombre);
        tvtipo.setText(pokemon.tipo);
        tvmUrll.setText(pokemon.img);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://63746cd448dfab73a4df8801.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServicesWebPokemon services = retrofit.create(ServicesWebPokemon.class);


                Call<Void> call = services.delete(pokemon.id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // La imagen se agregó correctamente a MockAPI
                            onBackPressed();
                        } else {
                            // Hubo un error al agregar la imagen a MockAPI
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Error de red o de la API
                    }
                });
            }
        });




    }
}