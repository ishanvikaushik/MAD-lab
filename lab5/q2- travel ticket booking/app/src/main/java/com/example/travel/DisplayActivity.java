package com.example.travel;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_display);

        TextView tvDetails = findViewById(R.id.tvDetails);
        Button btnBack = findViewById(R.id.btnBack);

        tvDetails.setText(
                "Source: " + getIntent().getStringExtra("source") +
                        "\nDestination: " + getIntent().getStringExtra("destination") +
                        "\nTravel Date: " + getIntent().getStringExtra("date") +
                        "\nTrip Type: " + getIntent().getStringExtra("trip")
        );

        btnBack.setOnClickListener(v -> finish());
    }
}
