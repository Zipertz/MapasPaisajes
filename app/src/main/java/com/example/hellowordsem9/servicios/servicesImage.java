package com.example.hellowordsem9.servicios;

import com.example.hellowordsem9.models.Paisaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface servicesImage {
    @GET("libros1/")
    Call<List<Paisaje>> getContacts();

    @GET("libros1/{id}")
    Call<Paisaje> findContact(@Path("id") int id);

    @POST("libros1")
    Call<Paisaje> create(@Body Paisaje libro);

    @PUT("libros1/{id}")
    Call<Void> actualizar(@Path("id") int id, @Body Paisaje libro);

    @DELETE("libros1/{id}")
    Call<Void> delete(@Path("id") int id);
}
