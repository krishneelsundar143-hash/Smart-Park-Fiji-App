package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class ParkingVideoActivity extends AppCompatActivity
{
    private Toolbar mToolbar;

    private VideoView videoView;
    private Button playButton;
    private Button pauseButton;
    private Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_video);

        mToolbar = (Toolbar) findViewById(R.id.parking_vid_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Parking Video");

        VideoView videoView = findViewById(R.id.videoView);
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);

        // Set the path to your video file in the "res/raw" directory
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.rfidcarparkingsystem; // Replace "your_video" with the actual video file name

        // Set the video URI and initialize the MediaController
        videoView.setVideoURI(Uri.parse(videoPath));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // Start playing the video
        //videoView.start();

        // Play button click listener
        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                videoView.start(); // Start playing the video
            }
        });

        // Pause button click listener
        pauseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                videoView.pause(); // Pause the video
            }
        });

        // Stop button click listener
        stopButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                videoView.stopPlayback(); // Stop the video
            }
        });
    }
}