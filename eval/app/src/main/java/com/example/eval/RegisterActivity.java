package com.example.eval;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_register);

        String activityName = getIntent().getStringExtra("activity");

        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        CheckBox cbAge = findViewById(R.id.cbAge);
        CheckBox cbFood = findViewById(R.id.cbFood);
        CheckBox cbCert = findViewById(R.id.cbCert);
        ToggleButton toggle = findViewById(R.id.toggleNotify);
        Button btnConfirm = findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(v -> {

            String details =
                    "Name: " + etName.getText() +
                            "\nEmail: " + etEmail.getText() +
                            "\nAge: " + cbAge.isChecked() +
                            "\nFood: " + cbFood.isChecked() +
                            "\nCertification: " + cbCert.isChecked() +
                            "\nNotify: " + toggle.isChecked();

            Intent i = new Intent(this, DisplayActivity.class);
            i.putExtra("activity", activityName);
            i.putExtra("details", details);
            startActivity(i);
        });
    }
}
