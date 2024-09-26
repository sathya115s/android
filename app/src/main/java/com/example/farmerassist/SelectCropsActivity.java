package com.example.farmerassist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class SelectCropsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_crops);

        // Set up the back button (imageButton9) to finish the activity
        ImageButton imageButton9 = findViewById(R.id.imageButton9);
        imageButton9.setOnClickListener(v -> finish());

        // Set up the other image buttons for crop selection
        ImageButton imageButton29 = findViewById(R.id.imageButton29);
        ImageButton imageButton30 = findViewById(R.id.imageButton30);
        ImageButton imageButton31 = findViewById(R.id.imageButton31);
        ImageButton imageButton32 = findViewById(R.id.imageButton32);
        ImageButton imageButton33 = findViewById(R.id.imageButton33);
        ImageButton imageButton34 = findViewById(R.id.imageButton34);

        // Add onClickListeners to these buttons to handle crop selection
        imageButton29.setOnClickListener(v -> openSelectSoilTypeActivity());
        imageButton30.setOnClickListener(v -> openSelectSoilTypeActivity());
        imageButton31.setOnClickListener(v -> openSelectSoilTypeActivity());
        imageButton32.setOnClickListener(v -> openSelectSoilTypeActivity());
        imageButton33.setOnClickListener(v -> openSelectSoilTypeActivity());
        imageButton34.setOnClickListener(v -> openSelectSoilTypeActivity());
    }

    private void openSelectSoilTypeActivity() {
        Intent intent = new Intent(this, SelectSoilTypeActivity.class);
        startActivity(intent);
    }
}
