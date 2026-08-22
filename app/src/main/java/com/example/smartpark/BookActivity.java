package com.example.smartpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BookActivity extends AppCompatActivity
{
    private Toolbar mToolbar;

    private EditText editTextFullName, editTextVehicleNumber;
    private Spinner spinnerTime, spinnerHours;
    private Button buttonSubmit;
    private EditText cardNumberEditText, pinEditText;

    private DatabaseReference RootRef;
    private FirebaseAuth mAuth;

    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;

    private int selectedYear, selectedMonth, selectedDay;

    private String selectedLocation;

    private Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /**mToolbar = (Toolbar) findViewById(R.id.book_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Book Slot");**/


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("bookings"); // Replace "bookings" with your desired database reference

        // Add a reference to the "users" node in the Firebase Database
        RootRef = FirebaseDatabase.getInstance().getReference().child("Users");

        // ... previous code ...
        Intent intent = getIntent();
        final int slotIndex = intent.getIntExtra("slot_index", -1);

        // Receive the location data from the intent
        String location = getIntent().getStringExtra("location");


        if (slotIndex != -1)
        {
            // Display the location in the slotLabelTextView
            TextView slotLabelTextView = findViewById(R.id.slotLabelTextView);
            String slotLabel = location + "Slot " + (slotIndex + 1); // Slot numbers are 1-based
            slotLabelTextView.setText(slotLabel);

            // Retrieve the selected slot label from the TextView
            String selectedSlot = slotLabelTextView.getText().toString();


            final EditText fullNameEditText = findViewById(R.id.fullNameEditText);
            final Spinner timeSpinner = findViewById(R.id.timeSpinner);
            final Spinner hourSpinner = findViewById(R.id.hourSpinner);
            final EditText vehicleNumberEditText = findViewById(R.id.vehicleNumberEditText);
            final EditText paymentAmountEditText = findViewById(R.id.paymentAmountEditText);
            final Spinner paymentOptionsSpinner = findViewById(R.id.paymentOptionsSpinner);
            cardNumberEditText = findViewById(R.id.cardNumberEditText);
            pinEditText = findViewById(R.id.pinEditText);
            // Assuming you have a TextView for displaying the amount
            final TextView amountLabel = findViewById(R.id.amountLabel);


            final Button datePickerButton = findViewById(R.id.datePickerButton);
            final TextView selectedDateTextView = findViewById(R.id.selectedDateTextView); // Add a TextView to display selected date

            if (currentUser != null)
            {
                String uid = currentUser.getUid();

                // Fetch user data from Firebase
                RootRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        if (dataSnapshot.exists())
                        {
                            // Retrieve the user's full name from the database
                            String fullName = dataSnapshot.child("name").getValue(String.class);

                            // Set the retrieved full name in the EditText
                            fullNameEditText.setText(fullName);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                        // Handle any errors that may occur during data retrieval
                        Log.e("FirebaseError", "Error fetching user data: " + databaseError.getMessage());
                    }
                });
            }


            // Initialize selectedYear, selectedMonth, and selectedDay with the current date
            selectedYear = calendar.get(Calendar.YEAR);
            selectedMonth = calendar.get(Calendar.MONTH);
            selectedDay = calendar.get(Calendar.DAY_OF_MONTH);

            datePickerButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // Create a DatePickerDialog to allow the user to pick a date
                    DatePickerDialog datePickerDialog = new DatePickerDialog(BookActivity.this, new DatePickerDialog.OnDateSetListener()
                    {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                        {
                            // Store the selected date
                            selectedYear = year;
                            selectedMonth = monthOfYear;
                            selectedDay = dayOfMonth;

                            // Display the selected date in a TextView
                            String formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                            selectedDateTextView.setText(formattedDate);
                        }
                    }, selectedYear, selectedMonth, selectedDay);

                    // Show the DatePickerDialog
                    datePickerDialog.show();
                }
            });



            // Populate the time spinner with values (you can fetch these from Firebase)
            ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this, R.array.times_array, android.R.layout.simple_spinner_item);
            timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            timeSpinner.setAdapter(timeAdapter);



             // Populate the hour spinner with values (e.g., 1 hour, 2 hours, etc.)
            ArrayAdapter<CharSequence> hourAdapter = ArrayAdapter.createFromResource(this, R.array.hours_array, android.R.layout.simple_spinner_item);
            hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            hourSpinner.setAdapter(hourAdapter);


            hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
                {
                    // Calculate the amount based on the selected hours
                    String selectedHours = hourSpinner.getSelectedItem().toString();
                    int hours = Integer.parseInt(selectedHours); // Convert to an integer
                    int amount = hours; // Assuming 1 hour = 1 dollar

                    // Update the amount label
                    amountLabel.setText("$" + amount);

                    // Automatically set the payment amount in the EditText
                    paymentAmountEditText.setText(String.valueOf(amount));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView)
                {
                    // Do nothing if nothing is selected
                }
            });

            // Populate the payment options Spinner with values (e.g., MasterCard, Visa, etc.)
            ArrayAdapter<CharSequence> paymentOptionsAdapter = ArrayAdapter.createFromResource(this, R.array.payment_options_array, android.R.layout.simple_spinner_item);
            paymentOptionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            paymentOptionsSpinner.setAdapter(paymentOptionsAdapter);

            boolean isPaymentSuccessful = true; // Set this based on your payment logic

            Button submitButton = findViewById(R.id.submitButton);
            submitButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // Ensure the user is authenticated
                    if (currentUser != null)
                    {
                        // Retrieve booking information
                        String fullName = fullNameEditText.getText().toString();
                        String selectedTime = timeSpinner.getSelectedItem().toString();
                        //String selectedHours = hourSpinner.getSelectedItem().toString();
                        String vehicleNumber = vehicleNumberEditText.getText().toString();
                        //String paymentAmount = paymentAmountEditText.getText().toString();
                        String selectedPaymentOption = paymentOptionsSpinner.getSelectedItem().toString();
                        // Retrieve card number and PIN
                        String cardNumber = cardNumberEditText.getText().toString();
                        String pin = pinEditText.getText().toString();

                        // Calculate the amount based on the selected hours
                        String selectedHours = hourSpinner.getSelectedItem().toString();
                        int hours = Integer.parseInt(selectedHours); // Convert to an integer
                        int expectedAmount = hours; // Assuming 1 hour = 1 dollar

                        // Retrieve payment amount entered by the user
                        String paymentAmount = paymentAmountEditText.getText().toString();

                        // Check for empty fields
                        if (fullName.isEmpty() || vehicleNumber.isEmpty() || paymentAmount.isEmpty() || cardNumber.isEmpty() || pin.isEmpty())
                        {
                            // Show error message for empty fields
                            showErrorDialog("Please fill in all required fields.");
                            return; // Exit the onClick method
                        }

                        // Check if the payment amount is correct
                        int enteredAmount = Integer.parseInt(paymentAmount);
                        if (enteredAmount != expectedAmount)
                        {
                            // Show error message for incorrect amount
                            showErrorDialog("Payment amount does not match the selected hours.");
                            return; // Exit the onClick method
                        }

                        // Generate a unique receipt number (e.g., "R20231005123456")
                        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(new Date());
                        String receiptNumber = "R" + timestamp;

                        // Get the current time
                        String currentTime = getCurrentTime();

                        // Create a Booking object (you can create a custom class for this)
                        Booking booking = new Booking(fullName, selectedTime, selectedHours, vehicleNumber, paymentAmount, selectedPaymentOption, cardNumber, pin);

                        // Set the receipt number
                        booking.setReceiptNumber(receiptNumber);
                        // Set the selected slot in the booking object
                        booking.setSelectedSlot(selectedSlot);

                        // Set the selected slot in the booking object
                       //booking.setSelectedSlot(" " + (slotIndex + 1)); // Assuming slot numbers are 1-based

                        // Add the selected date to the Booking object
                        String selectedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                        booking.setSelectedDate(selectedDate); // Assuming you have a setSelectedDate method in the Booking class

                        String currentDateTime = getCurrentTime(); // Get current time
                        booking.setCurrentTime(currentDateTime); // Assuming you have a setCurrentTime method in the Booking class

                        // Capture the current date
                        String currentDate = getCurrentDate();
                        // Set the current date
                        booking.setCurrentDate(currentDate);
                        // Set the current time in the booking object
                        //booking.setCurrentTime(currentTime);

                        // Set the payment status based on whether payment is successful or not
                        if (isPaymentSuccessful)
                        {
                            booking.setPaymentStatus("Paid");
                        }
                        else
                        {
                            booking.setPaymentStatus("Not Paid");
                        }


                        // Push the booking data to Firebase with the user's UID as the reference
                        databaseReference.child(currentUser.getUid()).push().setValue(booking);

                        // Show a receipt to the user
                        showReceiptDialog(booking);

                        // Here, you can also handle success/failure and navigation to other screens
                    }
                    else
                    {
                        // User is not authenticated, handle accordingly
                    }
                }
            });


        }


    }

    private String getCurrentDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getCurrentTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void showErrorDialog(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showReceiptDialog(Booking booking)
    {
        // Create a custom AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.receipt_dialog, null);
        builder.setView(dialogView);

        // Set the receipt text
        TextView receiptTextView = dialogView.findViewById(R.id.receiptTextView);
        // Set the text color using a color resource (recommended)
        receiptTextView.setTextColor(getResources().getColor(R.color.lime_green));
        // Alternatively, you can set the text color using a specific color code
        //receiptTextView.setTextColor(Color.parseColor("#FF0d0d0d")); // Replace with your desired color code

        String receiptText = "                Booking Successful ✓\n\n\n" +
                "Booking Details:\n\n" +
                "Receipt No: " + booking.getReceiptNumber() + "\n" +
                "Booking Date: " + booking.getCurrentDate() + "\n" +
                "Booking Time: " + booking.getCurrentTime() + "\n\n" +
                "Full Name: " + booking.getFullName() + "\n" +
                "Slot: " + booking.getSelectedSlot() + "\n" +
                "Vehicle Number: " + booking.getVehicleNumber() + "\n" +
                "Date: " + booking.getSelectedDate() + "\n" +
                "Time: " + booking.getSelectedTime() + "\n" +
                "Hours: " + booking.getSelectedHours() + "\n" +
                "Payment Amount: " + booking.getPaymentAmount() + "\n" +
                "Payment Method: " + booking.getSelectedPaymentOption() + "\n\n\n" +
                "Payment Status: " + booking.getPaymentStatus() + "\n"; // Display payment status

        // Add the location button to the receipt
        //receiptText += "Location: " + booking.getSelectedSlot() + "\n"; // Add this line

        receiptTextView.setText(receiptText);

        // Add a close button
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss(); // Close the dialog
                // Start the MainActivity and clear the back stack
                Intent mainIntent = new Intent(BookActivity.this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                finish(); // Finish the current activity
            }
        });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /**public void onBackPressed()
    {
        // Start the MainActivity and clear the back stack
        Intent mainIntent = new Intent(BookActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish(); // Finish the current activity
    }**/

}