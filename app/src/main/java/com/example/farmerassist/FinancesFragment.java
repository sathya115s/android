package com.example.farmerassist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.farmerassist.databinding.FragmentFinancesBinding;

public class FinancesFragment extends Fragment {
    Context context;

    FragmentFinancesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFinancesBinding.inflate(inflater, container, false);
        context = requireContext();
        // Inflate the layout for this fragment
        binding.textView153.setOnClickListener(v -> {
            String url   = "https://www.youtube.com/watch?v=9_3TJ1kX2Rw";
            int image    = R.drawable.livestock_insurance;
            String title = "Soil Preparation";
            String head  = binding.textView153.getText().toString();
            openActivity(url, image, title, head);
        });

       binding.textView154.setOnClickListener(view -> {
           String url   = "https://www.youtube.com/watch?v=AiD6SOOBKZI";
           int image    = R.drawable.livestock_insurance;
           String title = "Soil Preparation";
           String head  = binding.textView154.getText().toString();
           openActivity(url, image, title, head);
       });


        return binding.getRoot();
    }

    private void openActivity(String url, int image, String title, String head) {
        Intent intent = new Intent(context, StepsToFollowActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("image", image);
        intent.putExtra("head", head);
        intent.putExtra("title", title);
        startActivity(intent);
    }


}
