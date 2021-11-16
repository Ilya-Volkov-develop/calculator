package ru.iliavolkov.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,divide,multiply,fold,subtract, deleteOneElement,deleteAll,equals,comma, percent,sqrt;
    TextView field, textView;
    HorizontalScrollView horizontalScroll;
    LogicCalc logic;
    AppCompatImageView settingImage;
    ConstraintLayout layoutMainActivity;
    String themeApp;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClickListenerButtons();
        deleteTopBar();
    }

    private void deleteTopBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
    private void init() {
        textView = findViewById(R.id.textView);
        settingImage = findViewById(R.id.settingImage);
        layoutMainActivity = findViewById(R.id.layoutMainActivity);
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
        logic = new LogicCalc(MainActivity.this,field, horizontalScroll);
        themeApp = getSharedPreferences("SharedTheme", Activity.MODE_PRIVATE).getString("Theme","light");
        switchAndLoadingTheme("night");
    }
    private void onClickListenerButtons() {
        btn1.setOnClickListener(v -> logic.setNumber("1"));
        btn2.setOnClickListener(v -> logic.setNumber("2"));
        btn3.setOnClickListener(v -> logic.setNumber("3"));
        btn4.setOnClickListener(v -> logic.setNumber("4"));
        btn5.setOnClickListener(v -> logic.setNumber("5"));
        btn6.setOnClickListener(v -> logic.setNumber("6"));
        btn7.setOnClickListener(v -> logic.setNumber("7"));
        btn8.setOnClickListener(v -> logic.setNumber("8"));
        btn9.setOnClickListener(v -> logic.setNumber("9"));
        btn0.setOnClickListener(v -> logic.setNumber("0"));
        comma.setOnClickListener(v -> logic.setNumber("."));
        sqrt.setOnClickListener(v -> logic.setNumber("√"));
        divide.setOnClickListener(v -> logic.operation("/"));
        multiply.setOnClickListener(v -> logic.operation("*"));
        fold.setOnClickListener(v -> logic.operation("+"));
        subtract.setOnClickListener(v -> logic.operation("-"));
        percent.setOnClickListener(v -> logic.percentMethod());
        deleteOneElement.setOnClickListener(v -> logic.deleteOneItem());
        equals.setOnClickListener(v-> logic.equalsMethod());
        deleteAll.setOnClickListener(v -> {
            field.setText("");
            logic.arrStringNumber[0] = "";
            logic.arrStringNumber[1] = "";
        });
        settingImage.setOnClickListener(v -> summonDialog());
    }
    //Создаём диалоговое окно
    private void summonDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View exit_view = inflater.inflate(R.layout.layout_dialog_theme, null);
        dialog.setView(exit_view);
        Dialog dialog1 = dialog.create();
        Button yes = exit_view.findViewById(R.id.yes);
        Button no = exit_view.findViewById(R.id.no);
        yes.setOnClickListener(v1 -> {
            switchAndLoadingTheme("light");
            dialog1.dismiss();
        });
        no.setOnClickListener(v1 -> dialog1.dismiss());
        dialog1.show();
    }
    //Меняем или загружаем тему
    private void switchAndLoadingTheme(String theme) {
        if (themeApp.equals(theme)) setTheme("#000000","#ffffff",R.drawable.gear_white, "night");
        else setTheme("#ffffff","#000000",R.drawable.gear_black, "light");
        SharedPreferences.Editor editor = getSharedPreferences("SharedTheme", Activity.MODE_PRIVATE).edit();
        editor.putString("Theme",themeApp).apply();
    }
    //Устанавливаем тему
    private void setTheme(String colorF, String color0, int draw, String theme) {
        layoutMainActivity.setBackgroundColor(Color.parseColor(colorF));
        field.setTextColor(Color.parseColor(color0));
        btn1.setTextColor(Color.parseColor(color0));
        btn2.setTextColor(Color.parseColor(color0));
        btn3.setTextColor(Color.parseColor(color0));
        btn4.setTextColor(Color.parseColor(color0));
        btn5.setTextColor(Color.parseColor(color0));
        btn6.setTextColor(Color.parseColor(color0));
        btn7.setTextColor(Color.parseColor(color0));
        btn8.setTextColor(Color.parseColor(color0));
        btn9.setTextColor(Color.parseColor(color0));
        btn0.setTextColor(Color.parseColor(color0));
        divide.setTextColor(Color.parseColor(color0));
        multiply.setTextColor(Color.parseColor(color0));
        fold.setTextColor(Color.parseColor(color0));
        subtract.setTextColor(Color.parseColor(color0));
        deleteOneElement.setTextColor(Color.parseColor(color0));
        deleteAll.setTextColor(Color.parseColor(color0));
        equals.setTextColor(Color.parseColor(color0));
        comma.setTextColor(Color.parseColor(color0));
        percent.setTextColor(Color.parseColor(color0));
        sqrt.setTextColor(Color.parseColor(color0));
        textView.setBackgroundColor(Color.parseColor(color0));
        settingImage.setBackgroundDrawable(getDrawable(draw));
        themeApp = theme;
    }
}