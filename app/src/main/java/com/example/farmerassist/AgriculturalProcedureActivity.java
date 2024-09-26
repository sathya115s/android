package com.example.farmerassist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AgriculturalProcedureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agricultural_procedure);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton imageButton9 = findViewById(R.id.imageButton9);
        imageButton9.setOnClickListener(v -> finish());

        View view5 = findViewById(R.id.view27);
        view5.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        TextView textView153 = findViewById(R.id.textView153);
        textView153.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        ImageButton imageButton22 = findViewById(R.id.imageButton22);
        imageButton22.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });

        View view6 = findViewById(R.id.view28);
        view6.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        TextView textView154 = findViewById(R.id.textView154);
        textView154.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        ImageButton imageButton23 = findViewById(R.id.imageButton23);
        imageButton23.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });

        View view7 = findViewById(R.id.view29);
        view7.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        TextView textView155 = findViewById(R.id.textView155);
        textView155.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        ImageButton imageButton24 = findViewById(R.id.imageButton24);
        imageButton24.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });

        View view8 = findViewById(R.id.view30);
        view8.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        TextView textView156 = findViewById(R.id.textView156);
        textView156.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        ImageButton imageButton25 = findViewById(R.id.imageButton25);
        imageButton25.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });

        View view9 = findViewById(R.id.view31);
        view9.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        TextView textView157 = findViewById(R.id.textView157);
        textView157.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        ImageButton imageButton26 = findViewById(R.id.imageButton26);
        imageButton26.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });

        View view10 = findViewById(R.id.view32);
        view10.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        TextView textView158 = findViewById(R.id.textView158);
        textView158.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        ImageButton imageButton27 = findViewById(R.id.imageButton27);
        imageButton27.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });

        View view111 = findViewById(R.id.view33);
        view111.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        TextView textView159 = findViewById(R.id.textView159);
        textView159.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
        ImageButton imageButton28 = findViewById(R.id.imageButton28);
        imageButton28.setOnClickListener(v -> {
            Intent intent = new Intent(AgriculturalProcedureActivity.this, StepsToFollowActivity.class);
            startActivity(intent);
        });
    }
}
