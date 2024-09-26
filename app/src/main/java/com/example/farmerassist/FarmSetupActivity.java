package com.example.farmerassist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerassist.api.Request;
import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.databinding.FarmSetupBinding;
import com.example.farmerassist.databinding.FarmsteupitemBinding;
import com.example.farmerassist.responses.CommonResponse;
import com.example.farmerassist.responses.FarmSetupResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmSetupActivity extends AppCompatActivity {

    FarmSetupBinding binding;
    ArrayList<FarmSetupModule> list;
    Context context;
    FragmentActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FarmSetupBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        try {
            context = this;
            activity = this;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Handle Button click to navigate to NewFarmProductActivity
        Button button = findViewById(R.id.button11);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(FarmSetupActivity.this, NewFarmProductActivity.class);
            startActivity(intent);
        });

        // Handle ImageButton click to finish the activity
        ImageButton imageButton = findViewById(R.id.imageButton9);
        imageButton.setOnClickListener(v -> finish());
        apiCall();
    }

    private void apiCall() {
        Call<FarmSetupResponse> responseCall = RetroClient.getInterface().getfarmsetup();
        responseCall.enqueue(new Callback<FarmSetupResponse>() {
            @Override
            public void onResponse(@NonNull Call<FarmSetupResponse> call, @NonNull Response<FarmSetupResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSuccess()) {
                        FarmSetupResponse farmSetupResponse = response.body();
                        List<Data> dataList = new ArrayList<>();
                        for(FarmSetupResponse.Data data : farmSetupResponse.getData()){
                            dataList.add(new Data(data.getId(), data.getName_of_product()));
                        }
                        binding.formViewRV.setLayoutManager(new LinearLayoutManager(context));
                        binding.formViewRV.setAdapter(new MyAdapter(dataList, activity));
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

    class Data {
        private int id;
        private String formName;

        public Data(int id, String formName) {
            this.id = id;
            this.formName = formName;
        }

        public int getId() {
            return id;
        }

        public String getFormName() {
            return formName;
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        List<Data> dataList;
        FragmentActivity activity;

        public MyAdapter(List<Data> dataList, FragmentActivity activity) {
            this.dataList = dataList;
            this.activity = activity;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            FarmsteupitemBinding binding1 = FarmsteupitemBinding.inflate(activity.getLayoutInflater(),parent,false);
            return new MyViewHolder(binding1);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.binding.textView52.setText(dataList.get(position).getFormName());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            FarmsteupitemBinding binding;

            public MyViewHolder(@NonNull FarmsteupitemBinding itemView) {
                super(itemView.getRoot());
                binding = itemView;

            }
        }
    }
}
