package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity
{
    // Simulate whether the user is logged in or not.
    private boolean userLoggedIn = false;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        // Determine the target activity based on user login status.
        final Class<?> targetActivity = userLoggedIn ? MainActivity.class : LoginActivity.class;

        // Delay for a few seconds before transitioning to the target activity (splash screen effect).
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                FirebaseUser currentUser = mAuth.getCurrentUser();

                Class<?> targetActivity = (currentUser != null) ? MainActivity.class : LoginActivity.class;

                Intent intent = new Intent(SplashActivity.this, targetActivity);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.replaceExtras(new Bundle()); // Create an empty bundle

                startActivity(intent);
                finish();
            }
        }, 1000); // 2 seconds delay (adjust as needed)
    }

}