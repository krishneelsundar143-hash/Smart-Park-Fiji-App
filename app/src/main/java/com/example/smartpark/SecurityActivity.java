package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

public class SecurityActivity extends AppCompatActivity
{
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);


        mToolbar = (Toolbar) findViewById(R.id.security_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Security");
    }

    public void onBackPressed()
    {
        // Start the MainActivity and clear the back stack
        Intent mainIntent = new Intent(SecurityActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish(); // Finish the current activity
    }
}