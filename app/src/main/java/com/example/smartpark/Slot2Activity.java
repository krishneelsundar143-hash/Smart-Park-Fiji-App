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

public class Slot2Activity extends AppCompatActivity
{

    private Toolbar mToolbar;

    private boolean[] isSlotBooked = new boolean[20]; // To track booked slots

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot2);

        mToolbar = (Toolbar) findViewById(R.id.slot2_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Rups Mall Nakasi Slots");

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference isSlotBookedRef = database.getReference("isSlotBooked"); // Replace with your Firebase reference


        GridLayout parkingGrid = findViewById(R.id.parkingGrid2);

        // Set initial state and click listeners for all slots
        for (int i = 0; i < 20; i++)
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
                            Intent slot2intent = new Intent(Slot2Activity.this, BookActivity.class);
                            slot2intent.putExtra("slot_index", slotIndex);
                            slot2intent.putExtra("location", getIntent().getStringExtra("location")); // Pass the location data
                            //slot2intent.putExtra("location", "Rups Mall Nakasi"); // Replace with the actual location
                            startActivity(slot2intent);
                        }
                    }
                });
            }
        }

    }

}