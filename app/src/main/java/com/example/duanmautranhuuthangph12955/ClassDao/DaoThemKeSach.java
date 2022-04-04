 package com.example.duanmautranhuuthangph12955.ClassDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.duanmautranhuuthangph12955.MyModel.MyThemKeSach;

import java.util.ArrayList;

import MyData.MyDatabaseHelper;

public class DaoThemKeSach {
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase database;
    Context context;

    public DaoThemKeSach(Context context) {
        myDatabaseHelper = new MyDatabaseHelper(context);
        database = myDatabaseHelper.getWritableDatabase();
        this.context = context;
    }

    public long addKeSach(MyThemKeSach myThemKeSach) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyThemKeSach.COLUMN_TENKESACH, myThemKeSach.getTen_KeSach());
        contentValues.put(MyThemKeSach.COLUMN_NGAYTHEMKESACH, myThemKeSach.getNgay_ThemKeSach());
        contentValues.put(MyThemKeSach.COLUMN_IDTK, myThemKeSach.getId_tk());

        long res = database.insert(MyThemKeSach.TABLE_NAMEKESACH, null, contentValues);
        return res;
    }

    public ArrayList LayDuLieuKeSach() {
        ArrayList<MyThemKeSach> arrayList = new ArrayList<>();

        String [] data ={"id_kesach","ten_kesach","so_luongsach","ngay_thekesach","id_dk"};
        Cursor cursor = database.query("add_kesach",data,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String tenKe = cursor.getString(1);
            String soLuong = cursor.getString(2);
            String ngayThang = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            MyThemKeSach myThemKeSach = new MyThemKeSach();

            myThemKeSach.setId_KeSach(id);
            myThemKeSach.setTen_KeSach(tenKe);
            myThemKeSach.setSo_LuongSach(soLuong);
            myThemKeSach.setNgay_ThemKeSach(ngayThang);
            myThemKeSach.setId_tk(id_tk);

            arrayList.add(myThemKeSach);
            cursor.moveToNext();

        }
        return arrayList;
    }

    public Cursor readAllData() {
        MyThemKeSach myThemKeSach = new MyThemKeSach();
        String query = "SELECT * FROM " + myThemKeSach.TABLE_NAMEKESACH;

        SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void xoaHetDuLieu() {
        MyThemKeSach myThemKeSach = new MyThemKeSach();
        SQLiteDatabase database = myDatabaseHelper.getWritableDatabase();
        String s = "DELETE FROM " + myThemKeSach.TABLE_NAMEKESACH;
        database.execSQL(s);
    }

    public void xoaTenKe(String row_id) { // hàng id
        long ketqua = database.delete(MyThemKeSach.TABLE_NAMEKESACH, "id_kesach = ? ", new String[]{row_id});
        if (ketqua == -1) {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Xóa thành công ", Toast.LENGTH_LONG).show();
        }
    }

    public int chinhSuaTenKeSach(MyThemKeSach myThemKeSach) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyThemKeSach.COLUMN_TENKESACH, myThemKeSach.getTen_KeSach());

        return database.update(MyThemKeSach.TABLE_NAMEKESACH, contentValues, "id_kesach =  " + myThemKeSach.getId_KeSach(), null);
    }
}
