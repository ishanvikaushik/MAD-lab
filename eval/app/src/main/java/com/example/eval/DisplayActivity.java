package com.example.eval;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_display);

        TextView tv = findViewById(R.id.tvDisplay);
        Button btnBack = findViewById(R.id.btnBack);

        String activity = getIntent().getStringExtra("activity");
        String details = getIntent().getStringExtra("details");

        tv.setText("Details entered for " + activity + ":\n\n" + details);

        btnBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });
    }
}
