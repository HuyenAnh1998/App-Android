package com.example.gameadmin.quanlycauhoiguilen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gameadmin.R;
import java.util.ArrayList;

public class QuanlycauhoiAdapter extends BaseAdapter {
    ArrayList<Cauhoi> cauhois;

    public QuanlycauhoiAdapter(ArrayList<Cauhoi> cauhois) {
        this.cauhois = cauhois;
    }

    @Override
    public int getCount() {
        return cauhois.size();
    }

    @Override
    public Object getItem(int i) {
        return cauhois.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view ==null)
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quanly_cauhoi,viewGroup,false);

        TextView tvIndex = view.findViewById(R.id.index);
        TextView tvNoidung = view.findViewById(R.id.noidung);

        Cauhoi c = cauhois.get(i);
        tvIndex.setText(""+(i+1));
        tvNoidung.setText(c.getCauhoi());

        return view;
    }
}
