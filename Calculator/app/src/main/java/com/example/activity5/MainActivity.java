package com.example.activity5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView inputText, outputText;
    private String input = null, output = null, newOutput = null;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, pointBtn, equalBtn, plusBtn, minusBtn, multiBtn, divBtn, percentBtn, powerBtn, clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        pointBtn = findViewById(R.id.pointBtn);
        equalBtn = findViewById(R.id.equalBtn);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        multiBtn = findViewById(R.id.multiBtn);
        divBtn = findViewById(R.id.divBtn);
        percentBtn = findViewById(R.id.percentBtn);
        powerBtn = findViewById(R.id.powerBtn);
        clearBtn = findViewById(R.id.clearBtn);

    }

    public void buttonClick(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();

        switch(data) {
            // Sets all the values to null when the calculator is cleared
            case "C":
                input = null;
                output = null;
                newOutput = null;
                outputText.setText("");
                break;

            // Displays the input as '^' when the button is clicked
            case "^":
                solve();
                input += "^";
                break;

            // Displays the input as '+' when the button is clicked
            case "*":
                solve();
                input += "*";
                break;

            // Displays the input as '=' when the button is clicked
            case "=":
                solve();
                break;

            // Displays the input as '%' when the button is clicked
            case "%":
                input += "%";
                // The number entered gets converted into a decimal showing the percentage
                double d = Double.parseDouble(inputText.getText().toString()) / 100;
                // The given decimal is then outputted
                outputText.setText(String.valueOf(d));
                break;

            // Displays the input field being empty by default
            default:
                if (input == null) {
                    input = "";
                }
                // If any of the inputs are '+,' '/' or '-' it calls the function 'solve'
                // Setting the answer value to data
                if (data.equals("+") || data.equals("/") || data.equals("-")) {
                    solve();
                }
                // Adds the given calculation data onto the existing sum, unless it is null
                // Then it displays the main calculation sum
                input += data;
        }

        // Sets the inputText field to the answer that was solved for
        inputText.setText(input);

        }

    // This function is called upon when an arithmetic calculation needs to be done
    private void solve() {
        // Addition
        if (input.split("\\+").length == 2) {
            String numbers[] = input.split("\\+");
            try {
                double d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e) {
                outputText.setText(e.getMessage().toString());
            }
        }
        // Multiplication
        if (input.split("\\*").length == 2) {
            String numbers[] = input.split("\\*");
            try {
                double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
        // Division
        if (input.split("\\/").length == 2) {
            String numbers[] = input.split("\\/");
            try {
                double d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
        // Exponentiation
        if (input.split("\\^").length == 2) {
            String numbers[] = input.split("\\^");
            try {
                double d = Math.pow(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
        // Subtraction
        if (input.split("\\-").length == 2) {
            String numbers[] = input.split("\\-");
            try {
                if (Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])){
                    double d = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText("-" + newOutput);
                    input = d +"";
                }
                else {
                    double d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                    input = d + "";
                }
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
    }

    // This function is to remove the trailing decimal places from a number
    // If the values are all 0
    private String cutDecimal(String number){
        // Splits the number by decimal point
        String n [] = number.split("\\.");
        // Checks to see if there is a decimal part of the number
        if (n.length >1){
            // If the decimal part is '0' it is removed
            if (n[1].equals("0")){
                number = n[0];  // Sets the number to the integer part only
            }
        }
        // Returns the original or modified number
        return number;
    }

}
