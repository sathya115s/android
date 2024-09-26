package com.example.farmerassist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsuranceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insurance);

        // Handling window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Implementing imageButton9 to finish the current activity
        ImageView imageButton9 = findViewById(R.id.imageButton9);
        imageButton9.setOnClickListener(v -> finish());

        // Implementing view18 to navigate to CropInsurance2Activity
        View view18 = findViewById(R.id.view18);
        view18.setOnClickListener(v -> {
            Intent intent = new Intent(InsuranceActivity.this, CropInsurance2Activity.class);
            startActivity(intent);
        });

        // Implementing view19 to navigate to LivestockInsuranceActivity
        View view19 = findViewById(R.id.view19);
        view19.setOnClickListener(v -> {
            Intent intent = new Intent(InsuranceActivity.this, LivestockInsuranceActivity.class);
            startActivity(intent);
        });
    }
}
