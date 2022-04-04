package com.example.duanmautranhuuthangph12955.ClassDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmautranhuuthangph12955.MyModel.MyThemDonVi;

import java.util.ArrayList;

import MyData.MyDatabaseHelper;

public class DaoThemDonVi {

    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase database;
    Context context;

    public DaoThemDonVi(Context context) {
        myDatabaseHelper = new MyDatabaseHelper(context);
        database = myDatabaseHelper.getWritableDatabase();
        this.context = context;
    }


    public long ThemDonVi(MyThemDonVi myThemDonVi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyThemDonVi.COLUMN_DONVI, myThemDonVi.getDon_Vi());
        contentValues.put(MyThemDonVi.COLUMN_DAIDIENDV, myThemDonVi.getDai_DienDonDV());
        contentValues.put(MyThemDonVi.COLUMN_DIACHI, myThemDonVi.getDia_ChiDV());
        contentValues.put(MyThemDonVi.COLUMN_SODTDV, myThemDonVi.getSo_DTDonVi());
        contentValues.put(MyThemDonVi.COLUMN_IDTK, myThemDonVi.getId_tk());
        contentValues.put(MyThemDonVi.COLUMN_IDNV, myThemDonVi.getId_nv());

        long res = database.insert(MyThemDonVi.TABLE_NAMETENDV, null, contentValues);
        return res;
    }

    public ArrayList LayDuLieuDonVi() {
        ArrayList<MyThemDonVi> list = new ArrayList<>();

        String[] data = {"id_donvi", "donvi", "daidiendv", "diachidv", "sodienthoaidv", "id_dk", "id_nhanvien"};
        Cursor cursor = database.query("themdonvi", data, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String donVi = cursor.getString(1);
            String dadien = cursor.getString(2);
            String diaChi = cursor.getString(3);
            String sdt = cursor.getString(4);
            int id_dk = cursor.getInt(5);
            int _id_nv = cursor.getInt(6);

            MyThemDonVi myThemDonVi = new MyThemDonVi();

            myThemDonVi.setId_DV(id);
            myThemDonVi.setDon_Vi(donVi);
            myThemDonVi.setDai_DienDonDV(dadien);
            myThemDonVi.setDia_ChiDV(diaChi);
            myThemDonVi.setSo_DTDonVi(sdt);
            myThemDonVi.setId_tk(id_dk);
            myThemDonVi.setId_nv(_id_nv);

            list.add(myThemDonVi);
            cursor.moveToNext();
        }
        return list;
    }
    public int xoaTenDonVi(String row_id) {
        return database.delete("themdonvi", "id_donvi= ? ", new String[]{row_id});
    }
    public int chinhSuaDonVi (MyThemDonVi myThemDonVi){
        ContentValues  contentValues = new ContentValues();
        contentValues.put(MyThemDonVi.COLUMN_DONVI,myThemDonVi.getDon_Vi());
        contentValues.put(MyThemDonVi.COLUMN_DAIDIENDV,myThemDonVi.getDai_DienDonDV());
        contentValues.put(MyThemDonVi.COLUMN_DIACHI,myThemDonVi.getDia_ChiDV());
        contentValues.put(MyThemDonVi.COLUMN_SODTDV,myThemDonVi.getSo_DTDonVi());

        return database.update(MyThemDonVi.TABLE_NAMETENDV,contentValues,"id_donvi = "+myThemDonVi.getId_DV(),null);
    }
}
