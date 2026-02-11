package com.example.moviebooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        Spinner spMovie = findViewById(R.id.spMovie);
        Spinner spTheatre = findViewById(R.id.spTheatre);
        DatePicker datePicker = findViewById(R.id.datePicker);
        TimePicker timePicker = findViewById(R.id.timePicker);
        ToggleButton toggleTicket = findViewById(R.id.toggleTicket);
        Button btnBook = findViewById(R.id.btnBook);
        Button btnReset = findViewById(R.id.btnReset);

        timePicker.setIs24HourView(true);

        toggleTicket.setOnCheckedChangeListener((btn, isPremium) -> {
            if (isPremium) {
                btnBook.setEnabled(timePicker.getHour() >= 12);
            } else {
                btnBook.setEnabled(true);
            }
        });

        btnBook.setOnClickListener(v -> {

            if (spMovie.getSelectedItemPosition() == 0 ||
                    spTheatre.getSelectedItemPosition() == 0) {

                Toast.makeText(this, "Select movie and theatre", Toast.LENGTH_SHORT).show();
                return;
            }

            if (toggleTicket.isChecked() && timePicker.getHour() < 12) {
                Toast.makeText(this, "Premium tickets allowed after 12 PM", Toast.LENGTH_SHORT).show();
                return;
            }

            String date = datePicker.getDayOfMonth() + "/" +
                    (datePicker.getMonth() + 1) + "/" +
                    datePicker.getYear();

            String time = timePicker.getHour() + ":" +
                    String.format("%02d", timePicker.getMinute());

            int seats = new Random().nextInt(40) + 10;

            Intent i = new Intent(this, BookingDetailsActivity.class);
            i.putExtra("movie", spMovie.getSelectedItem().toString());
            i.putExtra("theatre", spTheatre.getSelectedItem().toString());
            i.putExtra("date", date);
            i.putExtra("time", time);
            i.putExtra("ticket", toggleTicket.isChecked() ? "Premium" : "Standard");
            i.putExtra("seats", seats);

            startActivity(i);
        });

        btnReset.setOnClickListener(v -> {
            spMovie.setSelection(0);
            spTheatre.setSelection(0);
            toggleTicket.setChecked(false);

            Calendar c = Calendar.getInstance();
            datePicker.updateDate(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            );
        });
    }
}
