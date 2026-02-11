package com.example.layouts;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_grid_view);

        GridView gridView = findViewById(R.id.gridView);
        String[] items = {"A","B","C","D","E"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        items);

        gridView.setAdapter(adapter);
    }
}
