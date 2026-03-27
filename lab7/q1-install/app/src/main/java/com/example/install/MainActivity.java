package com.example.install;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity: The entry point of the application.
 * It fetches the list of installed apps and displays them in a ListView.
 * It also handles long-press actions via a Context Menu.
 */
public class MainActivity extends AppCompatActivity {

    // UI Component to display the list
    ListView appListView;

    // Data structures to hold application information
    List<ApplicationInfo> installedApps;
    ArrayList<String> appNames;

    // PackageManager is used to retrieve information about the application packages installed on the device
    PackageManager packageManager;

    // Stores the index of the item long-pressed in the ListView
    int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        appListView = findViewById(R.id.appListView);

        // Get an instance of the PackageManager
        packageManager = getPackageManager();

        // Retrieve a list of all installed applications on the device.
        installedApps = packageManager.getInstalledApplications(0);

        // Initialize the list that will hold app names for the UI
        appNames = new ArrayList<>();

        // Loop through all installed apps and get their human-readable labels (names)
        for (ApplicationInfo app : installedApps) {
            appNames.add(packageManager.getApplicationLabel(app).toString());
        }

        // ArrayAdapter bridges the gap between the Data (appNames) and the UI (ListView).
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        appNames);

        // Set the adapter to the ListView to populate it with names
        appListView.setAdapter(adapter);

        // This registers the ListView to show a Context Menu when an item is long-pressed.
        registerForContextMenu(appListView);

        // Listener to capture which item was long-pressed so we know which app to act upon.
        appListView.setOnItemLongClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            return false;
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ApplicationInfo app = installedApps.get(selectedPosition);
        String packageName = app.packageName;
        String appName = packageManager.getApplicationLabel(app).toString();

        int itemId = item.getItemId();

        if (itemId == R.id.menu_type) {
            boolean isSystemApp = (app.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
            String type = isSystemApp ? "System App" : "User Installed App";
            Toast.makeText(this, type, Toast.LENGTH_SHORT).show();
            return true;

        } else if (itemId == R.id.menu_open) {
            Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName);
            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                Toast.makeText(this, "Cannot open this app", Toast.LENGTH_SHORT).show();
            }
            return true;

        } else if (itemId == R.id.menu_uninstall) {
            // Requirement: Prompt for confirmation before uninstalling
            new AlertDialog.Builder(this)
                .setTitle("Uninstall App")
                .setMessage("Are you sure you want to uninstall " + appName + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE);
                        uninstallIntent.setData(Uri.parse("package:" + packageName));
                        startActivity(uninstallIntent);
                    }
                })
                .setNegativeButton("No", null)
                .show();
            return true;

        } else if (itemId == R.id.menu_details) {
            Intent detailsIntent = new Intent(this, AppDetailsActivity.class);
            detailsIntent.putExtra("packageName", packageName);
            startActivity(detailsIntent);
            return true;
        }

        return super.onContextItemSelected(item);
    }
}
