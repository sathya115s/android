package com.example.farmerassist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.farmerassist.api.Request;
import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.databinding.LivestockDetailsBinding;
import com.example.farmerassist.responses.CommonResponse;
import com.example.farmerassist.responses.EditLiveStocksResponse;
import com.example.farmerassist.utils.Static;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LivestockDetailsActivity extends AppCompatActivity {

    Context context ;
    FragmentActivity activity;
    LivestockDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = LivestockDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
        activity = this;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        int cattleId = getIntent().getIntExtra("CATTLE_ID", 0);

        binding.button12.setOnClickListener(v -> {
            Intent intent = new Intent(this, ManageLivestock4Activity.class);
            intent.putExtra("CATTLE_ID", cattleId);
            startActivity(intent);
        });


        binding.button4.setOnClickListener(view -> finish());
        apiCall(cattleId);
    }
    private void apiCall(int id) {
        Call<EditLiveStocksResponse> responseCall = RetroClient.getInterface().editLiveStakes(id);
        responseCall.enqueue(new Callback<EditLiveStocksResponse>() {
            @Override
            public void onResponse(@NonNull Call<EditLiveStocksResponse> call, @NonNull Response<EditLiveStocksResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().isSuccess()) {
                        EditLiveStocksResponse.Data data = response.body().getData();
                        Static.setGlide(activity, data.getImage(), binding.imageView3 );
                        binding.textView11.setText(data.getName());
                        binding.textView9.setText(""+data.getId());
                        binding.textView15.setText(data.getBirthdate());
                        binding.textView18.setText(data.getFeeding_time());
                        binding.textView20.setText(data.getGender());
                        binding.textView25.setText(""+data.getVaccinated_date());
                        binding.editTextText2.setOnClickListener(v -> {
                            Intent intent = new Intent(context, ViewCattleReportsActivity.class);
                            intent.putExtra("CATTLE_ID", data.getId());
                            intent.putExtra("DOC_NAME", ""+data.getDoctor_name());
                            intent.putExtra("REPORT", ""+data.getPrescription());
                            startActivity(intent);
                        });
                        if(data.getVaccinated().equalsIgnoreCase("yes")){
                            binding.checkBox.setChecked(true);
                            binding.checkBox2.setChecked(false);
                        }else{
                            binding.checkBox2.setChecked(true);
                            binding.checkBox.setChecked(false);
                        }
                        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    binding.checkBox2.setChecked(false);
                                }
                            }
                        });
                        binding.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    binding.checkBox.setChecked(false);
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
}