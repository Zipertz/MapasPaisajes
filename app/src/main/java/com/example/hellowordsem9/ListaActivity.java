package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hellowordsem9.Adapters.NameAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        RecyclerView rv_lista = findViewById(R.id.rv_lista);

        NameAdapter nameAdapter = new NameAdapter(data());

        rv_lista.setLayoutManager(new LinearLayoutManager(this));
        rv_lista.setAdapter(nameAdapter);



    }

    private List<String> data() {
        List<String> names = new ArrayList<>();
        names.add("Luis");
        names.add("José");
        names.add("Pedro");
        names.add("Pablo");
        return  names;
    }
}