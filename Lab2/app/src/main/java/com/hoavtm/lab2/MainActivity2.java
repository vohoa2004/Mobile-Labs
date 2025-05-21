package com.hoavtm.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
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
        min = Integer.parseInt(editTextMin.getText().toString());

        editTextMax = (EditText) findViewById(R.id.etMax);
        max = Integer.parseInt((editTextMax).getText().toString());

        btnRandom = (Button) findViewById(R.id.buttonRandom);
        textViewResult = (TextView) findViewById(R.id.tvResult);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = random.nextInt((max - min) + 1) + min;
                textViewResult.setText(result);
            }
        });
    }
}