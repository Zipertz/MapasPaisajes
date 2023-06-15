package com.example.hellowordsem9.servicios;

import com.example.hellowordsem9.models.Comentarios;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicesWebComentarios {
    @GET("comentarios/")
    Call<List<Comentarios>> getContacts();

    @GET("comentarios/{id}")
    Call<Comentarios> findContact(@Path("id") int id);

    @POST("comentarios")
    Call<Comentarios> create(@Body Comentarios comentarios);

    @PUT("comentarios/{id}")
    Call<Void> actualizar(@Path("id") int id, @Body Comentarios comentarios);

    @DELETE("comentarios/{id}")
    Call<Void> delete(@Path("id") int id);

    @POST("image")
    Call<servicesWeb.ImageResponse> saveImage(@Body servicesWeb.ImageToSave image);

    class ImageResponse {
        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }
    }
    class ImageToSave {
        String base64Image;

        public ImageToSave(String base64Image) {
            this.base64Image = base64Image;
        }
    }
}
