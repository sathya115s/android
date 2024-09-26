package com.example.farmerassist;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.farmerassist.databinding.LivestockInsurance2Binding;
import com.example.farmerassist.utils.Static;

public class LivestockInsurance2Activity extends AppCompatActivity {
    LivestockInsurance2Binding binding;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LivestockInsurance2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Implementing imageButton9 to finish the current activity
        binding.imageButton9.setOnClickListener(v -> finish());
        binding.open.setOnClickListener(view -> {
            Static.openLink(context, "https://general.futuregenerali.in/rural-insurance/cattle-and-livestock-insurance#:~:text=Buy%20Cattle%20and%20Livestock%20Insurance%20Policy%20by%20Future");
        });
    }
}
