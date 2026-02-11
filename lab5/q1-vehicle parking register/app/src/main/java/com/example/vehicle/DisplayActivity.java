package com.example.vehicle;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.UUID;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_display);

        TextView tvDetails = findViewById(R.id.tvDetails);
        Button btnConfirm = findViewById(R.id.btnConfirm);
        Button btnEdit = findViewById(R.id.btnEdit);

        String type = getIntent().getStringExtra("type");
        String vehicleNo = getIntent().getStringExtra("vehicleNo");
        String rcNo = getIntent().getStringExtra("rcNo");

        tvDetails.setText(
                "Vehicle Type: " + type +
                        "\nVehicle Number: " + vehicleNo +
                        "\nRC Number: " + rcNo
        );

        btnConfirm.setOnClickListener(v -> {
            String serial = UUID.randomUUID().toString().substring(0, 8);
            Toast.makeText(
                    this,
                    "Parking Confirmed!\nSerial No: " + serial,
                    Toast.LENGTH_LONG
            ).show();
        });

        btnEdit.setOnClickListener(v -> finish());
    }
}
