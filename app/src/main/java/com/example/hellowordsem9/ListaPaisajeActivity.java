package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.hellowordsem9.Adapters.paisajeAdapter;
import com.example.hellowordsem9.models.Paisaje;
import com.example.hellowordsem9.servicios.servicesWeb;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ListaPaisajeActivity extends AppCompatActivity {
    public RecyclerView rv;
    List<Paisaje> libros= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paisaje);

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

        servicesWeb services = retrofit.create(servicesWeb.class);
        Call<List<Paisaje>> call=services.getContacts();

        call.enqueue(new Callback<List<Paisaje>>() {
            @Override
            public void onResponse(Call<List<Paisaje>> call, Response<List<Paisaje>> response) {
                if (!response.isSuccessful()){
                    Log.e("asd1234", "error");
                }else{
                    Log.i("asdasd12312", new Gson().toJson(response.body()));
                    Log.i("asd32", "Respuesta correcta");

                    libros=response.body();

                    paisajeAdapter adapter=new paisajeAdapter(libros);


                    rv= findViewById(R.id.rvLibro);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Paisaje>> call, Throwable t) {
                Log.e("asd1234", "no hay conexion");
            }
        });

    }
}