package com.example.foodorder;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class OrderSummaryActivity extends AppCompatActivity {
    TextView tvOrderDetails, tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        tvOrderDetails = findViewById(R.id.tvOrderDetails);
        tvTotal = findViewById(R.id.tvTotal);
        String order = getIntent().getStringExtra("order");
        int total = getIntent().getIntExtra("total", 0);
        tvOrderDetails.setText("Items Ordered:\n" + order);
        tvTotal.setText("Total Cost: ₹" + total);
    }
}