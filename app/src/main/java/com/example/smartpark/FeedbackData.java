package com.example.smartpark;

public class FeedbackData
{
        private float rating;
        private String comment;

        public FeedbackData()
        {
            super();// Default constructor required for Firebase
        }

        public FeedbackData(float rating, String comment)
        {
            this.rating = rating;
            this.comment = comment;
        }

        public float getRating()
        {
            return rating;
        }

        public void setRating(float rating)
        {
            this.rating = rating;
        }

        public String getComment()
        {
            return comment;
        }

        public void setComment(String comment)
        {
            this.comment = comment;
        }
}


