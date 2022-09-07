package com.hutech.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hutech.utils.Sharedpreference;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        final boolean login = Sharedpreference.getBoolen(SplashscreenActivity.this, "User_Login");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (login)
                {
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                }  else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        },1000);


    }
}