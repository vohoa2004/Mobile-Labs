package com.hoavtm.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Get references to the buttons
        Button buttonMain1 = findViewById(R.id.button_main1);
        Button buttonMain2 = findViewById(R.id.button_main2);

        // Set OnClickListener for each button
        buttonMain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start MainActivity1a
                Intent intent = new Intent(MainActivity.this, MainActivity1.class);
                startActivity(intent); // Start the new activity
            }
        });

        buttonMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}