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

import com.example.hellowordsem9.ContactsActivity;
import com.example.hellowordsem9.R;
import com.example.hellowordsem9.models.Contacto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactsAdapter  extends RecyclerView.Adapter {

    private Context context;
    private List<Contacto> contactos;



    public ContactsAdapter(Context context, List<Contacto> contactos) {
        this.context = context;
        this.contactos = contactos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        ContactoViewHolder viewHolder = new ContactoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Contacto item = contactos.get(position);
        Log.i("Validar Contacto", "Information message: " + item.toString());
        View view = holder.itemView;

        Button btnCall = view.findViewById(R.id.button_phone);
        btnCall.setOnClickListener(vista -> {
//            int sum = numbers.stream().reduce(0, Integer::sum);
//            tvSum.setText(String.valueOf(sum));
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + item.getNumero()));
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                context.startActivity(intent);
            } else {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        });

        TextView tvName = view.findViewById(R.id.name_contact);
        TextView tvPhone = view.findViewById(R.id.phone_contact);
        ImageView imageView = view.findViewById(R.id.picture_contact);
        tvName.setText(item.getNombre());
        tvPhone.setText(item.getNumero());
        Picasso.get().load(item.getFoto()).into(imageView);

    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}