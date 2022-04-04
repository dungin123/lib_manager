package QuanLyThuVien.ui.ThongTinCaNhan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmautranhuuthangph12955.R;

import org.jetbrains.annotations.NotNull;

import QuanLyThuVien.MainActivityQuanLyThuVien;


public class ThongTinCaNhanFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button btn_XemNhanVien;
    private Button btn_Thoat;

    private TextView tv_HoTen;
    private TextView tv_Email;
    private TextView tv_SoDienThoai;
    private TextView tv_NamSinh;
    private TextView tv_GioiTinh;
    private TextView tv_VaiTro;
    private TextView tv_doiMatKhau;

    private SharedPreferences sharedPreferences_admin;
    private SharedPreferences sharedPreferences_nv;

    public ThongTinCaNhanFragment() {
    }

    public static ThongTinCaNhanFragment newInstance(String param1, String param2) {
        ThongTinCaNhanFragment fragment = new ThongTinCaNhanFragment();
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
        return inflater.inflate(R.layout.fragment_thong_tin_ca_nhan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tv_HoTen = view.findViewById(R.id.tv_Hoten);
        tv_Email = view.findViewById(R.id.tv_Email);
        tv_SoDienThoai = view.findViewById(R.id.tv_Sdt);
        tv_NamSinh = view.findViewById(R.id.tv_namSinh);
        tv_GioiTinh = view.findViewById(R.id.tv_Gioitinh);
        tv_VaiTro = view.findViewById(R.id.tv_Vaitro);
        btn_Thoat = view.findViewById(R.id.btn_Thoat);
        tv_doiMatKhau = view.findViewById(R.id.textView44);


        sharedPreferences_admin = getActivity().getSharedPreferences("MyPrefsFile_Admin", getActivity().MODE_PRIVATE);
        sharedPreferences_nv = getActivity().getSharedPreferences("MyPrefsFile_NhanVien", getActivity().MODE_PRIVATE);


        ThongTinNhanVien();
        Thoat();
        viewThongTin();
    }

    public void viewThongTin() {

        String vaiTro = sharedPreferences_nv.getString("id10", "");

        if (vaiTro.equals("Quản lý") || vaiTro.equals("Nhân viên kho") || vaiTro.equals("Nhân viên bán hàng")) {


            String HoTen = sharedPreferences_nv.getString("hoTen", "");
            String Email = sharedPreferences_nv.getString("email", "");
            String SoDienThoai = sharedPreferences_nv.getString("sdt_nv", "");
            String NgaySinh = sharedPreferences_nv.getString("ngaySinh_nv", "");
            String GioiTinh = sharedPreferences_nv.getString("gioi_tinh", "");

            tv_HoTen.setText(HoTen);
            tv_Email.setText(Email);
            tv_SoDienThoai.setText(SoDienThoai);
            tv_NamSinh.setText(NgaySinh);
            tv_GioiTinh.setText(GioiTinh);
            tv_VaiTro.setText(vaiTro);


            Log.d("vaaaa", HoTen);
            Log.d("vaaaa", Email);
            Log.d("vaaaa", SoDienThoai);
            Log.d("vaaaa", NgaySinh);
            Log.d("vaaaa", GioiTinh);
            Log.d("vaaaa", vaiTro);

        } else {
            String HoTen = sharedPreferences_admin.getString("hoTen", "");
            String Email = sharedPreferences_admin.getString("eMail", "");
            String SoDienThoai = sharedPreferences_admin.getString("SodienThoai", "");
            String NgaySinh = sharedPreferences_admin.getString("NgaySinh", "");
            String GioiTinh = sharedPreferences_admin.getString("GioiTinh", "");
            String Admin = sharedPreferences_admin.getString("avc", "");

            tv_HoTen.setText(HoTen);
            tv_Email.setText(Email);
            tv_SoDienThoai.setText(SoDienThoai);
            tv_NamSinh.setText(NgaySinh);
            tv_GioiTinh.setText(GioiTinh);
            tv_VaiTro.setText(Admin);

        }
    }

    public void ThongTinNhanVien() {
        tv_doiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DoiMatKhauMainActivity.class);
                startActivity(intent);
               // getActivity().finish();
            }
        });
    }

    public void Thoat() {
        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), MainActivityQuanLyThuVien.class);
               startActivity(intent);
            }
        });
    }
}