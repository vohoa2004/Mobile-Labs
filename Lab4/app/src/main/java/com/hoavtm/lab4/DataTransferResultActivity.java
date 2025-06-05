package com.hoavtm.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoavtm.lab4.models.User;

import java.util.Arrays;

public class DataTransferResultActivity extends AppCompatActivity {
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_transfer_result);
        resultText = findViewById(R.id.resultText);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        switch (type) {
            case "string":
                resultText.setText(getString(R.string.result_string, intent.getStringExtra("value")));
                break;
            case "int":
                resultText.setText(getString(R.string.result_int, intent.getIntExtra("value", 0)));
                break;
            case "array":
                String[] arr = intent.getStringArrayExtra("value");
                resultText.setText(getString(R.string.result_array, Arrays.toString(arr)));
                break;
            case "bundle":
                Bundle bundle = intent.getBundleExtra("bundle");
                resultText.setText(getString(R.string.result_bundle,
                        bundle.getString("name"),
                        bundle.getInt("id")));
                break;
            case "object":
                User user = (User) intent.getSerializableExtra("user");
                resultText.setText(getString(R.string.result_object,
                        user.getName(),
                        user.getAge()));
                break;
        }

    }
}