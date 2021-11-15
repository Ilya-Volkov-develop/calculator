package ru.iliavolkov.calculator;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,
            divide,multiply,fold,subtract, deleteOneElement,deleteAll,equals,comma, percent,sqrt;
    TextView field, fieldTest;
    HorizontalScrollView horizontalScroll;

    BigDecimal number1;
    BigDecimal number2;
    char mathematicalChar;
    String[] arrStringNumber = {"",""};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onClickListenerButtons();
    }

    private void init() {
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
        comma = findViewById(R.id.comma);
        field = findViewById(R.id.field1);
        fieldTest = findViewById(R.id.field2);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        fold = findViewById(R.id.fold);
        subtract = findViewById(R.id.subtract);
        equals = findViewById(R.id.equals);
        percent = findViewById(R.id.percent);
        sqrt = findViewById(R.id.sqrt);
        deleteOneElement = findViewById(R.id.delete_one_element);
        deleteAll = findViewById(R.id.delete_text);
        horizontalScroll = findViewById(R.id.horizontal_scroll);
    }
    private void onClickListenerButtons() {
        btn1.setOnClickListener(v -> setNumber("1"));
        btn2.setOnClickListener(v -> setNumber("2"));
        btn3.setOnClickListener(v -> setNumber("3"));
        btn4.setOnClickListener(v -> setNumber("4"));
        btn5.setOnClickListener(v -> setNumber("5"));
        btn6.setOnClickListener(v -> setNumber("6"));
        btn7.setOnClickListener(v -> setNumber("7"));
        btn8.setOnClickListener(v -> setNumber("8"));
        btn9.setOnClickListener(v -> setNumber("9"));
        btn0.setOnClickListener(v -> setNumber("0"));
        comma.setOnClickListener(v -> setNumber("."));
        divide.setOnClickListener(v -> operation("/"));
        multiply.setOnClickListener(v -> operation("*"));
        fold.setOnClickListener(v -> operation("+"));
        subtract.setOnClickListener(v -> operation("-"));
        percent.setOnClickListener(v -> percentMethod());
        sqrt.setOnClickListener(v -> setNumber("√"));
        deleteOneElement.setOnClickListener(v -> deleteOneItem());
        equals.setOnClickListener(v-> equalsMethod());
        deleteAll.setOnClickListener(v -> {
            field.setText("");
            arrStringNumber[0] = "";
            arrStringNumber[1] = "";
        });
    }


    //Процент от числа
    private void percentMethod() {
//        if (!field.getText().toString().equals("")) {
//            String[] arrNumber = field.getText().toString().replace(" ","").replace("+", " ").replace("-", " ").replace("*"," ").replace("/"," ").split(" ");
//            BigDecimal[] arrDecimal = new BigDecimal[arrNumber.length];
//            for (int i = 0; i < arrNumber.length; i++) arrDecimal[i] = BigDecimal.valueOf(Double.parseDouble(arrNumber[i]));
//            String strChar = field.getText().toString().replace(" ","");
//            for (int i = 0; i < 10; i++) strChar = strChar.replace(String.valueOf(i),"");
//            String[] arrSigns = strChar.replace(".","").split("");
//            for (int i = 1; i < arrNumber.length; i++) {
//                switch (arrSigns[i-1]) {
//                    case "+" : result = result.add(arrDecimal[i]); break;
//                    case "-" : result = result.subtract(arrDecimal[i]); break;
//                    case "*" : result = result.multiply(arrDecimal[i]); break;
//                    case "/" : result = result.divide(arrDecimal[i]); break;
//                }
//                field.setText(editingResult(result));
//            }
//            fieldTest.setText(String.valueOf(result));
//        }
    }

    //region Всё работает
    //метод вызывается каждый раз когда нажимается знак арифметического действия или равно
    private void equalsMethod() {
        if (!getLastElement().equals("√")) {
            setNumberStringFromArr();
            if (!arrStringNumber[0].equals("") && !getLastElement().equals("+")
                    && !getLastElement().equals("-") && !getLastElement().equals("*")
                    && !getLastElement().equals("/") && !getLastElement().equals(".") && !getLastElement().equals("√")) {
                if (checkingIfThereCharInString(arrStringNumber[0], "√"))
                    convertingSquareToNumber(arrStringNumber[0], 0);
                number1 = BigDecimal.valueOf(Double.parseDouble(arrStringNumber[0]));
            }
            if (!arrStringNumber[1].equals("") && !getLastElement().equals(".") && !getLastElement().equals("√")) {
                mathematicalChar = arrStringNumber[1].charAt(0);
                arrStringNumber[1] = arrStringNumber[1].replace("-", "").replace("+", "").replace("*", "").replace("/", "");
                if (checkingIfThereCharInString(arrStringNumber[1], "√"))
                    convertingSquareToNumber(arrStringNumber[1], 1);
                number2 = BigDecimal.valueOf(Double.parseDouble(arrStringNumber[1]));
                arrStringNumber[1] = "";
                switch (mathematicalChar) {
                    case '+':
                        arrStringNumber[0] = String.valueOf(number1.add(number2));
                        break;
                    case '-':
                        arrStringNumber[0] = String.valueOf(number1.subtract(number2));
                        break;
                    case '*':
                        arrStringNumber[0] = String.valueOf(number1.multiply(number2));
                        break;
                    case '/':
                        try {
                            arrStringNumber[0] = String.valueOf(number1.divide(number2));
                        } catch (Exception e) {
                            field.setText("Делить на 0 нельзя!");
                        }
                        break;
                }
            }
            if (!field.getText().equals("Делить на 0 нельзя!"))
                field.setText(editingResult(arrStringNumber[0]));
        }
    }
    //Вычисляет корни в числе
    private void convertingSquareToNumber(String string,int item) {
            String[] arr = string.split("");
            String str = "";
            BigDecimal[] tempNumber = {BigDecimal.valueOf(0),BigDecimal.valueOf(0)};
            int countBigDecimal = 1;
            for (int i = arr.length-1; i >= 0; i--) {
                if (arr[i].equals("√")){
                    if (!str.equals("")) {
                        tempNumber[countBigDecimal] = BigDecimal.valueOf(Math.sqrt(Double.parseDouble(str)));
                    } else {
                        tempNumber[countBigDecimal+1] = BigDecimal.valueOf(Math.sqrt(Double.parseDouble(String.valueOf(tempNumber[countBigDecimal+1]))));//BigDecimal.valueOf(Math.sqrt(Double.parseDouble(str)));
                        countBigDecimal++;
                    }
                    countBigDecimal--;
                    str = "";
                    if (countBigDecimal == -1) {
                        tempNumber[1] = tempNumber[0].multiply(tempNumber[1]);
                        tempNumber[0] = BigDecimal.valueOf(0);
                        countBigDecimal = 0;
                    }
                } else if (!arr[i].equals("") && !arr[i].equals("√")) str = arr[i] + str;
            }
            if (!str.equals("")) tempNumber[1] = BigDecimal.valueOf(Double.parseDouble(str)).multiply(tempNumber[1]);
            arrStringNumber[item] = String.valueOf(tempNumber[1]);
        }
    //Устанавливаем числа примера в массив arrStringNumber
    private void setNumberStringFromArr() {
        String[] arr = field.getText().toString().split("");
        String string = "";
        int count = 1;
        for (int i = arr.length-1; i >= 0; i--){
            if (arr[i].equals("+") || arr[i].equals("-") || arr[i].equals("*") || arr[i].equals("/") || i==0) {
                string = arr[i] + string;
                if (string.equals("+") || string.equals("-") || string.equals("*") || string.equals("/")) break;
                arrStringNumber[count] = string;
                string = "";
                count--;
            } else string = arr[i] + string;
        }
        if (arrStringNumber[0].equals("") && !arrStringNumber[1].equals("")) {
            arrStringNumber[0] = arrStringNumber[1];
            arrStringNumber[1] = "";
        }
    }
    //Добавление цифры или математического знака (. √) в пример
    @SuppressLint("SetTextI18n")
    private void setNumber(String number) {
        if (field.getText().equals("Делить на 0 нельзя!")) field.setText("");
        if (getLastString().equals("0") && number.equals("0") || getLastElement().equals(".") && number.equals("√")
                || checkingIfThereCharInString(getLastString(),".") && number.equals(".")) {
        } else if(getLastElement().equals("√") && number.equals(".") || getLastElement().equals("") && number.equals(".")
                || (getLastElement().equals("+") || getLastElement().equals("-")
                || getLastElement().equals("*") || getLastElement().equals("/")) && number.equals(".")) {
            field.setText(field.getText() + "0.");
        }else if ((getLastString().equals("0") || getTwoLastElement().equals("√0")) && !number.equals(".") && !number.equals("√")) {
            deleteOneItem();
            field.setText(field.getText() + number);
        }
        else field.setText(field.getText() + number);
        scrollingRight();
    }
    //Математические операции
    @SuppressLint("SetTextI18n")
    private void operation(String s) {
        if (!field.getText().toString().equals("") && !getLastElement().equals("√")) {
            String[] strings = field.getText().toString().split("");
            if (!strings[strings.length - 1].equals("+") && !strings[strings.length - 1].equals("-") &&
                    !strings[strings.length - 1].equals("*") && !strings[strings.length - 1].equals("/")) {
                if (getLastElement().equals(".")) field.setText(field.getText() + "0");
                if (checkingIfThereCharInString(field.getText().toString(),"+") || checkingIfThereCharInString(field.getText().toString(),"-")
                        || checkingIfThereCharInString(field.getText().toString(),"*") || checkingIfThereCharInString(field.getText().toString(),"/")) equalsMethod();
                field.setText(field.getText() + "" + s);
            } else {
                String[] arr = field.getText().toString().split("");
                String string = "";
                if (arr.length != 1) {
                    for (int i = 0; i < arr.length - 1; i++) string += arr[i];
                    field.setText(string + "" + s);
                }
            }

        } else if (s.equals("-") && !getLastElement().equals("√")) {
            field.setText(field.getText() + s);
        }

        scrollingRight();
    }
    //Удаляем нули после .
    private String editingResult(String result) {
        String[] arr = result.split("");
        String string = "";
        String resStr = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(".")) {
                for (int j = arr.length-1; j > i; j--) {
                    if (!arr[j].equals("0")){
                        string = arr[j] + string;
                    } else if (!string.equals("")) string = arr[j] + string;
                }
                if (!string.equals("")) string = "."+string;
                resStr = resStr + string;
                break;
            } else resStr += arr[i];
        }
        return resStr;
    }
    //Удаляет один элемент
    private void deleteOneItem() {
        if (!field.getText().toString().equals("")) {
            String[] arr = field.getText().toString().split("");
            String resultStr = "";
            for (int i = 0; i < arr.length - 1; i++) resultStr += arr[i];
            field.setText(resultStr);
        }
    }
    //Получаем последний элемент поля field
    private String getLastElement() {
        String[] arr = field.getText().toString().split("");
        return arr[arr.length-1];
    }
    //Получаем 2 последних элемента в строке field
    private String getTwoLastElement() {
        String[] arr = field.getText().toString().split("");
        try { return arr[arr.length-2] + arr[arr.length-1];
        } catch (ArrayIndexOutOfBoundsException e) {return "";}

    }
    //Получаем последнее слова поля field без знака арифметического действия
    private String getLastString(){
        String[] arr = field.getText().toString().split("");
        String string = "";
        for (int i = arr.length-1; i >= 0; i--){
            if (arr[i].equals("+") || arr[i].equals("-") || arr[i].equals("*") || arr[i].equals("/")) break;
            else string = arr[i] + string;
        }
        return string;
    }
    //Проверяем содержание символа в указаном слове
    private Boolean checkingIfThereCharInString(String field, String c) {
        String[] arr = field.split("");
        for (int i = 0; i< arr.length; i++) if (arr[i].equals(c)) return true;
        return false;
    }
    //Прокрутка scrollView в конец, чтобы отображался пример корректно
    private void scrollingRight() { horizontalScroll.post(() -> horizontalScroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT)); }
    //endregion
}