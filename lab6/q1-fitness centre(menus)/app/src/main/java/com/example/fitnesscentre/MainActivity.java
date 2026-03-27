package com.example.fitnesscentre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView contentText;
    ImageView trainerImage;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contentText = findViewById(R.id.contentText);
        trainerImage = findViewById(R.id.trainerImage);

        ImageButton home = findViewById(R.id.homeIcon);
        ImageButton about = findViewById(R.id.aboutIcon);
        ImageButton contact = findViewById(R.id.contactIcon);

        home.setOnClickListener(v -> {
            trainerImage.setVisibility(View.GONE);
            contentText.setText("Welcome to XYZ Fitness Center!\nTransform Your Body.");
        });

        about.setOnClickListener(v -> {
            trainerImage.setVisibility(View.GONE);
            contentText.setText("About Us:\nWe provide expert trainers and modern equipment.");
        });

        contact.setOnClickListener(v -> {
            trainerImage.setVisibility(View.GONE);
            contentText.setText("Contact Us:\nPhone: +91 9876543210\nEmail: xyz@fitness.com");
        });

        // IMPORTANT: Attach menu click listener manually
        toolbar.setOnMenuItemClickListener(item -> {
            trainerImage.setVisibility(View.GONE);

            if (item.getItemId() == R.id.workout) {
                contentText.setText("Workout Plans:\n\n• Weight Loss\n• Cardio\n• Strength\n• Yoga");
                return true;
            }

            if (item.getItemId() == R.id.trainers) {
                contentText.setText("Trainers:\n\nJohn - Strength\nRiya - Yoga\nDavid - Cardio");
                trainerImage.setImageResource(R.drawable.trainer1);
                trainerImage.setVisibility(View.VISIBLE);
                return true;
            }

            if (item.getItemId() == R.id.membership) {
                contentText.setText("Membership:\n\nBasic ₹1000\nStandard ₹2000\nPremium ₹3500");
                return true;
            }

            return false;
        });
    }


    // PART A (Options Menu)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        trainerImage.setVisibility(View.GONE);

        if (item.getItemId() == R.id.workout) {
            contentText.setText(
                    "Workout Plans:\n\n" +
                            "• Weight Loss\n" +
                            "• Cardio Training\n" +
                            "• Strength Training\n" +
                            "• Yoga & Flexibility"
            );
            return true;
        }

        if (item.getItemId() == R.id.trainers) {
            contentText.setText(
                    "Trainers:\n\n" +
                            "John - Strength Specialist\n" +
                            "Riya - Yoga Expert\n" +
                            "David - Cardio Trainer"
            );
            trainerImage.setImageResource(R.drawable.trainer1);
            trainerImage.setVisibility(View.VISIBLE);
            return true;
        }

        if (item.getItemId() == R.id.membership) {
            contentText.setText(
                    "Membership Packages:\n\n" +
                            "Basic - ₹1000/month\n" +
                            "Standard - ₹2000/month\n" +
                            "Premium - ₹3500/month"
            );
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
