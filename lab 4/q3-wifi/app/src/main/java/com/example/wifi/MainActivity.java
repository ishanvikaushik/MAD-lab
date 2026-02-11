package com.example.wifi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleMode;
    ImageView imgMode;
    Button btnChangeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleMode = findViewById(R.id.toggleMode);
        imgMode = findViewById(R.id.imgMode);
        btnChangeMode = findViewById(R.id.btnChangeMode);

        updateUI(toggleMode.isChecked());

        toggleMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateUI(isChecked);
        });

        btnChangeMode.setOnClickListener(v -> {
            toggleMode.setChecked(!toggleMode.isChecked());
        });
    }

    private void updateUI(boolean isWifi) {
        if (isWifi) {
            imgMode.setImageResource(R.drawable.ic_wifi);
            Toast.makeText(this, "Current Mode: Wi-Fi", Toast.LENGTH_SHORT).show();
        } else {
            imgMode.setImageResource(R.drawable.ic_mobile_data);
            Toast.makeText(this, "Current Mode: Mobile Data", Toast.LENGTH_SHORT).show();
        }
    }
}
