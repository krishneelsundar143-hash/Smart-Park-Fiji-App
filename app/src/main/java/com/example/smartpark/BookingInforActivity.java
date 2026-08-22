package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.VideoView;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class BookingInforActivity extends AppCompatActivity
{
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_infor);

        mToolbar = (Toolbar) findViewById(R.id.booking_infor_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Booking Information");

    }
}