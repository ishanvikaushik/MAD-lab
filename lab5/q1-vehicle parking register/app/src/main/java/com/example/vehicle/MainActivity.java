package com.example.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        Spinner spVehicle = findViewById(R.id.spVehicle);
        EditText etVehicleNo = findViewById(R.id.etVehicleNo);
        EditText etRcNo = findViewById(R.id.etRcNo);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        spVehicle.setOnTouchListener((v, event) -> {
            v.performClick();
            return false;
        });

        btnSubmit.setOnClickListener(v -> {
            Intent i = new Intent(this, DisplayActivity.class);
            i.putExtra("type", spVehicle.getSelectedItem().toString());
            i.putExtra("vehicleNo", etVehicleNo.getText().toString());
            i.putExtra("rcNo", etRcNo.getText().toString());
            startActivity(i);
        });
    }
}
