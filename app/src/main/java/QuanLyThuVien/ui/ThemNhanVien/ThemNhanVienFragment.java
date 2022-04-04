package QuanLyThuVien.ui.ThemNhanVien;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.RecyclerViewAdapter.ThemNhanVienAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import MyData.MyDatabaseHelper;


public class ThemNhanVienFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private FloatingActionButton mfbNhanVien;
//    public static final int REQUEST_CODE = 11;
//    public static final int RESULT_CODE = 12;
//    public static final String EXTRA_KEY_TEST = "testKey";
//    Intent intent = new Intent();
//                intent.putExtra(ThemNhanVienFragment.EXTRA_KEY_TEST, "Testing passing data back to ActivityOne");
//    setResult(ThemNhanVienFragment.RESULT_CODE, intent); // You can also send result without any data using setResult(int resultCode)
//    finish();

    MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> nv_id, nv_ten, nv_tenDN, nv_email, nv_soDienThoai, nv_namSinh, nv_gioiTinh, nv_maPin, nv_xacNhanMp, nv_idtk, nv_vaiTro;
    ThemNhanVienAdapter themNhanVienAdapter;
    RecyclerView recyclerView;

    public ThemNhanVienFragment() {
        // Required empty public constructor
    }


    public static ThemNhanVienFragment newInstance(String param1, String param2) {
        ThemNhanVienFragment fragment = new ThemNhanVienFragment();
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
        return inflater.inflate(R.layout.fragment_them_nhan_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mfbNhanVien = view.findViewById(R.id.mfb_addNhanVien);
        recyclerView = view.findViewById(R.id.rcv_ThemNhanVien);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        myDatabaseHelper = new MyDatabaseHelper(getActivity());
        nv_id = new ArrayList<>();
        nv_ten = new ArrayList<>();
        nv_tenDN = new ArrayList<>();
        nv_email = new ArrayList<>();
        nv_soDienThoai = new ArrayList<>();
        nv_namSinh = new ArrayList<>();
        nv_gioiTinh = new ArrayList<>();
        nv_maPin = new ArrayList<>();
        nv_xacNhanMp = new ArrayList<>();
        nv_vaiTro = new ArrayList<>();
        nv_idtk = new ArrayList<>();
        dulieuMang();

        themNhanVienAdapter = new ThemNhanVienAdapter(getActivity(), getActivity(), nv_id, nv_ten, nv_tenDN, nv_email, nv_soDienThoai, nv_namSinh,
                nv_gioiTinh, nv_maPin, nv_xacNhanMp, nv_idtk, nv_vaiTro, getActivity());

        themNhanVienAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(themNhanVienAdapter);
        addNhanVien();

    }

    public void addNhanVien() {
        mfbNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemNhanVienFragment.this.getContext(), ThemNhanVienActivity.class);
                startActivity(intent);
            }
        });
    }

    public void dulieuMang() {
        Cursor cursor = myDatabaseHelper.LayDuLieuBangNV();
        if (cursor.getCount() == 0) {

        } else {
            while (cursor.moveToNext()) {
                nv_id.add(cursor.getString(0));
                nv_ten.add(cursor.getString(1));
                nv_tenDN.add(cursor.getString(2));
                nv_email.add(cursor.getString(3));
                nv_soDienThoai.add(cursor.getString(4));
                nv_namSinh.add(cursor.getString(5));
                nv_gioiTinh.add(cursor.getString(6));
                nv_maPin.add(cursor.getString(7));
                nv_xacNhanMp.add(cursor.getString(8));
                nv_vaiTro.add(cursor.getString(9));
                nv_idtk.add(cursor.getString(10));
            }
        }
    }
}