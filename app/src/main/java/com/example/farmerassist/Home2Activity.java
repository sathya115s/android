package com.example.farmerassist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Home2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2);

        setupImageButtonNavigation();
    }

    private void setupImageButtonNavigation() {
        try {
            // ImageButton for Select Crops
            ImageButton imageButton12 = findViewById(R.id.imageButton12);
            imageButton12.setOnClickListener(v -> openActivity(FarmScheduleActivityActivity.class));

            // ImageButton for Manage Livestock
            ImageButton imageButton13 = findViewById(R.id.imageButton13);
            imageButton13.setOnClickListener(v -> openActivity(ManageLivestock2Activity.class));

            // ImageButton for Market Price
            ImageButton imageButtonMarketPrice = findViewById(R.id.imageButtonMarketPrice);
            imageButtonMarketPrice.setOnClickListener(v -> openActivity(MarketPricesActivity.class));

            // ImageButton for Weather Forecast
            ImageButton imageButtonWeather = findViewById(R.id.imageButtonWeather);
            imageButtonWeather.setOnClickListener(v -> openActivity(WeatherForecastActivity.class));

            // ImageButton for Financial Activities
            ImageButton imageButtonFinancial = findViewById(R.id.imageButtonFinancial);
            imageButtonFinancial.setOnClickListener(v -> openActivity(FinancialActivity.class));

            // ImageButton for Insurance Activities
            ImageButton imageButtonInsurance = findViewById(R.id.imageButtonInsurance);
            imageButtonInsurance.setOnClickListener(v -> openActivity(InsuranceActivity.class));

            // ImageButton for Finishing Activity
            ImageButton imageButton9 = findViewById(R.id.imageButton9);
            imageButton9.setOnClickListener(v -> finish());

        } catch (NullPointerException e) {
            Log.e("Home2Activity", "Error setting up image button navigation", e);
        }
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
