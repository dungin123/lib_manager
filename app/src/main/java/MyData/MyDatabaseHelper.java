package MyData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String TB_NAME = "Duanmau.db";
    public static final int DB_VESION = 1;

    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, TB_NAME, null, DB_VESION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String taiKhoan = "CREATE TABLE dk_tai_khoan (id_dk INTEGER NOT NULL,ho_ten INTEGER,ten_dang_nhap TEXT,email TEXT,sodienthoai VARCHAR(50),matkhau TEXT,xacnhanmatkhau TEXT,namsinh DATE,gioitinh BIT,vaitro TEXT,PRIMARY KEY(id_dk AUTOINCREMENT))";
        db.execSQL(taiKhoan);

        String addNhanVien = "CREATE TABLE addnhanvien (id_nhanvien INTEGER NOT NULL, ten_nhanvien TEXT,ten_dangnhapnv TEXT,email_nhanvien TEXT,sodienthoai_nhanvien,namsinh_nhanvien,gioitinh_nhanvien,mapin_nhanvien TEXT,xacnhan_mapin TEXT,vaitro_nhanvien BIT,id_dk INTEGER,PRIMARY KEY(id_nhanvien AUTOINCREMENT),FOREIGN KEY(id_dk) REFERENCES dk_tai_khoan(id_dk) ON DELETE CASCADE)";
        db.execSQL(addNhanVien);

        String addKeSach = "CREATE TABLE add_kesach (id_kesach INTEGER NOT NULL,ten_kesach TEXT,so_luongsach TEXT,ngay_thekesach TEXT, id_dk INTEGER,PRIMARY KEY(id_kesach AUTOINCREMENT),FOREIGN KEY(id_dk) REFERENCES dk_tai_khoan(id_dk))";
        db.execSQL(addKeSach);
        db.execSQL("INSERT INTO add_kesach VALUES('1','Sách Công nghệ TT',0,'',0)");
        db.execSQL("INSERT INTO add_kesach VALUES('2','Sách Văn Học',0,'',0)");
        db.execSQL("INSERT INTO add_kesach VALUES('3','Sách Giả thuật',0,'',0)");

        String addSach = "CREATE TABLE themSach (id_tenSach INTEGER NOT NULL,ts_maSach TEXT,ts_tenSach TEXT,ts_nhaXb TEXT," +
                "ts_soTrang TEXT,ts_theLoai TEXT,ts_loaiSach TEXT,ts_donVi TEXT,ts_loaiThanhToan TEXT,ts_ntNam DATE,ts_soLuongSach TEXT,ts_imgLoad BYTE,ts_soTien TEXT,ts_tongTien TEXT,id_KeSach INTEGER NOT NULL,id_dk INTEGER NOT NULL,FOREIGN KEY(id_KeSach) REFERENCES add_kesach(id_kesach),FOREIGN KEY(id_dk) REFERENCES dk_tai_khoan(id_dk),PRIMARY KEY(id_tenSach AUTOINCREMENT))";
        db.execSQL(addSach);

        String addDonVi = "CREATE TABLE themdonvi (id_donvi INTEGER NOT NULL,donvi TEXT,daidiendv TEXT,diachidv TEXT,sodienthoaidv TEXT, id_dk INTEGER NOT NULL,id_nhanvien INTEGER NOT NULL,PRIMARY KEY(id_donvi AUTOINCREMENT),FOREIGN KEY(id_dk) REFERENCES dk_tai_khoan(id_dk),FOREIGN KEY(id_nhanvien) REFERENCES addnhanvien(id_nhanvien))";
        db.execSQL(addDonVi);
        db.execSQL("INSERT INTO themdonvi VALUES('1','Khách lẻ','Khách lẻ','Trống','Trống',0,0)");
        db.execSQL("INSERT INTO themdonvi VALUES('2','CTCP Sách Thái Nguyên','Trần Hữu Thắng','36.Hoàng Văn Thụ','0987654321',0,0)");
        db.execSQL("INSERT INTO themdonvi VALUES('3','CTCP Sách Thái Nguyên','Trần Hữu Thắng','36.Hoàng Văn Thụ','0987654321',0,0)");

        String addPhieuMuon = "CREATE TABLE addphieumuon (id_pm INTEGER NOT NULL,tt_pm TEXT,tenkh_pm TEXT,diachi_pm TEXT,sdt_pm TEXT,ltt_pm TEXT,masach_pm TEXT,tensach_pm TEXT,ngay_pm TEXT,ngaytra_pm TEXT,sol_pm TEXT,dongia_pm TEXT,tongtien_pm TEXT,PRIMARY KEY(id_pm AUTOINCREMENT))";
        db.execSQL(addPhieuMuon);

        String ghiChu = "CREATE TABLE ghichu(id_ghichu INTEGER NOT NULL,tieude TEXT,noidung TEXT,PRIMARY KEY(id_ghichu AUTOINCREMENT))";
        db.execSQL(ghiChu);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Tạo tài khoản andmin
    public Boolean insertData(String hoTen, String tenTaiKhoan, String email, String soDienthoai, String matKhau, String xacnhan, String namSinh, String gioiTinh, String vaiTro) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ho_ten", hoTen);
        contentValues.put("ten_dang_nhap", tenTaiKhoan);
        contentValues.put("sodienthoai", soDienthoai);
        contentValues.put("email", email);
        contentValues.put("matkhau", matKhau);
        contentValues.put("xacnhanmatkhau", xacnhan);
        contentValues.put("namsinh", namSinh);
        contentValues.put("gioitinh", gioiTinh);
        contentValues.put("vaitro", vaiTro);

        long res = database.insert("dk_tai_khoan", null, contentValues);
        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean checkTenDangNhap(String tenTaiKhoan) {
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT *FROM dk_tai_khoan WHERE ten_dang_nhap=?", new String[]{tenTaiKhoan});
        Cursor cursor1 = database.rawQuery("SELECT *FROM addnhanvien WHERE ten_dangnhapnv = ?", new String[]{tenTaiKhoan});

        if (cursor.getCount() > 0 || cursor1.getCount() > 0) {
            return true;
        } else {
            return false;
        }


    }

    public Boolean checkMatKhau(String tenTaiKhoan, String matkhau) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT  * FROM dk_tai_khoan WHERE ten_dang_nhap=? AND matkhau =? ", new String[]{tenTaiKhoan, matkhau});
        Cursor cursor1 = database.rawQuery("SELECT *FROM addnhanvien WHERE ten_dangnhapnv = ? AND mapin_nhanvien = ? ", new String[]{tenTaiKhoan, matkhau});
        if (cursor.getCount() > 0 || cursor1.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean CapNhapMatKhau(String username, String passWord) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matkhau", passWord);
        long result = database.update("dk_tai_khoan", contentValues, "ten_dang_nhap = ?", new String[]{username});
        if (result == -1) {
            return false;

        } else {
            return true;
        }
    }

    public String[] getInfo_Admin(String tenTaiKhoan, String matkhau) {
        String[] arrData_admin = new String[10];
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT  * FROM dk_tai_khoan WHERE ten_dang_nhap=? AND matkhau =? ", new String[]{tenTaiKhoan, matkhau});
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                arrData_admin[0] = cursor.getString(0);
                arrData_admin[1] = cursor.getString(1);
                arrData_admin[2] = cursor.getString(2);
                arrData_admin[3] = cursor.getString(3);
                arrData_admin[4] = cursor.getString(4);
                arrData_admin[5] = cursor.getString(5);
                arrData_admin[6] = cursor.getString(6);
                arrData_admin[7] = cursor.getString(7);
                arrData_admin[8] = cursor.getString(8);
                arrData_admin[9] = cursor.getString(9);
            }
        }
        return arrData_admin;
    }

    public String[] getInfo_ThemNhanVien(String tenTaiKhoan, String matkhau) {
        String[] arrData_nhanVien = new String[11];
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor1 = database.rawQuery("SELECT *FROM addnhanvien WHERE ten_dangnhapnv = ? AND mapin_nhanvien = ? ", new String[]{tenTaiKhoan, matkhau});

        if (cursor1.getCount() > 0) {
            if (cursor1.moveToFirst()) {
                arrData_nhanVien[0] = cursor1.getInt(0) + "";
                arrData_nhanVien[1] = cursor1.getString(1);
                arrData_nhanVien[2] = cursor1.getString(2);
                arrData_nhanVien[3] = cursor1.getString(3);
                arrData_nhanVien[4] = cursor1.getString(4);
                arrData_nhanVien[5] = cursor1.getString(5);
                arrData_nhanVien[6] = cursor1.getString(6);
                arrData_nhanVien[7] = cursor1.getString(7);
                arrData_nhanVien[8] = cursor1.getString(8);
                arrData_nhanVien[9] = cursor1.getString(9);
                arrData_nhanVien[10] = cursor1.getString(10);
            }
        }
        return arrData_nhanVien;
    }

    public String[] getInfo_KeSach(String tenKeSach) {
        String[] arrData_keSach = new String[5];
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM add_kesach WHERE ten_kesach = ?", new String[]{tenKeSach});

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                arrData_keSach[0] = cursor.getInt(0) + "";
                arrData_keSach[1] = cursor.getString(1);
                arrData_keSach[2] = cursor.getString(2);
                arrData_keSach[3] = cursor.getString(3);
                arrData_keSach[4] = cursor.getString(4);
            }
        }
        return arrData_keSach;
    }


    public String[] getInfo_ThemSach(String maSach) {
        String[] arrData_tenSach = new String[16];

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM themSach WHERE ts_maSach= ? ", new String[]{maSach});
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                arrData_tenSach[0] = cursor.getInt(0) + "";
                arrData_tenSach[1] = cursor.getString(1);
                arrData_tenSach[2] = cursor.getString(2);
                arrData_tenSach[3] = cursor.getString(3);
                arrData_tenSach[4] = cursor.getString(4);
                arrData_tenSach[5] = cursor.getString(5);
                arrData_tenSach[6] = cursor.getString(6);
                arrData_tenSach[7] = cursor.getString(7);
                arrData_tenSach[8] = cursor.getString(8);
                arrData_tenSach[9] = cursor.getString(9);
                arrData_tenSach[10] = cursor.getString(10);
                arrData_tenSach[11] = cursor.getBlob(cursor.getColumnIndex("ts_imgLoad")) + "";
                arrData_tenSach[12] = cursor.getString(12);
                arrData_tenSach[13] = cursor.getString(13);
                arrData_tenSach[14] = cursor.getInt(14) + "";
                arrData_tenSach[15] = cursor.getInt(15) + "";
            }
        }
        return arrData_tenSach;
    }

    public String[] getInfo_ThemSach_4(String maSach) {
        String[] arrData_tenSach = new String[16];

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM themSach WHERE ts_maSach= ? ", new String[]{maSach});
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                arrData_tenSach[0] = cursor.getInt(0) + "";
                arrData_tenSach[1] = cursor.getString(1);
                arrData_tenSach[2] = cursor.getString(2);
                arrData_tenSach[3] = cursor.getString(3);
                arrData_tenSach[4] = cursor.getString(4);
                arrData_tenSach[5] = cursor.getString(5);
                arrData_tenSach[6] = cursor.getString(6);
                arrData_tenSach[7] = cursor.getString(7);
                arrData_tenSach[8] = cursor.getString(8);
                arrData_tenSach[9] = cursor.getString(9);
                arrData_tenSach[10] = cursor.getString(10);
                arrData_tenSach[11] = cursor.getBlob(cursor.getColumnIndex("ts_imgLoad")) + "";
                arrData_tenSach[12] = cursor.getString(12);
                arrData_tenSach[13] = cursor.getString(13);
                arrData_tenSach[14] = cursor.getInt(14) + "";
                arrData_tenSach[15] = cursor.getInt(15) + "";
            }
        }
        return arrData_tenSach;
    }

    public String[] getInfo_PhieuMuon(String phieuMuon) {

        String[] arrData_tenSach = new String[13];
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM addphieumuon WHERE id_pm= ? ", new String[]{phieuMuon});
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                arrData_tenSach[0] = cursor.getInt(0) + "";
                arrData_tenSach[1] = cursor.getString(1);
                arrData_tenSach[2] = cursor.getString(2);
                arrData_tenSach[3] = cursor.getString(3);
                arrData_tenSach[4] = cursor.getString(4);
                arrData_tenSach[5] = cursor.getString(5);
                arrData_tenSach[6] = cursor.getString(6);
                arrData_tenSach[7] = cursor.getString(7);
                arrData_tenSach[8] = cursor.getString(8);
                arrData_tenSach[9] = cursor.getString(9);
                arrData_tenSach[10] = cursor.getString(10);
                arrData_tenSach[11] = cursor.getString(11);
                arrData_tenSach[12] = cursor.getString(12);

            }
        }
        return arrData_tenSach;
    }

    public String[] getInfo_ThemSach_5(String maSach) {
        String[] arrData_tenSach = new String[16];

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM themSach WHERE ts_maSach= ? ", new String[]{maSach});
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                arrData_tenSach[0] = cursor.getInt(0) + "";
                arrData_tenSach[1] = cursor.getString(1);
                arrData_tenSach[2] = cursor.getString(2);
                arrData_tenSach[3] = cursor.getString(3);
                arrData_tenSach[4] = cursor.getString(4);
                arrData_tenSach[5] = cursor.getString(5);
                arrData_tenSach[6] = cursor.getString(6);
                arrData_tenSach[7] = cursor.getString(7);
                arrData_tenSach[8] = cursor.getString(8);
                arrData_tenSach[9] = cursor.getString(9);
                arrData_tenSach[10] = cursor.getString(10);
                arrData_tenSach[11] = cursor.getBlob(cursor.getColumnIndex("ts_imgLoad")) + "";
                arrData_tenSach[12] = cursor.getString(12);
                arrData_tenSach[13] = cursor.getString(13);
                arrData_tenSach[14] = cursor.getInt(14) + "";
                arrData_tenSach[15] = cursor.getInt(15) + "";
            }
        }
        return arrData_tenSach;
    }


    //end

    //Tạo tài khoản nhân viên

    public Boolean insertDataNhanVien(String hoTenNV, String tenTaiKhoanNV, String emailNV, String soDienThoaiNV, String namSinhNV, String gioiTinhNV, String maPin, String xacNhanMaPin, String vaiTro, String id_admin) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ten_nhanvien", hoTenNV);
        contentValues.put("ten_dangnhapnv ", tenTaiKhoanNV);
        contentValues.put("email_nhanvien ", emailNV);
        contentValues.put("sodienthoai_nhanvien ", soDienThoaiNV);
        contentValues.put("namsinh_nhanvien ", namSinhNV);
        contentValues.put("gioitinh_nhanvien ", gioiTinhNV);
        contentValues.put("mapin_nhanvien", maPin);
        contentValues.put("xacnhan_mapin", xacNhanMaPin);
        contentValues.put("vaitro_nhanvien", vaiTro);
        contentValues.put("id_dk", id_admin);


        long res = database.insert("addnhanvien ", null, contentValues);
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor LayDuLieuBangNV() {
        String sql = "SELECT * FROM addnhanvien ";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(sql, null);
        }
        return cursor;
    }

    public void updateData(String id_nv, String ho_ten, String email, String sdt, String namSinh, String gioiTinh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("ten_nhanvien", ho_ten);
        cv.put("email_nhanvien", email);
        cv.put("sodienthoai_nhanvien", sdt);
        cv.put("namsinh_nhanvien", namSinh);
        cv.put("gioitinh_nhanvien", gioiTinh);

        long result = db.update("addnhanvien", cv, "id_nhanvien=?", new String[]{id_nv});
        if (result == -1) {
            Toast.makeText(context, "Chỉnh sửa thất bại.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Chỉnh sửa thành công.", Toast.LENGTH_SHORT).show();
        }
    }

    public void XoaNhanVien(String id_nv) {
        SQLiteDatabase database = this.getWritableDatabase();
        long res = database.delete("addnhanvien", "id_nhanvien=?", new String[]{id_nv});
        if (res == -1) {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
    }
}
