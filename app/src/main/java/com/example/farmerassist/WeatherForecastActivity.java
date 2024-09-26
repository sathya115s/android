package com.example.farmerassist;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.
        LocationRequest;

import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.farmerassist.api.Interface;
import com.example.farmerassist.databinding.WeatherForecastBinding;
import com.example.farmerassist.responses.WeatherResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.time.LocalDate;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherForecastActivity extends AppCompatActivity {

    WeatherForecastBinding binding;
    Context context;
    FragmentActivity activity;
    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        context = this;
        binding = WeatherForecastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            return;
        }

        LocalDate date = LocalDate.now();
        binding.textView87.setText(date.toString());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getCurrentLocation();

        binding.imageButton9.setOnClickListener(v -> finish());

    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    Log.d("TAG", "lat "+latitude +" lon "+longitude);
                    apiCall(latitude, longitude);
//                    Toast.makeText(context, "Lat: " + latitude + ", Lon: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Unable to get location", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void apiCall(double lat, double lang) {
        String url = "https://api.openweathermap.org/data/";

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)  // Add the logging interceptor
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)  // Set the OkHttpClient with interceptors
                .addConverterFactory(GsonConverterFactory.create())  // Convert JSON responses
                .build();

        Interface inter = retrofit.create(Interface.class);

        String apiKey = "84b1dc263a4f4faeb7593777302003e0";
        Call<WeatherResponse> responseCall = inter.getWeather(lat, lang, apiKey);
        responseCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    WeatherResponse.Weather weather = response.body().getWeather().get(0);
                    WeatherResponse.Main main = response.body().getMain();
                    WeatherResponse.Wind wind = response.body().getWind();

                    double temperature = main.getTemp() - 273.15;
                    double feelsLike = main.getFeels_like() - 273.15;
                    int humidity = main.getHumidity();
                    double windSpeed = wind.getSpeed();
                    //city
                    binding.textView86.setText(weatherResponse.getName());
                    binding.weatherDescription.setText(weather.getDescription());
                    binding.temperature.setText(String.format("%.2f°C", temperature));
                    binding.feelsLike.setText(String.format("%.2f°C", feelsLike));
                    binding.humidity.setText(humidity + "%");
                    binding.windSpeed.setText(windSpeed + " m/s");
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }

}
