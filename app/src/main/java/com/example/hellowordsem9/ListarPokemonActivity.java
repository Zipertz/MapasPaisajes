package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;


import com.example.hellowordsem9.Adapters.pokemonAdapter;

import com.example.hellowordsem9.models.Pokemon;
import com.example.hellowordsem9.servicios.ServicesWebPokemon;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarPokemonActivity extends AppCompatActivity {
    public RecyclerView rv;
    List<Pokemon> pokemons= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pokemon);
        cargarData();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Aquí puedes llamar al método que deseas ejecutar cuando la pantalla está en primer plano
        cargarData();
    }
    protected void cargarData(){
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://63746cd448dfab73a4df8801.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServicesWebPokemon services = retrofit.create(ServicesWebPokemon.class);
        Call<List<Pokemon>> call=services.getPokemons();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (!response.isSuccessful()){
                    Log.e("asd1234", "error");
                }else{
                    Log.i("asdasd12312", new Gson().toJson(response.body()));
                    Log.i("asd32", "Respuesta correcta");

                    pokemons=response.body();

                    pokemonAdapter adapter=new pokemonAdapter(pokemons);


                    rv= findViewById(R.id.rvPokemon);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Log.e("asd1234", "no hay conexion");
            }
        });

    }
}