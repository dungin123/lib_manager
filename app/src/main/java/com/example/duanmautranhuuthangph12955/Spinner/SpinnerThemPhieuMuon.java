package com.example.duanmautranhuuthangph12955.Spinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmautranhuuthangph12955.MyModel.MyThemSach;
import com.example.duanmautranhuuthangph12955.R;

import java.util.ArrayList;

public class SpinnerThemPhieuMuon extends BaseAdapter {

    final ArrayList<MyThemSach> myThemSaches;

    public SpinnerThemPhieuMuon(ArrayList<MyThemSach> myThemSaches) {
        this.myThemSaches = myThemSaches;
    }

    @Override
    public int getCount() {
        return myThemSaches.size();
    }

    @Override
    public Object getItem(int position) {
        return myThemSaches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myThemSaches.get(position).getId_tenSach();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemview;
        if (convertView == null) {
            itemview = View.inflate(parent.getContext(), R.layout.item_spinner_donvi, null);

        } else
            itemview = convertView;
        MyThemSach myThemSach = (MyThemSach) myThemSaches.get(position);
        TextView tv_item = (TextView) itemview.findViewById(R.id.tv_spinnerDonVi);

        tv_item.setText(myThemSach.getTs_maSach());
        return itemview;
    }


}
