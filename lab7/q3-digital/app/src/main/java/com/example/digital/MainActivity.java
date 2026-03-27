package com.example.digital;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * MainActivity handles the logic for displaying, filtering, highlighting, and sorting
 * content related to Digital Transformation.
 */
public class MainActivity extends AppCompatActivity {

    private TextView textContent;
    private EditText inputKeyword;
    private List<String> originalContent;
    private List<String> currentDisplayContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting the content view to the layout created in activity_main.xml
        setContentView(R.layout.activity_main);

        // Setting up the toolbar to support the options menu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing views from the layout
        textContent = findViewById(R.id.textContent);
        inputKeyword = findViewById(R.id.inputKeyword);

        // Initializing the content describing Digital Transformation
        initializeContent();
        
        // Displaying initial content in the TextView
        updateTextView(currentDisplayContent);
    }

    /**
     * Populates the list with sentences describing Digital Transformation.
     * This acts as our data source.
     */
    private void initializeContent() {
        originalContent = new ArrayList<>();
        originalContent.add("Digital transformation is the process of using digital technologies to create new — or modify existing — business processes, culture, and customer experiences.");
        originalContent.add("It involves a reimagining of business in the digital age.");
        originalContent.add("Data analytics and cloud computing are key drivers of digital change.");
        originalContent.add("Customer-centricity is at the heart of most digital transformation strategies.");
        originalContent.add("Artificial Intelligence and Machine Learning accelerate digital transformation.");
        originalContent.add("Legacy systems often need to be modernized to support digital initiatives.");
        originalContent.add("Agile methodologies help organizations adapt faster to digital demands.");
        
        currentDisplayContent = new ArrayList<>(originalContent);
    }

    /**
     * Updates the TextView with the provided list of strings, adding spacing between sentences.
     */
    private void updateTextView(List<String> contentList) {
        StringBuilder sb = new StringBuilder();
        for (String line : contentList) {
            sb.append(line).append("\n\n");
        }
        textContent.setText(sb.toString());
    }

    /**
     * Inflates the menu options from the menu_main.xml resource file.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles clicks on the menu items and submenus.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Get the keyword from the EditText for processing
        String keyword = inputKeyword.getText().toString().toLowerCase().trim();

        int itemId = item.getItemId();
        if (itemId == R.id.submenu_search) {
            handleSearch(keyword);
            return true;
        } else if (itemId == R.id.submenu_highlight) {
            handleHighlight(keyword);
            return true;
        } else if (itemId == R.id.sort_alphabetical) {
            handleSortAlphabetical();
            return true;
        } else if (itemId == R.id.sort_relevance) {
            handleSortRelevance(keyword);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Filters the original content based on the user-provided keyword.
     * If empty, shows all content.
     */
    private void handleSearch(String keyword) {
        if (keyword.isEmpty()) {
            currentDisplayContent = new ArrayList<>(originalContent);
            Toast.makeText(this, "Showing all content", Toast.LENGTH_SHORT).show();
        } else {
            List<String> filteredList = new ArrayList<>();
            for (String text : originalContent) {
                if (text.toLowerCase().contains(keyword)) {
                    filteredList.add(text);
                }
            }
            currentDisplayContent = filteredList;
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No matches found", Toast.LENGTH_SHORT).show();
            }
        }
        updateTextView(currentDisplayContent);
    }

    /**
     * Highlights the user's keyword within the currently displayed content using a yellow background.
     */
    private void handleHighlight(String keyword) {
        if (keyword.isEmpty()) {
            updateTextView(currentDisplayContent);
            Toast.makeText(this, "Enter a keyword to highlight", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String line : currentDisplayContent) {
            sb.append(line).append("\n\n");
        }

        String fullText = sb.toString();
        SpannableString spannableString = new SpannableString(fullText);
        String lowerFullText = fullText.toLowerCase();
        
        int index = lowerFullText.indexOf(keyword);
        while (index >= 0) {
            // Apply yellow highlight to each occurrence of the keyword
            spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW), 
                                    index, index + keyword.length(), 
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            index = lowerFullText.indexOf(keyword, index + keyword.length());
        }
        textContent.setText(spannableString);
    }

    /**
     * Sorts the currently displayed content list alphabetically.
     */
    private void handleSortAlphabetical() {
        Collections.sort(currentDisplayContent);
        updateTextView(currentDisplayContent);
        Toast.makeText(this, "Sorted alphabetically", Toast.LENGTH_SHORT).show();
    }

    /**
     * Sorts the content based on how many times the keyword appears in each sentence.
     */
    private void handleSortRelevance(final String keyword) {
        if (keyword.isEmpty()) {
            Toast.makeText(this, "Enter a keyword for relevance sorting", Toast.LENGTH_SHORT).show();
            return;
        }

        Collections.sort(currentDisplayContent, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int count1 = countOccurrences(s1.toLowerCase(), keyword);
                int count2 = countOccurrences(s2.toLowerCase(), keyword);
                // Compare counts in descending order (highest relevance first)
                return Integer.compare(count2, count1);
            }
        });
        updateTextView(currentDisplayContent);
        Toast.makeText(this, "Sorted by relevance to: " + keyword, Toast.LENGTH_SHORT).show();
    }

    /**
     * Utility method to count how many times a keyword appears in a string.
     */
    private int countOccurrences(String text, String keyword) {
        int count = 0;
        int index = text.indexOf(keyword);
        while (index != -1) {
            count++;
            index = text.indexOf(keyword, index + keyword.length());
        }
        return count;
    }
}
