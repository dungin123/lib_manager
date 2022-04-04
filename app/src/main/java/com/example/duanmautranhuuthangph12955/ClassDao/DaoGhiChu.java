package com.example.duanmautranhuuthangph12955.ClassDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmautranhuuthangph12955.MyModel.MyThemGhiChu;

import java.util.ArrayList;

import MyData.MyDatabaseHelper;

public class DaoGhiChu {

    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase database;
    Context context ;

    public DaoGhiChu(Context context) {
        myDatabaseHelper = new MyDatabaseHelper(context);
        this.context=context;
        database= myDatabaseHelper.getWritableDatabase();

    }


    public long insert(MyThemGhiChu myThemGhiChu) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyThemGhiChu.TB_NAME_TD_GC, myThemGhiChu.getTieude());
        contentValues.put(MyThemGhiChu.COL_NAME_ND_GC,myThemGhiChu.getNoidung());
        long res = database.insert(MyThemGhiChu.TB_NAME, null, contentValues);
        return res;

    }
    public ArrayList getAll() {
        ArrayList<MyThemGhiChu> myThemGhiChus = new ArrayList<>();
        String [] data = {"id_ghichu","tieude","noidung"};
        Cursor cursor = database.query("ghichu",data,null,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String tieu_de = cursor.getString(1);
            String noi_dung = cursor.getString(2);

            MyThemGhiChu myThemGhiChu = new MyThemGhiChu();
            myThemGhiChu.setId_ghichu(id);
            myThemGhiChu.setTieude(tieu_de );
            myThemGhiChu.setNoidung(noi_dung);

            myThemGhiChus.add(myThemGhiChu);

            cursor.moveToNext();
        }
        return myThemGhiChus;
    }
}
