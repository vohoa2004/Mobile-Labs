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

import com.hoavtm.lab4.adapters.FoodAdapter;
import com.hoavtm.lab4.models.Food;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFood;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        recyclerViewFood = findViewById(R.id.recyclerViewFoods);
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this));

        foodList = new ArrayList<>();
        foodList.add(new Food("Bún riêu", "Thơm ngon tròn vị", R.drawable.bun_rieu, 50000));
        foodList.add(new Food("Gỏi cuốn", "Thanh mát tươi ngon", R.drawable.goi_cuon, 30000));
        foodList.add(new Food("Gỏi đu đủ", "Chua cay bùng vị", R.drawable.goi_du_du, 25000));
        foodList.add(new Food("Hủ tiếu mực", "Ngọt thanh từ mực", R.drawable.hu_tieu_muc, 45000));
        foodList.add(new Food("Há cảo hấp", "Điểm tâm tinh tế", R.drawable.ha_cao, 30000));
        foodList.add(new Food("Bánh mỳ thập cẩm", "Đỉnh của chóp", R.drawable.banh_mi, 35000));

        foodAdapter = new FoodAdapter(foodList);
        recyclerViewFood.setAdapter(foodAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashSet<Food> selectedFoods = foodAdapter.getSelectedFoods();
                ArrayList<String> selectedFoodNames = new ArrayList<>();
                ArrayList<Integer> selectedFoodPrices = new ArrayList<>();

                for (Food food : selectedFoods) {
                    selectedFoodNames.add(food.getName());
                    selectedFoodPrices.add(food.getPrice());
                }
                Intent intent = new Intent(FoodActivity.this, MainActivity.class);
                intent.putStringArrayListExtra("selected_food_names", selectedFoodNames);
                intent.putIntegerArrayListExtra("selected_food_prices", selectedFoodPrices);
                setResult(RESULT_OK, intent);
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