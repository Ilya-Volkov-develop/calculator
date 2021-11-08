package ru.iliavolkov.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,divide,multiply,fold,subtract;
    TextView field1, field2, field3;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        fold = findViewById(R.id.fold);
        subtract = findViewById(R.id.subtract);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btn0 = findViewById(R.id.button0);
        field1 = findViewById(R.id.field1);
        field2 = findViewById(R.id.field2);
        field3 = findViewById(R.id.field3);

//        btn1.setOnClickListener(v -> setNumber(1));
//        btn2.setOnClickListener(v -> setNumber(2));
//        btn3.setOnClickListener(v -> setNumber(3));
//        btn4.setOnClickListener(v -> setNumber(4));
//        btn5.setOnClickListener(v -> setNumber(5));
//        btn6.setOnClickListener(v -> setNumber(6));
//        btn7.setOnClickListener(v -> setNumber(7));
//        btn8.setOnClickListener(v -> setNumber(8));
//        btn9.setOnClickListener(v -> setNumber(9));
//        btn0.setOnClickListener(v -> setNumber(0));
//        divide.setOnClickListener(v -> operation("/"));
//        multiply.setOnClickListener(v -> operation("*"));
//        fold.setOnClickListener(v -> operation("+"));
//        subtract.setOnClickListener(v -> operation("-"));


    }

//    private void operation(String s) {
//        double number1 = 0;
//        double number2 = 0;
//        double result = 0;
//        try { number1 = Double.parseDouble(getField(field2.getText().toString())); } catch (Exception e) {}
//        try { number2 = Double.parseDouble(field1.getText().toString()); } catch (Exception e) {}
//        try {
//            String[] strings = field2.getText().toString().split("");
//            switch (strings[strings.length]) {
//                case "+" : result = number1 + number2; break;
//                case "-" : result = number1 - number2; break;
//                case "*" : result = number1 * number2; break;
//                case "/" : result = number1 / number2; break;
//            }
//        } catch (Exception e) {
//            switch (s) {
//                case "+" : result = number1 + number2; break;
//                case "-" : result = number1 - number2; break;
//                case "*" : result = number1 * number2; break;
//                case "/" : result = number1 / number2; break;
//            }
//        }
//
//        if (field1.length() > 0) {
//            field2.setText(result + " " + s);
//            field1.setText(null);
//        } else {
//            if (field2.length() > 0) {
//                String[] arr = field2.getText().toString().split("");
//                String string = "";
//                for (int i = 0; i < arr.length-1; i++) string += arr[i];
//                string += s;
//                field2.setText(string);
//            }
//        }
//    }
//
//    private String getField(String field) {
//        String[] arr = field.split("[+-/* ]");
//        String string = "";
//        for (int i = 0; i < arr.length; i++) string += arr[i];
//        return string;
//    }
//
//    @SuppressLint("SetTextI18n")
//    private void setNumber(int i) {
//        if (field1.length() <= 12) {
//            field1.setText(field1.getText() + "" + i);
//        }
//    }
}