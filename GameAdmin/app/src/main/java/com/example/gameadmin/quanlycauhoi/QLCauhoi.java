package com.example.gameadmin.quanlycauhoi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gameadmin.R;
import com.example.gameadmin.quanlycauhoiguilen.Cauhoi;
import com.example.gameadmin.quanlycauhoiguilen.QuanlycauhoiActivity;
import com.example.gameadmin.quanlycauhoiguilen.QuanlycauhoiAdapter;
import com.example.gameadmin.quanlycauhoiguilen.XulycauhoiActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class QLCauhoi extends AppCompatActivity {
    ListView listCauhoi;
    QuanlycauhoiAdapter adapter;
    ArrayList<Cauhoi> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_cauhoi);
        listCauhoi = findViewById(R.id.list_cauhoi);
        adapter = new QuanlycauhoiAdapter(list);
        listCauhoi.setAdapter(adapter);

        getListCauhoi();
        listCauhoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QLCauhoi.this, XLCauhoi.class);
                intent.putExtra("item_cauhoi",list.get(i));
                intent.putExtra("id",i);
                startActivityForResult(intent,123);
            }
        });
    }
    private void getListCauhoi() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("cauhoi")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot dc: task.getResult()){
                                String cauhoi = (String) dc.get("cauhoi");
                                String a = (String) dc.get("a");
                                String b = (String) dc.get("b");
                                String c = (String) dc.get("c");
                                String d = (String) dc.get("d");
                                String dapan = (String) dc.get("dapan");
                                String giaidap = (String) dc.get("giaidap");
                                String id = dc.getId();
                                Cauhoi ch = new Cauhoi(cauhoi,a,b,c,d,dapan,giaidap,id);
                                if(cauhoi!=null&& !cauhoi.equals("")) {
                                    list.add(ch);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                        else Log.d("hihi", "onComplete: fail");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123&&resultCode== RESULT_OK){
            int index = data.getIntExtra("item_delete",-1);
            if(index!=-1){
                list.remove(index);
                adapter.notifyDataSetChanged();
            }
            else{
                index = data.getIntExtra("item_update",-1);
                if(index!=-1){
                    Cauhoi choi = (Cauhoi) data.getSerializableExtra("cauhoi");
                    list.set(index,choi);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
