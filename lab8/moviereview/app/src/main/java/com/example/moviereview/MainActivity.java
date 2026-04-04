package com.example.moviereview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button addBtn;
    TextView tName, tYear, tRating;
    DBHelper db;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.addBtn);

        tName = findViewById(R.id.tName);
        tYear = findViewById(R.id.tYear);
        tRating = findViewById(R.id.tRating);

        db = new DBHelper(this);

        loadData();

        addBtn.setOnClickListener(v ->
                startActivity(new Intent(this, AddMovieActivity.class))
        );

        listView.setOnItemClickListener((parent, view, pos, id) -> {
            cursor.moveToPosition(pos);

            String name = cursor.getString(1);
            String year = cursor.getString(2);
            int rating = cursor.getInt(3);

            tName.setText(name);
            tYear.setText(year);
            tRating.setText(String.valueOf(rating));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    void loadData() {
        cursor = db.getMovies();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{"name"},
                new int[]{android.R.id.text1},
                0
        );

        listView.setAdapter(adapter);
    }
}