package com.example.duanmautranhuuthangph12955.NhapSach;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemDonVi;
import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemKeSach;
import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemDonVi;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemKeSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemSach;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.Spinner.SpinnerAdapterViTriKe;
import com.example.duanmautranhuuthangph12955.Spinner.SpinnerDonViThem;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import MyData.MyDatabaseHelper;

import static android.app.Activity.RESULT_OK;

public class NhapSachFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ArrayList<MyThemKeSach> arrayList;
    ArrayList<MyThemDonVi> arrayList_Dv;
    List<String> list_ThanhToan;
    SpinnerAdapterViTriKe spinnerAdapterViTriKe;
    SpinnerDonViThem spinnerDonViThem;
    DaoThemKeSach daoThemKeSach = null;

    DaoThemDonVi daoThemDonVi = null;

    Spinner spinner_TenLoaiSach;
    Spinner spinner_donViThem;
    Spinner spinner_loaiThanhToan;

    String srcTenKeSach;
    String srcTenKeSach_Ten;
    String srcDonVi_Ten;
    String srcLoaiThanh_Toan;
    final int PINK_GALLERY = 1;

    DaoThemSach daoThemSach;
    MyDatabaseHelper myDatabaseHelper;

    private EditText ed_maSach;
    private EditText ed_tenSach;
    private EditText ed_nhaXb;
    private EditText ed_soTrang;
    private EditText ed_theLoai;
    private EditText ed_NgayThem;
    private EditText ed_soLuongSach;
    private EditText ed_donGia;
    private TextView tv_tongTien;

    private ImageView img_up;
    private ImageView img_shot;
    private ImageView img_date;
    private Button btn_luuSach;
    Uri imgUri;

    SharedPreferences sharedPreferences_Admin;
    String id_dk;

    public NhapSachFragment() {
        // Required empty public constructor
    }


    public static NhapSachFragment newInstance(String param1, String param2) {
        NhapSachFragment fragment = new NhapSachFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nhap_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        daoThemKeSach = new DaoThemKeSach(getContext());
        daoThemSach = new DaoThemSach(getContext());
        daoThemDonVi = new DaoThemDonVi(getContext());

        myDatabaseHelper = new MyDatabaseHelper(getContext());

        spinner_TenLoaiSach = view.findViewById(R.id.sp_tenKeSach);
        spinner_donViThem = view.findViewById(R.id.sp_DonViNhapHang);
        spinner_loaiThanhToan = view.findViewById(R.id.sp_LoaiThanhToan);

        arrayList = daoThemKeSach.LayDuLieuKeSach();
        spinnerAdapterViTriKe = new SpinnerAdapterViTriKe(arrayList);
        spinner_TenLoaiSach.setAdapter(spinnerAdapterViTriKe);
        spinnerAdapterViTriKe.notifyDataSetChanged();

        arrayList_Dv = daoThemDonVi.LayDuLieuDonVi();
        spinnerDonViThem = new SpinnerDonViThem(arrayList_Dv);
        spinner_donViThem.setAdapter(spinnerDonViThem);
        spinnerDonViThem.notifyDataSetChanged();

        list_ThanhToan = new ArrayList<>();
        list_ThanhToan.add("Nhập thanh toán ngay");
        list_ThanhToan.add("Nhập công nợ");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, list_ThanhToan);
        spinner_loaiThanhToan.setAdapter(arrayAdapter);



        anhXa();
        ClickUpIMG();
        RequestCamera();
        ClickCamera();
        themSach();
        ItemClickSpinner();
        getdate();
    }

    public void ItemClickSpinner() {
        spinner_TenLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MyThemKeSach myThemKeSach = arrayList.get(position);
                srcTenKeSach = myThemKeSach.getId_KeSach() + "";
                srcTenKeSach_Ten = myThemKeSach.getTen_KeSach();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_donViThem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MyThemDonVi myThemDonVi = arrayList_Dv.get(position);
                srcDonVi_Ten = myThemDonVi.getDon_Vi();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_loaiThanhToan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                list_ThanhToan.get(position);
                Log.d("ccccc", list_ThanhToan.get(position));

                srcLoaiThanh_Toan = list_ThanhToan.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void themSach() {
        btn_luuSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_maSach.length() == 0) {
                    ed_maSach.requestFocus();
                    ed_maSach.setError("Mã sách trống");
                } else if (ed_tenSach.length() == 0) {
                    ed_tenSach.requestFocus();
                    ed_tenSach.setError("Tên sách trống");
                } else if (ed_nhaXb.length() == 0) {
                    ed_nhaXb.requestFocus();
                    ed_nhaXb.setError("Nhà xuất bản trống");
                } else if (ed_soTrang.length() == 0) {
                    ed_soTrang.requestFocus();
                    ed_soTrang.setError("Số trang trống");
                } else if (ed_theLoai.length() == 0) {
                    ed_theLoai.requestFocus();
                    ed_theLoai.setError("Thể loại trống");
                } else if (ed_NgayThem.length() == 0) {
                    ed_NgayThem.requestFocus();
                    ed_NgayThem.setError("Ngày thêm trống");
                } else if (ed_soLuongSach.length() == 0) {
                    ed_soLuongSach.requestFocus();
                    ed_soLuongSach.setError("Số lượng trống");
                } else if (ed_donGia.length() == 0) {
                    ed_donGia.requestFocus();
                    ed_donGia.setError("Đơn giá trống");
                } else {
                //   if (checkNgay(ed_NgayThem)) {
//                        if (checkSoLuong(ed_soLuongSach)) {
//                            if (checkPrice(ed_donGia)) {
                                MyThemSach myThemSach = new MyThemSach();
                                myThemSach.setTs_maSach(ed_maSach.getText().toString());
                                myThemSach.setTs_tenSach(ed_tenSach.getText().toString());
                                myThemSach.setTs_nhaXb(ed_nhaXb.getText().toString());
                                myThemSach.setTs_soTrang(ed_soTrang.getText().toString());
                                myThemSach.setTs_theLoai(ed_theLoai.getText().toString());
                                myThemSach.setTs_ntNam(ed_NgayThem.getText().toString());
                                double Tong = 0;
                                final double GiaThanh = Double.parseDouble(ed_donGia.getText().toString());
                                final double SoLuong = Double.parseDouble(ed_soLuongSach.getText().toString());
                                Tong = GiaThanh * SoLuong;
                                myThemSach.setTs_soLuongSach(SoLuong + "");
                                myThemSach.setTs_soTienSach(GiaThanh + "");
                                tv_tongTien.setText(Tong + "");
                                myThemSach.setTs_tongSoTien(tv_tongTien.getText().toString());
                                myThemSach.setTs_imgLoad(imageViewToByte(img_up));
                                myThemSach.setTs_loaiSach(srcTenKeSach_Ten);
                                myThemSach.setId_KeSach(Integer.parseInt(srcTenKeSach));
                                myThemSach.setTs_donVi(srcDonVi_Ten);
                                myThemSach.setTs_loaiThanhToan(srcLoaiThanh_Toan);
                                daoThemSach.ThemSach(myThemSach);
                            }
                      // }
                //    }
              //  }
            }
        });
    }

    public static byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    public void ClickUpIMG() {
        img_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select picture"), PINK_GALLERY);
            }
        });
    }

    public void RequestCamera() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    public void ClickCamera() {
        img_shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PINK_GALLERY && resultCode == RESULT_OK) {
            imgUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imgUri);
                img_up.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 100) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img_up.setImageBitmap(bitmap);
        }
    }

    public void getdate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        img_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ed_NgayThem.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }

    public void anhXa() {
        ed_maSach = getView().findViewById(R.id.ed_tsMaSach);
        ed_tenSach = getView().findViewById(R.id.ed_tsTenSach);
        ed_nhaXb = getView().findViewById(R.id.ed_tsNXB);
        ed_soTrang = getView().findViewById(R.id.ed_tsSoTrang);
        ed_theLoai = getView().findViewById(R.id.ed_tsTheLoai);
        ed_NgayThem = getView().findViewById(R.id.ed_tsNgayThangThem);
        ed_soLuongSach = getView().findViewById(R.id.ed_tsSoLuong);
        ed_donGia = getView().findViewById(R.id.ed_ts_donGia);
        tv_tongTien = getView().findViewById(R.id.tv_ts_TongTien);
        img_up = getView().findViewById(R.id.img_upLoadAnh);
        img_shot = getView().findViewById(R.id.img_Camera);
        btn_luuSach = getView().findViewById(R.id.btn_LuuTenSach);
        img_date = getView().findViewById(R.id.img_tsDate);
    }

    public boolean checkNgay(EditText editText) {
        Date date = null;
        String s = editText.getText().toString();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = simpleDateFormat.parse(s);
            if (!s.equals(simpleDateFormat.format(date))) {
                date = null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Sai định dạng ngày \n dd/MM/yyyy ", Toast.LENGTH_SHORT).show();

        }
        return date != null;
    }

    public boolean checkPrice(EditText ed) {
        boolean check = true;
        try {
            Integer.parseInt(ed.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getContext(), "Tiền thuê phải là số", Toast.LENGTH_SHORT).show();
            check = false;
        }
        return check;
    }

    public boolean checkSoLuong(EditText ed) {
        boolean check = true;
        try {
            Integer.parseInt(ed.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getContext(), "Số lượng phải là số", Toast.LENGTH_SHORT).show();
            check = false;
        }
        return check;
    }
}