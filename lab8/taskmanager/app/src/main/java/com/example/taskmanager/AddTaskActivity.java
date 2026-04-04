package com.example.taskmanager;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    EditText name, date;
    Spinner priority;
    Button save;
    DBHelper db;

    int taskId = -1;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_add_task);

        name = findViewById(R.id.taskName);
        date = findViewById(R.id.taskDate);
        priority = findViewById(R.id.prioritySpinner);
        save = findViewById(R.id.saveBtn);

        db = new DBHelper(this);

        String[] levels = {"High", "Medium", "Low"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                levels
        );
        priority.setAdapter(adapter);

        // CHECK IF EDIT MODE
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getInt("id");
            name.setText(extras.getString("name"));
            date.setText(extras.getString("date"));

            String pr = extras.getString("priority");

            for (int i = 0; i < levels.length; i++) {
                if (levels[i].equals(pr)) {
                    priority.setSelection(i);
                }
            }
        }

        save.setOnClickListener(v -> {

            String n = name.getText().toString();
            String d = date.getText().toString();
            String p = priority.getSelectedItem().toString();

            if (taskId == -1) {
                db.insertTask(n, d, p);
            } else {
                db.updateTask(taskId, n, d, p);
            }

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}