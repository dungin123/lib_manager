package QuanLyThuVien.ui.QuanLyPhieuMuon.CustomPhieuMuon;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemDonVi;
import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemPhieuMuon;
import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemDonVi;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemPhieuMuon;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.Spinner.SpinnerDonViThem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import QuanLyThuVien.MainActivityQuanLyThuVien;

public class ChinhSuaPhieuMuonMainActivity extends AppCompatActivity {
    ArrayList<MyThemPhieuMuon> arrayList_PhieuMuon;
    DaoThemPhieuMuon daoThemPhieuMuon;
    DaoThemDonVi daoThemDonVi = null;
    DaoThemSach daoThemSach;

    RadioGroup rdg_TrangThai;
    Spinner spinnerTenKhacHang, spinnerLoaiThanhToan;
    TextView tv_DiaChi, tv_Sdt, tv_tongTien, tv_ngaytraSach;
    EditText ed_ngayThang, ed_ngayTraSach, ed_soLuongSach, ed_donGia;
    ImageView img1, img2;
    Button btn_Them;
    String id_phieuMuon;
    AutoCompleteTextView tv_maSach, tv_tenSach;


    String src_donVi;
    String src_loaiThanhToan;
    String trangThai;
    SpinnerDonViThem spinnerDonViThem;

    ArrayList<MyThemDonVi> arrayList_DonVi;
    List<String> list_LoaiThanhToan;

    SharedPreferences sharedPreferences_phieuMuon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_phieu_muon_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chỉnh sửa phiếu mượn");
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        anhxaView();

        sharedPreferences_phieuMuon = getSharedPreferences("MyPrefsFile_idPhieuMuon", Context.MODE_PRIVATE);
        id_phieuMuon = sharedPreferences_phieuMuon.getString("id_phieumuon", "");
        daoThemPhieuMuon = new DaoThemPhieuMuon(this);
        arrayList_PhieuMuon = new ArrayList<>();
        arrayList_PhieuMuon = daoThemPhieuMuon.LayDuLieuPhieuMuon_2(id_phieuMuon);

        tv_maSach = findViewById(R.id.ed_maSach_pm);
        tv_tenSach = findViewById(R.id.ed_tenSach_pm);

        spinnerTenKhacHang = findViewById(R.id.sp_tenKh_pm_);
        spinnerLoaiThanhToan = findViewById(R.id.sp_loaitt_pm_);


        daoThemDonVi = new DaoThemDonVi(this);
        daoThemSach = new DaoThemSach(this);


        arrayList_DonVi = daoThemDonVi.LayDuLieuDonVi();
        spinnerDonViThem = new SpinnerDonViThem(arrayList_DonVi);
        spinnerTenKhacHang.setAdapter(spinnerDonViThem);
        spinnerDonViThem.notifyDataSetChanged();
        ///
        list_LoaiThanhToan = new ArrayList<>();
        list_LoaiThanhToan.add("Nhập thanh toán ngay");
        list_LoaiThanhToan.add("Nhập công nợ");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list_LoaiThanhToan);
        spinnerLoaiThanhToan.setAdapter(arrayAdapter);


        ChinhSua();
        itemClickSpinner();
        clickTrangThai();
        AutoTexview_maSach();
        AutoTexview_tenSach();
        clickImgDate();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void anhxaView() {

        rdg_TrangThai = findViewById(R.id.group_pm);
        tv_DiaChi = findViewById(R.id.tv_diaChi_pm);
        tv_Sdt = findViewById(R.id.tv_soDienThoi_pm);
        tv_tongTien = findViewById(R.id.tv_tongtienpm);
        tv_ngaytraSach = findViewById(R.id.tv_ntra);
        ed_ngayThang = findViewById(R.id.ed_nt_pm);
        ed_ngayTraSach = findViewById(R.id.ed_ngayTra_pm);
        ed_soLuongSach = findViewById(R.id.ed_doLuong_pm);
        ed_donGia = findViewById(R.id.ed_donGia_pm);
        btn_Them = findViewById(R.id.btn_Luupm);
        img1 = findViewById(R.id.imageView9);
        img2 = findViewById(R.id.imageView10);
    }

    public void ChinhSua() {
        MyThemPhieuMuon myThemPhieuMuon = arrayList_PhieuMuon.get(0);
        tv_maSach.setText(myThemPhieuMuon.getMasach_pm());
        tv_tenSach.setText(myThemPhieuMuon.getTensach_pm());
        tv_DiaChi.setText(myThemPhieuMuon.getDiachi_pm());
        tv_Sdt.setText(myThemPhieuMuon.getSdt_pm());
        tv_tongTien.setText(myThemPhieuMuon.getTongtien_pm());
        ed_ngayThang.setText(myThemPhieuMuon.getNgay_pm());
        ed_ngayTraSach.setText(myThemPhieuMuon.getNgaytra_pm());
        ed_soLuongSach.setText(myThemPhieuMuon.getSol_pm());
        ed_donGia.setText(myThemPhieuMuon.getDongia_pm());
        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_maSach.length()==0) {
                    tv_maSach.requestFocus();
                    tv_maSach.setError("Mã sách trống");
                } else if (tv_tenSach.length()==0) {
                    tv_maSach.requestFocus();
                    tv_maSach.setError("Tên sách trống");
                } else if (ed_ngayThang.length()==0) {
                    ed_ngayThang.requestFocus();
                    ed_ngayThang.setError("Ngày tháng trống");
                } else if (ed_soLuongSach.length()==0) {
                    ed_soLuongSach.requestFocus();
                    ed_soLuongSach.setError("Số lượng trống");
                } else if (ed_donGia.length()==0) {
                    ed_donGia.requestFocus();
                    ed_donGia.setError("Đơn giá trống");
                } else if (ed_ngayTraSach.length()==0) {
                    ed_ngayTraSach.requestFocus();
                    ed_ngayTraSach.setError("Ngày trả sách trống");
                } else {
                    if (tv_maSach.getText().toString().equals(myThemPhieuMuon.getMasach_pm()) &&
                            tv_tenSach.getText().toString().equals(myThemPhieuMuon.getTensach_pm()) &&
                            ed_ngayThang.getText().toString().equals(myThemPhieuMuon.getNgay_pm()) &&
                            ed_donGia.getText().toString().equals(myThemPhieuMuon.getDongia_pm()) &&
                            ed_ngayTraSach.getText().toString().equals(myThemPhieuMuon.getNgaytra_pm()) &&
                            ed_soLuongSach.getText().toString().equals(myThemPhieuMuon.getSol_pm())) {
                        Toast.makeText(getBaseContext(), "Không có gì để thay đổi", Toast.LENGTH_SHORT).show();

                    } else {
                        myThemPhieuMuon.setTt_pm(src_loaiThanhToan);
                        myThemPhieuMuon.setTenkh_pm(src_donVi);
                        myThemPhieuMuon.setLtt_pm(src_loaiThanhToan);
                        myThemPhieuMuon.setMasach_pm(tv_maSach.getText().toString());
                        myThemPhieuMuon.setTensach_pm(tv_tenSach.getText().toString());
                        myThemPhieuMuon.setNgay_pm(ed_ngayThang.getText().toString());
                        myThemPhieuMuon.setNgaytra_pm(ed_ngayTraSach.getText().toString());
                        myThemPhieuMuon.setTt_pm(trangThai);
                        double Tong = 0;
                        final double GiaThanh = Double.parseDouble(ed_donGia.getText().toString());
                        final double SoLuong = Double.parseDouble(ed_soLuongSach.getText().toString());
                        Tong = GiaThanh * SoLuong;
                        myThemPhieuMuon.setSol_pm(SoLuong + "");
                        myThemPhieuMuon.setDongia_pm(GiaThanh + "");
                        tv_tongTien.setText(Tong + "");
                        myThemPhieuMuon.setTongtien_pm(tv_tongTien.getText().toString());
                        int ketQua = daoThemPhieuMuon.chinhSuaPhieuMuon(myThemPhieuMuon);
                        if (ketQua > 0) {
                            Toast.makeText(getBaseContext(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                            arrayList_PhieuMuon.clear();
                            arrayList_PhieuMuon.addAll(daoThemPhieuMuon.LayDuLieuPhieuMuon_2(id_phieuMuon));
                            Intent intent = new Intent(ChinhSuaPhieuMuonMainActivity.this, MainActivityQuanLyThuVien.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getBaseContext(), "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }

    public void itemClickSpinner() {
        spinnerTenKhacHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MyThemDonVi myThemDonVi = arrayList_DonVi.get(position);
                src_donVi = myThemDonVi.getDon_Vi();
                tv_DiaChi.setText(myThemDonVi.getDia_ChiDV());
                tv_Sdt.setText(myThemDonVi.getSo_DTDonVi());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerLoaiThanhToan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                src_loaiThanhToan = list_LoaiThanhToan.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void clickTrangThai() {
        rdg_TrangThai.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.pm_muonSach:
                        trangThai = "Mượn sách";
                        ed_ngayTraSach.setVisibility(View.VISIBLE);
                        tv_ngaytraSach.setVisibility(View.VISIBLE);
                        img2.setVisibility(View.VISIBLE);
                        break;
                    case R.id.pm_muaSach:
                        trangThai = "Mua sách";
                        ed_ngayTraSach.setVisibility(View.INVISIBLE);
                        tv_ngaytraSach.setVisibility(View.INVISIBLE);
                        img2.setVisibility(View.INVISIBLE);
                        ed_ngayTraSach.setText("");
                }
            }
        });
    }

    public void AutoTexview_maSach() {
        String[] myData = daoThemSach.SelectAllData_maSach();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, myData);
        tv_maSach.setAdapter(adapter);
        Log.d("tv_maSach", tv_maSach + "");
        Log.d("mydata_maSach", myData + "");
    }

    public void AutoTexview_tenSach() {
        String[] myData = daoThemSach.SelectAllData_tenSach();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, myData);
        tv_tenSach.setAdapter(adapter);
        Log.d("tv_tenSach", tv_tenSach + "");
        Log.d("mydata_tenSach", myData + "");
    }

    public void clickImgDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(ChinhSuaPhieuMuonMainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ed_ngayThang.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(ChinhSuaPhieuMuonMainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ed_ngayTraSach.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }
}