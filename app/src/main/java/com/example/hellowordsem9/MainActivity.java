package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int count = 0;
    Button accion;
    Button reset;
    Button next;
    Button crear;
    EditText punt1;
    EditText punt2;
    EditText ganador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accion = findViewById(R.id.action);
        reset = findViewById(R.id.reset);
        next = findViewById(R.id.button_next);
        crear = findViewById(R.id.btnCrearLibro);
        punt1 = findViewById(R.id.punt1);
        punt2 = findViewById(R.id.punt2);
        ganador = findViewById(R.id.ganador);

        accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accion();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
        next.setOnClickListener(view -> {
//            int sum = numbers.stream().reduce(0, Integer::sum);
//            tvSum.setText(String.valueOf(sum));

            Intent intent = new Intent(getApplicationContext(), ListaPaisajeActivity.class);
            startActivity(intent);

        });
        crear.setOnClickListener(view -> {
//            int sum = numbers.stream().reduce(0, Integer::sum);
//            tvSum.setText(String.valueOf(sum));

            Intent intent = new Intent(getApplicationContext(), CrearPaisajeActivity.class);
            startActivity(intent);

        });
    }

    public void accion() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(10) + 1;
        if(count == 0) {
            punt1.setText(""+numeroAleatorio);
            accion.setText("JUGADOR 2");
        } else if(count == 1) {
            punt2.setText(""+numeroAleatorio);
            int puntaje1 = Integer.parseInt(punt1.getText().toString());
            int puntaje2 =Integer.parseInt(punt2.getText().toString());
            if(puntaje1 == puntaje2) {
                ganador.setText("EMPATE");
            } else if(puntaje1 > puntaje2) {
                ganador.setText("JUGADOR 1 GANA");
            } else {
                ganador.setText("JUGADOR 2 GANA");
            }
        }
        count++;
    }

    public void clear() {
        accion.setText("JUGADOR 1");
        punt1.setText("");
        punt2.setText("");
        ganador.setText("");
        count = 0;
    }
}
