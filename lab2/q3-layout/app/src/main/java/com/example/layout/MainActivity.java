package com.example.layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLinear, btnRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLinear = findViewById(R.id.btnLinear);
        btnRelative = findViewById(R.id.btnRelative);

        btnLinear.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, LinearLayoutActivity.class);
            startActivity(i);
        });

        btnRelative.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, RelativeLayoutActivity.class);
            startActivity(i);
        });
    }
}
