package com.example.foodorder;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    CheckBox cbPizza, cbBurger, cbPasta;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cbPizza = findViewById(R.id.cbPizza);
        cbBurger = findViewById(R.id.cbBurger);
        cbPasta = findViewById(R.id.cbPasta);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {
            StringBuilder orderDetails = new StringBuilder();
            int totalCost = 0;
            if (cbPizza.isChecked()) {
                orderDetails.append("Pizza - ₹150\n");
                totalCost += 150;
            }
            if (cbBurger.isChecked()) {
                orderDetails.append("Burger - ₹100\n");
                totalCost += 100;
            }
            if (cbPasta.isChecked()) {
                orderDetails.append("Pasta - ₹120\n");
                totalCost += 120;
            }
            Intent intent = new Intent(MainActivity.this, OrderSummaryActivity.class);
            intent.putExtra("order", orderDetails.toString());
            intent.putExtra("total", totalCost);
            startActivity(intent);
//why not set enabled for pizza too, why when I did was it not checking the box
            cbBurger.setEnabled(false);
            cbPasta.setEnabled(false);
            btnSubmit.setEnabled(false);
        });
    }
}

/*
✅ MOCK 1: ADD QUANTITY & UPDATE TOTAL (Food Ordering)
XML (add quantity input)
<EditText
    android:id="@+id/etQuantity"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:hint="Quantity"
    android:inputType="number"/>

Java
EditText etQuantity;
TextView tvTotal;

etQuantity = findViewById(R.id.etQuantity);
tvTotal = findViewById(R.id.tvTotal);

int price = 100; // example price

btnOrder.setOnClickListener(v -> {
    int qty = Integer.parseInt(etQuantity.getText().toString());
    int total = qty * price;
    tvTotal.setText("Total: ₹" + total);
});


✅ MOCK 2: ADD RATING FEATURE
XML
<RatingBar
    android:id="@+id/ratingBar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:numStars="5"
    android:stepSize="1.0"/>

Java
RatingBar ratingBar;

ratingBar = findViewById(R.id.ratingBar);

btnSubmit.setOnClickListener(v -> {
    float rating = ratingBar.getRating();
    Toast.makeText(this,
            "Rating: " + rating,
            Toast.LENGTH_SHORT).show();
});


✅ MOCK 3: TOGGLE → CHANGE BACKGROUND COLOR
Java
ToggleButton toggle;

toggle = findViewById(R.id.toggle);

toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
    if (isChecked) {
        getWindow().getDecorView().setBackgroundColor(Color.GREEN);
    } else {
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
    }
});


✅ MOCK 4: DIFFERENT IMAGES IN CUSTOM TOAST (ON / OFF)
if (toggle.isChecked()) {
    showCustomToast("Toggle ON", R.drawable.ic_on);
} else {
    showCustomToast("Toggle OFF", R.drawable.ic_off);
}

(Your showCustomToast() method stays SAME)

✅ MOCK 5: ADD ONE MORE ANDROID VERSION BUTTON
Java
public void showVersion(String name, int icon) {
    showCustomToast(name, icon);
}

// Usage
btnOreo.setOnClickListener(v ->
        showVersion("Android Oreo", R.drawable.ic_oreo));

💡 Examiner keyword: “code reusability”

✅ MOCK 6: DISABLE BUTTON BASED ON TOGGLE
toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
    btnChangeMode.setEnabled(isChecked);
});


✅ MOCK 7: TABLELAYOUT STYLE CHANGE
XML
<TextView
    android:text="Open"
    android:textStyle="bold"/>

<TextView
    android:text="Ctrl+O"
    android:gravity="end"/>


✅ MOCK 8: ORDER SUMMARY USING TOAST
String summary = "Burger x2\nTotal: ₹200";
Toast.makeText(this, summary, Toast.LENGTH_LONG).show();


✅ MOCK 9: RESET FORM
btnReset.setOnClickListener(v -> {
    etQuantity.setText("");
    ratingBar.setRating(0);
    toggle.setChecked(false);
    tvTotal.setText("");
});


✅ MOCK 10: VALIDATION (NO ITEM SELECTED)
if (!cbBurger.isChecked() && !cbPizza.isChecked()) {
    Toast.makeText(this,
            "Please select at least one item",
            Toast.LENGTH_SHORT).show();
    return;
}


🧠 VIVA ONE-LINERS (MEMORISE)
EditText value → getText().toString()
Toggle state → isChecked()
Rating value → getRating()
Disable button → setEnabled(false)
Toast → Toast.makeText().show()

 */
