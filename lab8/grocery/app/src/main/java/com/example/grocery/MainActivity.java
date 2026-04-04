package com.example.grocery;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button addBtn;
    ListView listView;
    TextView total;

    DBHelper db;

    String[] items = {"Rice", "Milk", "Bread", "Eggs", "Apple"};
    int[] prices = {50, 30, 40, 60, 80};

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        addBtn = findViewById(R.id.addBtn);
        listView = findViewById(R.id.listView);
        total = findViewById(R.id.total);

        db = new DBHelper(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                items
        );
        spinner.setAdapter(adapter);

        loadData();

        addBtn.setOnClickListener(v -> {

            int pos = spinner.getSelectedItemPosition();
            String name = items[pos];
            int cost = prices[pos];

            db.insertItem(name, cost);

            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();

            loadData();
        });
    }

    void loadData() {
        Cursor cursor = db.getItems();
        listView.setAdapter(new ItemAdapter(this, cursor));

        int totalCost = db.getTotalCost();
        total.setText("Total: ₹" + totalCost);
    }
}