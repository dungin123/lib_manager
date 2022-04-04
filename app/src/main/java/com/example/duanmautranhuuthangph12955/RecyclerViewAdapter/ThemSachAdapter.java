package com.example.duanmautranhuuthangph12955.RecyclerViewAdapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemKeSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemSach;
import com.example.duanmautranhuuthangph12955.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import MyData.MyDatabaseHelper;
import QuanLyThuVien.ui.XemSach.XemChiTietSachMainActivity;

public class ThemSachAdapter extends RecyclerView.Adapter<ThemSachAdapter.ThemSachViewHolder> implements Filterable {
    Context context;
    ArrayList<MyThemSach> list;
    ArrayList<MyThemSach> listFull;
    MyThemKeSach myThemKeSach;
    SharedPreferences sharedPreferences_KeSach;
    DaoThemSach daoThemSach;
    MyDatabaseHelper myDatabaseHelper;
    public static final String MY_PREFS_NAME_SACH = "MyPrefsFile_Sach";

    public ThemSachAdapter(Context context, ArrayList<MyThemSach> list) {
        this.context = context;
        this.list = list;
        listFull = new ArrayList<>(list);
    }

    @NotNull
    @Override
    public ThemSachViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_themsach_, parent, false);

        ThemSachViewHolder viewHolder = new ThemSachViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ThemSachViewHolder holder, int position) {
        MyThemSach myThemSach = list.get(position);
        if (myThemSach == null) {
            return;
        }
        byte[] imge = myThemSach.getTs_imgLoad();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imge, 0, imge.length);
        holder._img_themSach.setImageBitmap(bitmap);
        holder._tv_themSach.setText(myThemSach.getTs_tenSach());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence items[] = {"Xem chi tiết", "Chỉnh sửa sách", "Xóa sách"};
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Chức năng thêm");
                Log.d("tess.ca1", myThemSach.getId_tenSach() + "");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent intent = new Intent(v.getContext(), XemChiTietSachMainActivity.class);
                            v.getContext().startActivity(intent);
                            Log.d("tess.ca2", myThemSach.getId_tenSach() + "");
                            String[] info_Sach = myDatabaseHelper.getInfo_ThemSach(myThemSach.getTs_maSach());
                            SharedPreferences.Editor editor_KeSach = v.getContext().getSharedPreferences(MY_PREFS_NAME_SACH, Context.MODE_PRIVATE).edit();
                            editor_KeSach.putString("id_Sach_", info_Sach[0]);
                            editor_KeSach.apply();
                        } else if (which == 1) {

                            String[] info_Sach = myDatabaseHelper.getInfo_ThemSach_4(myThemSach.getTs_maSach());
                            SharedPreferences.Editor editor_KeSach = v.getContext().getSharedPreferences(MY_PREFS_NAME_SACH, Context.MODE_PRIVATE).edit();
                            editor_KeSach.putString("id_Sach_", info_Sach[0]);
                            editor_KeSach.apply();


                            AlertDialog.Builder builder_viewCsSach = new AlertDialog.Builder(v.getContext());
                            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = inflater.inflate(R.layout.custom_dialog_update_tensach, null);
                            builder_viewCsSach.setView(view);
                            builder_viewCsSach.setCancelable(false);

                            EditText ed_maSach = view.findViewById(R.id.ed_updateMaSach);
                            EditText ed_tenSach = view.findViewById(R.id.ed_updateTenSach);
                            EditText ed_NXB = view.findViewById(R.id.ed_updateNhaXuatBan);
                            EditText ed_soTrang = view.findViewById(R.id.ed_updateSoTrang);
                            EditText ed_theLoai = view.findViewById(R.id.ed_updateTheLoai);
                            EditText ed_ngayThem = view.findViewById(R.id.ed_updateNgayThem);
                            EditText ed_soLuong = view.findViewById(R.id.ed_updateSoLuong);
                            EditText ed_soTien = view.findViewById(R.id.ed_updateSoTien);
                            TextView tv_tongTien = view.findViewById(R.id.tv_updateSoTienSach);
                            ImageView img_date = view.findViewById(R.id.img_updateDate);

                            ed_maSach.setText(myThemSach.getTs_maSach());
                            ed_tenSach.setText(myThemSach.getTs_tenSach());
                            ed_NXB.setText(myThemSach.getTs_nhaXb());
                            ed_soTrang.setText(myThemSach.getTs_soTrang());
                            ed_theLoai.setText(myThemSach.getTs_theLoai());
                            ed_ngayThem.setText(myThemSach.getTs_ntNam());
                            ed_soLuong.setText(myThemSach.getTs_soLuongSach());
                            ed_soTien.setText(myThemSach.getTs_soTienSach());
                            tv_tongTien.setText(myThemSach.getTs_tongSoTien());
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(System.currentTimeMillis());
                            img_date.setOnClickListener(new View.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void onClick(View v) {
                                    DatePickerDialog dialog_date = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                            ed_ngayThem.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                        }
                                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH));
                                    dialog_date.show();
                                }
                            });


                            builder_viewCsSach.setCancelable(false);
                            builder_viewCsSach.setPositiveButton("Lưu thay đổi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences sharedPreferences_Sach = context.getSharedPreferences("MyPrefsFile_Sach", Context.MODE_PRIVATE);
                                    String id_Sach = sharedPreferences_Sach.getString("id_Sach_", "");
                                    Log.d("tess.ca3",id_Sach );
                                    if (ed_maSach.getText().toString().equals(myThemSach.getTs_maSach()) &&
                                            ed_tenSach.getText().toString().equals(myThemSach.getTs_tenSach()) &&
                                            ed_NXB.getText().toString().equals(myThemSach.getTs_nhaXb()) &&
                                            ed_soTrang.getText().toString().equals(myThemSach.getTs_soTrang()) &&
                                            ed_theLoai.getText().toString().equals(myThemSach.getTs_theLoai()) &&
                                            ed_ngayThem.getText().toString().equals(myThemSach.getTs_ntNam()) &&
                                            ed_soLuong.getText().toString().equals(myThemSach.getTs_soLuongSach()) &&
                                            ed_soTien.getText().toString().equals(myThemSach.getTs_soTienSach()) &&
                                            tv_tongTien.getText().toString().equals(myThemSach.getTs_tongSoTien())) {
                                        Toast.makeText(context, "Không có gì để thay đổi ", Toast.LENGTH_SHORT).show();
                                    } else {
                                        myThemSach.setTs_maSach(ed_maSach.getText().toString());
                                        myThemSach.setTs_tenSach(ed_tenSach.getText().toString());
                                        myThemSach.setTs_nhaXb(ed_NXB.getText().toString());
                                        myThemSach.setTs_soTrang(ed_soTrang.getText().toString());
                                        myThemSach.setTs_theLoai(ed_theLoai.getText().toString());
                                        myThemSach.setTs_ntNam(ed_ngayThem.getText().toString());
                                        double Tong = 0;
                                        final double GiaThanh = Double.parseDouble(ed_soTien.getText().toString());
                                        final double SoLuong = Double.parseDouble(ed_soLuong.getText().toString());
                                        Tong = GiaThanh * SoLuong;
                                        myThemSach.setTs_soLuongSach(SoLuong + "");
                                        myThemSach.setTs_soTienSach(GiaThanh + "");
                                        tv_tongTien.setText(Tong + "");
                                        myThemSach.setTs_tongSoTien(tv_tongTien.getText().toString());
                                        int ketqua = daoThemSach.chinhSuaTenSach(myThemSach,id_Sach);
                                        if (ketqua > 0) {
                                            SharedPreferences sharedPreferences_KeSach = context.getSharedPreferences("MyPrefsFile_KeSach", Context.MODE_PRIVATE);
                                            String id_keSach = sharedPreferences_KeSach.getString("id_keSach", "");
                                            Toast.makeText(context, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                                            list.clear();
                                            list.addAll(daoThemSach.LayDuLieuSach(id_keSach));
                                            notifyDataSetChanged();
                                        } else {
                                            Toast.makeText(context, "Thay đổi thất bai", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }
                            });
                            builder_viewCsSach.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog_show = builder_viewCsSach.create();
                            dialog_show.show();
                        } else {
                            String[] info_Sach = myDatabaseHelper.getInfo_ThemSach_5(myThemSach.getTs_maSach());
                            SharedPreferences.Editor editor_KeSach = v.getContext().getSharedPreferences(MY_PREFS_NAME_SACH, Context.MODE_PRIVATE).edit();
                            editor_KeSach.putString("id_Sach_", info_Sach[0]);
                            editor_KeSach.apply();

                            AlertDialog.Builder builder_view = new AlertDialog.Builder(v.getContext());
                            builder_view.setCancelable(false);
                            builder_view.setTitle("Bạn có chắc chắn muốn xóa sách này không ?");
                            builder_view.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    daoThemSach = new DaoThemSach(v.getContext());
                                    SharedPreferences sharedPreferences_KeSach = context.getSharedPreferences("MyPrefsFile_KeSach", Context.MODE_PRIVATE);
                                    String id_keSach = sharedPreferences_KeSach.getString("id_keSach", "");
                                    if (myThemSach != null) {
                                        SharedPreferences sharedPreferences_Sach = context.getSharedPreferences("MyPrefsFile_Sach", Context.MODE_PRIVATE);
                                        String id_Sach = sharedPreferences_Sach.getString("id_Sach_", "");
                                        long res = daoThemSach.xoaTenSach(id_Sach);
                                        if (res > 0) {
                                            list.clear();
                                            list.addAll(daoThemSach.LayDuLieuSach(id_keSach));
                                            notifyDataSetChanged();
                                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(context, "Lỗi xóa", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    dialog.dismiss();
                                }
                            });
                            builder_view.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder_view.create();
                            alertDialog.show();

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
                }, 4000);
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

    @Override
    public Filter getFilter() {
        return FilterMyThemSach;
    }

    private Filter FilterMyThemSach = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchText = constraint.toString().toLowerCase();
            ArrayList<MyThemSach> arrayList = new ArrayList<>();
            if (searchText.length() == 0 || searchText.isEmpty()) {
                arrayList.addAll(listFull);
            } else {
                for (MyThemSach item : listFull) {
                    if (item.getTs_tenSach().toLowerCase().contains(searchText)) {
                        arrayList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = arrayList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends MyThemSach>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ThemSachViewHolder extends RecyclerView.ViewHolder {
        TextView _tv_themSach;
        ImageView _img_themSach;


        public ThemSachViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            _tv_themSach = itemView.findViewById(R.id._tv_tenSach);
            _img_themSach = itemView.findViewById(R.id._img_itemview);

            myThemKeSach = new MyThemKeSach();

            daoThemSach = new DaoThemSach(itemView.getContext());

            myDatabaseHelper = new MyDatabaseHelper(itemView.getContext());


        }
    }
}
