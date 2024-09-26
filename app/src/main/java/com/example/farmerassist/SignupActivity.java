package com.example.farmerassist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.farmerassist.api.Request;
import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.databinding.SignupBinding;
import com.example.farmerassist.responses.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    SignupBinding binding;
    String name,email,password,confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.signUpBtn.setOnClickListener(view -> {
            if(validateData()){
                Request.RegisterRequest request = new Request.RegisterRequest(name,email,password);
                Call<CommonResponse> res = RetroClient.getInterface().register(request);

                res.enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                        if (response.isSuccessful()){
                            if(response.code() == 200){
                                Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, response.code() +response.message().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CommonResponse> call, Throwable t) {
                        Toast.makeText(SignupActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private boolean validateData() {

        boolean isValid = true;

        name = binding.editTextTextUsername.getText().toString();
        email = binding.editTextTextEmailAddress.getText().toString();
        password = binding.editTextTextCreatePassword.getText().toString();
        confirmPassword = binding.editTextTextConfirmPassword.getText().toString();

       if(name.isEmpty()){
           binding.editTextTextUsername.setError("Enter Name");
           isValid = false;
       }
       if(email.isEmpty()){
           binding.editTextTextEmailAddress.setError("Enter Email");
           isValid = false;
       }
       if(password.isEmpty()){
           binding.editTextTextCreatePassword.setError("Enter Password");
           isValid = false;
       }
       if(confirmPassword.isEmpty()){
           binding.editTextTextConfirmPassword.setError("Enter Confirm Password");
           isValid = false;
       }
       if(!password.equals(confirmPassword)){
           binding.editTextTextConfirmPassword.setError("Password does not match");
           isValid = false;
       }


        return isValid;
    }
}
