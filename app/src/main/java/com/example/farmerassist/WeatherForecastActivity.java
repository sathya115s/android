package com.example.farmerassist;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;

import com.example.farmerassist.databinding.ForecastItemLayoutBinding;
import com.example.farmerassist.responses.DaysWeatherResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.
        LocationRequest;

import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerassist.api.Interface;
import com.example.farmerassist.databinding.WeatherForecastBinding;
import com.example.farmerassist.responses.WeatherResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

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

    ArrayList<DaysWeatherResponse.List> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        context = this;
        binding = WeatherForecastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            return;
        }

        LocalDate date = LocalDate.now();
//        binding.textView87.setText(date.toString());

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
                    Log.d("TAG", "lat " + latitude + " lon " + longitude);
                    apiCall(latitude, longitude);
//                    Toast.makeText(context, "Lat: " + latitude + ", Lon: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Unable to get location", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void apiCall(double lat, double lang) {

//        https://api.openweathermap.org/data/2.5/forecast?lat=12.9813484&lon=80.0666207&appid=eb598c768375941e4132d7fec878c58c&units=metric
//        String url = "https://api.openweathermap.org/data/";
        String forecastUrl = "https://api.openweathermap.org/data/";

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)  // Add the logging interceptor
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(forecastUrl)
                .client(okHttpClient)  // Set the OkHttpClient with interceptors
                .addConverterFactory(GsonConverterFactory.create())  // Convert JSON responses
                .build();

        Interface inter = retrofit.create(Interface.class);

        String apiKey = "eb598c768375941e4132d7fec878c58c";
        Call<DaysWeatherResponse> responseCall = inter.getForecast(lat, lang, apiKey);
        responseCall.enqueue(new Callback<DaysWeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<DaysWeatherResponse> call, @NonNull Response<DaysWeatherResponse> response) {
                if (response.isSuccessful()) {

                    if (Objects.equals(response.body().getCod(), "200")) {


                        DaysWeatherResponse forecast = response.body();
                        ArrayList<DaysWeatherResponse.List> forecasts = forecast.list;
                        ArrayList<DaysWeatherResponse.List> fiveDayForecast = getFiveDayForecast(forecasts);

                        binding.location.setText(forecast.getCity().getName());
                        binding.date.setText(fiveDayForecast.get(0).getDt_txt().split(" ")[0]);
                        double temp = fiveDayForecast.get(0).getMain().getTemp() - 273.15;
                        String tempreture = String.format("%.2f", temp);
                        binding.temp.setText("Tempreture: " + tempreture + "°C");
                        binding.humidity.setText("Humidity: " + forecasts.get(0).getMain().getHumidity() + "%");
                        binding.wind.setText("Wind Speed: " + forecasts.get(0).getWind().getSpeed() + "m/s");

                        if (!fiveDayForecast.isEmpty()) {

                            for (DaysWeatherResponse.List forecastItem : fiveDayForecast) {
                                list.add(forecastItem);
                            }
                        }

                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        WeatherForecastAdapter adapter = new WeatherForecastAdapter(context, list);
                        binding.recyclerView.setAdapter(adapter);
                    }

                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DaysWeatherResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }

    private static ArrayList<DaysWeatherResponse.List> getFiveDayForecast(ArrayList<DaysWeatherResponse.List> forecasts) {
        Set<String> uniqueDates = new HashSet<>();
        ArrayList<DaysWeatherResponse.List> filteredForecast = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Loop through the forecast list and pick one forecast per day
        for (DaysWeatherResponse.List forecast : forecasts) {
            String dateOnly = forecast.dt_txt.split(" ")[0]; // Extract the date part (yyyy-MM-dd)

            if (!uniqueDates.contains(dateOnly)) {
                uniqueDates.add(dateOnly);
                filteredForecast.add(forecast);

                // Break the loop when we have 5 unique dates
                if (uniqueDates.size() == 5) {
                    break;
                }
            }
        }
        return filteredForecast;
    }

    private class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {

        private final Context context;
        private final ArrayList<DaysWeatherResponse.List> list;

        private WeatherForecastAdapter(Context context, ArrayList<DaysWeatherResponse.List> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public WeatherForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ForecastItemLayoutBinding binding = ForecastItemLayoutBinding.inflate(getLayoutInflater(), parent, false);
            return new WeatherForecastAdapter.ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull WeatherForecastAdapter.ViewHolder holder, int position) {

            DaysWeatherResponse.List forecast = list.get(position);

            holder.binding.day.setText(getDayOfWeek(forecast.getDt_txt().split(" ")[0]));
            double temp = forecast.getMain().getTemp() - 273.15;
            String tempreture = String.format("%.2f", temp);
            holder.binding.temp.setText("Tempreture: " + tempreture + "°C");
            holder.binding.cloud.setText(forecast.getWeather().get(0).getDescription());

        }

        // Helper method to convert date string to day of the week
        private String getDayOfWeek(String dateString) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                Date date = format.parse(dateString);
                // Format the date to show the day of the week (e.g., Monday, Tuesday)
                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
                return dayFormat.format(date);  // Returns the day name, like "Sunday", "Monday", etc.
            } catch (ParseException e) {
                e.printStackTrace();
                return ""; // Return empty if there's an error
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final ForecastItemLayoutBinding binding;

            public ViewHolder(ForecastItemLayoutBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }
}
