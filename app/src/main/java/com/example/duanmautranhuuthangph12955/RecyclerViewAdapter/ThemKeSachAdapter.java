package com.example.duanmautranhuuthangph12955.RecyclerViewAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemKeSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemKeSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemSach;
import com.example.duanmautranhuuthangph12955.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import MyData.MyDatabaseHelper;
import QuanLyThuVien.ui.XemSach.XemSachFragment;

public class ThemKeSachAdapter extends RecyclerView.Adapter<ThemKeSachAdapter.ThemKeSachViewHolder> {

    public Context context;
    public ArrayList<MyThemKeSach> list;
    DaoThemKeSach daoThemKeSach;
    FragmentActivity activity;
    MyThemKeSach myThemKeSach;
    String id_dk;
    MyDatabaseHelper myDatabaseHelper;
    public static final String MY_PREFS_NAME_KESACH = "MyPrefsFile_KeSach";
    MyThemSach myThemSach;


    public ThemKeSachAdapter(Activity activity, Context context, ArrayList<MyThemKeSach> sachList) {
        this.context = context;
        this.list = sachList;
        this.activity = (FragmentActivity) activity;
        notifyItemInserted(1);
    }


    @NotNull
    @Override
    public ThemKeSachViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_themkesach, parent, false);

        ThemKeSachViewHolder viewHolder = new ThemKeSachViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ThemKeSachViewHolder holder, int position) {
        MyThemKeSach myThemKeSach = list.get(position);
        if (myThemKeSach == null) {
            return;
        }
        holder.tv_TenKe.setText(myThemKeSach.getTen_KeSach());
        holder.tv_SoLuong.setText(myThemKeSach.getSo_LuongSach());
        holder.tv_NgayThang.setText(myThemKeSach.getNgay_ThemKeSach());
        holder.itemView.setTag(myThemKeSach.getId_KeSach());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] items = {"Danh sách các sách", "Chỉnh sửa tên loại sách ", "Xóa loại sách"};
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Chỉnh sửa loại sách");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Fragment fragment = new XemSachFragment();
                            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_temp, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();

                            String[] info_KeSach = myDatabaseHelper.getInfo_KeSach(myThemKeSach.getTen_KeSach());
                            SharedPreferences.Editor editor_KeSach = context.getSharedPreferences(MY_PREFS_NAME_KESACH, Context.MODE_PRIVATE).edit();
                            editor_KeSach.putString("id_keSach", info_KeSach[0]);
                            editor_KeSach.apply();

                        } else if (which == 1) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view_edTenKe = layoutInflater.inflate(R.layout.custom_dialog_edit_tenkesach, null);
                            builder.setView(view_edTenKe);
                            builder.setCancelable(true);
                            EditText ed_tenKeSach = view_edTenKe.findViewById(R.id.ed_ChinhSuaTenKeSach);
                            ed_tenKeSach.setText(myThemKeSach.getTen_KeSach());
                            builder.setPositiveButton("Thay đổi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (ed_tenKeSach.length() == 0) {
                                        ed_tenKeSach.requestFocus();
                                        ed_tenKeSach.setError("Không được để trống");
                                    } else {
                                        if (ed_tenKeSach.getText().toString().equals(myThemKeSach.getTen_KeSach())) {
                                            Toast.makeText(context, "Không có gì để thay đổi", Toast.LENGTH_SHORT).show();
                                        } else {
                                            myThemKeSach.setTen_KeSach(ed_tenKeSach.getText().toString());
                                            int ketQua = daoThemKeSach.chinhSuaTenKeSach(myThemKeSach);
                                            if (ketQua > 0) {
                                                Toast.makeText(context, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                                                list.clear();
                                                list.addAll(daoThemKeSach.LayDuLieuKeSach());
                                                notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(context, "Thay không thành công", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setTitle("Xóa kệ");
                            builder.setMessage("Bạn có chắc chắn muốn xóa kệ này không?");
                            builder.setCancelable(false);
                            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    daoThemKeSach.xoaTenKe(myThemKeSach.getId_KeSach() + "");
                                    list.clear();
                                    list.addAll(daoThemKeSach.LayDuLieuKeSach());
                                    notifyDataSetChanged();
                                }
                            });
                            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.show();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        timer.cancel();
                    }
                }, 5000);
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

    public class ThemKeSachViewHolder extends RecyclerView.ViewHolder {
        ImageView img_Book;
        TextView tv_TenKe;
        TextView tv_SoLuong;
        TextView tv_NgayThang;
        LinearLayout layout;

        public ThemKeSachViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            img_Book = itemView.findViewById(R.id.img_book_KeSach);
            tv_TenKe = itemView.findViewById(R.id.tv_tenKe);
            tv_SoLuong = itemView.findViewById(R.id.tv_numberSoLuong);
            tv_NgayThang = itemView.findViewById(R.id.tv_ngayThang);
            layout = itemView.findViewById(R.id.linear);

            daoThemKeSach = new DaoThemKeSach(itemView.getContext());

            myThemKeSach = new MyThemKeSach();

            myThemSach = new MyThemSach();

            myDatabaseHelper = new MyDatabaseHelper(context);

            SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefsFile_Admin", Context.MODE_PRIVATE);
            id_dk = sharedPreferences.getString("id_dk", "");

            Animation animation = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            layout.setAnimation(animation);
        }
    }
}
