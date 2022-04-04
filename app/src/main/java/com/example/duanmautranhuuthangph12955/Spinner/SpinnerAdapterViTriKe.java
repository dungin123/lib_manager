package com.example.duanmautranhuuthangph12955.Spinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmautranhuuthangph12955.MyModel.MyThemKeSach;
import com.example.duanmautranhuuthangph12955.R;

import java.util.ArrayList;

public class SpinnerAdapterViTriKe extends BaseAdapter {

    final ArrayList<MyThemKeSach> myThemKeSaches ;

    public SpinnerAdapterViTriKe(ArrayList<MyThemKeSach> myThemKeSaches) {
        this.myThemKeSaches = myThemKeSaches;
    }

    @Override
    public int getCount() {
        return myThemKeSaches.size();
    }

    @Override
    public Object getItem(int position) {
        return myThemKeSaches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myThemKeSaches.get(position).getId_KeSach();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView  ;
        if (convertView == null){
            itemView = View.inflate(parent.getContext(), R.layout.item_spinner_ke_sach,null);
        }else
            itemView =convertView ;
        MyThemKeSach myThemKeSach = (MyThemKeSach) myThemKeSaches.get(position);
        TextView tv_Item =(TextView) itemView.findViewById(R.id.tv_item_nameKeSach);

        tv_Item.setText(myThemKeSach.getTen_KeSach()+"");
        return itemView;
    }
}
