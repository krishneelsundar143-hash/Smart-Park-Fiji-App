package com.example.smartpark;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class AboutUsActivity extends AppCompatActivity
{
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        mToolbar = (Toolbar) findViewById(R.id.about_us_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("About Us");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}