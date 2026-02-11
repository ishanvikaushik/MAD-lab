package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnToast;
    ToggleButton toggleToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = findViewById(R.id.btnToast);
        toggleToast = findViewById(R.id.toggleToast);

        btnToast.setOnClickListener(v ->
                showCustomToast("Button Clicked!", R.drawable.button_img)
        );

        toggleToast.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showCustomToast("Toggle ON", R.drawable.toggle_img);
            } else {
                showCustomToast("Toggle OFF", R.drawable.toggle_img);
            }
        });
    }

    private void showCustomToast(String message, int imageRes) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        ImageView imageView = layout.findViewById(R.id.toastImage);
        TextView textView = layout.findViewById(R.id.toastText);

        imageView.setImageResource(imageRes);
        textView.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
