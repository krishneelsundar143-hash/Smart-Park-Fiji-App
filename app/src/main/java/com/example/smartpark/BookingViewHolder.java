package com.example.smartpark;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookingViewHolder extends RecyclerView.ViewHolder
{
    private TextView fullNameTextView, timeTextView, hoursTextView, vehicleNumberTextView;

    public BookingViewHolder(@NonNull View itemView)
    {
        super(itemView);
        fullNameTextView = itemView.findViewById(R.id.fullNameTextView);
        timeTextView = itemView.findViewById(R.id.timeTextView);
        hoursTextView = itemView.findViewById(R.id.hoursTextView);
        vehicleNumberTextView = itemView.findViewById(R.id.vehicleNumberTextView);
    }

    public void bind(Booking booking) {
        // Bind the booking data to the ViewHolder
        fullNameTextView.setText("Full Name: " + booking.getFullName());
        timeTextView.setText("Time: " + booking.getSelectedTime());
        hoursTextView.setText("Hours: " + booking.getSelectedHours());
        vehicleNumberTextView.setText("Vehicle Number: " + booking.getVehicleNumber());
    }
}

