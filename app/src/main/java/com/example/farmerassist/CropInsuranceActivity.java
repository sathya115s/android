package com.example.farmerassist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

import com.example.farmerassist.databinding.CropInsuranceBinding;

public class CropInsuranceActivity extends AppCompatActivity {

    CropInsuranceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CropInsuranceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Handle Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle ImageButton Click to finish the activity
        binding.imageButton9.setOnClickListener(v -> finish());

        binding.open.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://agritech.tnau.ac.in/crop_insurance/crop_insurance_nias.html#crop"));
            startActivity(browserIntent);
        });
    }
}
