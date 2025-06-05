package com.hoavtm.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoavtm.lab4.adapters.DrinkAdapter;
import com.hoavtm.lab4.models.Drink;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DrinkActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDrinks;
    private DrinkAdapter drinkAdapter;
    private List<Drink> drinkList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        recyclerViewDrinks = findViewById(R.id.recyclerViewDrinks);
        recyclerViewDrinks.setLayoutManager(new LinearLayoutManager(this));

        drinkList = new ArrayList<>();
        drinkList.add(new Drink("Nước suối Lavie", "Thanh mát tự nhiên", R.drawable.lavie, 5000));
        drinkList.add(new Drink("Pepsi", "Đã quá pepsi ơi", R.drawable.pepsi, 10000));
        drinkList.add(new Drink("7Up", "Bảy Up", R.drawable.sevenup, 10000));
        drinkList.add(new Drink("Boncha", "Trà chanh mật ong", R.drawable.boncha, 12000));
        drinkList.add(new Drink("Cocacola", "Cô ca cô la", R.drawable.coca, 15000));
        drinkList.add(new Drink("Sữa Vinamilk", "Thơm ngon, bổ dưỡng", R.drawable.vinamilk, 10000));

        drinkAdapter = new DrinkAdapter(drinkList);
        recyclerViewDrinks.setAdapter(drinkAdapter);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashSet<Drink> selectedDrinks = drinkAdapter.getSelectedDrinks();
                ArrayList<String> drinkNames = new ArrayList<>();
                ArrayList<Integer> drinkPrices = new ArrayList<>();

                for (Drink drink : selectedDrinks) {
                    drinkNames.add(drink.getName());
                    drinkPrices.add(drink.getPrice());
                }
                Intent resultIntent = new Intent();
                resultIntent.putStringArrayListExtra("selected_drink_names", drinkNames);
                resultIntent.putIntegerArrayListExtra("selected_drink_prices", drinkPrices);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        Button btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}