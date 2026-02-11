package com.example.travel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        Spinner spSource = findViewById(R.id.spSource);
        Spinner spDestination = findViewById(R.id.spDestination);
        DatePicker datePicker = findViewById(R.id.datePicker);
        ToggleButton toggleTrip = findViewById(R.id.toggleTrip);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnReset = findViewById(R.id.btnReset);

        btnSubmit.setOnClickListener(v -> {
            String source = spSource.getSelectedItem().toString();
            String destination = spDestination.getSelectedItem().toString();

            String date =
                    datePicker.getDayOfMonth() + "/" +
                            (datePicker.getMonth() + 1) + "/" +
                            datePicker.getYear();

            String trip = toggleTrip.isChecked() ? "Round Trip" : "One Way";

            Intent i = new Intent(this, DisplayActivity.class);
            i.putExtra("source", source);
            i.putExtra("destination", destination);
            i.putExtra("date", date);
            i.putExtra("trip", trip);
            startActivity(i);
        });

        btnReset.setOnClickListener(v -> {
            spSource.setSelection(0);
            spDestination.setSelection(0);
            toggleTrip.setChecked(false);

            Calendar c = Calendar.getInstance();
            datePicker.updateDate(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            );
        });
    }
}
