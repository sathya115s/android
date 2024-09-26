package com.example.farmerassist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlantingMaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planting_material);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle ImageButton29 click to start DateOfSowingActivity
        ImageButton imageButton29 = findViewById(R.id.imageButton29);
        imageButton29.setOnClickListener(v -> {
            Intent intent = new Intent(PlantingMaterialActivity.this, DateOfSowingActivity.class);
            startActivity(intent);
        });

        // Handle ImageButton30 click to start DateOfSowingActivity
        ImageButton imageButton30 = findViewById(R.id.imageButton30);
        imageButton30.setOnClickListener(v -> {
            Intent intent = new Intent(PlantingMaterialActivity.this, DateOfSowingActivity.class);
            startActivity(intent);
        });

        // Handle ImageButton9 click to finish the activity
        ImageButton imageButton9 = findViewById(R.id.imageButton9);
        imageButton9.setOnClickListener(v -> finish());
    }
}
