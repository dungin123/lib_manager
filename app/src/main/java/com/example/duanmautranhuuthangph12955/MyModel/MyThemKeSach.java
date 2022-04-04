package com.example.duanmautranhuuthangph12955.MyModel;

public class MyThemKeSach {
    int id_KeSach ;
    String ten_KeSach;
    String so_LuongSach ;
    String ngay_ThemKeSach ;
    int id_tk ;

    public static final String TABLE_NAMEKESACH = "add_kesach";
    public static final String COLUMN_IDKESACH = "id_kesach";
    public static final String COLUMN_TENKESACH = "ten_kesach";
    public static final String COLUMN_SOLUONGSACH = "so_luongsach";
    public static final String COLUMN_NGAYTHEMKESACH = "ngay_thekesach";
    public static final String COLUMN_IDTK = "id_dk";



    public int getId_KeSach() {
        return id_KeSach;
    }

    public void setId_KeSach(int id_KeSach) {
        this.id_KeSach = id_KeSach;
    }

    public String getTen_KeSach() {
        return ten_KeSach;
    }

    public void setTen_KeSach(String ten_KeSach) {
        this.ten_KeSach = ten_KeSach;
    }

    public String getSo_LuongSach() {
        return so_LuongSach;
    }

    public void setSo_LuongSach(String so_LuongSach) {
        this.so_LuongSach = so_LuongSach;
    }

    public String getNgay_ThemKeSach() {
        return ngay_ThemKeSach;
    }

    public void setNgay_ThemKeSach(String ngay_ThemKeSach) {
        this.ngay_ThemKeSach = ngay_ThemKeSach;
    }

    public int getId_tk() {
        return id_tk;
    }

    public void setId_tk(int id_tk) {
        this.id_tk = id_tk;
    }
}
