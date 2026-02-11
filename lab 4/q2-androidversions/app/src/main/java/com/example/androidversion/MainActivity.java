package com.example.androidversion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCupcake = findViewById(R.id.btnCupcake);
        Button btnKitkat = findViewById(R.id.btnKitkat);
        Button btnLollipop = findViewById(R.id.btnLollipop);
        Button btnOreo = findViewById(R.id.btnOreo);

        btnCupcake.setOnClickListener(v ->
                showCustomToast("Android Cupcake", R.drawable.ic_cupcake));

        btnKitkat.setOnClickListener(v ->
                showCustomToast("Android KitKat", R.drawable.ic_kitkat));

        btnLollipop.setOnClickListener(v ->
                showCustomToast("Android Lollipop", R.drawable.ic_lollipop));

        btnOreo.setOnClickListener(v ->
                showCustomToast("Android Oreo", R.drawable.ic_oreo));
    }

    private void showCustomToast(String text, int iconRes) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, null);

        ImageView icon = layout.findViewById(R.id.toastIcon);
        TextView message = layout.findViewById(R.id.toastText);

        icon.setImageResource(iconRes);
        message.setText(text);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
