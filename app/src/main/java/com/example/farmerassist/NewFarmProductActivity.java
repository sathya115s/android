package com.example.farmerassist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.farmerassist.api.Request;
import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.databinding.NewFarmProductBinding;
import com.example.farmerassist.responses.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewFarmProductActivity extends AppCompatActivity {

    NewFarmProductBinding binding;
    String p_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = NewFarmProductBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.saveBtn.setOnClickListener(v->{
            if(validateData()){
                Request.AddproductRequest request = new Request.AddproductRequest(p_name);
                Call<CommonResponse> res = RetroClient.getInterface().addproduct(request);

                res.enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                        if(response.isSuccessful()){
                            CommonResponse commonResponse = response.body();
                            if(commonResponse.isSuccess()){
                                Toast.makeText(NewFarmProductActivity.this, commonResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(NewFarmProductActivity.this, commonResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(NewFarmProductActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CommonResponse> call, Throwable t) {
                        Toast.makeText(NewFarmProductActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        // Handle ImageButton click to finish the activity
        ImageButton imageButton = findViewById(R.id.imageButton9);
        imageButton.setOnClickListener(v -> finish());
    }

    private boolean validateData() {
        boolean isValid = true;

        p_name = binding.pName.getText().toString();

        if(p_name.isEmpty()){
            binding.pName.setError("Enter Product Name");
            isValid = false;
        }

        return isValid;
    }
}
