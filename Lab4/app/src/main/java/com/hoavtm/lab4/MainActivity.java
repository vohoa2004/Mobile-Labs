package com.hoavtm.lab4;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textViewSelectedFood;
    private TextView textViewSelectedDrink;

    private Button buttonTotalCost;
    private Button buttonRefresh;
    private static final String TAG = "MainActivity";

    // ActivityResultLauncher for FoodActivity and DrinkActivity
    private ActivityResultLauncher<Intent> foodActivityResultLauncher;
    private ActivityResultLauncher<Intent> drinkActivityResultLauncher;
    private int totalCost = 0;
    private int totalFoodCost = 0;
    private int totalDrinkCost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);

            textViewSelectedFood = findViewById(R.id.tvSelectedFood);
            textViewSelectedDrink = findViewById(R.id.tvSelectedDrink);
            buttonTotalCost = findViewById(R.id.totalCost);
            buttonRefresh = findViewById(R.id.refresh);

            Button buttonChooseFood = findViewById(R.id.btn_choose_food);
            Button buttonChooseDrink = findViewById(R.id.btn_choose_drink);
            buttonTotalCost.setText("Check Out: "+totalCost+" VNĐ");

            buttonTotalCost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(totalCost<=0){
                        Toast.makeText(MainActivity.this, "Please choose food and drink", Toast.LENGTH_SHORT).show();
                    }else
                        CheckOut();
                }
            });
            buttonRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (totalCost <= 0) {
                        Toast.makeText(MainActivity.this, "Please choose food and drink", Toast.LENGTH_SHORT).show();
                    } else {
                        recreate();
                    }
                }
            });

            // Register the ActivityResultLauncher for FoodActivity
            foodActivityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        totalFoodCost = 0;
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            ArrayList<String> selectedFoodNames = data.getStringArrayListExtra("selected_food_names");
                            ArrayList<Integer> selectedFoodPrices = data.getIntegerArrayListExtra("selected_food_prices");

                            StringBuilder foodSummary = new StringBuilder("Foods:\n");
                            if (selectedFoodNames != null) {
                                for (int i = 0; i < selectedFoodNames.size(); i++) {
                                    foodSummary.append(selectedFoodNames.get(i))
                                            .append(" - ")
                                            .append(selectedFoodPrices.get(i))
                                            .append(" VNĐ\n");
                                    // add cost to total food cost
                                    totalFoodCost += selectedFoodPrices.get(i);
                                }
                            }
                            textViewSelectedFood.setText(foodSummary.toString());
                            CalTotal();
                        }
                    }
            );

            // Register the ActivityResultLauncher for DrinkActivity
            drinkActivityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        totalDrinkCost = 0;
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            ArrayList<String> selectedDrinkNames = data.getStringArrayListExtra("selected_drink_names");
                            ArrayList<Integer> selectedDrinkPrices = data.getIntegerArrayListExtra("selected_drink_prices");

                            StringBuilder drinkSummary = new StringBuilder("Drinks:\n");
                            if (selectedDrinkNames != null) {
                                for (int i = 0; i < selectedDrinkNames.size(); i++) {
                                    drinkSummary.append(selectedDrinkNames.get(i))
                                            .append(" - ")
                                            .append(selectedDrinkPrices.get(i))
                                            .append(" VNĐ\n");
                                    totalDrinkCost += selectedDrinkPrices.get(i);
                                }
                            }
                            textViewSelectedDrink.setText(drinkSummary.toString());
                            CalTotal();
                        }
                    }
            );

            // Set onClickListeners to launch the activities
            buttonChooseFood.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                foodActivityResultLauncher.launch(intent);
            });

            buttonChooseDrink.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                drinkActivityResultLauncher.launch(intent);
            });
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage(), e);
            Toast.makeText(this, "Error loading Lab4: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish(); // Return to previous activity
        }
    }

    protected void CalTotal() {
        totalCost = totalFoodCost + totalDrinkCost;
        buttonTotalCost = findViewById(R.id.totalCost);
        buttonTotalCost.setText("Check Out: "+totalCost+" VNĐ");
    }

    protected void CheckOut(){
        new AlertDialog.Builder(this)
                .setTitle("Confirm Checkout?")
                .setMessage("Payment for food and drinks: "+ totalCost)
                .setPositiveButton("Yes", (dialog, which) -> Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }
}