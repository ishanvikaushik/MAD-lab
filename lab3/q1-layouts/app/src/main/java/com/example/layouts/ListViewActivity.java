package com.example.layouts;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_list_view);

        ListView listView = findViewById(R.id.listView);

        String[] items = {
                "American Samoa","El Salvador","Saint Helena",
                "Saint Kitts and Nevis","Saint Lucia",
                "Saint Vincent and the Grenadines",
                "Samoa","San Marino","Saudi Arabia"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        items);

        listView.setAdapter(adapter);
    }
}
