package com.example.gameadmin.start;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.gameadmin.R;
import com.example.gameadmin.quanlycauhoi.QLCauhoi;
import com.example.gameadmin.quanlycauhoiguilen.QuanlycauhoiActivity;
import com.example.gameadmin.quanlynguoichoi.QuanlynguoichoiActivity;

public class DashBoard extends AppCompatActivity {

    CardView btQuanlyCauhoi, btQuanlyNguoidung, btQLCauhoi;
    Button btLogin, btLogout;
    SharedPreferences sharedPreferences;
    String email = "", name = "Admin";
    TextView tvUser;

    GridLayout layoutAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        sharedPreferences = getSharedPreferences(String.valueOf(R.string.email), MODE_PRIVATE);
        email = sharedPreferences.getString(String.valueOf(R.string.email), "");
        if (email.equals("")) {
            btLogin.setVisibility(View.VISIBLE);
            btLogout.setVisibility(View.GONE);
            tvUser.setVisibility(View.GONE);
            layoutAdmin.setVisibility(View.GONE);
        } else {
           // checkAdmin();
            btLogout.setVisibility(View.VISIBLE);
            btLogin.setVisibility(View.GONE);
            tvUser.setVisibility(View.VISIBLE);
            tvUser.setText(name);
            tvUser.setText(name);
            layoutAdmin.setVisibility(View.VISIBLE);
        }
        addListener();
    }
    private void init() {
        btLogin = findViewById(R.id.login);
        btLogout = findViewById(R.id.logout);
        btQuanlyCauhoi = findViewById(R.id.quanlycauhoiguilen);
        btQuanlyNguoidung = findViewById(R.id.quanlynguoichoi);
        tvUser = findViewById(R.id.text_user);
        layoutAdmin = findViewById(R.id.layout_admin);
        btQLCauhoi = findViewById(R.id.quanlycauhoi);
    }


    private void addListener() {
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard.this);
                builder.setMessage("Bạn muốn đăng xuất ?").setTitle("Logout");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        btLogout.setVisibility(View.GONE);
                        layoutAdmin.setVisibility(View.GONE);
                        btLogin.setVisibility(View.VISIBLE);
                        tvUser.setVisibility(View.GONE);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btQuanlyCauhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, QuanlycauhoiActivity.class));
            }
        });
        btQuanlyNguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, QuanlynguoichoiActivity.class));
            }
        });
        btQLCauhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, QLCauhoi.class));
            }
        });
    }
}