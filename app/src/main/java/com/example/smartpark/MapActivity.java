package com.example.smartpark;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MapActivity extends AppCompatActivity
{
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mToolbar = (Toolbar) findViewById(R.id.map_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Google Map");

        Button openMapButton = findViewById(R.id.openMapButton);

        openMapButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Create an intent to open Google Maps with Fiji's location
                Uri gmmIntentUri = Uri.parse("geo:-17.7134,178.0650?q=Fiji");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(mapIntent);
                }
                else
                {
                    Toast.makeText(MapActivity.this, "Google Maps app is not installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}