package ru.iliavolkov.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LogicCalc {

    BigDecimal number1;
    BigDecimal number2;
    char mathematicalChar;
    String[] arrStringNumber = {"",""};
    TextView field;
    HorizontalScrollView horizontalScroll;
    Activity MainActivity;
    int divide0 = R.string.divideText;

    LogicCalc(Activity MainActivity,TextView field, HorizontalScrollView horizontalScroll) {
        this.MainActivity = MainActivity;
        this.field = field;
        this.horizontalScroll = horizontalScroll;
    }

    //Процент от числа
    protected void percentMethod() {
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
    protected void equalsMethod() {
        if (checkingIfThereCharInString(field.getText().toString(),"E")) {
            field.setText("");
            makeTextToast(R.string.sorry);
        } else {
            if (!getLastElement().equals("√") && !getLastElement().equals("+") && !getLastElement().equals("-")
                    && !getLastElement().equals("*") && !getLastElement().equals("/")) {
                setNumberStringFromArr();
                if (!arrStringNumber[0].equals("")  && !getLastElement().equals(".") && !getLastElement().equals("√")) {
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
                    switch (mathematicalChar) {
                        case '+':
                            field.setText(editingResult(String.valueOf(number1.add(number2))));
                            break;
                        case '-':
                            field.setText(editingResult(String.valueOf(number1.subtract(number2))));
                            break;
                        case '*':
                            field.setText(editingResult(String.valueOf(number1.multiply(number2))));
                            break;
                        case '/':
                            try {
                                field.setText(editingResult(String.valueOf(number1.divide(number2, 7, RoundingMode.HALF_UP))));
                            } catch (Exception e) {
                                field.setText("");
                                makeTextToast(R.string.divide0);
                            }
                            break;
                    }
                } else field.setText(editingResult(String.valueOf(number1)));
                arrStringNumber[0] = "";
                arrStringNumber[1] = "";
                number1 = BigDecimal.valueOf(0);
                number2 = BigDecimal.valueOf(0);
            }
        }
    }


    //Вычисляет корни в числе
    private void convertingSquareToNumber(String string,int item) {
        String[] arr = string.split("");
        String str = "";
        BigDecimal tempNumber = BigDecimal.valueOf(0);
        for (int i = arr.length-1; i >= 0; i--) {
            if (arr[i].equals("√")){
                if (!str.equals("")) {
                    if (tempNumber.compareTo(BigDecimal.ZERO) == 0) {
                        tempNumber = BigDecimal.valueOf(Math.sqrt(Double.parseDouble(str)));
                    } else {
                        tempNumber = BigDecimal.valueOf(Math.sqrt(Double.parseDouble(String.valueOf(BigDecimal.valueOf(Double.parseDouble(str)).multiply(tempNumber)))));
                    }
                } else tempNumber = BigDecimal.valueOf(Math.sqrt(Double.parseDouble(String.valueOf(tempNumber))));//BigDecimal.valueOf(Math.sqrt(Double.parseDouble(str)));
                str = "";
//                if (countBigDecimal == -1) {
//                    tempNumber[1] = tempNumber[0].multiply(tempNumber[1]);
//                    tempNumber[0] = BigDecimal.valueOf(0);
//                    countBigDecimal = 0;
//                }
            } else if (!arr[i].equals("") && !arr[i].equals("√")) str = arr[i] + str;
        }
        if (!str.equals("")) tempNumber = BigDecimal.valueOf(Double.parseDouble(str)).multiply(tempNumber);
        arrStringNumber[item] = String.valueOf(tempNumber);
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
    protected void setNumber(String number) {
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
    protected void operation(String s) {
        if (!field.getText().toString().equals("") && !getLastElement().equals("√")) {
            if (!getLastElement().equals("+") && !getLastElement().equals("-") && !getLastElement().equals("*") && !getLastElement().equals("/")) {
                if (getLastElement().equals(".")) field.setText(field.getText() + "0");
                if (checkingIfThereCharInString(field.getText().toString(),"+") || checkingIfThereCharInString(field.getText().toString(),"-")
                        || checkingIfThereCharInString(field.getText().toString(),"*") || checkingIfThereCharInString(field.getText().toString(),"/")) equalsMethod();
                if (!field.getText().equals("")) field.setText(field.getText() + s);
            } else {
                String[] arr = field.getText().toString().split("");
                if (arr.length != 1) {
                    deleteOneItem();
                    field.setText(field.getText() + s);
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
    protected void deleteOneItem() {
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
    //Toast
    private void makeTextToast(int s) { Toast.makeText(MainActivity,s,Toast.LENGTH_SHORT).show(); }
    //endregion
}
