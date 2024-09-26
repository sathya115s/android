package com.example.farmerassist;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.farmerassist.api.Request;
import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.responses.AddIncomeResponse;
import com.example.farmerassist.responses.CommonResponse;
import com.example.farmerassist.responses.FarmSetupResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewIncomeActivity extends AppCompatActivity {

    Context context;
    FragmentActivity activity;
    Spinner spinner;
    EditText amountET, dateET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_income);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        amountET  =  findViewById(R.id.editTextText3);
        dateET  =  findViewById(R.id.editTextText4);
        try {
            context = this;
            activity = this;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize the Spinner
         spinner = findViewById(R.id.spinner);
        apiCall();

        // Handle the Spinner item selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle spinner item selection if needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
            }
        });

        // Handle the Button click
        Button button = findViewById(R.id.button10);
        button.setOnClickListener(v ->{

             String sourceOfIncome = spinner.getSelectedItem().toString();
             String amount = amountET.getText().toString();
             String date = dateET.getText().toString();
             if(sourceOfIncome.isEmpty()|| amount.isEmpty()|| date.isEmpty()) {
                 Toast.makeText(context, "Fill All The Fields", Toast.LENGTH_SHORT).show();
                 return;
             }
             addIncomeApiCall(sourceOfIncome, amount, date);

        });

        // Handle the ImageButton click (e.g., finish the activity)
        ImageButton imageButton = findViewById(R.id.imageButton9);
        imageButton.setOnClickListener(v -> finish());

        // Initialize the DatePicker for EditText
        EditText editTextDate = findViewById(R.id.editTextText4);
        editTextDate.setFocusable(false); // Ensure it is not focusable
        editTextDate.setOnClickListener(v -> showDatePicker());
    }

    private void addIncomeApiCall(String soi, String ia, String date) {
        Request.AddIncomeRequest loginRequest = new Request.AddIncomeRequest(soi, "fib", ia, date);
        Call<AddIncomeResponse> responseCall = RetroClient.getInterface().addIncome(soi,ia,date);
        responseCall.enqueue(new Callback<AddIncomeResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddIncomeResponse> call, @NonNull Response<AddIncomeResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSuccess()) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AddIncomeResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }


    private void apiCall() {
        Call<FarmSetupResponse> responseCall = RetroClient.getInterface().getfarmsetup();
        responseCall.enqueue(new Callback<FarmSetupResponse>() {
            @Override
            public void onResponse(@NonNull Call<FarmSetupResponse> call, @NonNull Response<FarmSetupResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSuccess()) {
                        FarmSetupResponse farmSetupResponse = response.body();

                        if(response.body().getData().isEmpty()){
                            Toast.makeText(context, "Data Not Found", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ArrayList<String> list = new ArrayList<>();
                        for(FarmSetupResponse.Data data : farmSetupResponse.getData()){
                            list.add(data.getName_of_product());
                        }

                        ArrayAdapter<String> adapter =new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, list);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);

                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FarmSetupResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }


    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(selectedYear, selectedMonth, selectedDay);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    ((EditText) findViewById(R.id.editTextText4)).setText(selectedYear +"-"+(selectedMonth+1)+"-"+selectedDay);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
