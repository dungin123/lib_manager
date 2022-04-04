package com.example.duanmautranhuuthangph12955.RecyclerViewAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmautranhuuthangph12955.MyModel.MyThemGhiChu;
import com.example.duanmautranhuuthangph12955.R;

import java.util.ArrayList;

public class GhiChuAdapter extends BaseAdapter {

    final ArrayList<MyThemGhiChu> myThemGhiChus;

    public GhiChuAdapter(ArrayList<MyThemGhiChu> myThemGhiChus) {
        this.myThemGhiChus = myThemGhiChus;
    }


    @Override
    public int getCount() {
        return myThemGhiChus.size();
    }


    @Override
    public Object getItem(int position) {
        return myThemGhiChus.get(position);
    }


    @Override
    public long getItemId(int position) {
        return myThemGhiChus.get(position).getId_ghichu();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.custom_listview_ghi_chu, null);

        } else {
            view = convertView;
        }
        MyThemGhiChu myThemGhiChu = (MyThemGhiChu) myThemGhiChus.get(position);

        TextView tv_tieuDe = (TextView) view.findViewById(R.id.tv_tieude);
        TextView tv_NoiDung = (TextView) view.findViewById(R.id.tv_noidung);

        tv_tieuDe.setText(myThemGhiChu.getTieude() + "");
        tv_NoiDung.setText(myThemGhiChu.getNoidung() + "");

        return view;
    }
}
