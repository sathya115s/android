package com.example.farmerassist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.farmerassist.api.Request;
import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.databinding.RegisteredCattlesRecyclerBinding;
import com.example.farmerassist.responses.CommonResponse;
import com.example.farmerassist.responses.GetCattleResponse;
import com.example.farmerassist.utils.Static;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageLivestock2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_livestock_2);
        context = this;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.cattleRV);
        apiCall();

        // Set up click listener for button5
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(v -> {
            Intent intent = new Intent(this, ManageLivestock3Activity.class);
            startActivity(intent);
        });

        // Set up click listener for imageButton4
        ImageButton imageButton9 = findViewById(R.id.imageButton4);
        imageButton9.setOnClickListener(v -> finish());
    }
    private void apiCall() {
        Call<GetCattleResponse> responseCall = RetroClient.getInterface().getCattle();
        responseCall.enqueue(new Callback<GetCattleResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetCattleResponse> call, @NonNull Response<GetCattleResponse> response) {
                if(response.isSuccessful()) {

                    GetCattleResponse getCattleResponse = response.body();
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(new MyAdapter(getCattleResponse.getAnimals(), ManageLivestock2Activity.this));
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetCattleResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<GetCattleResponse.Animels> list;
        private FragmentActivity activity;

        public MyAdapter(List<GetCattleResponse.Animels> list, FragmentActivity activity) {
            this.list = list;
            this.activity = activity;
        }



        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RegisteredCattlesRecyclerBinding binding = RegisteredCattlesRecyclerBinding.inflate(activity.getLayoutInflater(), parent, false);
            return new MyViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                GetCattleResponse.Animels animels = list.get(position);
            RegisteredCattlesRecyclerBinding binding = holder.binding;
            Static.setGlide(activity, animels.getImage(), binding.imageView3);
            binding.textView9.setText(""+animels.getId());
            binding.textView11.setText(animels.getName());

            binding.view2.setOnClickListener(v -> {
                // Create an Intent to start LivestockDetailsActivity
                Intent intent = new Intent(activity, LivestockDetailsActivity.class);
                // Pass data to the intent
                intent.putExtra("CATTLE_ID", animels.getId());
                intent.putExtra("CATTLE_NAME", animels.getName());
                intent.putExtra("CATTLE_IMAGE", animels.getImage());

                // Start LivestockDetailsActivity
                activity.startActivity(intent);
            });

        }

        @Override
        public int getItemCount() {
            return (list != null) ? list.size() : 0;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            RegisteredCattlesRecyclerBinding binding;
            public MyViewHolder(@NonNull RegisteredCattlesRecyclerBinding itemView) {
                super(itemView.getRoot());
                binding = itemView;
            }
        }

    }
}
