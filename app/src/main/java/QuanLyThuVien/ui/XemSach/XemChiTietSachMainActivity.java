package QuanLyThuVien.ui.XemSach;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemSach;
import com.example.duanmautranhuuthangph12955.R;

import java.util.ArrayList;

public class XemChiTietSachMainActivity extends AppCompatActivity {
    TextView tv_ma,
            tv_ten,
            tv_nxb,
            tv_sotrang,
            tv_theLoai,
            tv_ngayThem,
            tv_soLuong,
            tv_donGia;
    ArrayList<MyThemSach> list;
    DaoThemSach daoThemSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_chi_tiet_sach_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin sách ");
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        Anhxa();
        daoThemSach = new DaoThemSach(this);

        SharedPreferences sharedPreferences_Sach = getSharedPreferences("MyPrefsFile_Sach", Context.MODE_PRIVATE);

        String id_Sach = sharedPreferences_Sach.getString("id_Sach_", "");

        list = daoThemSach.LayDuLieuSach_2(id_Sach);

        MyThemSach myThemSach = new MyThemSach();
        myThemSach = list.get(0);
        tv_ma.setText(myThemSach.getTs_maSach());
        tv_ten.setText(myThemSach.getTs_tenSach());
        tv_nxb.setText(myThemSach.getTs_nhaXb());
        tv_sotrang.setText(myThemSach.getTs_soTrang());
        tv_theLoai.setText(myThemSach.getTs_theLoai());
        tv_ngayThem.setText(myThemSach.getTs_ntNam());
        tv_soLuong.setText(myThemSach.getTs_soLuongSach());
        tv_donGia.setText(myThemSach.getTs_soTienSach()+"\t \t VND");


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
        tv_ma = findViewById(R.id.view_ms);
        tv_ten = findViewById(R.id.view_tensach);
        tv_nxb = findViewById(R.id.view_nxb);
        tv_sotrang = findViewById(R.id.view_sotrang);
        tv_theLoai = findViewById(R.id.view_theloai);
        tv_ngayThem = findViewById(R.id.view_ngthem);
        tv_soLuong = findViewById(R.id.view_soluong);
        tv_donGia = findViewById(R.id.view_dongia);
    }
}