package com.example.practice;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText text;
    private String firstNumber = "";
    private String secondNumber = "";
    private String operator = "";
    private boolean isNewNumber = false;
    private boolean isSqrt = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        text = findViewById(R.id.string_example);
        Button button_0 = findViewById(R.id.button_0);
        Button button_1 = findViewById(R.id.button_1);
        Button button_2 = findViewById(R.id.button_2);
        Button button_3 = findViewById(R.id.button_3);
        Button button_4 = findViewById(R.id.button_4);
        Button button_5 = findViewById(R.id.button_5);
        Button button_6 = findViewById(R.id.button_6);
        Button button_7 = findViewById(R.id.button_7);
        Button button_8 = findViewById(R.id.button_8);
        Button button_9 = findViewById(R.id.button_9);
        Button button_dot = findViewById(R.id.button_dot);
        Button button_plus = findViewById(R.id.button_plus);
        Button button_minus = findViewById(R.id.button_minus);
        Button button_multiply = findViewById(R.id.button_multiply);
        Button button_devide = findViewById(R.id.button_devide);
        Button button_percent = findViewById(R.id.button_percent);
        Button button_square = findViewById(R.id.button_square);
        Button button_delete = findViewById(R.id.button_delete);
        Button button_all_delete = findViewById(R.id.button_all_delete);
        Button button_reselt = findViewById(R.id.button_result);

        button_0.setOnClickListener(buttonClickListener);
        button_1.setOnClickListener(buttonClickListener);
        button_2.setOnClickListener(buttonClickListener);
        button_3.setOnClickListener(buttonClickListener);
        button_4.setOnClickListener(buttonClickListener);
        button_5.setOnClickListener(buttonClickListener);
        button_6.setOnClickListener(buttonClickListener);
        button_7.setOnClickListener(buttonClickListener);
        button_8.setOnClickListener(buttonClickListener);
        button_9.setOnClickListener(buttonClickListener);
        button_dot.setOnClickListener(buttonClickListener);
        button_plus.setOnClickListener(buttonClickListener);
        button_minus.setOnClickListener(buttonClickListener);
        button_multiply.setOnClickListener(buttonClickListener);
        button_devide.setOnClickListener(buttonClickListener);
        button_percent.setOnClickListener(buttonClickListener);
        button_square.setOnClickListener(buttonClickListener);
        button_delete.setOnClickListener(buttonClickListener);
        button_all_delete.setOnClickListener(buttonClickListener);
        button_reselt.setOnClickListener(buttonClickListener);
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            String buttonText = clickedButton.getText().toString();
            boolean squareRootMode = false; // Добавляем флаг режима корня
            if (buttonText.matches("[0-9.]")) {
                if (operator.isEmpty()) {
                    firstNumber += buttonText;
                }
                else if(operator.equals("√")){
                    firstNumber += buttonText;
                }
                else {
                    if (isNewNumber) {
                        secondNumber = "";
                        isNewNumber = false;
                    }
                    secondNumber += buttonText;
                }
            }
            // Если нажат оператор
            else if (buttonText.matches("[+\\-x÷%]")) {
                if (!firstNumber.isEmpty()) {
                    operator = buttonText;
                    isNewNumber = true;
                }
            }
            // Если нажато равно
            else if (buttonText.equals("=")) {
                if (!firstNumber.isEmpty() && !secondNumber.isEmpty() && !operator.isEmpty()) {
                    double result = calculate(
                            Double.parseDouble(firstNumber),
                            Double.parseDouble(secondNumber),
                            operator
                    );
                    text.setText(String.valueOf(result));
                    firstNumber = String.valueOf(result);
                    secondNumber = "";
                    operator = "";
                }
                else if(isSqrt){
                    if (!firstNumber.isEmpty()) {
                        try {
                            double value = Double.parseDouble(firstNumber);
                            if (value < 0) {
                                text.setText("Ошибка: √<0");
                            } else {
                                String result = String.valueOf(Math.sqrt(value));
                                text.setText(result);
                                firstNumber = result;
                            }
                        } catch (NumberFormatException e) {
                            text.setText("Ошибка ввода");
                        }
                    }
                    secondNumber = "";
                    operator = "";
                    isSqrt = false;
                    return;
                }
            }
            //если нажато удалить
            else if(buttonText.equals("⌫")){
                if(operator.isEmpty()) {
                    if (!firstNumber.isEmpty()) {
                        firstNumber = firstNumber.substring(0, firstNumber.length() - 1);
                    }
                }
                else{
                    if (!secondNumber.isEmpty()) {
                        secondNumber = secondNumber.substring(0, secondNumber.length() - 1);
                    }
                }
            }
            //если нажато удалить все
            else if(buttonText.equals("AC")){
                    firstNumber = "";
                    secondNumber = "";
                    operator = "";
            }
            else if(buttonText.equals("√")) {
                operator = buttonText;
                isSqrt = true;
            }
            // вывод на экран
            String resultString = "";
            if(operator.equals("√")){
                resultString = operator + firstNumber;
            }
            else {
                resultString = firstNumber + operator + secondNumber;
            }
            text.setText(resultString);
        };
    };

    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "x": return num1 * num2;
            case "÷": return num1 / num2;
            case "%": return num1 % num2;
            default: return 0;
        }
    }


}
