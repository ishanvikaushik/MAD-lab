# Digital Transformation Content Viewer - Project Documentation

This project is an Android application designed to display textual information about "Digital Transformation" and provide interactive features like searching, highlighting, and sorting.

## Project Structure and Files Created

### 1. `activity_main.xml`
**Path:** `app/src/main/res/layout/activity_main.xml`
- **How it was created:** I modified the default layout to include an `EditText` for user input and a `TextView` wrapped in a `ScrollView`.
- **Purpose:** 
    - The `EditText` (ID: `inputKeyword`) allows users to enter search terms or phrases they want to find or highlight.
    - The `ScrollView` ensures that if the text content is long, the user can scroll through it.
    - The `TextView` (ID: `textContent`) is the primary view for displaying the description of Digital Transformation.

### 2. `menu_main.xml`
**Path:** `app/src/main/res/menu/menu_main.xml`
- **How it was created:** Created a new menu resource directory and file to define the App Bar menu.
- **Purpose:** 
    - Defines a "Filter" menu item that acts as a container for submenus.
    - Includes "Search Keywords", "Highlight", and a nested "Sort" menu with "Alphabetically" and "By Relevance" options as per the requirements.

### 3. `MainActivity.java`
**Path:** `app/src/main/java/com/example/digital/MainActivity.java`
- **How it was created:** Overwrote the default Activity code to implement the core logic.
- **Purpose:**
    - **Initialization:** Sets up a list of strings containing the "Digital Transformation" description.
    - **Search Logic:** Filters the list to show only sentences containing the user's keyword.
    - **Highlight Logic:** Uses `SpannableString` and `BackgroundColorSpan` to visually highlight all occurrences of the input keyword in yellow within the text.
    - **Sorting Logic:** 
        - *Alphabetical:* Uses `Collections.sort()` on the list of strings.
        - *Relevance:* Uses a custom `Comparator` that counts the frequency of the keyword in each sentence and sorts them in descending order.

## Implementation Details

- **Text Manipulation:** I used `SpannableString` because it allows styling specific parts of a string without changing the actual text content.
- **Dynamic Updates:** The UI is updated dynamically by calling `updateTextView()` whenever the content is filtered or sorted.
- **User Feedback:** `Toast` messages are used to provide immediate feedback to the user (e.g., "No matches found" or "Sorted alphabetically").

## How to use the app:
1. Enter a word like "business" or "digital" in the input field at the top.
2. Open the menu (three dots or icon) and select **Filter**.
3. Choose **Search Keywords** to filter the text.
4. Choose **Highlight** to see the words turn yellow.
5. Choose **Sort -> Alphabetically** to reorder the sentences.
