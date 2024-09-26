package com.example.farmerassist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.farmerassist.databinding.StepsToFollowBinding;

public class StepsToFollowActivity extends AppCompatActivity {

    StepsToFollowBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = StepsToFollowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int image = getIntent().getIntExtra("image",0);
        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        String head = getIntent().getStringExtra("head");
        binding.textView46.setText(head);

        binding.imageView25.setImageResource(image);
        binding.textView160.setText(title);
        binding.videoView.getSettings().setJavaScriptEnabled(true);
        binding.videoView.loadUrl(url);
        binding.videoView.getSettings().setDomStorageEnabled(true);
        binding.videoView.setWebViewClient(new loadWebView());

        binding.imageButton9.setOnClickListener(v -> finish());
    }
    private class loadWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
