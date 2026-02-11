package com.example.calculator;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvResult = findViewById(R.id.tvResult);
        String result = getIntent().getStringExtra("result");
        tvResult.setText(result);
        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            finish(); // closes this activity and goes back
        });
    }
}
