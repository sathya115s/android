package com.example.farmerassist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.farmerassist.databinding.ViewCattleReportsDcBinding;

public class ViewCattleReportsActivity extends AppCompatActivity {

    ViewCattleReportsDcBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ViewCattleReportsDcBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle ImageButton click to finish the activity
        binding.imageButton4.setOnClickListener(v -> finish());
        int cattleId = getIntent().getIntExtra("CATTLE_ID", 0);
        String docName = getIntent().getStringExtra("DOC_NAME");
        String report = getIntent().getStringExtra("REPORT");
        binding.editTextText13.setText(docName);
        binding.editTextText14.setText(report);
        binding.textView9.setText(""+cattleId);

    }
}
