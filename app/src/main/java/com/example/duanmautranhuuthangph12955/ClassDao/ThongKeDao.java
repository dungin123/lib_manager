package com.example.duanmautranhuuthangph12955.ClassDao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmautranhuuthangph12955.MyModel.MyThemSach;
import com.example.duanmautranhuuthangph12955.MyModel.Top;

import java.util.ArrayList;
import java.util.List;

import MyData.MyDatabaseHelper;

public class ThongKeDao {
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase database;
    Context context;

    public ThongKeDao(Context context) {
        this.context = context;
        myDatabaseHelper = new MyDatabaseHelper(context);
        database = myDatabaseHelper.getWritableDatabase();

    }

    public double TongtienNhapHang() {
        Cursor cursor = database.rawQuery("SELECT SUM (ts_tongTien) FROM themSach", null);
        cursor.moveToFirst();
        return cursor.getDouble(0);
    }

    public double TongtienXuatHang() {
        Cursor cursor = database.rawQuery("SELECT SUM (tongtien_pm) FROM addphieumuon", null);
        cursor.moveToFirst();
        return cursor.getDouble(0);
    }

    public int TongSoSach() {
        Cursor cursor = database.rawQuery("SELECT SUM (ts_soLuongSach) FROM themSach", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public double TongBetwenNhapSach(String startDay, String endDay) {

        Cursor cursor = database.rawQuery("SELECT SUM(ts_tongTien) FROM themSach WHERE ts_ntNam BETWEEN '" + startDay + "' AND '" + endDay + "'", null);
        cursor.moveToFirst();
        return cursor.getDouble(0);
    }

    public List<Top> getTop() {
        String sql = "SELECT masach_pm,COUNT(masach_pm) AS soLuong FROM addphieumuon GROUP BY masach_pm ORDER BY soLuong DESC LIMIT 10  ";
        List<Top> list = new ArrayList<Top>();
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Top top = new Top();
            DaoThemSach daoThemSach = new DaoThemSach(context);
            MyThemSach myThemSach = daoThemSach.getMaSach(cursor.getString(cursor.getColumnIndex("masach_pm")));
            top.ma_sach = myThemSach.getTs_maSach() ;
            top.so_luong = Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong")));

            list.add(top);

        }
        return list;
    }
}
