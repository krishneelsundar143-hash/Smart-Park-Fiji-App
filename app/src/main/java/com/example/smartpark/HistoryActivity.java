package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity
{

    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;
    private ListView historyListView;

    private Toolbar mToolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mToolbar = (Toolbar) findViewById(R.id.history_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Parking History");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("bookings"); // Replace "bookings" with your database reference

        historyListView = findViewById(R.id.historyListView);

        // Retrieve and display parking history for the current user
        displayParkingHistory();
    }

    private void displayParkingHistory()
    {
        if (currentUser != null)
        {
            String userUid = currentUser.getUid();

            // Query the database for parking history of the current user by their UID
            DatabaseReference userHistoryRef = databaseReference.child(userUid);

            userHistoryRef.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    // Create a list to store parking history items
                    List<String> parkingHistoryList = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        // Parse each booking entry and add it to the list
                        Booking booking = snapshot.getValue(Booking.class);
                        if (booking != null)
                        {
                            // Get the current date from the mobile device
                            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            //String currentDate = sdf.format(new Date());

                            // Create a history item with the date above the user's name
                            String historyItem = booking.getCurrentDate() + "  " + "\n" + booking.getCurrentTime() + "\n\n" +
                                    "Receipt No: " + booking.getReceiptNumber() + "\n" +
                                    "Name: " + booking.getFullName() + "\n" +
                                    "Slot: " + booking.getSelectedSlot() + "\n" +
                                    "Vehicle Number: " + booking.getVehicleNumber() + "\n" +
                                    "Date: " + booking.getSelectedDate() + "\n" +
                                    "Time: " + booking.getSelectedTime() + "\n" +
                                    "Hours: " + booking.getSelectedHours() + "\n" +
                                    "Payment Amount: " + "$" + booking.getPaymentAmount() + "\n" +
                                    "Payment Method: " + booking.getSelectedPaymentOption() + "\n" +
                                    "Payment Status: " + booking.getPaymentStatus() + "\n";
                            parkingHistoryList.add(historyItem);
                        }
                    }

                    // Sort the parkingHistoryList based on the getCurrentDate() using Java streams
                    parkingHistoryList.sort((item1, item2) ->
                    {
                        // Assuming getCurrentDate() returns a string in the format "yyyy-MM-dd"
                        String date1 = item1.split("\n")[0].trim();
                        String date2 = item2.split("\n")[0].trim();
                        return date2.compareTo(date1); // Sort in descending order (most recent first)
                    });

                    // Create an ArrayAdapter to display the parking history in a ListView
                    ArrayAdapter<String> historyAdapter = new ArrayAdapter<>(HistoryActivity.this, android.R.layout.simple_list_item_1, parkingHistoryList);
                    historyListView.setAdapter(historyAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError)
                {
                    // Handle database error
                }
            });
        }
    }

}