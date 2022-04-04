package QuanLyThuVien.ui.ThemDonViDaiDien;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemDonVi;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemDonVi;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.RecyclerViewAdapter.ThemDonViAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import MyData.MyDatabaseHelper;


public class ThemDonViFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FloatingActionButton btn_DonVi;

    private EditText ed_DonVi, ed_DaiDienDV, ed_DiaChiDv, ed_SDTDonVi;

    private DaoThemDonVi daoThemDonVi;

    private RecyclerView recyclerView;
    private ThemDonViAdapter themDonViAdapter;

    MyDatabaseHelper myDatabaseHelper;

    ArrayList<MyThemDonVi> myThemDonVis;

    public ThemDonViFragment() {
        // Required empty public constructor
    }

    public static ThemDonViFragment newInstance(String param1, String param2) {
        ThemDonViFragment fragment = new ThemDonViFragment();
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
        return inflater.inflate(R.layout.fragment_them_thanh_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_DonVi = view.findViewById(R.id.mfb_addThanhVien);
        recyclerView = view.findViewById(R.id.rcv_DanhSachThanhVien);

        daoThemDonVi = new DaoThemDonVi(getActivity());
        myDatabaseHelper = new MyDatabaseHelper(getActivity());

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        SharedPreferences sharedPreferences_Ad = getActivity().getSharedPreferences("MyPrefsFile_Admin", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences_Nv = getActivity().getSharedPreferences("MyPrefsFile_NhanVien", Context.MODE_PRIVATE);


        String id_dk = (sharedPreferences_Ad.getString("id_dk", ""));
        String id_nv = sharedPreferences_Nv.getString("id1", "");
        Log.d("aaaa", id_dk);
        Log.d("aaaa", id_nv);

        myThemDonVis = new ArrayList<>();
        myThemDonVis = daoThemDonVi.LayDuLieuDonVi();

        themDonViAdapter = new ThemDonViAdapter(getActivity(), getActivity(), myThemDonVis);

        recyclerView.setAdapter(themDonViAdapter);

        ThemDonViDaiDien();
    }

    public void ThemDonViDaiDien() {
        btn_DonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.custom_dialog_donvi, null);

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setPositiveButton("Lưu", null)
                        .setNegativeButton("Hủy", null)
                        .setView(view)
                        .show();
                ed_DonVi = view.findViewById(R.id.ed_TenDonVi);
                ed_DaiDienDV = view.findViewById(R.id.ed_TenDaiDienDonVi);
                ed_DiaChiDv = view.findViewById(R.id.ed_DiaChiDonVi);
                ed_SDTDonVi = view.findViewById(R.id.ed_SDT_DonVi);
                Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ed_DonVi.length() == 0) {
                            ed_DonVi.requestFocus();
                            ed_DonVi.setError("Không để trống");
                        } else if (ed_DaiDienDV.length() == 0) {
                            ed_DaiDienDV.requestFocus();
                            ed_DaiDienDV.setError("Không để trống");
                        } else if (ed_DiaChiDv.length() == 0) {
                            ed_DiaChiDv.requestFocus();
                            ed_DiaChiDv.setError("Không để trống");
                        } else if (ed_SDTDonVi.length() == 0) {
                            ed_SDTDonVi.requestFocus();
                            ed_SDTDonVi.setError("Không để trống");
                        } else {
                            SharedPreferences sharedPreferences_ad = getActivity().getSharedPreferences("MyPrefsFile_Admin", Context.MODE_PRIVATE);
                            SharedPreferences sharedPreferences_nv = getActivity().getSharedPreferences("MyPrefsFile_NhanVien", Context.MODE_PRIVATE);
                            String vaiTro = sharedPreferences_nv.getString("vaiTro", "");
                            MyThemDonVi myThemDonVi = new MyThemDonVi();
                            if (vaiTro.equals("Quản lý") || vaiTro.equals("Nhân viên kho") || vaiTro.equals("Nhân viên bán hàng")) {
                                myThemDonVi.setDon_Vi(ed_DonVi.getText().toString());
                                myThemDonVi.setDai_DienDonDV(ed_DaiDienDV.getText().toString());
                                myThemDonVi.setDia_ChiDV(ed_DiaChiDv.getText().toString());
                                myThemDonVi.setSo_DTDonVi(ed_SDTDonVi.getText().toString());
                                myThemDonVi.setId_nv(Integer.parseInt(sharedPreferences_nv.getString("id1", "")));
                                long kq = daoThemDonVi.ThemDonVi(myThemDonVi);
                                if (kq > 0) {
                                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    myThemDonVis.clear();
                                    myThemDonVis.addAll(daoThemDonVi.LayDuLieuDonVi());
                                    themDonViAdapter.notifyDataSetChanged();
                                }
                            } else {
                                myThemDonVi.setDon_Vi(ed_DonVi.getText().toString());
                                myThemDonVi.setDai_DienDonDV(ed_DaiDienDV.getText().toString());
                                myThemDonVi.setDia_ChiDV(ed_DiaChiDv.getText().toString());
                                myThemDonVi.setSo_DTDonVi(ed_SDTDonVi.getText().toString());
                                myThemDonVi.setId_tk(Integer.parseInt(sharedPreferences_ad.getString("id_dk", "")));
                                long kq = daoThemDonVi.ThemDonVi(myThemDonVi);
                                if (kq > 0) {
                                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    myThemDonVis.clear();
                                    myThemDonVis.addAll(daoThemDonVi.LayDuLieuDonVi());
                                    themDonViAdapter.notifyDataSetChanged();
                                }
                            }
                            alertDialog.dismiss();
                        }
                    }
                });
            }
        });
    }
}