package QuanLyThuVien.ui.ThongTinCaNhan;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmautranhuuthangph12955.R;

import MyData.MyDatabaseHelper;

public class DoiMatKhauMainActivity extends AppCompatActivity {

    EditText ed_tenDangNhap, ed_matKhauCu, ed_matKhauMoi, ed_xacNhanMatKhauMoi;
    Button button;
    TextView textView;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Xác thực thông tin");

        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        myDatabaseHelper = new MyDatabaseHelper(this);
        ed_tenDangNhap = findViewById(R.id.editTextTextPersonName9);
        ed_matKhauCu = findViewById(R.id.ed_passCu);
        button = findViewById(R.id.btn_tiepTuc);
        doiMatKhau();
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

    public void doiMatKhau() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_tenDangNhap.length() == 0) {
                    ed_tenDangNhap.requestFocus();
                    ed_tenDangNhap.setError("Không bỏ trống");
                } else if (ed_matKhauCu.length() == 0) {
                    ed_matKhauCu.requestFocus();
                    ed_matKhauCu.setError("không bỏ trống");
                } else {
                    String ed_tenDN = ed_tenDangNhap.getText().toString();
                    String ed_passCu = ed_matKhauCu.getText().toString();
                    Boolean checkPass = myDatabaseHelper.checkMatKhau(ed_passCu, ed_tenDN);
                    Boolean aBoolean_name = myDatabaseHelper.checkTenDangNhap(ed_tenDN);
                    if (aBoolean_name == true) {
                        if (checkPass == true) {

                        } else {
                            Intent intent = new Intent(DoiMatKhauMainActivity.this, MainActivityThayDoiMatKhauCaNhan.class);
                            startActivity(intent);
                        }
                    } else {

                    }
                }


            }
        });
    }
}


