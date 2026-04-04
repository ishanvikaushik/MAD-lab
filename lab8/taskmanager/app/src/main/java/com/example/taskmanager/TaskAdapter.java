package com.example.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.*;
import android.widget.*;

public class TaskAdapter extends BaseAdapter {

    Context context;
    Cursor cursor;
    DBHelper db;

    public TaskAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        db = new DBHelper(context);
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.task_item, parent, false);
        }

        TextView name = view.findViewById(R.id.name);
        TextView date = view.findViewById(R.id.date);
        TextView priority = view.findViewById(R.id.priority);

        Button editBtn = view.findViewById(R.id.editBtn);
        Button deleteBtn = view.findViewById(R.id.deleteBtn);

        cursor.moveToPosition(i);

        int taskId = cursor.getInt(0);
        String taskName = cursor.getString(1);
        String taskDate = cursor.getString(2);
        String taskPriority = cursor.getString(3);

        name.setText(taskName);
        date.setText("Due: " + taskDate);
        priority.setText("Priority: " + taskPriority);

        // DELETE
        deleteBtn.setOnClickListener(v -> {
            db.deleteTask(taskId);
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            ((MainActivity) context).loadData();
        });

        // EDIT
        editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddTaskActivity.class);
            intent.putExtra("id", taskId);
            intent.putExtra("name", taskName);
            intent.putExtra("date", taskDate);
            intent.putExtra("priority", taskPriority);
            context.startActivity(intent);
        });

        return view;
    }
}