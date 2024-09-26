package com.example.farmerassist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

public class CropInsurance2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_insurance2);

        // Handle Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle Button Click to navigate to CropInsuranceActivity
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(v -> {
            Intent intent = new Intent(CropInsurance2Activity.this, CropInsuranceActivity.class);
            startActivity(intent);
        });

        // Handle ImageButton Click to finish the activity
        ImageButton imageButton9 = findViewById(R.id.imageButton9);
        imageButton9.setOnClickListener(v -> finish());
    }
}
