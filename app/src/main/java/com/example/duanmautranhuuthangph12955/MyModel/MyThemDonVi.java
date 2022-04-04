package com.example.duanmautranhuuthangph12955.MyModel;

public class MyThemDonVi {

    int id_DV ;
    String don_Vi ;
    String dai_DienDonDV ;
    String dia_ChiDV;
    String so_DTDonVi ;
    int id_tk ;
    int id_nv ;

    public static final String TABLE_NAMETENDV = "themdonvi";
    public static final String COLUMN_IDDONVI = "id_donvi";
    public static final String COLUMN_DONVI = "donvi";
    public static final String COLUMN_DAIDIENDV = "daidiendv";
    public static final String COLUMN_DIACHI = "diachidv";
    public static final String COLUMN_SODTDV= "sodienthoaidv";
    public static final String COLUMN_IDTK = "id_dk";
    public static final String COLUMN_IDNV = "id_nhanvien";

    public int getId_DV() {
        return id_DV;
    }

    public void setId_DV(int id_DV) {
        this.id_DV = id_DV;
    }

    public String getDon_Vi() {
        return don_Vi;
    }

    public void setDon_Vi(String don_Vi) {
        this.don_Vi = don_Vi;
    }

    public String getDai_DienDonDV() {
        return dai_DienDonDV;
    }

    public void setDai_DienDonDV(String dai_DienDonDV) {
        this.dai_DienDonDV = dai_DienDonDV;
    }

    public String getDia_ChiDV() {
        return dia_ChiDV;
    }

    public void setDia_ChiDV(String dia_ChiDV) {
        this.dia_ChiDV = dia_ChiDV;
    }

    public String getSo_DTDonVi() {
        return so_DTDonVi;
    }

    public void setSo_DTDonVi(String so_DTDonVi) {
        this.so_DTDonVi = so_DTDonVi;
    }

    public int getId_tk() {
        return id_tk;
    }

    public void setId_tk(int id_tk) {
        this.id_tk = id_tk;
    }

    public int getId_nv() {
        return id_nv;
    }

    public void setId_nv(int id_nv) {
        this.id_nv = id_nv;
    }
}
