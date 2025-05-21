package com.hoavtm.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Khai bao bien: view nao khai bao field do
//    TextView tvNoidung;
//    Button btnClick;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//
//        // anh xa
//        // R == resources, want to get id => id, want to get layout => layout
//        tvNoidung = (TextView) findViewById(R.id.textViewNoidung); // access the variable - must always cast type
//        btnClick = (Button) findViewById(R.id.buttonClick);
//
//        // viet code
//        btnClick.setOnClickListener(new View.OnClickListener() { // bat su kien click button
//            @Override
//            public void onClick(View view) {
//                tvNoidung.setText("Lập trình Android"); // set value for variable
//            }
//        });
//    }

    EditText editTextMin;
    int min;
    EditText editTextMax;
    int max;
    Button btnRandom;
    TextView textViewResult;
    int result;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        editTextMin = (EditText) findViewById(R.id.etMin);
        editTextMax = (EditText) findViewById(R.id.etMax);

        btnRandom = (Button) findViewById(R.id.buttonRandom);
        textViewResult = (TextView) findViewById(R.id.tvResult);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min = Integer.parseInt(editTextMin.getText().toString()); // onClick ms get value and parse
                max = Integer.parseInt((editTextMax).getText().toString());
                result = random.nextInt((max - min) + 1) + min;
                textViewResult.setText("" + result);
            }
        });
    }


}