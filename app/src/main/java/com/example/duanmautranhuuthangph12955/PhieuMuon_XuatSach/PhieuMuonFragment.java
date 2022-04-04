package com.example.duanmautranhuuthangph12955.PhieuMuon_XuatSach;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemDonVi;
import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemPhieuMuon;
import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemDonVi;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemPhieuMuon;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemSach;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.Spinner.SpinnerDonViThem;
import com.example.duanmautranhuuthangph12955.Spinner.SpinnerThemPhieuMuon;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import QuanLyThuVien.MainActivityQuanLyThuVien;


public class PhieuMuonFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RadioGroup rdg_TrangThai;
    Spinner spinnerTenKhacHang, spinnerLoaiThanhToan, spinner_maSach;
    ;
    TextView tv_DiaChi, tv_Sdt, tv_tongTien, tv_ngaytraSach;
    EditText ed_ngayThang, ed_ngayTraSach, ed_soLuongSach, ed_donGia;
    Button btn_Them, btn_Huy;

    AutoCompleteTextView tv_tenSach;

    ImageView imageView11, imageView12;


    ArrayList<MyThemDonVi> arrayList_DonVi;
    ArrayList<MyThemSach> arrayList_ThemSach;
    List<String> list_LoaiThanhToan;
    DaoThemDonVi daoThemDonVi = null;
    DaoThemSach daoThemSach;
    DaoThemPhieuMuon daoThemPhieuMuon;
    String src_donVi;
    String src_loaiThanhToan;
    String src_maSach;
    SpinnerDonViThem spinnerDonViThem;
    SpinnerThemPhieuMuon spinnerThemPhieuMuon;

    String trangThai;

    public PhieuMuonFragment() {
        // Required empty public constructor
    }


    public static PhieuMuonFragment newInstance(String param1, String param2) {
        PhieuMuonFragment fragment = new PhieuMuonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_xuat_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        spinnerTenKhacHang = view.findViewById(R.id.sp_tenKh_pm_);
        spinnerLoaiThanhToan = view.findViewById(R.id.sp_loaitt_pm_);
        tv_tenSach = view.findViewById(R.id.ed_tenSach_pm);
        spinner_maSach = view.findViewById(R.id.spinner_maSach);

        daoThemDonVi = new DaoThemDonVi(getContext());
        daoThemSach = new DaoThemSach(getContext());
        daoThemPhieuMuon = new DaoThemPhieuMuon(getContext());

        arrayList_DonVi = daoThemDonVi.LayDuLieuDonVi();
        spinnerDonViThem = new SpinnerDonViThem(arrayList_DonVi);
        spinnerTenKhacHang.setAdapter(spinnerDonViThem);
        spinnerDonViThem.notifyDataSetChanged();
        ///
        arrayList_ThemSach = daoThemSach._LayDuLieuSach();
        spinnerThemPhieuMuon = new SpinnerThemPhieuMuon(arrayList_ThemSach);
        spinner_maSach.setAdapter(spinnerThemPhieuMuon);
        spinnerThemPhieuMuon.notifyDataSetChanged();


        ///
        list_LoaiThanhToan = new ArrayList<>();
        list_LoaiThanhToan.add("Nhập thanh toán ngay");
        list_LoaiThanhToan.add("Nhập công nợ");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, list_LoaiThanhToan);
        spinnerLoaiThanhToan.setAdapter(arrayAdapter);

        ///
        AutoTexview_tenSach();
        anhxaView();
        itemClickSpinner();
        clickTrangThai();
        onclickLuuPm();
        onclickHuyPm();
        clickImgDate();
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
        try {
            spinner_maSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MyThemSach myThemSach = arrayList_ThemSach.get(position);
                    src_maSach = myThemSach.getTs_maSach() + "";
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {
            Log.d("EEE", e + "");
        }

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
                        imageView12.setVisibility(View.VISIBLE);
                        break;
                    case R.id.pm_muaSach:
                        trangThai = "Mua sách";
                        ed_ngayTraSach.setVisibility(View.INVISIBLE);
                        tv_ngaytraSach.setVisibility(View.INVISIBLE);
                        imageView12.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void onclickLuuPm() {
        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdg_TrangThai.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Bạn chưa chọn loại phiếu", Toast.LENGTH_SHORT).show();
                } else if (tv_tenSach.length() == 0) {
                    tv_tenSach.requestFocus();
                    tv_tenSach.setError("Tên sách trống");
                } else if (ed_ngayThang.length() == 0) {
                    ed_ngayThang.requestFocus();
                    ed_ngayThang.setError("Ngày tháng trống");
                } else if (ed_soLuongSach.length() == 0) {
                    ed_soLuongSach.requestFocus();
                    ed_soLuongSach.setError("Số lượng trống");
                } else if (ed_donGia.length() == 0) {
                    ed_donGia.requestFocus();
                    ed_donGia.setError("Đơn giá trống");
                } else if (ed_ngayTraSach.length() == 0) {
                    ed_ngayTraSach.requestFocus();
                    ed_ngayTraSach.setError("Ngày trả trống");
                } else {
                    MyThemPhieuMuon myThemPhieuMuon = new MyThemPhieuMuon();
                    myThemPhieuMuon.setTt_pm(trangThai);
                    myThemPhieuMuon.setTenkh_pm(src_donVi);
                    myThemPhieuMuon.setDiachi_pm(tv_DiaChi.getText().toString());
                    myThemPhieuMuon.setSdt_pm(tv_Sdt.getText().toString());
                    myThemPhieuMuon.setLtt_pm(src_loaiThanhToan);
                    myThemPhieuMuon.setMasach_pm(src_maSach);
                    myThemPhieuMuon.setTensach_pm(tv_tenSach.getText().toString());
                    myThemPhieuMuon.setNgay_pm(ed_ngayThang.getText().toString());
                    myThemPhieuMuon.setNgaytra_pm(ed_ngayTraSach.getText().toString());

                    double Tong = 0;
                    final double GiaThanh = Double.parseDouble(ed_donGia.getText().toString());
                    final double SoLuong = Double.parseDouble(ed_soLuongSach.getText().toString());
                    Tong = GiaThanh * SoLuong;
                    myThemPhieuMuon.setSol_pm(SoLuong + "");
                    myThemPhieuMuon.setDongia_pm(GiaThanh + "");
                    tv_tongTien.setText(Tong + "");
                    myThemPhieuMuon.setTongtien_pm(tv_tongTien.getText().toString());
                    Log.d("xxxxx", trangThai);
                    daoThemPhieuMuon.themPhieuMuon(myThemPhieuMuon);

                    rdg_TrangThai.clearCheck();
                    tv_tongTien.setText("");
                    ed_ngayThang.setText("");
                    ed_ngayTraSach.setText("");
                    ed_soLuongSach.setText("");
                    ed_donGia.setText("");
                }
            }
        });
    }

    public void onclickHuyPm() {
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_tongTien.setText("");
                ed_ngayThang.setText("");
                ed_ngayTraSach.setText("");
                ed_soLuongSach.setText("");
                ed_donGia.setText("");
                Intent intent = new Intent(getActivity(), MainActivityQuanLyThuVien.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void anhxaView() {
        rdg_TrangThai = getView().findViewById(R.id.group_pm);
        tv_DiaChi = getView().findViewById(R.id.tv_diaChi_pm);
        tv_Sdt = getView().findViewById(R.id.tv_soDienThoi_pm);
        tv_tongTien = getView().findViewById(R.id.tv_tongtienpm);
        tv_ngaytraSach = getView().findViewById(R.id.tv_ntra);
        ed_ngayThang = getView().findViewById(R.id.ed_nt_pm);
        ed_ngayTraSach = getView().findViewById(R.id.ed_ngayTra_pm);
        ed_soLuongSach = getView().findViewById(R.id.ed_doLuong_pm);
        ed_donGia = getView().findViewById(R.id.ed_donGia_pm);
        btn_Them = getView().findViewById(R.id.btn_Luupm);
        btn_Huy = getView().findViewById(R.id.btn_Huypm);
        imageView11 = getView().findViewById(R.id.imageView11);
        imageView12 = getView().findViewById(R.id.dateeeee);
    }

    public void clickImgDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ed_ngayTraSach.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();

            }
        });
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ed_ngayThang.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }

    public void AutoTexview_tenSach() {
        String[] myData = daoThemSach.SelectAllData_tenSach();
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, myData);
            tv_tenSach.setAdapter(adapter);
            Log.d("tv_tenSach", tv_tenSach + "");
            Log.d("mydata_tenSach", myData + "");
        } catch (Exception e) {
            Log.d("eeee", e + "");
        }

    }
}