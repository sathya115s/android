package com.example.farmerassist;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.databinding.AnalyticsBinding;
import com.example.farmerassist.responses.GetAnalyticsResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalyticsActivity extends AppCompatActivity {

    Context context;
    FragmentActivity activity;

    AnalyticsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AnalyticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            context = this;
            activity = this;

            binding.editTextText2.setOnClickListener(view -> {
                datePicker();
            });

        // Handle ImageButton click to finish the activity
        binding.imageButton9.setOnClickListener(v -> finish());

    }
    private void datePicker() {
        final Calendar c = Calendar.getInstance();

        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // on below line we are creating a variable for date picker dialog.
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                activity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our text view.
//                        binding.editTextText2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        binding.editTextText2.setText(year+"-"+(monthOfYear + 1)+"-"+dayOfMonth);
                        apiCall(binding.editTextText2.getText().toString());

                    }
                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
    }


    private void apiCall(String date) {
        Call<GetAnalyticsResponse> responseCall = RetroClient.getInterface().getanalytics(date);
        responseCall.enqueue(new Callback<GetAnalyticsResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetAnalyticsResponse> call, @NonNull Response<GetAnalyticsResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().getStatus().equalsIgnoreCase("success")) {
                        binding.textView64.setText("Rs : "+String.valueOf(response.body().getTotalIncome()));
                        binding.textView69.setText("Rs : "+String.valueOf(response.body().getTotalExpenses()));
                        binding.textView72.setText("Rs : "+String.valueOf(response.body().getNetAmount()));
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetAnalyticsResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }

}
