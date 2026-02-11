package com.example.layouts;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnList)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, ListViewActivity.class)));

        findViewById(R.id.btnGrid)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, GridViewActivity.class)));

        findViewById(R.id.btnTab)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, TabLayoutActivity.class)));

        findViewById(R.id.btnTable)
                .setOnClickListener(v ->
                        startActivity(new Intent(this, TableLayoutActivity.class)));
    }
}
