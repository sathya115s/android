package com.example.farmerassist;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "home_param1";
    private static final String ARG_PARAM2 = "home_param2";

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Find views by their IDs
        Button button6 = view.findViewById(R.id.button6);
        View view7 = view.findViewById(R.id.view7);
        View view8 = view.findViewById(R.id.view8);
        View view9 = view.findViewById(R.id.view9);
        View view10 = view.findViewById(R.id.view10);

        // Set up click listeners
        button6.setOnClickListener(v -> startActivity(new Intent(getActivity(), Home2Activity.class)));
        view7.setOnClickListener(v -> startActivity(new Intent(getActivity(), FarmScheduleActivityActivity.class)));
        view8.setOnClickListener(v -> startActivity(new Intent(getActivity(), AnalyticsActivity.class)));
        view9.setOnClickListener(v -> startActivity(new Intent(getActivity(), WeatherForecastActivity.class)));
        view10.setOnClickListener(v -> startActivity(new Intent(getActivity(), MarketPricesActivity.class)));

        return view;
    }
}
