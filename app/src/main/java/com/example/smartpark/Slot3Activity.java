package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Slot3Activity extends AppCompatActivity
{
    private Toolbar mToolbar;

    private boolean[] isSlotBooked = new boolean[16]; // To track booked slots

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot3);

        mToolbar = (Toolbar) findViewById(R.id.slot3_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Tapoos City Slots");

        //InitializeFields();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference isSlotBookedRef = database.getReference("isSlotBooked"); // Replace with your Firebase reference


        GridLayout parkingGrid = findViewById(R.id.parkingGrid3);

        // Set initial state and click listeners for all slots
        for (int i = 0; i < 16; i++)
        {
            Button slot = findViewById(getResources().getIdentifier("slot" + (i + 1), "id", getPackageName()));
            slot.setTag(i); // Set the slot index as a tag

            if (isSlotBooked[i])
            {
                slot.setBackgroundResource(R.drawable.booked_slot);
                slot.setEnabled(false); // Disable booked slots
            }
            else
            {
                slot.setBackgroundResource(R.drawable.available_slot);
                slot.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int slotIndex = (int) v.getTag();
                        if (!isSlotBooked[slotIndex])
                        {
                            // Slot is available, navigate to the BookActivity
                            Intent slot3intent = new Intent(Slot3Activity.this, BookActivity.class);
                            slot3intent.putExtra("slot_index", slotIndex);
                            slot3intent.putExtra("location", getIntent().getStringExtra("location")); // Pass the location data
                            startActivity(slot3intent);
                        }
                    }
                });
            }
        }

    }

}