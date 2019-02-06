package com.mmanchala.coen268.mycalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addButton, substractButton, divideButton, multiplyButton;
    TextView firstNumber, secondNumber, resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = (Button) findViewById(R.id.addButton);
        substractButton = findViewById(R.id.substractButton);
        divideButton = findViewById(R.id.divideButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        resultText = (TextView) findViewById(R.id.resultText);

        firstNumber = (EditText) findViewById(R.id.firstNumber);
        secondNumber = (EditText) findViewById(R.id.secondNumber);

        addButton.setOnClickListener(this);
        substractButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String ed_text1 = firstNumber.getText().toString().trim();
        String ed_text2 = secondNumber.getText().toString().trim();

        if(ed_text1.isEmpty() || ed_text1.length() == 0 || ed_text1.equals("") || ed_text1 == null) {
            resultText.setText("Please enter the first number"); //checking whether the first number is null
        }else if(ed_text2.isEmpty() || ed_text2.length() == 0 || ed_text2.equals("") || ed_text2 == null){
            resultText.setText("Please enter the second number"); //checking whether the second number is null
        } else
            {
            double first = Double.valueOf(firstNumber.getText().toString());
            double second = Double.valueOf(secondNumber.getText().toString());

            if (view.getId() == R.id.addButton) {
                //adding
                resultText.setText(String.valueOf(first + second));
            }

            if (view.getId() == R.id.substractButton) {
                //subtracting
                resultText.setText(String.valueOf(first - second));
            }

            if (view.getId() == R.id.divideButton) {
                //division
                if (second == 0) {
                    //checking whether second number is zero or not
                    resultText.setText("Cannot divide by zero");
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                resultText.setText(String.valueOf(first / second));
            }

            if (view.getId() == R.id.multiplyButton) {
                //multiplication
                resultText.setText(String.valueOf(first * second));
            }
        }
    }
}