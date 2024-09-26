package com.example.farmerassist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.databinding.ActivityScheduledLayoutBinding;
import com.example.farmerassist.responses.CropsActivityResponse;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduledCropsActivity extends AppCompatActivity {

    Context context;
    FragmentActivity activity;
    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.scheduled_crops);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        context = this;
        activity = this;
        recyclerView = findViewById(R.id.activityRV);


        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        int year = intent.getIntExtra("year",0);
        int month = intent.getIntExtra("month",0);
        int day = intent.getIntExtra("day",0);
        String corn = intent.getStringExtra("crop");
        String solid = intent.getStringExtra("soilType");
        String planting = intent.getStringExtra("plantingType");
//        Toast.makeText(context,date1 , Toast.LENGTH_SHORT).show();
        apiCall(date, solid, planting, corn, day, month, year);

    }
    private void apiCall(String date, String solid, String planting, String corn, int day, int month, int year) {
        Call<CropsActivityResponse> responseCall = RetroClient.getInterface().getCropsActivity(corn, solid, planting, date);

        responseCall.enqueue(new Callback<CropsActivityResponse>() {
            @Override
            public void onResponse(@NonNull Call<CropsActivityResponse> call, @NonNull Response<CropsActivityResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSuccess()) {
//                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(new MyAdapter(response.body().getData(), activity, day, month, year));
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CropsActivityResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        List<CropsActivityResponse.Data> list;
        FragmentActivity activity;
        int day;
        int month;
        int year;

        public MyAdapter(List<CropsActivityResponse.Data> list, FragmentActivity activity, int day, int month, int year) {
            this.list = list;
            this.activity = activity;
            this.day = day;
            this.month = month;
            this.year = year;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ActivityScheduledLayoutBinding binding = ActivityScheduledLayoutBinding.inflate(activity.getLayoutInflater(), parent, false);
            return new MyViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.binding.textAnswer6.setText(list.get(position).getActivity());
            String endDate = list.get(position).getEnd_date();
            String num ="";
            for(int i=0;i<endDate.length();i++) {
                if(Character.isDigit(endDate.charAt(i))) {
                    num += endDate.charAt(i);
                }
            }

            int endDay = Integer.parseInt(num);
            LocalDate currentDate = LocalDate.of(year, month, day);
            String endDateText = currentDate.plusDays(endDay).getDayOfMonth() + "/" + currentDate.plusDays(endDay).getMonthValue() + "/" + currentDate.plusDays(endDay).getYear();
            if(position == 0) {
                String startDateText = day + "/" + month + "/" + year;
                holder.binding.textView63.setText(startDateText+" to "+ endDateText);
            } else {
                LocalDate secondCurrentDate = LocalDate.of(currentDate.plusDays(endDay).getYear(), currentDate.plusDays(endDay).getMonthValue(), currentDate.plusDays(endDay).getDayOfMonth());
                String secondEndDateText = secondCurrentDate.plusDays(endDay).getDayOfMonth() + "/" + secondCurrentDate.plusDays(endDay).getMonthValue() + "/" + secondCurrentDate.plusDays(endDay).getYear();
                holder.binding.textView63.setText(currentDate.getDayOfMonth()+"/"+currentDate.getMonthValue()+"/"+currentDate.getYear()+" to "+secondEndDateText);
            }
            this.year = currentDate.plusDays(endDay).getYear();
            this.month = currentDate.plusDays(endDay).getMonthValue();
            this.day = currentDate.plusDays(endDay).getDayOfMonth();

        }

        @Override
        public int getItemCount() {
            return (list==null)?0:list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ActivityScheduledLayoutBinding binding;

            public MyViewHolder(@NonNull ActivityScheduledLayoutBinding itemView) {
                super(itemView.getRoot());
                binding = itemView;
            }
        }
    }

}