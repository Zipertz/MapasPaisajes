package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hellowordsem9.Adapters.ContactsAdapter;
import com.example.hellowordsem9.models.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    List<Contacto> contactos = new ArrayList<Contacto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        cargarContactos();

        ContactsAdapter adapter = new ContactsAdapter(this,contactos);

        RecyclerView rvContactos =  findViewById(R.id.rv_contactos);
        rvContactos.setLayoutManager(new LinearLayoutManager(this));
        rvContactos.setAdapter(adapter);
    }

    private void cargarContactos() {
        contactos.add(new Contacto("Pedro","987654321","https://i.imgur.com/DvpvklR.png"));
        contactos.add(new Contacto("Juan","987164321","https://i.imgur.com/DvpvklR.png"));
        contactos.add(new Contacto("Jose","987654961","https://i.imgur.com/DvpvklR.png"));
        contactos.add(new Contacto("Pablo","987123482","https://i.imgur.com/DvpvklR.png"));
        contactos.add(new Contacto("Luis","987628321","https://i.imgur.com/DvpvklR.png"));
        contactos.add(new Contacto("Miguel","987656721","https://i.imgur.com/DvpvklR.png"));
        contactos.add(new Contacto("Mauricio","987345543","https://i.imgur.com/DvpvklR.png"));
        contactos.add(new Contacto("Paul","987651231","https://i.imgur.com/DvpvklR.png"));
        contactos.add(new Contacto("Renato","987567456","https://i.imgur.com/DvpvklR.png"));
    }
}