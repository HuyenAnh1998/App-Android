package com.example.gameadmin.quanlynguoichoi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gameadmin.R;
import java.util.ArrayList;

public class QuanlynguoichoiAdapter extends BaseAdapter {
    ArrayList<User> users;


    public QuanlynguoichoiAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view ==null)
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quanly_user,viewGroup,false);

        TextView tvIndex = view.findViewById(R.id.index);
        TextView tvUsername = view.findViewById(R.id.user_name);

        User c = users.get(i);
        tvIndex.setText(""+(i+1));
        tvUsername.setText(c.getName());

        return view;
    }
}
