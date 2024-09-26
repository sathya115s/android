package com.example.farmerassist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.farmerassist.api.Request;
import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.databinding.ActivityLoginBinding;
import com.example.farmerassist.responses.LoginResponse;
import com.example.farmerassist.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    String email, password;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            context = this;

        } catch (Exception e){
            e.printStackTrace();
        }



        // Implementing textView4 to navigate to SignupActivity
        TextView textButton = findViewById(R.id.textView4);
        textButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });

        binding.button2.setOnClickListener(view -> {
            if(validateData()){

                Request.LoginRequest loginRequest = new Request.LoginRequest(email,password);


                Call<LoginResponse> res = RetroClient.getInterface().login(loginRequest);

                res.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.isSuccessful()){
                            if(response.body() != null){

                                Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                if(!response.body().isSuccess()){
                                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Constants.getSF(context).edit().putString(Constants.USER_NAME, response.body().getUser().getName()).apply();
                                Constants.getSF(context).edit().putString(Constants.USER_EMAIL, response.body().getUser().getEmail()).apply();
                                Constants.getSF(context).edit().putString(Constants.USER_TYPE, response.body().getUser().getUsertype()).apply();
                                Constants.getSF(context).edit().putInt(Constants.USER_ID, response.body().getUser().getId()).apply();
                                Constants.getSF(context).edit().putBoolean(Constants.USER_ID, true).apply();

                                Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("error", "onFailure: "+t.getMessage());
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    private boolean validateData() {
        boolean isValid = true;
        email = binding.editTextTextEmailAddress.getText().toString();
        password = binding.editTextTextPassword.getText().toString();

        if(email.isEmpty()){
            binding.editTextTextEmailAddress.setError("Email is required");
            isValid = false;
        }
        if(password.isEmpty()){
            binding.editTextTextPassword.setError("Password is required");
            isValid = false;
        }

        return isValid;
    }
}
