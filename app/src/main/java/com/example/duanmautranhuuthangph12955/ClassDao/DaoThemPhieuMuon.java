package com.example.duanmautranhuuthangph12955.ClassDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.duanmautranhuuthangph12955.MyModel.MyThemPhieuMuon;

import java.util.ArrayList;

import MyData.MyDatabaseHelper;

public class DaoThemPhieuMuon {

    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase database;
    Context context;

    public DaoThemPhieuMuon(Context context) {
        myDatabaseHelper = new MyDatabaseHelper(context);
        database = myDatabaseHelper.getWritableDatabase();
        this.context = context;
    }

    public long themPhieuMuon(MyThemPhieuMuon myThemPhieuMuon) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyThemPhieuMuon.COLUMN_TRANGTHAIPM, myThemPhieuMuon.getTt_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_TENKHACHHANGPM, myThemPhieuMuon.getTenkh_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_DIACHIPM, myThemPhieuMuon.getDiachi_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_SODTPM, myThemPhieuMuon.getDiachi_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_LOAITHANHTOANPM, myThemPhieuMuon.getLtt_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_MASACHPM, myThemPhieuMuon.getMasach_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_TENSACHPM, myThemPhieuMuon.getTensach_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_NGAYPM, myThemPhieuMuon.getNgay_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_NGAYTRAPM, myThemPhieuMuon.getNgaytra_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_SOLUONGSACHPM, myThemPhieuMuon.getSol_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_DONGIAPM, myThemPhieuMuon.getDongia_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_TONGTINPM, myThemPhieuMuon.getTongtien_pm());

        long res = database.insert(MyThemPhieuMuon.TABLE_NAMETENPM, null, contentValues);
        return res;
    }
    public ArrayList LayDuLieuPhieuMuon (){
        ArrayList<MyThemPhieuMuon> phieuMuons = new ArrayList<>();
        String [] data = {"id_pm","tt_pm","tenkh_pm","diachi_pm","sdt_pm","ltt_pm","masach_pm","tensach_pm",
                "ngay_pm","ngaytra_pm","sol_pm","dongia_pm","tongtien_pm"};

        Cursor cursor = database.query("addphieumuon",data,null,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id_pm = cursor.getInt(0);
            String tt_pm =cursor.getString(1);
            String tenkh_pm =cursor.getString(2);
            String diachi_pm =cursor.getString(3);
            String sdt_pm =cursor.getString(4);
            String ltt_pm =cursor.getString(5);
            String masach_pm =cursor.getString(6);
            String tensach_pm =cursor.getString(7);
            String ngay_pm =cursor.getString(8);
            String ngaytra_pm =cursor.getString(9);
            String sol_pm =cursor.getString(10);
            String dongia_pm =cursor.getString(11);
            String tongtien_pm =cursor.getString(12);

            MyThemPhieuMuon myThemPhieuMuon = new MyThemPhieuMuon();

            myThemPhieuMuon.setId_pm(id_pm);
            myThemPhieuMuon.setTt_pm(tt_pm);
            myThemPhieuMuon.setTenkh_pm(tenkh_pm);
            myThemPhieuMuon.setDiachi_pm(diachi_pm);
            myThemPhieuMuon.setSdt_pm(sdt_pm);
            myThemPhieuMuon.setLtt_pm(ltt_pm);
            myThemPhieuMuon.setMasach_pm(masach_pm);
            myThemPhieuMuon.setTensach_pm(tensach_pm);
            myThemPhieuMuon.setNgay_pm(ngay_pm);
            myThemPhieuMuon.setNgaytra_pm(ngaytra_pm);
            myThemPhieuMuon.setSol_pm(sol_pm);
            myThemPhieuMuon.setDongia_pm(dongia_pm);
            myThemPhieuMuon.setTongtien_pm(tongtien_pm);

            phieuMuons.add(myThemPhieuMuon);

            cursor.moveToNext();
        }
        return phieuMuons ;
    }
    public ArrayList<MyThemPhieuMuon> LayDuLieuPhieuMuon_2 (String row_id){
        ArrayList<MyThemPhieuMuon> arrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM addphieumuon WHERE id_pm = ?  ",new String[]{row_id});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id_pm = cursor.getInt(0);
            String tt_pm =cursor.getString(1);
            String tenkh_pm =cursor.getString(2);
            String diachi_pm =cursor.getString(3);
            String sdt_pm =cursor.getString(4);
            String ltt_pm =cursor.getString(5);
            String masach_pm =cursor.getString(6);
            String tensach_pm =cursor.getString(7);
            String ngay_pm =cursor.getString(8);
            String ngaytra_pm =cursor.getString(9);
            String sol_pm =cursor.getString(10);
            String dongia_pm =cursor.getString(11);
            String tongtien_pm =cursor.getString(12);

            MyThemPhieuMuon myThemPhieuMuon = new MyThemPhieuMuon();

            myThemPhieuMuon.setId_pm(id_pm);
            myThemPhieuMuon.setTt_pm(tt_pm);
            myThemPhieuMuon.setTenkh_pm(tenkh_pm);
            myThemPhieuMuon.setDiachi_pm(diachi_pm);
            myThemPhieuMuon.setSdt_pm(sdt_pm);
            myThemPhieuMuon.setLtt_pm(ltt_pm);
            myThemPhieuMuon.setMasach_pm(masach_pm);
            myThemPhieuMuon.setTensach_pm(tensach_pm);
            myThemPhieuMuon.setNgay_pm(ngay_pm);
            myThemPhieuMuon.setNgaytra_pm(ngaytra_pm);
            myThemPhieuMuon.setSol_pm(sol_pm);
            myThemPhieuMuon.setDongia_pm(dongia_pm);
            myThemPhieuMuon.setTongtien_pm(tongtien_pm);

            arrayList.add(myThemPhieuMuon);

            cursor.moveToNext();
        }
        return arrayList ;
    }
    public void xoaPhieuMuon(String row_id) { // hàng id
        long ketqua = database.delete(MyThemPhieuMuon.TABLE_NAMETENPM, "id_pm = ? ", new String[]{row_id});
        if (ketqua == -1) {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Xóa thành công ", Toast.LENGTH_LONG).show();
        }
    }
    public int chinhSuaPhieuMuon (MyThemPhieuMuon myThemPhieuMuon){
        ContentValues  contentValues = new ContentValues();
        contentValues.put(MyThemPhieuMuon.COLUMN_TRANGTHAIPM,myThemPhieuMuon.getTt_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_TENKHACHHANGPM,myThemPhieuMuon.getTenkh_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_DIACHIPM,myThemPhieuMuon.getDiachi_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_SODTPM,myThemPhieuMuon.getSdt_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_LOAITHANHTOANPM,myThemPhieuMuon.getLtt_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_MASACHPM,myThemPhieuMuon.getMasach_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_TENSACHPM,myThemPhieuMuon.getTensach_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_NGAYPM,myThemPhieuMuon.getNgay_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_NGAYTRAPM,myThemPhieuMuon.getNgaytra_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_SOLUONGSACHPM,myThemPhieuMuon.getSol_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_DONGIAPM,myThemPhieuMuon.getDongia_pm());
        contentValues.put(MyThemPhieuMuon.COLUMN_TONGTINPM,myThemPhieuMuon.getTongtien_pm());

        return database.update(MyThemPhieuMuon.TABLE_NAMETENPM,contentValues,"id_pm = "+myThemPhieuMuon.getId_pm(),null);
    }

}
