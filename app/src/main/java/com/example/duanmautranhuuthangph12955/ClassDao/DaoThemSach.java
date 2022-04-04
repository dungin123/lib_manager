package com.example.duanmautranhuuthangph12955.ClassDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmautranhuuthangph12955.MyModel.MyThemSach;

import java.util.ArrayList;
import java.util.List;

import MyData.MyDatabaseHelper;

public class DaoThemSach {

    MyDatabaseHelper myDatabaseHelper ;
    SQLiteDatabase database ;
    Context context ;

    public DaoThemSach(Context context) {
        myDatabaseHelper = new MyDatabaseHelper(context);
        database = myDatabaseHelper.getWritableDatabase();
        this.context = context;
    }
    public long  ThemSach(MyThemSach myThemSach){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyThemSach.COLUMN_MASACH,myThemSach.getTs_maSach());
        contentValues.put(MyThemSach.COLUMN_TENSACH,myThemSach.getTs_tenSach());
        contentValues.put(MyThemSach.COLUMN_NHAXB,myThemSach.getTs_nhaXb());
        contentValues.put(MyThemSach.COLUMN_SOTRANG,myThemSach.getTs_soTrang());
        contentValues.put(MyThemSach.COLUMN_THELOAI,myThemSach.getTs_theLoai());
        contentValues.put(MyThemSach.COLUMN_LOAISACH,myThemSach.getTs_loaiSach());
        contentValues.put(MyThemSach.COLUMN_DONVI,myThemSach.getTs_donVi());
        contentValues.put(MyThemSach.COLUMN_LOAITHANHTOAN,myThemSach.getTs_loaiThanhToan());
        contentValues.put(MyThemSach.COLUMN_NGAYTHEMSACH,myThemSach.getTs_ntNam());
        contentValues.put(MyThemSach.COLUMN_SOLUONG,myThemSach.getTs_soLuongSach());
        contentValues.put(MyThemSach.COLUMN_IMGSACH,myThemSach.getTs_imgLoad());
        contentValues.put(MyThemSach.COLUMN_SOTIEN,myThemSach.getTs_soTienSach());
        contentValues.put(MyThemSach.COLUMN_TONGSOTIEN,myThemSach.getTs_tongSoTien());
        contentValues.put(MyThemSach.COLUMN_IDKESACH,myThemSach.getId_KeSach());
        contentValues.put(MyThemSach.COLUMN_IDTK,myThemSach.getId_tk());

        long res = database.insert(MyThemSach.TABLE_NAMETENSACH,null,contentValues);
        return res ;
    }
    public ArrayList<MyThemSach> LayDuLieuSach (String _id_keSach){
        ArrayList<MyThemSach> arrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM themSach WHERE id_KeSach = ?  ",new String[]{_id_keSach});

        cursor.moveToFirst();
        while ((!cursor.isAfterLast())){
            int id =cursor.getInt(0);
            String maSach =cursor.getString(1);
            String tenSach =cursor.getString(2);
            String nhaXuatBan =cursor.getString(3);
            String soTrang =cursor.getString(4);
            String theToai =cursor.getString(5);
            String loaiSach =cursor.getString(6);
            String donvi =cursor.getString(7);
            String loaithanhtoan =cursor.getString(8);
            String ngayThemSach =cursor.getString(9);
            String soLuong =cursor.getString(10);
             byte [] imgSach =cursor.getBlob(cursor.getColumnIndex("ts_imgLoad"));
            String soTien =cursor.getString(12);
            String tongSoTien =cursor.getString(13);
            int id_keSach  =cursor.getInt(14);
            int id_dk =cursor.getInt(15);

            MyThemSach myThemSach = new MyThemSach();

            myThemSach.setId_tenSach(id);
            myThemSach.setTs_maSach(maSach);
            myThemSach.setTs_tenSach(tenSach);
            myThemSach.setTs_nhaXb(nhaXuatBan);
            myThemSach.setTs_soTrang(soTrang);
            myThemSach.setTs_theLoai(theToai);
            myThemSach.setTs_loaiSach(loaiSach);
            myThemSach.setTs_donVi(donvi);
            myThemSach.setTs_loaiThanhToan(loaithanhtoan);
            myThemSach.setTs_ntNam(ngayThemSach);
            myThemSach.setTs_soLuongSach(soLuong);
            myThemSach.setTs_imgLoad(imgSach);
            myThemSach.setTs_soTienSach(soTien);
            myThemSach.setTs_tongSoTien(tongSoTien);
            myThemSach.setId_tenSach(id_keSach);
            myThemSach.setId_tenSach(id_dk);

            arrayList.add(myThemSach);
            cursor.moveToNext();
        }
        return arrayList ;
    }
    public ArrayList _LayDuLieuSach (){
        ArrayList<MyThemSach> arrayList = new ArrayList<>();
        String[] data = {"id_tenSach", "ts_maSach", "ts_tenSach", "ts_nhaXb", "ts_soTrang", "ts_theLoai", "ts_loaiSach", "ts_donVi", "ts_loaiThanhToan", "ts_ntNam", "ts_soLuongSach", "ts_imgLoad", "ts_soTien", "ts_tongTien", "id_KeSach", "id_dk"};
        Cursor cursor = database.query("themSach", data, null, null, null, null, null);
        cursor.moveToFirst();
        while ((!cursor.isAfterLast())){
            int id =cursor.getInt(0);
            String maSach =cursor.getString(1);
            String tenSach =cursor.getString(2);
            String nhaXuatBan =cursor.getString(3);
            String soTrang =cursor.getString(4);
            String theToai =cursor.getString(5);
            String loaiSach =cursor.getString(6);
            String donvi =cursor.getString(7);
            String loaithanhtoan =cursor.getString(8);
            String ngayThemSach =cursor.getString(9);
            String soLuong =cursor.getString(10);
            byte [] imgSach =cursor.getBlob(cursor.getColumnIndex("ts_imgLoad"));
            String soTien =cursor.getString(12);
            String tongSoTien =cursor.getString(13);
            int id_keSach  =cursor.getInt(14);
            int id_dk =cursor.getInt(15);

            MyThemSach myThemSach = new MyThemSach();

            myThemSach.setId_tenSach(id);
            myThemSach.setTs_maSach(maSach);
            myThemSach.setTs_tenSach(tenSach);
            myThemSach.setTs_nhaXb(nhaXuatBan);
            myThemSach.setTs_soTrang(soTrang);
            myThemSach.setTs_theLoai(theToai);
            myThemSach.setTs_loaiSach(loaiSach);
            myThemSach.setTs_donVi(donvi);
            myThemSach.setTs_loaiThanhToan(loaithanhtoan);
            myThemSach.setTs_ntNam(ngayThemSach);
            myThemSach.setTs_soLuongSach(soLuong);
            myThemSach.setTs_imgLoad(imgSach);
            myThemSach.setTs_soTienSach(soTien);
            myThemSach.setTs_tongSoTien(tongSoTien);
            myThemSach.setId_tenSach(id_keSach);
            myThemSach.setId_tenSach(id_dk);

            arrayList.add(myThemSach);
            cursor.moveToNext();
        }
        return arrayList ;
    }

    public ArrayList<MyThemSach> LayDuLieuSach_2 (String id_sach){
        ArrayList<MyThemSach> arrayList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM themSach WHERE id_tenSach = ?  ",new String[]{id_sach});

        cursor.moveToFirst();
        while ((!cursor.isAfterLast())){
            int id =cursor.getInt(0);
            String maSach =cursor.getString(1);
            String tenSach =cursor.getString(2);
            String nhaXuatBan =cursor.getString(3);
            String soTrang =cursor.getString(4);
            String theToai =cursor.getString(5);
            String loaiSach =cursor.getString(6);
            String donvi =cursor.getString(7);
            String loaithanhtoan =cursor.getString(8);
            String ngayThemSach =cursor.getString(9);
            String soLuong =cursor.getString(10);
             byte [] imgSach =cursor.getBlob(cursor.getColumnIndex("ts_imgLoad"));
            String soTien =cursor.getString(12);
            String tongSoTien =cursor.getString(13);
            int id_keSach  =cursor.getInt(14);
            int id_dk =cursor.getInt(15);

            MyThemSach myThemSach = new MyThemSach();

            myThemSach.setId_tenSach(id);
            myThemSach.setTs_maSach(maSach);
            myThemSach.setTs_tenSach(tenSach);
            myThemSach.setTs_nhaXb(nhaXuatBan);
            myThemSach.setTs_soTrang(soTrang);
            myThemSach.setTs_theLoai(theToai);
            myThemSach.setTs_loaiSach(loaiSach);
            myThemSach.setTs_donVi(donvi);
            myThemSach.setTs_loaiThanhToan(loaithanhtoan);
            myThemSach.setTs_ntNam(ngayThemSach);
            myThemSach.setTs_soLuongSach(soLuong);
            myThemSach.setTs_imgLoad(imgSach);
            myThemSach.setTs_soTienSach(soTien);
            myThemSach.setTs_tongSoTien(tongSoTien);
            myThemSach.setId_tenSach(id_keSach);
            myThemSach.setId_tenSach(id_dk);

            arrayList.add(myThemSach);
            cursor.moveToNext();
        }
        return arrayList ;
    }
    public int xoaTenSach (String row_id){
       return  database.delete(MyThemSach.TABLE_NAMETENSACH,"id_tenSach= ? ",new String[]{row_id});
    }
    public int chinhSuaTenSach (MyThemSach  myThemSach, String id_sach){
        ContentValues  contentValues = new ContentValues();

        contentValues.put(MyThemSach.COLUMN_MASACH,myThemSach.getTs_maSach());
        contentValues.put(MyThemSach.COLUMN_TENSACH,myThemSach.getTs_tenSach());
        contentValues.put(MyThemSach.COLUMN_NHAXB,myThemSach.getTs_nhaXb());
        contentValues.put(MyThemSach.COLUMN_SOTRANG,myThemSach.getTs_soTrang());
        contentValues.put(MyThemSach.COLUMN_THELOAI,myThemSach.getTs_theLoai());
        contentValues.put(MyThemSach.COLUMN_NGAYTHEMSACH,myThemSach.getTs_ntNam());
        contentValues.put(MyThemSach.COLUMN_SOLUONG,myThemSach.getTs_soLuongSach());
        contentValues.put(MyThemSach.COLUMN_SOTIEN,myThemSach.getTs_soTienSach());
        contentValues.put(MyThemSach.COLUMN_TONGSOTIEN,myThemSach.getTs_tongSoTien());

        return database.update(MyThemSach.TABLE_NAMETENSACH,contentValues,"id_tenSach = ? ",new String[]{id_sach});
    }

    public String[] SelectAllData_maSach() {

        try {
            String arrData[] = null;

            database = myDatabaseHelper.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  ts_maSach FROM " + MyThemSach.TABLE_NAMETENSACH;
            Cursor cursor = database.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()];

                    int i= 0;
                    do {
                        arrData[i] = cursor.getString(0);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }

    }
    public String[] SelectAllData_tenSach() {

        try {
            String arrData[] = null;

            database = myDatabaseHelper.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  ts_tenSach FROM " + MyThemSach.TABLE_NAMETENSACH;
            Cursor cursor = database.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()];

                    int i= 0;
                    do {
                        arrData[i] = cursor.getString(0);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }
    }
    public MyThemSach getMaSach(String _id) {
        String sql = "SELECT * FROM themSach WHERE ts_maSach =? ";
        List<MyThemSach> myThemSaches = new ArrayList<>();
        myThemSaches = getData(sql,_id);
        return myThemSaches.get(0);
    }
    public List<MyThemSach> getData(String sql, String... selectionArgs) {
        List<MyThemSach> myThemSaches = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            MyThemSach myThemSach = new MyThemSach();
            myThemSach.setId_tenSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MyThemSach.COLUMN_IDTENSACH))));
            myThemSach.setTs_maSach(cursor.getString(cursor.getColumnIndex(MyThemSach.COLUMN_MASACH)));
            myThemSaches.add(myThemSach);
        }
        return myThemSaches;
    }
}
