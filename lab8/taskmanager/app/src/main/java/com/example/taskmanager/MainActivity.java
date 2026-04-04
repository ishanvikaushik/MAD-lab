package com.example.taskmanager;

import android.content.Intent;
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

        loadData();

        addBtn.setOnClickListener(v ->
                startActivity(new Intent(this, AddTaskActivity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {
        Cursor cursor = db.getTasks();
        TaskAdapter adapter = new TaskAdapter(this, cursor);
        listView.setAdapter(adapter);
    }
}