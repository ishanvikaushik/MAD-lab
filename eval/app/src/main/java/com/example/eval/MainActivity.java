package com.example.eval;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btnActivity1);
        Button btn2 = findViewById(R.id.btnActivity2);

        btn1.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            i.putExtra("activity", "Activity 1");
            startActivity(i);
        });

        btn2.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            i.putExtra("activity", "Activity 2");
            startActivity(i);
        });
    }
}
