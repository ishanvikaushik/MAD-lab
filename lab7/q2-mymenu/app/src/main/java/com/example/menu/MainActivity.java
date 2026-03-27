package com.example.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * MainActivity handles the main screen of the application.
 * It sets up the Action Bar menu and responds to menu item clicks.
 */
public class MainActivity extends AppCompatActivity {

    // ImageView component to display the selected images
    private ImageView displayImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Enabling Edge-to-Edge for a modern UI look
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initializing the ImageView from the layout
        displayImageView = findViewById(R.id.displayImageView);

        // Adjusting padding for system bars (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * This method inflates the menu resource file into the existing menu.
     * It adds the 'My Menu' item with its submenu to the Action Bar.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * This method is called whenever an item in the options menu is selected.
     * It identifies the clicked item and performs the corresponding action.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Check if Image -1 was clicked
        if (id == R.id.image_1) {
            // Set the first image (using a built-in Android icon for demo)
            displayImageView.setImageResource(android.R.drawable.ic_menu_camera);
            // Display a Toast message with the content
            Toast.makeText(this, "Image -1 Selected", Toast.LENGTH_SHORT).show();
            return true;
        } 
        // Check if Image -2 was clicked
        else if (id == R.id.image_2) {
            // Set the second image (using a built-in Android icon for demo)
            displayImageView.setImageResource(android.R.drawable.ic_menu_gallery);
            // Display a Toast message with the content
            Toast.makeText(this, "Image -2 Selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
