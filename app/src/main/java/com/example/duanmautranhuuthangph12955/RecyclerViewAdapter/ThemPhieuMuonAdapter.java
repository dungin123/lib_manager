package com.example.duanmautranhuuthangph12955.RecyclerViewAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemPhieuMuon;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemPhieuMuon;
import com.example.duanmautranhuuthangph12955.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import MyData.MyDatabaseHelper;
import QuanLyThuVien.ui.QuanLyPhieuMuon.CustomPhieuMuon.ChinhSuaPhieuMuonMainActivity;
import QuanLyThuVien.ui.QuanLyPhieuMuon.CustomPhieuMuon.XemChiTietPhieuMuonMainActivity;

public class ThemPhieuMuonAdapter extends RecyclerView.Adapter<ThemPhieuMuonAdapter.ThemPhieuMuonViewHolder> {

    Context context;
    ArrayList<MyThemPhieuMuon> list;
    FragmentActivity activity;
    DaoThemPhieuMuon daoThemPhieuMuon;
    MyDatabaseHelper myDatabaseHelper;

    public static final String MY_PREFS_NAME_PHIEUMUON = "MyPrefsFile_idPhieuMuon";

    public ThemPhieuMuonAdapter(Context context, Activity activity, ArrayList<MyThemPhieuMuon> list) {
        this.context = context;
        this.list = list;
        this.activity = (FragmentActivity) activity;
    }

    @NonNull
    @NotNull
    @Override
    public ThemPhieuMuonViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_themphieumuon, parent, false);

        ThemPhieuMuonViewHolder viewHolder = new ThemPhieuMuonViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ThemPhieuMuonViewHolder holder, int position) {

        MyThemPhieuMuon myThemPhieuMuon = list.get(position);
        ;
        if (myThemPhieuMuon == null) {
            return;
        }
        holder.tv_donVi.setText(myThemPhieuMuon.getTenkh_pm());
        holder.tv_tenSach.setText(myThemPhieuMuon.getTensach_pm());
        holder.tv_trangThai.setText(myThemPhieuMuon.getTt_pm());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] charSequences = {"Xem chi tiết phiếu mượn", "Chỉnh sửa phiếu mượn", "Xóa phiếu mượn"};
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thêm các chức năng");
                builder.setItems(charSequences, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent intent = new Intent(v.getContext(), XemChiTietPhieuMuonMainActivity.class);
                            v.getContext().startActivity(intent);

                            String[] info_phieuMuon_= myDatabaseHelper.getInfo_PhieuMuon(myThemPhieuMuon.getId_pm()+"");
                            SharedPreferences.Editor editor_phieuMuon = context.getSharedPreferences(MY_PREFS_NAME_PHIEUMUON, Context.MODE_PRIVATE).edit();
                            editor_phieuMuon.putString("id_phieumuon", info_phieuMuon_[0]);
                            editor_phieuMuon.apply();

                        } else if (which == 1) {
                            Intent intent = new Intent(v.getContext(), ChinhSuaPhieuMuonMainActivity.class);
                            v.getContext().startActivity(intent);

                            String[] info_phieuMuon_= myDatabaseHelper.getInfo_PhieuMuon(myThemPhieuMuon.getId_pm()+"");
                            SharedPreferences.Editor editor_phieuMuon = context.getSharedPreferences(MY_PREFS_NAME_PHIEUMUON, Context.MODE_PRIVATE).edit();
                            editor_phieuMuon.putString("id_phieumuon", info_phieuMuon_[0]);
                            editor_phieuMuon.apply();

                        } else {
                            AlertDialog.Builder builder_xoaPhieuMuon = new AlertDialog.Builder(v.getContext());
                            builder_xoaPhieuMuon.setTitle("Bạn có chắc xóa phiêu mượn này không ?");
                            builder_xoaPhieuMuon.setCancelable(false);
                            builder_xoaPhieuMuon.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    daoThemPhieuMuon.xoaPhieuMuon(myThemPhieuMuon.getId_pm() + "");
                                    list.clear();
                                    list.addAll(daoThemPhieuMuon.LayDuLieuPhieuMuon());
                                    notifyDataSetChanged();
                                }
                            });
                            builder_xoaPhieuMuon.setNegativeButton("Hủy ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder_xoaPhieuMuon.create();
                            alertDialog.show();

                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }

        return 0;
    }

    public class ThemPhieuMuonViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_donVi, tv_tenSach, tv_trangThai;

        public ThemPhieuMuonViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView3);
            tv_donVi = itemView.findViewById(R.id.textView27);
            tv_tenSach = itemView.findViewById(R.id.textView30);
            tv_trangThai = itemView.findViewById(R.id.tv_trangThai);

            daoThemPhieuMuon = new DaoThemPhieuMuon(itemView.getContext());

            myDatabaseHelper = new MyDatabaseHelper(itemView.getContext());

        }
    }
}
