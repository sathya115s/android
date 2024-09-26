package com.example.farmerassist;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.farmerassist.api.Interface;
import com.example.farmerassist.api.Request;
import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.responses.CommonResponse;
import com.example.farmerassist.responses.GetAllCropsResponse;
import com.example.farmerassist.responses.GetPlanticTypeCorp;
import com.example.farmerassist.responses.GetSolideTypeCrop;
import com.example.farmerassist.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FarmScheduleActivityActivity extends AppCompatActivity {

    private EditText editTextText7;

    private Spinner spinner2, spinner3, spinner4;
    private Button buttonOpenScheduledCrops;


    Context context;
    FragmentActivity activity;
    int year, month, day;
    String date, crop, soilType, plantingType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farm_schedule_activity);

        context = this;
        activity = this;

        // Initialize Views
        editTextText7 = findViewById(R.id.editTextText7);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);

        buttonOpenScheduledCrops = findViewById(R.id.button9);



        // Set up onApplyWindowInsetsListener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getCropApi();

        // Set up DatePicker for editTextText7
        editTextText7.setOnClickListener(v -> showDatePickerDialog());

        // Load data into spinners
//        loadSpinnerData();

        // Set up button click listener to open ScheduledCropsActivity
        buttonOpenScheduledCrops.setOnClickListener(v -> openScheduledCropsActivity());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            // Format the selected date and set it to the EditText
//            String selectedDate = String.format("%d-%d-%d", dayOfMonth, month1 + 1, year1);
            String d = ""+dayOfMonth+"/"+(month1+1)+"/"+year1+"";
            this.day = dayOfMonth;
            this.month = month1+1;
            this.year = year1;
            editTextText7.setText(d);
            date = d;
        }, year, month, day);


        datePickerDialog.show();
    }

    private void openScheduledCropsActivity() {
        if(date==null || soilType==null || plantingType==null || crop==null) {
            Toast.makeText(context, "Fill All The Fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(FarmScheduleActivityActivity.this, ScheduledCropsActivity.class);
        intent.putExtra("date",date);
        intent.putExtra("year",year);
        intent.putExtra("day", day);
        intent.putExtra("month",month);
        intent.putExtra("crop",crop);
        intent.putExtra("soilType",soilType);
        intent.putExtra("plantingType",plantingType);
        startActivity(intent);
    }

    private void getPlantingTypeCropApi(String corn) {

        String url = Constants.BASE_URL + "/api/getplanting/" + corn +"/";

        Call<GetPlanticTypeCorp> responseCall = RetroClient.getInterface().getPlantingCorn(url);
        responseCall.enqueue(new Callback<GetPlanticTypeCorp>() {
            @Override
            public void onResponse(@NonNull Call<GetPlanticTypeCorp> call, @NonNull Response<GetPlanticTypeCorp> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSuccess()) {
                        List<String> getPlanting = response.body().getPlantingTypes();
                        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getPlanting);
                        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner4.setAdapter(adapter3);
                        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                                getPlantingTypeCropApi(getPlanting.get(i));
                                plantingType = getPlanting.get(i);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetPlanticTypeCorp> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }

    private void getSlideTypeApi(String corn) {
        String url = Constants.BASE_URL + "/api/getbycrop/" + corn + "/" ;

        Call<GetSolideTypeCrop> responseCall = RetroClient.getInterface().getSolingTypeCorn(url);
        responseCall.enqueue(new Callback<GetSolideTypeCrop>() {
            @Override
            public void onResponse(@NonNull Call<GetSolideTypeCrop> call, @NonNull Response<GetSolideTypeCrop> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSucess()) {
                        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, response.body().getSoilTypes());
                        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner3.setAdapter(adapter4);
                        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                                getPlantingTypeCropApi(response.body().getSoilTypes().get(i));
                                soilType = response.body().getSoilTypes().get(i);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetSolideTypeCrop> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }

    private void getCropApi() {
        Call<GetAllCropsResponse> responseCall = RetroClient.getInterface().getAllCrops();
        responseCall.enqueue(new Callback<GetAllCropsResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetAllCropsResponse> call, @NonNull Response<GetAllCropsResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSuccess()) {
                        List<GetAllCropsResponse.Data> res = response.body().getData();
                        List<String> getAllCrops = new ArrayList<>();
                        for(GetAllCropsResponse.Data data:res) {
                            getAllCrops.add(data.getCrop());
                        }
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getAllCrops);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adapter2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                getPlantingTypeCropApi(getAllCrops.get(i));
                                getSlideTypeApi(getAllCrops.get(i));
                                crop = getAllCrops.get(i);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetAllCropsResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }


}
