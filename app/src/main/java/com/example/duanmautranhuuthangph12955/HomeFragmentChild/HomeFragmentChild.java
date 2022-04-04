package com.example.duanmautranhuuthangph12955.HomeFragmentChild;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemKeSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemKeSach;
import com.example.duanmautranhuuthangph12955.NhapSach.NhapSachFragment;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.RecyclerViewAdapter.ThemKeSachAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import MyData.MyDatabaseHelper;


public class HomeFragmentChild extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FloatingActionButton button;

    MyDatabaseHelper myDatabaseHelper;
    DaoThemKeSach daoThemKeSach;

    private RecyclerView recyclerView;
    private ThemKeSachAdapter themKeSachAdapter;

    ArrayList<MyThemKeSach> myThemKeSaches;
    ImageView empty_imageview;
    TextView no_data;

    public HomeFragmentChild() {
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
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = view.findViewById(R.id.default_activity_button);
        recyclerView = view.findViewById(R.id.rcv_ThemKeSach1);

        empty_imageview = view.findViewById(R.id.empty_imageview);
        no_data = view.findViewById(R.id.no_data);

        daoThemKeSach = new DaoThemKeSach(getActivity());

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        myDatabaseHelper = new MyDatabaseHelper(getActivity());

        myThemKeSaches = new ArrayList<>();
        myThemKeSaches = daoThemKeSach.LayDuLieuKeSach();

        themKeSachAdapter = new ThemKeSachAdapter(getActivity(), getActivity(), myThemKeSaches);

        recyclerView.setAdapter(themKeSachAdapter);

        viewIcon();
        setHasOptionsMenu(true); //// menu

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.custtom_dialog_ke_sach, null);
                AlertDialog builder = new AlertDialog.Builder(view.getContext())
                        .setPositiveButton("Thêm", null)
                        .setNegativeButton("Hủy", null)
                        .setView(view).show();
                EditText ed_TenKeSach = view.findViewById(R.id.ed_TenKeSach);
                Button button = builder.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ed_TenKeSach.length() == 0) {
                            ed_TenKeSach.requestFocus();
                            ed_TenKeSach.setError("Tên loại sách trống");
                        } else {
                            MyThemKeSach myThemKeSach = new MyThemKeSach();
                            myThemKeSach.setTen_KeSach(ed_TenKeSach.getText().toString());
                            daoThemKeSach.addKeSach(myThemKeSach);
                            Toast.makeText(getContext(), "Đã được lưu", Toast.LENGTH_LONG).show();
                            myThemKeSaches.clear();
                            myThemKeSaches.addAll(daoThemKeSach.LayDuLieuKeSach());
                            themKeSachAdapter.notifyDataSetChanged();
                            builder.dismiss();
                        }
                    }
                });
            }
        });
    }

    public void viewIcon() {
        Cursor cursor = daoThemKeSach.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String tenKe = cursor.getString(1);
                String soLuong = cursor.getString(2);
                String ngayThang = cursor.getString(3);
                int id_tk = cursor.getInt(4);

                MyThemKeSach myThemKeSach = new MyThemKeSach();
                myThemKeSach.setId_KeSach(id);
                myThemKeSach.setTen_KeSach(tenKe);
                myThemKeSach.setSo_LuongSach(soLuong);
                myThemKeSach.setNgay_ThemKeSach(ngayThang);
                myThemKeSach.setId_tk(id_tk);
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.my_menu_custom_delete_all, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {

        if (item.getItemId() == R.id.delete_all) {
            xacNhan();
        }
        return super.onOptionsItemSelected(item);
    }

    private void xacNhan() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Xóa tất cả kệ sách?");
        builder.setMessage("Bạn có chắc chắn muốn xóa hết tất cả không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                daoThemKeSach = new DaoThemKeSach(getActivity());
                daoThemKeSach.xoaHetDuLieu();

                myThemKeSaches.clear();
                myThemKeSaches.addAll(daoThemKeSach.LayDuLieuKeSach());
                themKeSachAdapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragmentchild, container, false);
    }

}