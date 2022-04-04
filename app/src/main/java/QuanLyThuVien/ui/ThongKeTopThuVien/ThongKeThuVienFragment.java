package QuanLyThuVien.ui.ThongKeTopThuVien;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmautranhuuthangph12955.ClassDao.ThongKeDao;
import com.example.duanmautranhuuthangph12955.MyModel.Top;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.RecyclerViewAdapter.ThongKeAdapter;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class ThongKeThuVienFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ThongKeDao thongKeDao;
    TextView tv_tongNhapHang, tv_tongXuatHang, tv_tongSoLuongSach, tv_tongTienTrongKhoang;
    ImageView imageView1, imageView2;
    EditText ed_tuNgay, ed_denNgay;

    Button button;

    Locale lc = new Locale("nv", "VN");
    NumberFormat nf = NumberFormat.getCurrencyInstance(lc);

    ListView listView;
    ArrayList<Top> list;
    ThongKeAdapter thongKeAdapter;


    public ThongKeThuVienFragment() {
        // Required empty public constructor
    }

    public static ThongKeThuVienFragment newInstance(String param1, String param2) {
        ThongKeThuVienFragment fragment = new ThongKeThuVienFragment();
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
        return inflater.inflate(R.layout.fragment_thong_ke_thu_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        thongKeDao = new ThongKeDao(getContext());
        tv_tongNhapHang = view.findViewById(R.id.textView79);
        tv_tongXuatHang = view.findViewById(R.id.textView88);
        tv_tongSoLuongSach = view.findViewById(R.id.textView80);
        tv_tongTienTrongKhoang = view.findViewById(R.id.textView84);

        imageView1 = view.findViewById(R.id.imageView6);
        imageView2 = view.findViewById(R.id.imageView8);
        ed_tuNgay = view.findViewById(R.id.editTextTextPersonName5);
        ed_denNgay = view.findViewById(R.id.editTextTextPersonName6);
        button = view.findViewById(R.id.button);


        listView = view.findViewById(R.id.lv_thongke);
        list = (ArrayList<Top>) thongKeDao.getTop();
        thongKeAdapter = new ThongKeAdapter(getContext(), list);
        listView.setAdapter(thongKeAdapter);


        double tienNhapHang = thongKeDao.TongtienNhapHang();
        tv_tongNhapHang.setText(tienNhapHang + "");
        String tien_fm1 = nf.format(tienNhapHang);
        tv_tongNhapHang.setText(tien_fm1);

        double tienXuatHang = thongKeDao.TongtienXuatHang();
        tv_tongXuatHang.setText(tienXuatHang + "");
        String tien_fm2 = nf.format(tienNhapHang);
        tv_tongXuatHang.setText(tien_fm2);

        int tongSoSach = thongKeDao.TongSoSach();
        tv_tongSoLuongSach.setText(tongSoSach + "\t quyển");


        getdateStart();
        getdateend();
        onclickViewTT();
    }

    public void getdateStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ed_tuNgay.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }

    public void getdateend() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ed_denNgay.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
    }

    public void onclickViewTT() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_tuNgay.length() == 0) {
                    ed_tuNgay.requestFocus();
                    ed_tuNgay.setError("Không để trống");
                } else if (ed_denNgay.length() == 0) {
                    ed_denNgay.requestFocus();
                    ed_denNgay.setError("Không để trống");
                } else {
                    String startDay1 = ed_tuNgay.getText().toString();
                    String endDay1 = ed_denNgay.getText().toString();
                    double hienthi = thongKeDao.TongBetwenNhapSach(startDay1, endDay1);
                    Log.d("zzzzaaaaa", hienthi + "");
                    tv_tongTienTrongKhoang.setText(hienthi + "\t \t VND");

                    Locale lc = new Locale("nv", "VN");
                    NumberFormat nf = NumberFormat.getCurrencyInstance(lc);

                    String tien_fm1 = nf.format(hienthi);
                    tv_tongTienTrongKhoang.setText(tien_fm1);
                }

            }
        });
    }
}