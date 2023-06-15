package com.example.hellowordsem9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hellowordsem9.Adapters.paisajeAdapter;
import com.example.hellowordsem9.models.Paisaje;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetallePaisajeActivity extends AppCompatActivity implements OnMapReadyCallback{
    ImageView imgA;
    Button btnEliminar;
    private paisajeAdapter adapter;
    GoogleMap mMap;
    EditText tvmLatitud;
    EditText tvmLongitud;
    Button btnMapa;
    EditText tvTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_paisaje);

        String libroJson = getIntent().getStringExtra("Libros");
        Paisaje libro = new Gson().fromJson(libroJson, Paisaje.class);


        imgA = findViewById(R.id.imgAvatar);
        tvTitulo = findViewById(R.id.tvTitulomin);


        tvmLatitud = findViewById(R.id.tvLatitud);
        tvmLongitud = findViewById(R.id.tvLongitud);

        btnMapa= findViewById(R.id.btnVerMapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getView().setVisibility(View.GONE); // Oculta el fragmento del mapa

        mapFragment.getMapAsync(this);


        Picasso.get()
                .load(libro.img) // Carga la imagen desde el enlace proporcionado en el objeto Libro
                .into(imgA);
        tvTitulo.setText(libro.titulo);


        tvmLatitud.setText(String.valueOf(libro.latitude));
        tvmLongitud.setText(String.valueOf(libro.longitude));
        Log.i("primero","Primerooo");
        //setCurrentLocation(libro.latitude,libro.longitude);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DetallePaisajeActivity.this, MapsActivity.class);
                String LatitudJson = new Gson().toJson(new LatLng(Double.parseDouble(String.valueOf(tvmLatitud.getText())),Double.parseDouble(String.valueOf(tvmLongitud.getText()))));
                intent.putExtra("LatLong",LatitudJson);
                intent.putExtra("TituloLibro", tvTitulo.getText().toString());
               startActivity(intent);
            }
        });



    }

    public void setAdapter(paisajeAdapter adapter) {
        this.adapter = adapter;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        Log.i("Segundo","Segundooooo"+tvmLongitud.getText());
        LatLng actual = new LatLng(Double.parseDouble(String.valueOf(tvmLatitud.getText())),Double.parseDouble(String.valueOf(tvmLongitud.getText())));
        Log.i("Tercero","Tercerooo"+actual.toString());
        mMap.addMarker(new MarkerOptions().position(actual).title("actual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(actual));
    }



}