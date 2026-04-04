package com.example.moviereview;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AddMovieActivity extends AppCompatActivity {

    EditText name, year;
    Spinner rating;
    Button save;
    DBHelper db;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_add_movie);

        name = findViewById(R.id.name);
        year = findViewById(R.id.year);
        rating = findViewById(R.id.rating);
        save = findViewById(R.id.save);

        db = new DBHelper(this);

        Integer[] ratings = {1,2,3,4,5};
        rating.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, ratings));

        save.setOnClickListener(v -> {
            db.insertMovie(
                    name.getText().toString(),
                    year.getText().toString(),
                    (Integer) rating.getSelectedItem()
            );

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}