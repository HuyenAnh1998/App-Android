package com.example.gameadmin.start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gameadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {
    EditText edEmail, edPass;
    Button btLogin;
    Context context;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;
        edEmail = findViewById(R.id.et_signIn_email);
        edPass = findViewById(R.id.et_signIn_password);
        btLogin = findViewById(R.id.btn_signIn);
        addListener();
    }

    private void addListener() {
        btLogin.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View view){
                final String mail = edEmail.getText().toString();
                final String pass = edPass.getText().toString();

                if (mail.equals("") || pass.equals("")) {
                    Toast.makeText(context, "Vui lòng nhập đủ thông tin!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        checkAdmin(mail);
                                    }
                                    else{
                                        Toast.makeText(context,"login fail",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    }
                }
            });
        }
        private void login (String email) {
            sharedPreferences = getSharedPreferences(String.valueOf(R.string.email),MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(String.valueOf(R.string.email),email);
            editor.commit();
            Intent intent = new Intent(context, DashBoard.class);
            intent.putExtra(String.valueOf(R.string.email),email);
            startActivity(intent);
            finish();
        }
    private void checkAdmin(final String email){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("admin")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int kt =0;
                            for (QueryDocumentSnapshot dc : task.getResult()) {
                                String mail = (String) dc.get("mail");
                                if (email.equals(mail)) {
                                    kt =1;
                                    login(email);
                                    break;
                                }
                            }
                            if(kt==0){
                                Toast.makeText(context,"Tài khoản không chính xác!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    }
