package com.hoavtm.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoavtm.lab4.models.User;

public class TransferDataActivity extends AppCompatActivity {

    EditText inputField;
    Button btnString, btnInt, btnArray, btnBundle, btnObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_data);

        inputField = findViewById(R.id.inputField);
        btnString = findViewById(R.id.btnString);
        btnInt = findViewById(R.id.btnInt);
        btnArray = findViewById(R.id.btnArray);
        btnBundle = findViewById(R.id.btnBundle);
        btnObject = findViewById(R.id.btnObject);

        btnString.setOnClickListener(v -> {
            String value = inputField.getText().toString();
            Intent intent = new Intent(this, DataTransferResultActivity.class);
            intent.putExtra("type", "string");
            intent.putExtra("value", value);
            startActivity(intent);
        });

        btnInt.setOnClickListener(v -> {
            String input = inputField.getText().toString();
            try {
                int number = Integer.parseInt(input);
                Intent intent = new Intent(this, DataTransferResultActivity.class);
                intent.putExtra("type", "int");
                intent.putExtra("value", number);
                startActivity(intent);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập số nguyên hợp lệ!", Toast.LENGTH_SHORT).show();
            }
        });

        btnArray.setOnClickListener(v -> {
            String input = inputField.getText().toString();
            if (!input.contains(",")) {
                Toast.makeText(this, "Vui lòng nhập ít nhất 2 phần tử, cách nhau bằng dấu phẩy!", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] array = input.split(",");
            Intent intent = new Intent(this, DataTransferResultActivity.class);
            intent.putExtra("type", "array");
            intent.putExtra("value", array);
            startActivity(intent);
        });


        btnBundle.setOnClickListener(v -> {
            String input = inputField.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập dữ liệu!", Toast.LENGTH_SHORT).show();
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString("name", input);
            bundle.putInt("id", 123);

            Intent intent = new Intent(this, DataTransferResultActivity.class);
            intent.putExtra("type", "bundle");
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        });


        btnObject.setOnClickListener(v -> {
            String input = inputField.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập dữ liệu!", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = new User(inputField.getText().toString(), 20);
            Intent intent = new Intent(this, DataTransferResultActivity.class);
            intent.putExtra("type", "object");
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }
}