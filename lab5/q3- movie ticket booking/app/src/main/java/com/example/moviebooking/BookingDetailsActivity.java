package com.example.moviebooking;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class BookingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_booking_details);

        TextView tvDetails = findViewById(R.id.tvDetails);
        Button btnBack = findViewById(R.id.btnBack);

        tvDetails.setText(
                "Movie: " + getIntent().getStringExtra("movie") +
                        "\nTheatre: " + getIntent().getStringExtra("theatre") +
                        "\nDate: " + getIntent().getStringExtra("date") +
                        "\nTime: " + getIntent().getStringExtra("time") +
                        "\nTicket Type: " + getIntent().getStringExtra("ticket") +
                        "\nAvailable Seats: " + getIntent().getIntExtra("seats", 0)
        );

        btnBack.setOnClickListener(v -> finish());
    }
}
