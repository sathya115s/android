package com.example.farmerassist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectSoilTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_soil_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the image buttons for selecting planting material
        ImageButton imageButton29 = findViewById(R.id.imageButton29);
        ImageButton imageButton30 = findViewById(R.id.imageButton30);
        ImageButton imageButton31 = findViewById(R.id.imageButton31);
        ImageButton imageButton9 = findViewById(R.id.imageButton9);

        // Set click listeners for the image buttons
        imageButton29.setOnClickListener(v -> openPlantingMaterialActivity());
        imageButton30.setOnClickListener(v -> openPlantingMaterialActivity());
        imageButton31.setOnClickListener(v -> openPlantingMaterialActivity());

        // Set click listener for the back button
        imageButton9.setOnClickListener(v -> finish());
    }

    private void openPlantingMaterialActivity() {
        Intent intent = new Intent(this, PlantingMaterialActivity.class);
        startActivity(intent);
    }
}
