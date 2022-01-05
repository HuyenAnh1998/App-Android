package com.example.gameadmin.quanlynguoichoi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gameadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class QuanlynguoichoiActivity extends AppCompatActivity {

    ListView lvNguoichoi;
    QuanlynguoichoiAdapter adapter;
    ArrayList<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlynguoichoi);

        lvNguoichoi = findViewById(R.id.list_nguoichoi);
        adapter = new QuanlynguoichoiAdapter(list);
        lvNguoichoi.setAdapter(adapter);
        getListUser();
        lvNguoichoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QuanlynguoichoiActivity.this, ThongtinNguoichoi.class);
                intent.putExtra("user",list.get(i));
                startActivity(intent);
            }
        });
    }

    private void getListUser() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot dc : task.getResult()) {
                                String mail = (String) dc.get("email");
                                String name = (String) dc.get("name");
                                String diem = (String) dc.get("score");
                                int score = Integer.parseInt(diem);
                                User u = new User(name, mail, score);
                                list.add(u);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
