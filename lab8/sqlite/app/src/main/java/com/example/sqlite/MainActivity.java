package com.example.sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button addBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.addBtn);

        db = new DBHelper(this);

        // Insert sample data
        addBtn.setOnClickListener(v -> {
            db.insertData("Student " + System.currentTimeMillis()%100, 80);
            loadData();
        });

        loadData();
    }

    void loadData() {
        Cursor cursor = db.getData();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"name", "marks"},
                new int[]{android.R.id.text1, android.R.id.text2},
                0
        );

        listView.setAdapter(adapter);
    }
}