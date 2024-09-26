package com.example.farmerassist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.farmerassist.api.Request;
import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.databinding.ManageLivestock3Binding;
import com.example.farmerassist.responses.CommonResponse;
import com.example.farmerassist.utils.Static;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageLivestock3Activity extends AppCompatActivity {
    Context context;
    FragmentActivity activity;
    ManageLivestock3Binding binding;
    String vaccinated, birthDate, feedTime, color, gender, vaccinatedDate, name;
    Uri uri;

    private ActivityResultLauncher<PickVisualMediaRequest>  pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        this.uri = uri;
        if (this.uri != null) {
            File file = new File(FileUtils.getPath(context, uri));
            binding.textView32.setText(file.getName());
        } else {
            Toast.makeText(context, "No media selected", Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ManageLivestock3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        activity = this;
        context = this;

        // Set up click listener for imageButton4
        binding.imageButton4.setOnClickListener(v -> finish());

        // Set up click listener for editTextText2
        binding.button12.setOnClickListener(v -> finish());
        clickListener();
    }
    private void clickListener() {
         birthDate = binding.textView15.getText().toString();
         feedTime  = binding.textView18.getText().toString();
         color     = binding.textView20.getText().toString();
        vaccinatedDate     = binding.textView25.getText().toString();
        name = binding.textView29.getText().toString();

        binding.textView25.setOnClickListener(view -> Static.showDatePicker(context, binding.textView25) );
        binding.textView15.setOnClickListener(view -> Static.showDatePicker(context, binding.textView15) );
        binding.textView18.setOnClickListener(view -> Static.showTimePicker(context, binding.textView18) );
        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    binding.checkBox2.setChecked(false);
                    vaccinated = binding.checkBox.getText().toString();
                }
            }
        });
        binding.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    vaccinated = binding.checkBox2.getText().toString();
                    binding.checkBox.setChecked(false);
                }
            }
        });
        binding.checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    binding.checkBox4.setChecked(false);
                    gender    = binding.checkBox3.getText().toString();

                }
            }
        });
        binding.checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    binding.checkBox3.setChecked(false);
                    gender    = binding.checkBox4.getText().toString();

                }
            }
        });
        binding.textView32.setOnClickListener(view -> {
            pickMedia.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        });
        binding.button12.setOnClickListener(view -> {
            birthDate = binding.textView15.getText().toString();
            feedTime  = binding.textView18.getText().toString();
            color     = binding.textView20.getText().toString();
            vaccinatedDate     = binding.textView25.getText().toString();
            name = binding.textView29.getText().toString();
            if(name.isEmpty() || vaccinated.isEmpty() || birthDate.isEmpty() || feedTime.isEmpty() || color.isEmpty() || gender.isEmpty()
                    || vaccinatedDate.isEmpty() || uri==null) {
                Log.e("data", "name "+name +" "+ vaccinated+" "+birthDate+" "+feedTime+" "+color+" "+gender+" "+vaccinatedDate );
                Toast.makeText(context, "Fill All The Fields", Toast.LENGTH_SHORT).show();
            }else {
                Log.e("data", "name "+name +" "+ vaccinated+" "+birthDate+" "+feedTime+" "+color+" "+gender+" "+vaccinatedDate );
                apiCall(name, birthDate, color, feedTime, vaccinated, vaccinatedDate, uri, gender);
            }
        });


    }

    private void apiCall(String name, String birthDate, String color, String feedingTime, String vaccinated,
                         String vaccinatedDate, Uri image, String gender) {
        Call<CommonResponse> responseCall = RetroClient.getInterface().addLiveStokes(Static.getRequestBody(name), Static.getRequestBody(birthDate),
                Static.getRequestBody(color), Static.getRequestBody(feedingTime), Static.getRequestBody(vaccinated), Static.getRequestBody(vaccinatedDate),
                Static.getImagePart(context, image), Static.getRequestBody(gender));
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(@NonNull Call<CommonResponse> call, @NonNull Response<CommonResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSuccess()) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CommonResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }

}
