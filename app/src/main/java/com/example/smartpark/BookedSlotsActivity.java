package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class BookedSlotsActivity extends AppCompatActivity
{
    private ListView listView;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    private Toolbar mToolbar;

    private EditText editTextSearch;
    private Button buttonSearch;

    private ArrayAdapter<String> arrayAdapter;
    private List<String> bookedSlotsList = new ArrayList<>();
    private List<String> filteredSlotsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_slots);

        mToolbar = (Toolbar) findViewById(R.id.booked_slots_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Booked Slots");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("bookings");

        listView = findViewById(R.id.listView);
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);

        // Create an ArrayList to store booked slots and dates
        //final ArrayList<String> bookedSlotsList = new ArrayList<>();

        // Create an ArrayAdapter to display the data in the ListView
        //final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookedSlotsList);

        // Create an ArrayAdapter to display the data in the ListView
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredSlotsList);
        //listView.setAdapter(arrayAdapter);

        listView.setAdapter(arrayAdapter);

        // Retrieve booked slots data from Firebase
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                bookedSlotsList.clear(); // Clear the list before adding data

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    for (DataSnapshot bookingSnapshot : snapshot.getChildren())
                    {
                        Booking booking = bookingSnapshot.getValue(Booking.class);

                        if (booking != null)
                        {
                            // Build a string with the slot and date information
                            // Build a string with the slot, date, time, and hours information
                            String slotInfo = "Date: " + booking.getSelectedDate() + "\n" +
                                    "Slot: " + booking.getSelectedSlot() + "\n" +
                                    "Time: " + booking.getSelectedTime() + "\n" +
                                    "Hours: " + booking.getSelectedHours();
                            bookedSlotsList.add(slotInfo);
                        }
                    }
                }

                filterSlots(""); // Initially, show all slots
                //arrayAdapter.notifyDataSetChanged(); // Notify the adapter of data changes
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                // Handle database read error if necessary
            }
        });

        // Set up the search button click listener
        buttonSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String searchText = editTextSearch.getText().toString().trim();
                filterSlots(searchText);
            }
        });

        // Add a text change listener to the search EditText for real-time filtering
        editTextSearch.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                String searchText = editable.toString().trim();
                filterSlots(searchText);
            }
        });
    }

    // Function to filter slots based on search text
    private void filterSlots(String searchText)
    {
        filteredSlotsList.clear();
        for (String slot : bookedSlotsList)
        {
            if (searchText.isEmpty() || slot.toLowerCase().contains(searchText.toLowerCase()))
            {
                filteredSlotsList.add(slot);
            }
        }
        arrayAdapter.notifyDataSetChanged(); // Notify the adapter of data changes


    }


}