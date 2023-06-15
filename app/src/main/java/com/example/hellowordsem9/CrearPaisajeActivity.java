package com.example.hellowordsem9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hellowordsem9.models.Paisaje;
import com.example.hellowordsem9.servicios.servicesWeb;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearPaisajeActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, LocationListener {
    private static final int REQUEST_CAMERA = 1;
    private static final int OPEN_GALLERY_REQUEST = 1002;
    private LocationManager mLocationManager;
    String urlImage = "";
    GoogleMap mMap;
    EditText etLatitud;
    EditText etlongitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_paisaje);
        Button btn = findViewById(R.id.btnCrearLibro);
        EditText etTitulo = findViewById(R.id.etTitulo);
        etLatitud = findViewById(R.id.etLatitud);
        etlongitud = findViewById(R.id.etLongitud);

        Button btnCamara = findViewById(R.id.btnCameraa);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getView().setVisibility(View.GONE); // Oculta el fragmento del mapa

        mapFragment.getMapAsync(this);


        if(
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            String[] permissions = new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
            requestPermissions(permissions, 3000);

        }
        else {
            // configurar frecuencia de actualización de GPS
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1, this);
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://63746cd448dfab73a4df8801.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                servicesWeb services = retrofit.create(servicesWeb.class);

                Paisaje libro = new Paisaje();
                libro.titulo = String.valueOf(etTitulo.getText());

                libro.latitude = Double.valueOf(String.valueOf(etLatitud.getText()));
                libro.longitude = Double.valueOf(String.valueOf(etlongitud.getText()));
                libro.img = String.valueOf("https://demo-upn.bit2bittest.com/"+urlImage);
                // libro.img = String.valueOf(etUrl.getText()); // Obtén el enlace de la imagen desde el EditText


                Call<Paisaje> call = services.create(libro);

                call.enqueue(new Callback<Paisaje>() {
                    @Override
                    public void onResponse(Call<Paisaje> call, Response<Paisaje> response) {
                        if (response.isSuccessful()) {
                            // La imagen se agregó correctamente a MockAPI
                        } else {
                            // Hubo un error al agregar la imagen a MockAPI
                        }
                    }

                    @Override
                    public void onFailure(Call<Paisaje> call, Throwable t) {
                        // Error de red o de la API
                    }
                });
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOpenCamara();
            }
        });


    }

    private void onOpenCamara() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Log.i("My app","El permiso de la cámara ya se ha otorgado, puedes realizar la acción deseada aquí");
            OpenCamera();
        } else {
            // El permiso de la cámara no se ha otorgado, solicítalo al usuario
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1000);
            onOpenCamara();
            Log.i("My app","No tienes permiso");
        }
    }

    private void OpenCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA );
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            // La foto se tomó correctamente, puedes manejar el resultado aquí
            // Por ejemplo, puedes obtener la imagen capturada utilizando data.getExtras().get("data")
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");

            // Convierte el bitmap a una cadena Base64
            String base64Image = convertBitmapToBase64(bitmap);
            // imprimirImagenEnLog(base64Image);
            Retrofit retrofit123 = new Retrofit.Builder()
                    .baseUrl("https://demo-upn.bit2bittest.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            servicesWeb services = retrofit123.create(servicesWeb.class);

            Call<servicesWeb.ImageResponse> call = services.saveImage(new servicesWeb.ImageToSave(base64Image));

            call.enqueue(new Callback<servicesWeb.ImageResponse>() {
                @Override
                public void onResponse(Call<servicesWeb.ImageResponse> call, Response<servicesWeb.ImageResponse> response) {
                    Log.i("Respuesta activa", response.toString());
                    if (response.isSuccessful()) {
                        servicesWeb.ImageResponse imageResponse = response.body();
                        Log.i("Respues", response.toString());
                        urlImage = imageResponse.getUrl();
                        Log.i("Imagen url:", urlImage);

                    } else {

                        Log.e("Error cargar imagen",response.toString());
                    }
                }

                @Override
                public void onFailure(Call<servicesWeb.ImageResponse> call, Throwable t) {
                    // Error de red o de la API
                    Log.i("Respuesta inactiva", "");
                }
            });

        }
        if(requestCode == OPEN_GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close(); // close cursor

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            String base64Image = convertBitmapToBase64(bitmap);

            Retrofit retrofit123 = new Retrofit.Builder()
                    .baseUrl("https://demo-upn.bit2bittest.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            servicesWeb services = retrofit123.create(servicesWeb.class);

            Call<servicesWeb.ImageResponse> call = services.saveImage(new servicesWeb.ImageToSave(base64Image));

            call.enqueue(new Callback<servicesWeb.ImageResponse>() {
                @Override
                public void onResponse(Call<servicesWeb.ImageResponse> call, Response<servicesWeb.ImageResponse> response) {
                    Log.i("Respuesta activa", response.toString());
                    if (response.isSuccessful()) {
                        servicesWeb.ImageResponse imageResponse = response.body();
                        Log.i("Respues", response.toString());
                        urlImage = imageResponse.getUrl();
                        Log.i("Imagen url:", urlImage);

                    } else {

                        Log.e("Error cargar imagen",response.toString());
                    }
                }

                @Override
                public void onFailure(Call<servicesWeb.ImageResponse> call, Throwable t) {
                    // Error de red o de la API
                    Log.i("Respuesta inactiva", "");
                }
            });
        }
    }

    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        showCurrentLocation();
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        etLatitud.setText(String.valueOf(latLng.latitude));
        etlongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(mexico).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        etLatitud.setText(String.valueOf(latLng.latitude));
        etlongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(mexico).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        Log.i("MAIN_APP: Location - ",  "Latitude: " + latitude);
        Log.i("MAIN_APP: Location - ",  "Longitude: " + longitude);
    }

    private void showCurrentLocation() {
        // Verificar si los permisos de ubicación están disponibles
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Obtener la última ubicación conocida del dispositivo
            FusedLocationProviderClient fusedLocationClient =
                    LocationServices.getFusedLocationProviderClient(this);
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            // Obtener la latitud y longitud
                            LatLng currentLatLng = new LatLng(location.getLatitude(),
                                    location.getLongitude());

                            // Mover la cámara del mapa a la ubicación actual
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
                            mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Marker"));
                            etLatitud.setText(String.valueOf(currentLatLng.latitude));
                            etlongitud.setText(String.valueOf(currentLatLng.longitude));
                        }
                    });
        }
    }
}

