package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNum1, etNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);

        Button add = findViewById(R.id.btnAdd);
        Button sub = findViewById(R.id.btnSub);
        Button mul = findViewById(R.id.btnMul);
        Button div = findViewById(R.id.btnDiv);

        add.setOnClickListener(v -> calculate("+"));
        sub.setOnClickListener(v -> calculate("-"));
        mul.setOnClickListener(v -> calculate("*"));
        div.setOnClickListener(v -> calculate("/"));
    }

    private void calculate(String operator) {
        double num1 = Double.parseDouble(etNum1.getText().toString());
        double num2 = Double.parseDouble(etNum2.getText().toString());
        double result = 0;

        switch (operator) {
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/": result = num1 / num2; break;
        }

        String expression = num1 + " " + operator + " " + num2 + " = " + result;

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("result", expression);
        startActivity(intent);
    }
}
