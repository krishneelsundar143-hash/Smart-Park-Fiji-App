package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity
{
    private Toolbar mToolbar;

    private RatingBar ratingBar;
    private EditText commentEditText;
    private Button submitFeedbackButton;

    private FirebaseAuth mAuth;
    private DatabaseReference feedbackRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        mToolbar = (Toolbar) findViewById(R.id.feedback_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Feedback");



        mAuth = FirebaseAuth.getInstance();
        String currentUserID = mAuth.getCurrentUser().getUid();
        feedbackRef = FirebaseDatabase.getInstance().getReference().child("Feedback").child(currentUserID);

        ratingBar = findViewById(R.id.ratingBar);
        commentEditText = findViewById(R.id.commentEditText);
        submitFeedbackButton = findViewById(R.id.submitFeedbackButton);

        submitFeedbackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                float rating = ratingBar.getRating();
                String comment = commentEditText.getText().toString();
                uploadFeedbackToFirebase(rating, comment);
            }
        });
    }

    private void uploadFeedbackToFirebase(float rating, String comment)
    {
        FeedbackData feedbackData = new FeedbackData(rating, comment);

        feedbackRef.setValue(feedbackData)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(FeedbackActivity.this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(FeedbackActivity.this, "Failed to submit feedback", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onBackPressed()
    {
        // Start the MainActivity and clear the back stack
        Intent mainIntent = new Intent(FeedbackActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish(); // Finish the current activity
    }

}