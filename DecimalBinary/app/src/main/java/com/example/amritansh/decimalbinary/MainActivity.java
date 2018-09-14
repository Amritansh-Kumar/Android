package com.example.amritansh.decimalbinary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText number;
    private TextView result;
    private String numText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.number);
        result = findViewById(R.id.result_text);
    }


    public void toBinary(View view) {

        boolean isValidated = validateNumber();

        if (isValidated) {
            numText = number.getText().toString();
            if (numText.length() <= 5) {
                int num = Integer.parseInt(numText);
                decimalToBinary(num);
            } else {
                Toast.makeText(this, "enter a decimal number of maximum 5 digits", Toast.LENGTH_SHORT)
                     .show();
            }
        }
    }

    public void toDecimal(View view) {

        boolean isValidated = validateNumber();

        if (isValidated) {
            numText = number.getText().toString();

            if (numText.matches("^[01]+$") && numText.length() < 8) {
                int num = Integer.parseInt(numText);
                binaryToDecimal(num);
            } else {
                Toast.makeText(this, "enter a valid binary number of maximum 8 bit", Toast.LENGTH_SHORT)
                     .show();
            }
        }
    }

    private void binaryToDecimal(int num) {

        int decimal = 0;
        int n = 0;

        while (true) {
            if (num == 0) {
                break;
            } else {
                int temp = num % 10;
                decimal += temp * Math.pow(2, n);
                num = num / 10;
                n++;
            }
        }

        String res = "decimal output is: " + decimal;
        result.setText(res);

    }

    private void decimalToBinary(int num) {

        int rem;
        String binary = "";

        while (num > 0) {
            rem = num % 2;
            binary = binary + "" + rem;
            num = num / 2;
        }

        StringBuilder bin = new StringBuilder();
        for (int i = binary.length() - 1; i >= 0; i--) {
            bin.append(binary.charAt(i));
        }

        String res = "binary output is: " + bin;
        result.setText(res);

    }

    private boolean validateNumber() {
        numText = number.getText().toString();
        if(!numText.matches("")) {
            return true;
        }else {
            Toast.makeText(this,"enter a number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
