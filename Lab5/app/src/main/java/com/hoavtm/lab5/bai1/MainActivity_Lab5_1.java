package com.hoavtm.lab5.bai1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoavtm.lab5.bai1.adapters.UserAdapter;
import com.hoavtm.lab5.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Lab5_1 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab5_1);

        userList = new ArrayList<>();
        initializeDefaultUsers();

        recyclerView = findViewById(R.id.recyclerView);
        userAdapter = new UserAdapter(userList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

    }

    private void initializeDefaultUsers() {
        userList.add(new User("binhlt", "Le Thanh Binh", "bltb@fpt.edu.vn"));
        userList.add(new User("cucth", "Tran Hoang Cuc", "cthc@fpt.edu.vn"));
        userList.add(new User("dungvn", "Vu Nhat Dung", "dvnd@fpt.edu.vn"));
        userList.add(new User("hadm", "Do Mai Ha", "hdmh@fpt.edu.vn"));
        userList.add(new User("khoing", "Ngo Gia Khoi", "kngk@fpt.edu.vn"));
        userList.add(new User("lamht", "Hoang Thanh Lam", "lhtl@fpt.edu.vn"));
        userList.add(new User("minhpc", "Pham Cong Minh", "mpcm@fpt.edu.vn"));
        userList.add(new User("ngocqt", "Quach Thu Ngoc", "nqtn@fpt.edu.vn"));
        userList.add(new User("phuongtt", "Truong Thuy Phuong", "pttp@fpt.edu.vn"));
        userList.add(new User("quangvm", "Vo Minh Quang", "vqmg@fpt.edu.vn"));
        userList.add(new User("sonnx", "Nguyen Xuan Son", "nsxs@fpt.edu.vn"));
        userList.add(new User("thaolh", "Le Hai Thao", "tlht@fpt.edu.vn"));
        userList.add(new User("uyennt", "Nguyen Thuy Uyen", "nttu@fpt.edu.vn"));
        userList.add(new User("vietmd", "Mai Duc Viet", "vmdv@fpt.edu.vn"));
        userList.add(new User("xuandt", "Dinh Thi Xuan", "xdtx@fpt.edu.vn"));
    }
}
