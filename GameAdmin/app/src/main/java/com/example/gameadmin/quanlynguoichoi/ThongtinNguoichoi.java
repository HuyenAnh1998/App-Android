package com.example.gameadmin.quanlynguoichoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gameadmin.R;

public class ThongtinNguoichoi extends AppCompatActivity {

    TextView tvName, tvMail, tvScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_nguoichoi);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        tvName = findViewById(R.id.tv_name);
        tvName.setText("Name: "+ user.getName());
        tvMail = findViewById(R.id.tv_mail);
        tvMail.setText("Email: "+ user.getEmail());
        tvScore = findViewById(R.id.tv_score);
        tvScore.setText("Score: "+ user.getScore());

    }
}
