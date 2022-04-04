package com.example.duanmautranhuuthangph12955.RecyclerViewAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmautranhuuthangph12955.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import MyData.MyDatabaseHelper;
import QuanLyThuVien.MainActivityQuanLyThuVien;

public class ThemNhanVienAdapter extends RecyclerView.Adapter<ThemNhanVienAdapter.ThemNhanVienViewHolder> {

    Context context;
    Activity activity;
    ArrayList nv_id, nv_ten, nv_tenDN, nv_email, nv_soDienThoai, nv_namSinh, nv_gioiTinh, nv_maPin, nv_xacNhanMp, nv_idtk, nv_vaiTro;

    MyDatabaseHelper myDB;
    FragmentActivity _activity;

    public ThemNhanVienAdapter(Context context, Activity activity, ArrayList nv_id, ArrayList nv_ten, ArrayList nv_tenDN,
                               ArrayList nv_email, ArrayList nv_soDienThoai, ArrayList nv_namSinh, ArrayList nv_gioiTinh,
                               ArrayList nv_maPin, ArrayList nv_xacNhanMp, ArrayList nv_idtk, ArrayList nv_vaiTro, FragmentActivity _activity) {
        this.context = context;
        this.activity = activity;
        this.nv_id = nv_id;
        this.nv_ten = nv_ten;
        this.nv_tenDN = nv_tenDN;
        this.nv_email = nv_email;
        this.nv_soDienThoai = nv_soDienThoai;
        this.nv_namSinh = nv_namSinh;
        this.nv_gioiTinh = nv_gioiTinh;
        this.nv_maPin = nv_maPin;
        this.nv_xacNhanMp = nv_xacNhanMp;
        this.nv_idtk = nv_idtk;
        this.nv_vaiTro = nv_vaiTro;
        this._activity = _activity;
        this.notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ThemNhanVienViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_themnhanvien, parent, false);
        return new ThemNhanVienViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ThemNhanVienViewHolder holder, int position) {
        holder.tv_HoTen.setText(String.valueOf(nv_ten.get(position)));
        holder.tv_soDienThoai.setText(String.valueOf(nv_soDienThoai.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence charSequence[] = {"Xem chi tiết", "Chỉnh sửa", "Xóa nhân viên"};
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Chức năng thêm");
                builder.setCancelable(true);
                builder.setItems(charSequence, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            AlertDialog.Builder builder_viewNv = new AlertDialog.Builder(v.getContext());
                            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = inflater.inflate(R.layout.custom_dialog_view_nhanvien, null);
                            builder_viewNv.setView(view);
                            builder_viewNv.setTitle("Xem chi tiết nhân viên");
                            TextView tv_hoTen = view.findViewById(R.id.tennnchinhsua);
                            TextView tv_email = view.findViewById(R.id.emailcs);
                            TextView tv_sdt = view.findViewById(R.id.sdtcs);
                            TextView tv_namSinh = view.findViewById(R.id.namsinhcs);
                            TextView tv_gioiTinh = view.findViewById(R.id.gioitinhcs);

                            tv_hoTen.setText(nv_ten.get(position) + "");
                            tv_email.setText(nv_email.get(position) + "");
                            tv_sdt.setText(nv_soDienThoai.get(position) + "");
                            tv_namSinh.setText(nv_namSinh.get(position) + "");
                            tv_gioiTinh.setText(nv_gioiTinh.get(position) + "");
                            builder_viewNv.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder_viewNv.create();
                            alertDialog.show();


                        } else if (which == 1) {
                            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = inflater.inflate(R.layout.custom_dialog_nhanvienchinhsua, null);
                            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext())
                                    .setPositiveButton("Lưu", null)
                                    .setNegativeButton("Hủy", null)
                                    .setTitle("Chỉnh sửa nhân viên")
                                    .setCancelable(false)
                                    .setView(view)
                                    .show();
                            EditText ed_hoTen = view.findViewById(R.id.tennnchinhsua);
                            EditText ed_email = view.findViewById(R.id.emailcs);
                            EditText ed_sdt = view.findViewById(R.id.sdtcs);
                            EditText ed_namSinh = view.findViewById(R.id.namsinhcs);
                            EditText ed_gioiTinh = view.findViewById(R.id.gioitinhcs);

                            ed_hoTen.setText(nv_ten.get(position) + "");
                            ed_email.setText(nv_email.get(position) + "");
                            ed_sdt.setText(nv_soDienThoai.get(position) + "");
                            ed_namSinh.setText(nv_namSinh.get(position) + "");
                            ed_gioiTinh.setText(nv_gioiTinh.get(position) + "");


                            Button button =  alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (ed_hoTen.length()==0){
                                        ed_hoTen.requestFocus();
                                        ed_hoTen.setError("Không để trống");
                                    }else if (ed_email.length()==0){
                                        ed_email.requestFocus();
                                        ed_email.setError("Không để trống");
                                    }else if (ed_sdt.length()==0){
                                        ed_sdt.requestFocus();
                                        ed_sdt.setError("Không để trống");
                                    }else if (ed_namSinh.length()==0){
                                        ed_namSinh.requestFocus();
                                        ed_namSinh.setError("Không để trống");
                                    }else if (ed_gioiTinh.length()==0){
                                        ed_gioiTinh.requestFocus();
                                        ed_gioiTinh.setError("Không để trống");
                                    }else {
                                        String c_ed_hoTen = ed_hoTen.getText().toString().trim();
                                        String _ed_email = ed_email.getText().toString().trim();
                                        String _ed_sdt = ed_sdt.getText().toString().trim();
                                        String _ed_namSinh = ed_namSinh.getText().toString().trim();
                                        String _ed_gioiTinh = ed_gioiTinh.getText().toString().trim();
                                        myDB.updateData(String.valueOf(nv_id.get(position)), c_ed_hoTen, _ed_email, _ed_sdt, _ed_namSinh, _ed_gioiTinh);
                                        alertDialog.dismiss();
                                        Intent intent = new Intent(v.getContext(), MainActivityQuanLyThuVien.class);
                                        activity.startActivity(intent);
                                        activity.finish();
                                    }

                                }
                            });
                        } else {
                            AlertDialog.Builder builder_xoaNv = new AlertDialog.Builder(v.getContext());
                            builder_xoaNv.setTitle("Bạn có chắc chắn muốn xóa nhân viên này không?");
                            builder_xoaNv.setCancelable(false);
                            builder_xoaNv.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    myDB.XoaNhanVien(nv_id.get(position) + "");
                                    Intent intent = new Intent(v.getContext(), MainActivityQuanLyThuVien.class);
                                    activity.startActivity(intent);
                                    activity.finish();
                                }
                            });
                            builder_xoaNv.setNegativeButton("Hủy ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog_xoaNv = builder_xoaNv.create();
                            alertDialog_xoaNv.show();
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
        return nv_id.size();
    }

    public class ThemNhanVienViewHolder extends RecyclerView.ViewHolder {

        TextView tv_HoTen;
        TextView tv_soDienThoai;

        public ThemNhanVienViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_HoTen = itemView.findViewById(R.id.tv_tenNv);
            tv_soDienThoai = itemView.findViewById(R.id.tv_soDienThoai_nv);
            myDB = new MyDatabaseHelper(itemView.getContext());

        }
    }
}
