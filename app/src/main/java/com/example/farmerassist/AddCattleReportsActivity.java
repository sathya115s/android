package com.example.farmerassist;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Toast;

import com.example.farmerassist.databinding.AddCattleReportsDcBinding;

public class AddCattleReportsActivity extends AppCompatActivity {

    AddCattleReportsDcBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddCattleReportsDcBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int cattleId = getIntent().getIntExtra("CATTLE_ID", 0);
        String docName = getIntent().getStringExtra("DOC_NAME");
        String report = getIntent().getStringExtra("REPORT");
        binding.editTextText13.setText(docName);
        binding.editTextText14.setText(report);
        binding.textView9.setText(""+cattleId);
        SharedPreferences sf = getSharedPreferences("LIVE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString("DOCTOR_NAME", docName);
        editor.putString("DOCTOR_REPORT", report);
        editor.apply();

        binding.imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.button13.setOnClickListener(view -> {
            String doctorName = binding.editTextText13.getText().toString();
            String doctorReport = binding.editTextText14.getText().toString();
            if(doctorName.isEmpty() || doctorReport.isEmpty()) {
                Toast.makeText(this, "Fill All The Fields", Toast.LENGTH_SHORT).show();
                return;
            }
            editor.putString("DOCTOR_NAME", doctorName);
            editor.putString("DOCTOR_REPORT", doctorReport);
            editor.apply();
            finish();
        });
    }
}
