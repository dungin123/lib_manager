package QuanLyThuVien.ui.ThemGhiChu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoGhiChu;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemGhiChu;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.RecyclerViewAdapter.GhiChuAdapter;

import java.util.ArrayList;

import QuanLyThuVien.MainActivityQuanLyThuVien;

public class TaoGhiChuMainActivity extends AppCompatActivity {

    EditText ed_noiDung, ed_TieuDe;
    DaoGhiChu daoGhiChu;
    ArrayList<MyThemGhiChu> myThemGhiChus;
    GhiChuAdapter ghiChuAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_ghi_chu_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thêm ghi chú mới");
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        ed_noiDung = findViewById(R.id.editTextTextMultiLine2);
        ed_TieuDe = findViewById(R.id.editTextTextMultiLine);
        daoGhiChu = new DaoGhiChu(this);


        myThemGhiChus = daoGhiChu.getAll();
        ghiChuAdapter = new GhiChuAdapter(myThemGhiChus);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.item_mark:
                if (ed_TieuDe.length() == 0) {
                    ed_TieuDe.requestFocus();
                    ed_TieuDe.setError("Tiêu đề không được để trống");
                } else if (ed_noiDung.length() == 0) {
                    ed_noiDung.requestFocus();
                    ed_noiDung.setError("Nội dung không được để trống");
                } else {
                    MyThemGhiChu myThemGhiChu = new MyThemGhiChu();
                    myThemGhiChu.setTieude(ed_TieuDe.getText().toString());
                    myThemGhiChu.setNoidung(ed_noiDung.getText().toString());
                    long kq = daoGhiChu.insert(myThemGhiChu);
                    if (kq > 0) {
                        Toast.makeText(getBaseContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TaoGhiChuMainActivity.this, MainActivityQuanLyThuVien.class);
                        startActivity(intent);
                        fileList();
                    } else {
                        Toast.makeText(getBaseContext(), "Lỗi thêm", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.item_delete:
                ed_TieuDe.setText("");
                ed_noiDung.setText("");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu_ghichu, menu);
        return true;
    }
}