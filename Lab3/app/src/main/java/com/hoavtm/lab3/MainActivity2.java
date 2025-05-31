package com.hoavtm.lab3;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoavtm.lab3.models.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ListView lvFruit;
    ArrayList<Fruit> arrayFruit;
    FruitAdapter adapter;
    Button btnAdd, btnEdit, btnDelete;
    int vitri;

    String[] imageNames = {"banana", "apple", "orange"};
    int[] imageIds = {R.drawable.banana, R.drawable.apple, R.drawable.orange};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        map();
        adapter = new FruitAdapter(this, R.layout.fruit_item, arrayFruit);
        lvFruit.setAdapter(adapter);

        lvFruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptView, View view, int i, long id){
                vitri = i;
                adapter.setSelectedPosition(vitri);
                adapter.notifyDataSetChanged();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFruitDialog("Add Fruit", false);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vitri >= 0 && vitri < arrayFruit.size()) {
                    showFruitDialog("Edit Fruit", true);
                } else {
                    Toast.makeText(MainActivity2.this, "Please select a fruit", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vitri >= 0 && vitri < arrayFruit.size()) {
                    arrayFruit.remove(vitri);
                    vitri = -1;
                    adapter.setSelectedPosition(-1);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity2.this, "Please select a fruit", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void map() {
        lvFruit = (ListView) findViewById(R.id.lvFruit);
        arrayFruit = new ArrayList<>();
        arrayFruit.add(new Fruit("Banana", "Banana from Sai Gon", R.drawable.banana));
        arrayFruit.add(new Fruit("Apple", "Apple from Ha Noi", R.drawable.apple));
        arrayFruit.add(new Fruit("Orange", "Orange from Da Nang", R.drawable.orange));
    }

    private void showFruitDialog(String title, boolean isEdit) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle(title);

        View view = getLayoutInflater().inflate(R.layout.dialog_fruit, null);
        EditText edtName = view.findViewById(R.id.edtName);
        EditText edtDescription = view.findViewById(R.id.edtDescription);
        Spinner spnImage = view.findViewById(R.id.spnImage);

        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, imageNames);
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnImage.setAdapter(spnAdapter);

        if (isEdit) {
            Fruit fruit = arrayFruit.get(vitri);
            edtName.setText(fruit.getName());
            edtDescription.setText(fruit.getDescription());
            for (int i = 0; i < imageIds.length; i++) {
                if (imageIds[i] == fruit.getImage()) {
                    spnImage.setSelection(i);
                    break;
                }
            }
        }

        builder.setView(view);

        builder.setPositiveButton(isEdit ? "Update" : "Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = edtName.getText().toString().trim();
                String description = edtDescription.getText().toString().trim();
                int imgRes = imageIds[spnImage.getSelectedItemPosition()];

                if (isEdit) {
                    Fruit fruit = arrayFruit.get(vitri);
                    fruit.setName(name);
                    fruit.setDescription(description);
                    fruit.setImage(imgRes);
                } else {
                    arrayFruit.add(new Fruit(name, description, imgRes));
                }

                vitri = -1;
                adapter.setSelectedPosition(-1);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

}