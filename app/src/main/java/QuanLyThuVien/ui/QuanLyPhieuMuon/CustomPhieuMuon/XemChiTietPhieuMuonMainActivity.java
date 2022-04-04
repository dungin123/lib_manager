package QuanLyThuVien.ui.QuanLyPhieuMuon.CustomPhieuMuon;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemPhieuMuon;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemPhieuMuon;
import com.example.duanmautranhuuthangph12955.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class XemChiTietPhieuMuonMainActivity extends AppCompatActivity {

    ArrayList<MyThemPhieuMuon> arrayList_PhieuMuon;
    DaoThemPhieuMuon daoThemPhieuMuon;

    SharedPreferences sharedPreferences_phieuMuon;

    TextView tv_run, tv_tenKh, tv_diaChi, tv_sdt, tv_ltt, tv_trangThai, tv_maSach, tv_tenSach,
            tv_ngayMuaSach, tv_ngayTraSach, tv_tongTienMua, tv_DTCT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_chi_tiet_phieu_muon_main);
        Anhxa();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Xem chi tiết phiêu mượn");

        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        tv_run = findViewById(R.id.tv_run);
        tv_run.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv_run.setSelected(true);


        sharedPreferences_phieuMuon = getSharedPreferences("MyPrefsFile_idPhieuMuon", Context.MODE_PRIVATE);

        String id_phieuMuon = sharedPreferences_phieuMuon.getString("id_phieumuon", "");
        daoThemPhieuMuon = new DaoThemPhieuMuon(this);
        arrayList_PhieuMuon = new ArrayList<>();
        arrayList_PhieuMuon = daoThemPhieuMuon.LayDuLieuPhieuMuon_2(id_phieuMuon);

        Log.d("chanvkl", id_phieuMuon);

        MyThemPhieuMuon myThemPhieuMuon = arrayList_PhieuMuon.get(0);

        tv_tenKh.setText(myThemPhieuMuon.getTenkh_pm());
        tv_diaChi.setText(myThemPhieuMuon.getDiachi_pm());
        tv_sdt.setText(myThemPhieuMuon.getSdt_pm());
        tv_ltt.setText(myThemPhieuMuon.getLtt_pm());
        tv_trangThai.setText(myThemPhieuMuon.getTt_pm());
        tv_maSach.setText(myThemPhieuMuon.getMasach_pm());
        tv_tenSach.setText(myThemPhieuMuon.getTensach_pm());
        tv_ngayMuaSach.setText(myThemPhieuMuon.getNgay_pm());
        tv_ngayTraSach.setText(myThemPhieuMuon.getNgaytra_pm());


        tv_tongTienMua.setText(myThemPhieuMuon.getTongtien_pm());

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date_ = simpleDateFormat.format(date);
        try {
            Date date_cd =simpleDateFormat.parse(date_);
            Date date_cd2 = simpleDateFormat.parse(myThemPhieuMuon.getNgaytra_pm());
            if (date_.equals(myThemPhieuMuon.getNgaytra_pm())) {
                tv_DTCT.setText("Đã đển hạn trả sách");
                tv_DTCT.setTextColor(getColor(R.color.purple_72));
                // bang
            } else if (date_cd.after(date_cd2)==false) {
                tv_DTCT.setText("Chưa trả sách");
                tv_DTCT.setTextColor(getColor(R.color.mauDo));
                //Sau
            } else {
                tv_DTCT.setText("Đẫ trả");
                tv_DTCT.setTextColor(getColor(R.color.mauXanh));
                //Trước
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("ngay", date_ + "");




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Anhxa() {
        tv_run = findViewById(R.id.tv_run);
        tv_tenKh = findViewById(R.id.textView68);
        tv_diaChi = findViewById(R.id.textView69);
        tv_sdt = findViewById(R.id.textView70);
        tv_ltt = findViewById(R.id.textView71);
        tv_trangThai = findViewById(R.id.textView72);
        tv_maSach = findViewById(R.id.textView73);
        tv_tenSach = findViewById(R.id.textView74);
        tv_ngayMuaSach = findViewById(R.id.textView75);
        tv_ngayTraSach = findViewById(R.id.textView76);
        tv_tongTienMua = findViewById(R.id.textView77);
        tv_DTCT = findViewById(R.id.id_DTCT);
    }
}