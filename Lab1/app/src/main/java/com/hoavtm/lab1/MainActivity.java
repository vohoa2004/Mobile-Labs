package com.hoavtm.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Get references to the buttons
        Button buttonMain1a = findViewById(R.id.button_main1a);
        Button buttonMain1b = findViewById(R.id.button_main1b);
        Button buttonMain2 = findViewById(R.id.button_main2);
        Button buttonMain3 = findViewById(R.id.button_main3);

        // Set OnClickListener for each button
        buttonMain1a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start MainActivity1a
                Intent intent = new Intent(MainActivity.this, MainActivity1a.class);
                startActivity(intent); // Start the new activity
            }
        });

        buttonMain1b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity1b.class);
                startActivity(intent);
            }
        });

        buttonMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        buttonMain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}