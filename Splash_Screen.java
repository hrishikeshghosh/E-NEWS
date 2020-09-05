package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        SystemClock.sleep(3000);
        Intent mainIntent=new Intent(Splash_Screen.this,MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}