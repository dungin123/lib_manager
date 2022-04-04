package com.example.duanmautranhuuthangph12955.MyModel;

public class MyThemGhiChu {

    int  id_ghichu ;
    String tieude ;
    String noidung ;

    public static final String TB_NAME = "ghichu";
    public static final String COL_NAME_ID_GC = "id_ghichu";
    public static final String TB_NAME_TD_GC = "tieude";
    public static final String COL_NAME_ND_GC = "noidung";

    public int getId_ghichu() {
        return id_ghichu;
    }

    public void setId_ghichu(int id_ghichu) {
        this.id_ghichu = id_ghichu;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
