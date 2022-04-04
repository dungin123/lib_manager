package com.example.duanmautranhuuthangph12955.RecyclerViewAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemDonVi;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemDonVi;
import com.example.duanmautranhuuthangph12955.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ThemDonViAdapter extends RecyclerView.Adapter<ThemDonViAdapter.ThemDonViAdapterViewHolder> {

    private Context mContext;
    private ArrayList<MyThemDonVi> list;
    DaoThemDonVi daoThemDonVi;

    public ThemDonViAdapter(Activity activity, Context mContext, ArrayList<MyThemDonVi> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ThemDonViAdapterViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_themdonvi_daidien, parent, false);

        return new ThemDonViAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NotNull ThemDonViAdapterViewHolder holder, int position) {
        MyThemDonVi myThemDonVi = list.get(position);
        if (myThemDonVi == null) {
            return;
        }
        holder.tv_DonVi.setText(myThemDonVi.getDon_Vi());
        holder.tv_DiaChi.setText(myThemDonVi.getDia_ChiDV());
        holder.tv_SoDienThoai.setText(myThemDonVi.getSo_DTDonVi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] charSequences = {"Xem chi tiết", "Chỉnh sửa thành viên", "Xóa thành viên"};
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Chức năng thêm");
                builder.setItems(charSequences, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            AlertDialog.Builder builder_view = new AlertDialog.Builder(v.getContext());
                            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = inflater.inflate(R.layout.custom_dialog_viewthanh_vien, null);
                            builder_view.setView(view);
                            TextView tv_donvi = (TextView) view.findViewById(R.id.ed_tv_TenDonVi);
                            TextView tv_daiDienDv = (TextView) view.findViewById(R.id.ed_tv_DaiDiendv);
                            TextView tv_diaChi = (TextView) view.findViewById(R.id.ed_tv_diaChidv);
                            TextView tv_sdt = (TextView) view.findViewById(R.id.textView50);
                            tv_donvi.setText(myThemDonVi.getDon_Vi());
                            tv_daiDienDv.setText(myThemDonVi.getDai_DienDonDV());
                            tv_diaChi.setText(myThemDonVi.getDia_ChiDV());
                            tv_sdt.setText(myThemDonVi.getSo_DTDonVi());
                            builder_view.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder_view.create();
                            alertDialog.show();
                        } else if (which == 1) {
                            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = inflater.inflate(R.layout.custom_dialog_viewchinh_sua_don_vi, null);
                            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext())
                                    .setPositiveButton("Lưu",null)
                                    .setNegativeButton("Hủy",null)
                                    .setCancelable(false)
                                    .setView(view)
                                    .show();
                            EditText ed_tenDv = (EditText) view.findViewById(R.id.editTextTextPersonName);
                            EditText ed_tenDDDv = (EditText) view.findViewById(R.id.editTextTextPersonName2);
                            EditText ed_tenDC = (EditText) view.findViewById(R.id.editTextTextPersonName3);
                            EditText ed_tenSDT = (EditText) view.findViewById(R.id.editTextTextPersonName4);

                            ed_tenDv.setText(myThemDonVi.getDon_Vi());
                            ed_tenDDDv.setText(myThemDonVi.getDai_DienDonDV());
                            ed_tenDC.setText(myThemDonVi.getDia_ChiDV());
                            ed_tenSDT.setText(myThemDonVi.getSo_DTDonVi());
                            Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (ed_tenDv.getText().toString().equals(myThemDonVi.getDon_Vi()) &&
                                            ed_tenDDDv.getText().toString().equals(myThemDonVi.getDai_DienDonDV()) &&
                                            ed_tenDC.getText().toString().equals(myThemDonVi.getDia_ChiDV()) &&
                                            ed_tenSDT.getText().toString().equals(myThemDonVi.getSo_DTDonVi())) {
                                        Toast.makeText(mContext, "Không có gì để thay đổi", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (ed_tenDv.length() == 0) {
                                            ed_tenDv.requestFocus();
                                            ed_tenDv.setError("Không được để trống");
                                        } else if (ed_tenDDDv.length() == 0) {
                                            ed_tenDDDv.requestFocus();
                                            ed_tenDDDv.setError("Không được để trống");
                                        } else if (ed_tenDC.length() == 0) {
                                            ed_tenDC.requestFocus();
                                            ed_tenDC.setError("Không được để trống");
                                        } else if (ed_tenSDT.length() == 0) {
                                            ed_tenSDT.requestFocus();
                                            ed_tenSDT.setError("Không được để trống");
                                        } else {
                                            myThemDonVi.setDon_Vi(ed_tenDv.getText().toString());
                                            myThemDonVi.setDai_DienDonDV(ed_tenDDDv.getText().toString());
                                            myThemDonVi.setDia_ChiDV(ed_tenDC.getText().toString());
                                            myThemDonVi.setSo_DTDonVi(ed_tenSDT.getText().toString());
                                            int ketQua = daoThemDonVi.chinhSuaDonVi(myThemDonVi);
                                            if (ketQua > 0) {
                                                Toast.makeText(mContext, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                                                list.clear();
                                                list.addAll(daoThemDonVi.LayDuLieuDonVi());
                                                notifyDataSetChanged();
                                                alertDialog.dismiss();
                                            } else {
                                                Toast.makeText(mContext, "Thay không thành công", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                }
                            });
                        } else {
                            AlertDialog.Builder builder_viewXoaDonVi = new AlertDialog.Builder(v.getContext());
                            builder_viewXoaDonVi.setCancelable(false);
                            builder_viewXoaDonVi.setTitle("Bạn có chắc chắn muốn xóa thành viên này không?");
                            builder_viewXoaDonVi.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    daoThemDonVi.xoaTenDonVi(myThemDonVi.getId_DV() + "");
                                    list.clear();
                                    list.addAll(daoThemDonVi.LayDuLieuDonVi());
                                    notifyDataSetChanged();
                                }
                            });
                            builder_viewXoaDonVi.setNegativeButton("Hủy ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder_viewXoaDonVi.create();
                            alertDialog.show();

                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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


    public class ThemDonViAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView tv_DonVi, tv_DiaChi, tv_SoDienThoai;

        public ThemDonViAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_donVi);
            tv_DonVi = itemView.findViewById(R.id.tv_tenDonVi);
            tv_DiaChi = itemView.findViewById(R.id.tv_diaChiDonVi);
            tv_SoDienThoai = itemView.findViewById(R.id.tv_soDienThoai);

            daoThemDonVi = new DaoThemDonVi(itemView.getContext());


        }
    }
}
