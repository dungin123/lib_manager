package com.example.duanmautranhuuthangph12955.Spinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmautranhuuthangph12955.MyModel.MyThemDonVi;
import com.example.duanmautranhuuthangph12955.R;

import java.util.ArrayList;

public class SpinnerDonViThem extends BaseAdapter {

    final ArrayList<MyThemDonVi> myThemDonVis;

    public SpinnerDonViThem(ArrayList<MyThemDonVi> myThemDonVis) {
        this.myThemDonVis = myThemDonVis;
    }

    @Override
    public int getCount() {
        return myThemDonVis.size();
    }

    @Override
    public Object getItem(int position) {
        return myThemDonVis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myThemDonVis.get(position).getId_DV();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemview;
        if (convertView == null) {
            itemview = View.inflate(parent.getContext(), R.layout.item_spinner_donvi, null);

        } else
            itemview = convertView;
        MyThemDonVi myThemDonVi = (MyThemDonVi) myThemDonVis.get(position);
        TextView tv_item = (TextView) itemview.findViewById(R.id.tv_spinnerDonVi);

        tv_item.setText(myThemDonVi.getDon_Vi());
        return itemview;
    }


}
