package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

// Splash screen activity
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Create a new handler to delay the start of the main activity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity after a delay of 2000 milliseconds (2 seconds)
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish(); // Finish the splash activity to prevent going back to it
            }
        }, 2000); // Delay duration in milliseconds
    }
}
