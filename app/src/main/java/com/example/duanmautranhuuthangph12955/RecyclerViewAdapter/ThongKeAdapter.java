package com.example.duanmautranhuuthangph12955.RecyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmautranhuuthangph12955.MyModel.Top;
import com.example.duanmautranhuuthangph12955.R;

import java.util.ArrayList;

public class ThongKeAdapter extends ArrayAdapter<Top> {

    private Context context;
    private ArrayList<Top> list;

    TextView tv_idSach , tv_soLuong ;

    public ThongKeAdapter(@NonNull Context context, ArrayList<Top> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.item_top10_thongke,null);

        }
        final Top item = list.get(position);
        if (item!=null){
            tv_idSach =view.findViewById(R.id.tv_sach_thongke);
            tv_soLuong =view.findViewById(R.id.tv_soLuong_thongke);
            tv_idSach.setText(item.ma_sach+"");
            tv_soLuong.setText(item.so_luong+"");
        }
        return view ;
    }
}
