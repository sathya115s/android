package com.example.farmerassist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;

public class FinancialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financial);

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find views and buttons by their IDs
        View view12 = findViewById(R.id.view12);
        ImageButton imageButton12 = findViewById(R.id.imageButton12);
        View view13 = findViewById(R.id.view13);
        ImageButton imageButton13 = findViewById(R.id.imageButton13);
        View view14 = findViewById(R.id.viewMarketPrice);
        ImageButton imageButton14 = findViewById(R.id.imageButtonMarketPrice);
        View view15 = findViewById(R.id.viewWeather);
        ImageButton imageButton15 = findViewById(R.id.imageButtonWeather);
        ImageButton imageButton9 = findViewById(R.id.imageButton9);

        // Set up click listeners
        view12.setOnClickListener(v -> startActivity(new Intent(FinancialActivity.this, NewIncomeActivity.class)));
        imageButton12.setOnClickListener(v -> startActivity(new Intent(FinancialActivity.this, NewIncomeActivity.class)));

        view13.setOnClickListener(v -> startActivity(new Intent(FinancialActivity.this, NewExpenseActivity.class)));
        imageButton13.setOnClickListener(v -> startActivity(new Intent(FinancialActivity.this, NewExpenseActivity.class)));

        view14.setOnClickListener(v -> startActivity(new Intent(FinancialActivity.this, FarmSetupActivity.class)));
        imageButton14.setOnClickListener(v -> startActivity(new Intent(FinancialActivity.this, FarmSetupActivity.class)));

        view15.setOnClickListener(v -> startActivity(new Intent(FinancialActivity.this, AnalyticsActivity.class)));
        imageButton15.setOnClickListener(v -> startActivity(new Intent(FinancialActivity.this, AnalyticsActivity.class)));

        imageButton9.setOnClickListener(v -> finish());  // Close the current activity
    }
}
