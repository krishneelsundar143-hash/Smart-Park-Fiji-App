package com.example.smartpark;

public class Booking
{
    private String fullName;
    private String selectedTime;
    private String selectedHours;
    private String vehicleNumber;
    private String paymentAmount;
    private String selectedPaymentOption;
    private String cardNumber;
    private String pin;

    private String selectedDate;

    private String selectedSlot;

    private String paymentStatus; // Add this field for payment status

    private String receiptNumber;

    private String currentTime; // Add currentTime field

    private String currentDate; // New field for current date


    public Booking()
    {
        // Default constructor required for Firebase
    }

    public Booking(String fullName, String selectedTime, String selectedHours, String vehicleNumber,
                   String paymentAmount, String selectedPaymentOption, String cardNumber, String pin)
    {
        this.fullName = fullName;
        this.selectedTime = selectedTime;
        this.selectedHours = selectedHours;
        this.vehicleNumber = vehicleNumber;
        this.paymentAmount = paymentAmount;
        this.selectedPaymentOption = selectedPaymentOption;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.selectedDate = selectedDate;
        this.selectedSlot = selectedSlot;

    }

    // Getter and setter for currentDate
    public String getCurrentDate()
    {
        return currentDate;
    }

    public void setCurrentDate(String currentDate)
    {
        this.currentDate = currentDate;
    }

    public String getCurrentTime()
    {
        return currentTime;
    }

    public void setCurrentTime(String currentTime)
    {
        this.currentTime = currentTime;
    }

    public String getReceiptNumber()
    {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber)
    {
        this.receiptNumber = receiptNumber;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public String getSelectedSlot()
    {
        return selectedSlot;
    }

    public void setSelectedSlot(String selectedSlot)
    {
        this.selectedSlot = selectedSlot;
    }

    public String getSelectedTime()
    {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime)
    {
        this.selectedTime = selectedTime;
    }

    public String getSelectedHours()
    {
        return selectedHours;
    }

    public void setSelectedHours(String selectedHours)
    {
        this.selectedHours = selectedHours;
    }

    public String getVehicleNumber()
    {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber)
    {
        this.vehicleNumber = vehicleNumber;
    }

    public String getPaymentAmount()
    {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount)
    {
        this.paymentAmount = paymentAmount;
    }

    public String getSelectedPaymentOption()
    {
        return selectedPaymentOption;
    }

    public void setSelectedPaymentOption(String selectedPaymentOption)
    {
        this.selectedPaymentOption = selectedPaymentOption;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public String getPin()
    {
        return pin;
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }

    public String getSelectedDate()
    {
        return selectedDate;
    }

    // Setter method for the selected date
    public void setSelectedDate(String selectedDate)
    {
        this.selectedDate = selectedDate;
    }

}