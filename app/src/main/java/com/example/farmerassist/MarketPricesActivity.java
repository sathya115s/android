package com.example.farmerassist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerassist.databinding.FruitLayoutBinding;
import com.example.farmerassist.databinding.MarketPricesBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MarketPricesActivity extends AppCompatActivity {

    MarketPricesBinding binding;
    String[] city = {"Chennai", "Bangalore", "Hyderabad"};
    Context context;
    String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MarketPricesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up click listener for imageButton9
        binding.imageButton9.setOnClickListener(v -> finish());


        List<MarketPriceFruitData> fruitData = new ArrayList<>();
        fruitData.add(new MarketPriceFruitData("Apple", "1 kg", "₹120", "Chennai"));
        fruitData.add(new MarketPriceFruitData("Apple", "1 kg", "₹120", "Bangalore"));
        fruitData.add(new MarketPriceFruitData("Apple", "1 kg", "₹120", "Hyderabad"));
        List<MarketPriceVegetableData> vegetableData = new ArrayList<>();
        vegetableData.add(new MarketPriceVegetableData("Tomato", "1 kg", "₹20", "Chennai"));
        vegetableData.add(new MarketPriceVegetableData("Tomato", "1 kg", "₹20", "Bangalore"));
        vegetableData.add(new MarketPriceVegetableData("Tomato", "1 kg", "₹20", "Hyderabad"));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, city);
        binding.city.setAdapter(adapter);

        binding.city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityName = city[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Handle tab selection
                int id = tab.getPosition();
                binding.rv.setAdapter(null
                );
                binding.rv.setLayoutManager(new LinearLayoutManager(context));
                if(id == 0) {
                    Toast.makeText(context, "Fruit "+id, Toast.LENGTH_SHORT).show();
                    binding.rv.setAdapter(new FruitAdapter(fruitData, cityName));
                } else if(id == 1) {
                    Toast.makeText(context, "Vegitable "+id, Toast.LENGTH_SHORT).show();
                    binding.rv.setAdapter(new VegetableAdapter(vegetableData, cityName));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle tab unselection
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle tab reselection
            }
        });



    }
    public class MarketPriceFruitData {
        private String fruit;
        private String weight;
        private String price;
        private String city;

        public MarketPriceFruitData(String fruit, String weight, String price, String city) {
            this.fruit = fruit;
            this.weight = weight;
            this.price = price;
            this.city = city;
        }

        public String getFruit() {
            return fruit;
        }

        public String getWeight() {
            return weight;
        }

        public String getPrice() {
            return price;
        }

        public String getCity() {
            return city;
        }
    }
    public class MarketPriceVegetableData {
        private String vegetable;
        private String weight;
        private String price;
        private String city;

        public MarketPriceVegetableData(String vegetable, String weight, String price, String city) {
            this.vegetable = vegetable;
            this.weight = weight;
            this.price = price;
            this.city = city;
        }

        public String getVegetable() {
            return vegetable;
        }

        public String getWeight() {
            return weight;
        }

        public String getPrice() {
            return price;
        }

        public String getCity() {
            return city;
        }
    }

    public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
        private List<MarketPriceFruitData> data;
        private String city;

        public FruitAdapter(List<MarketPriceFruitData> data, String city) {
            this.data = data;
            this.city = city;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            FruitLayoutBinding binding1 = FruitLayoutBinding.inflate(getLayoutInflater(), parent, false);
            return new ViewHolder(binding1);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            MarketPriceFruitData list = data.get(position);
            FruitLayoutBinding bind = holder.binding;
            if(city.equalsIgnoreCase(list.getCity())) {
                bind.fruiteName.setText(list.getFruit());
                bind.price.setText(list.getPrice());
                bind.weight.setText(list.getWeight());
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            FruitLayoutBinding binding;
            public ViewHolder(@NonNull FruitLayoutBinding itemView) {
                super(itemView.getRoot());
                binding = itemView;
            }
        }
    }
    public class VegetableAdapter extends RecyclerView.Adapter<VegetableAdapter.ViewHolder> {
        private List<MarketPriceVegetableData> data;
        private String city;

        public VegetableAdapter(List<MarketPriceVegetableData> data, String city) {
            this.data = data;
            this.city = city;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            FruitLayoutBinding binding1 = FruitLayoutBinding.inflate(getLayoutInflater(), parent, false);
            return new ViewHolder(binding1);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            MarketPriceVegetableData list = data.get(position);
            FruitLayoutBinding bind = holder.binding;
            if(city.equalsIgnoreCase(list.getCity())) {
                bind.fruiteName.setText(list.getVegetable());
                bind.price.setText(list.getPrice());
                bind.weight.setText(list.getWeight());
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            FruitLayoutBinding binding;
            public ViewHolder(@NonNull FruitLayoutBinding itemView) {
                super(itemView.getRoot());
                binding = itemView;
            }
        }
    }

}
