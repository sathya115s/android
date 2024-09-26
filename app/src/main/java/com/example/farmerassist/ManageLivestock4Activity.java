package com.example.farmerassist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.databinding.Managelivestock4Binding;
import com.example.farmerassist.responses.CommonResponse;
import com.example.farmerassist.responses.EditLiveStocksResponse;
import com.example.farmerassist.utils.Static;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageLivestock4Activity extends AppCompatActivity {

    Managelivestock4Binding binding;
    Context context ;
    FragmentActivity activity;
    String name, gender, vaccinated;
    int cattleId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Managelivestock4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        activity = this;

        cattleId = getIntent().getIntExtra("CATTLE_ID", 0);
        getLiveStokeApiCall(cattleId);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up click listener for imageButton4
        binding.imageButton4.setOnClickListener(v -> finish());

        onClickListener();
    }

    private void onClickListener() {
        binding.button4.setOnClickListener(v -> {

            String dob = binding.textView15.getText().toString();
            String feeding_time = binding.textView18.getText().toString();
            String color = binding.textView20.getText().toString();
            String dateOfVaccinated = binding.textView25.getText().toString();

            if(name.isEmpty() || dob.isEmpty() ||
                    feeding_time.isEmpty() ||
                    color.isEmpty() ||
                    dateOfVaccinated.isEmpty()
                    || vaccinated.isEmpty() ||
                    gender.isEmpty()) {
                Toast.makeText(context, "Fill All The Fields", Toast.LENGTH_SHORT).show();
            }else {
                SharedPreferences sf = getSharedPreferences("LIVE", MODE_PRIVATE);
                String docName =  sf.getString("DOCTOR_NAME", null);
                String prescription =  sf.getString("DOCTOR_REPORT", null);
                if(docName == null  || prescription == null) {
                    Toast.makeText(context, "Add Prescription", Toast.LENGTH_SHORT).show();
                    return;
                }
                apiCall(cattleId, name, dob, feeding_time, gender, color, dateOfVaccinated, vaccinated, docName, prescription);
            }


        });
        binding.textView15.setOnClickListener(view -> Static.showDatePicker(activity, binding.textView15));
        binding.textView18.setOnClickListener(view -> Static.showTimePicker(activity, binding.textView18));
        binding.textView25.setOnClickListener(view -> Static.showDatePicker(activity, binding.textView25));
    }

    private void getLiveStokeApiCall(int id) {
        Call<EditLiveStocksResponse> responseCall = RetroClient.getInterface().editLiveStakes(id);
        responseCall.enqueue(new Callback<EditLiveStocksResponse>() {
            @Override
            public void onResponse(@NonNull Call<EditLiveStocksResponse> call, @NonNull Response<EditLiveStocksResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSuccess()) {
                        EditLiveStocksResponse.Data data = response.body().getData();
                        name = data.getName();
                        binding.textView29.setText(""+data.getId());
                        binding.textView15.setText(data.getBirthdate());
                        binding.textView18.setText(data.getFeeding_time());
                        binding.textView20.setText(data.getColor());
                        binding.textView25.setText(""+data.getVaccinated_date());
                        binding.editTextText2.setOnClickListener(v -> {
                            Intent intent = new Intent(context, AddCattleReportsActivity.class);
                            intent.putExtra("CATTLE_ID", cattleId);
                            intent.putExtra("DOC_NAME", ""+data.getDoctor_name());
                            intent.putExtra("REPORT", ""+data.getPrescription());
                            startActivity(intent);
                        });
                        if(data.getVaccinated().equalsIgnoreCase("yes")){
                            binding.checkBox.setChecked(true);
                            vaccinated =  binding.checkBox.getText().toString();
                            binding.checkBox2.setChecked(false);
                        } else {
                            vaccinated =  binding.checkBox2.getText().toString();
                            binding.checkBox2.setChecked(true);
                            binding.checkBox.setChecked(false);
                        }
                        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    binding.checkBox2.setChecked(false);
                                   vaccinated =  binding.checkBox.getText().toString();
                                }
                            }
                        });
                        binding.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    vaccinated =  binding.checkBox2.getText().toString();
                                    binding.checkBox.setChecked(false);
                                }
                            }
                        });
                        if(data.getVaccinated().equalsIgnoreCase("male")){
                            binding.checkBox3.setChecked(true);
                            gender =  binding.checkBox3.getText().toString();
                            binding.checkBox4.setChecked(false);
                        }else{
                            gender =  binding.checkBox4.getText().toString();
                            binding.checkBox4.setChecked(true);
                            binding.checkBox3.setChecked(false);
                        }
                        binding.checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    gender =  binding.checkBox3.getText().toString();
                                    binding.checkBox4.setChecked(false);
                                }
                            }
                        });
                        binding.checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    gender =  binding.checkBox4.getText().toString();
                                    binding.checkBox3.setChecked(false);
                                }
                            }
                        });
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<EditLiveStocksResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }


    private void apiCall(int id, String name, String birthdate, String feeding_time, String gender,
                         String color, String vaccinated_date, String vaccinated, String docName, String prescription) {
        Call<CommonResponse> responseCall = RetroClient.getInterface().updateLiveStakes(id, name, birthdate, color, feeding_time, vaccinated, vaccinated_date, gender, docName, prescription);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(@NonNull Call<CommonResponse> call, @NonNull Response<CommonResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSuccess()) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        SharedPreferences sf = getSharedPreferences("LIVE", MODE_PRIVATE);
                        sf.edit().clear().apply();
                        finish();
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "onResponse: " + response.message());
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