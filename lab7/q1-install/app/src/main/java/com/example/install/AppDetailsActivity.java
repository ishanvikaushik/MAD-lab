package com.example.install;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

/**
 * AppDetailsActivity: This activity is responsible for displaying detailed information 
 * about a specific application, including its package name, version, size, and special permissions.
 */
public class AppDetailsActivity extends AppCompatActivity {

    // UI Component to display the concatenated details string
    TextView detailsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the layout for the details screen
        setContentView(R.layout.activity_app_details);

        // Link the TextView from XML to our Java code
        detailsText = findViewById(R.id.appDetailsText);

        // Retrieve the package name passed from MainActivity via the Intent
        String packageName = getIntent().getStringExtra("packageName");

        // Basic safety check: if for some reason packageName is null, stop and show error
        if (packageName == null) {
            detailsText.setText("Error: Package name not found.");
            return;
        }

        try {
            // Get an instance of the PackageManager to query app information
            PackageManager pm = getPackageManager();

            // Fetch PackageInfo which contains versioning and permissions data.
            // GET_PERMISSIONS flag ensures that requestedPermissions array is populated.
            PackageInfo info = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            
            // Fetch ApplicationInfo to get the app's size
            ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);

            // Use a StringBuilder for efficient string concatenation
            StringBuilder details = new StringBuilder();
            
            // 1. Basic Package Information (Requirement: version)
            details.append("--- App Information ---\n");
            details.append("Package Name: ").append(info.packageName).append("\n");
            details.append("Version Name: ").append(info.versionName).append("\n");
            details.append("Version Code: ").append(info.versionCode).append("\n");

            // 2. App Size Information (Requirement: size)
            // ApplicationInfo.sourceDir points to the APK file on disk
            File file = new File(appInfo.sourceDir);
            double sizeInMb = (double) file.length() / (1024 * 1024);
            details.append("App Size: ").append(String.format("%.2f", sizeInMb)).append(" MB\n\n");

            // 3. Special Permissions Check (Requirement: permissions)
            details.append("--- Special Permissions ---\n");

            // checkPermission() returns PERMISSION_GRANTED if the app currently has the permission.
            boolean hasCamera = pm.checkPermission(android.Manifest.permission.CAMERA, packageName)
                    == PackageManager.PERMISSION_GRANTED;
            
            boolean hasLocation = pm.checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, packageName)
                    == PackageManager.PERMISSION_GRANTED;

            details.append("Camera Access: ").append(hasCamera ? "GRANTED" : "Denied/Not Requested").append("\n");
            details.append("Location Access: ").append(hasLocation ? "GRANTED" : "Denied/Not Requested").append("\n\n");

            // 4. List of all requested permissions
            details.append("--- All Requested Permissions ---\n");
            if (info.requestedPermissions != null) {
                for (String permission : info.requestedPermissions) {
                    // Extract only the short name of the permission for readability
                    String shortName = permission.substring(permission.lastIndexOf('.') + 1);
                    details.append("• ").append(shortName).append("\n");
                }
            } else {
                details.append("No permissions requested.\n");
            }

            // Finally, update the UI with the constructed details string
            detailsText.setText(details.toString());

        } catch (PackageManager.NameNotFoundException e) {
            detailsText.setText("Unable to fetch details: App not found.");
        } catch (Exception e) {
            detailsText.setText("An error occurred while fetching details.");
        }
    }
}
