package com.example.farmerassist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ViewPager2 viewPager2 = findViewById(R.id.homepage_pager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Create and set up the adapter
        FragmentStateAdapter adapter = new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return 3;
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new ProfileFragment();
                    case 2:
                        return new FinancesFragment();
                    default:
                        throw new IllegalArgumentException("Invalid position: " + position);
                }
            }
        };

        viewPager2.setAdapter(adapter);

        // Attach the TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                    tab.setIcon(R.drawable.home);
                    break;
                case 1:
                    tab.setText("Profile");
                    tab.setIcon(R.drawable.profile);
                    break;
                case 2:
                    tab.setText("AgriPractices");
                    tab.setIcon(R.drawable.finances);
                    break;
            }
        }).attach();
    }
}
