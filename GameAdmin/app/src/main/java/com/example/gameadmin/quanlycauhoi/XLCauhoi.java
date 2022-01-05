package com.example.gameadmin.quanlycauhoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gameadmin.R;
import com.example.gameadmin.quanlycauhoiguilen.Cauhoi;
import com.example.gameadmin.quanlycauhoiguilen.XulycauhoiActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class XLCauhoi extends AppCompatActivity {

    EditText edNoidung, edA, edB, edC, edD, edgiaithich;
    RadioButton rbA, rbB, rbC, rbD;
    Button btUpdate, btDelete, btCancel;
    RadioGroup radioGroup;
    String idCauhoi;

    Cauhoi c;
    int id;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_l_cauhoi);
        init();
        loadCauhoi();
        addListener();
    }
    private void init() {
        edNoidung = findViewById(R.id.ed_cauhoi_noidung);
        edA = findViewById(R.id.ed_cauhoi_a);
        edB = findViewById(R.id.ed_cauhoi_b);
        edC = findViewById(R.id.ed_cauhoi_c);
        edD = findViewById(R.id.ed_cauhoi_d);
        edgiaithich = findViewById(R.id.ed_cauhoi_giaithich);

        radioGroup = findViewById(R.id.radio_group);
        rbA = findViewById(R.id.rb_cauhoi_a);
        rbB = findViewById(R.id.rb_cauhoi_b);
        rbC = findViewById(R.id.rb_cauhoi_c);
        rbD = findViewById(R.id.rb_cauhoi_d);

        btUpdate = findViewById(R.id.bt_update);
        btDelete = findViewById(R.id.bt_delete);
        btCancel = findViewById(R.id.bt_cancel);
    }
    private void loadCauhoi(){
        Intent intent = getIntent();
        c = (Cauhoi) intent.getSerializableExtra("item_cauhoi");
        id = intent.getIntExtra("id",-1);
        idCauhoi = c.getId();

        edNoidung.setText(c.getCauhoi());
        edA.setText(c.getA());
        edB.setText(c.getB());
        edC.setText(c.getC());
        edD.setText(c.getD());

        switch (c.getDapan()){
            case "a":{
                rbA.setChecked(true);
                break;
            }
            case "b":{
                rbB.setChecked(true);
                break;
            }
            case "c":{
                rbC.setChecked(true);
                break;
            }
            case "d": {
                rbD.setChecked(true);
                break;
            }
        }
        edgiaithich.setText(c.getGiaidap());
    }

    private void addListener(){
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noidung = edNoidung.getText().toString();
                String dapanA = edA.getText().toString();
                String dapanB = edB.getText().toString();
                String dapanC = edC.getText().toString();
                String dapanD = edD.getText().toString();
                String giaithich = edgiaithich.getText().toString();
                String dapan = "";

                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb_cauhoi_a : {
                        dapan = "a";
                        break;
                    }
                    case R.id.rb_cauhoi_b: {
                        dapan = "b";
                        break;
                    }
                    case R.id.rb_cauhoi_c: {
                        dapan = "c";
                        break;
                    }
                    case R.id.rb_cauhoi_d:{
                        dapan = "d";
                        break;
                    }
                }
                if(noidung.equals("")){
                    Toast.makeText(XLCauhoi.this,"Không được để trống nội dung",
                            Toast.LENGTH_SHORT).show();
                }
                else if(dapanA.equals("")||dapanB.equals("")||dapanC.equals("")||dapanD.equals("")){
                    Toast.makeText(XLCauhoi.this,"Phải nhập đủ các đáp án",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Cauhoi cauhoi = new Cauhoi(noidung, dapanA,dapanB,dapanC,dapanD,dapan,giaithich);
                    updateCauhoi(cauhoi);
                }
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDelete();
            }
        });
    }

    private void updateCauhoi(final Cauhoi c){
        Map<String, Object> cauhoi = new HashMap<>();
        cauhoi.put("cauhoi", c.getCauhoi());
        cauhoi.put("a", c.getA());
        cauhoi.put("b", c.getB());
        cauhoi.put("c", c.getC());
        cauhoi.put("d", c.getD());
        cauhoi.put("dapan", c.getDapan());
        cauhoi.put("giaidap", c.getGiaidap());
        db.collection("cauhoi").document(idCauhoi)
                .update(cauhoi)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(XLCauhoi.this,"Update Success!",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("item_update",id);
                        intent.putExtra("cauhoi",c);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });

    }
    private void deleteCauhoi(){
        DocumentReference docRef = db.collection("cauhoi").document(idCauhoi);
        docRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent();
                intent.putExtra("item_delete",id);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    private void showDialogDelete(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn chắc chắn muốn xóa câu hỏi này ?").setTitle("Delete");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteCauhoi();
                Toast.makeText(XLCauhoi.this,"Delete Success!",
                        Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
