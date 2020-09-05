package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class WebActivity extends AppCompatActivity {
    TextView tv_title,tv_source,tv_desc,tv_date;
    WebView webView;
    ImageView newsImage;
    ProgressBar loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        tv_title=findViewById(R.id.tvTITLE);
        tv_source=findViewById(R.id.tvSOURCE);
        tv_desc=findViewById(R.id.tvDESC);
        tv_date=findViewById(R.id.tvDATE);
        newsImage=findViewById(R.id.newsIMAGE);

        webView=findViewById(R.id.web_view);

        loader=findViewById(R.id.webViewLoader);
        loader.setVisibility(View.VISIBLE);

        String title=getIntent().getStringExtra("title");
        String source=getIntent().getStringExtra("source");
        String date_time=getIntent().getStringExtra("time");
        String imageUrl=getIntent().getStringExtra("imageUrl");
        String webUrl=getIntent().getStringExtra("url");
        String desc=getIntent().getStringExtra("desc");


        tv_title.setText(title);
        tv_source.setText(source);
        tv_desc.setText(desc);
        tv_date.setText(date_time);
        Glide.with(this).load(imageUrl).apply(new RequestOptions().placeholder(R.drawable.loading_168)).into(newsImage);


        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(webUrl);


        if (webView.isShown()){

            loader.setVisibility(View.GONE);

        }


    }
    }
