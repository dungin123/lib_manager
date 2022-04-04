package FormLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmautranhuuthangph12955.R;

import MyData.MyDatabaseHelper;
import QuanLyThuVien.MainActivityQuanLyThuVien;

public class MainActivityDangNhap extends AppCompatActivity {

    private Button btn_log;
    private EditText ed_tenDangNhap;
    private EditText ed_passWord;
    private MyDatabaseHelper myDatabaseHelper;

    private TextView tv_rsPass;

    public static final String MY_PREFS_NAME_AD = "MyPrefsFile_Admin";
    public static final String MY_PREFS_NAME_NV = "MyPrefsFile_NhanVien";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        btn_log = findViewById(R.id.btn_DangNhap);
        ed_tenDangNhap = findViewById(R.id.ed_tenDangNhap);
        ed_passWord = findViewById(R.id.ed_passWord);

        tv_rsPass = findViewById(R.id.tv_rsPassword);

        myDatabaseHelper = new MyDatabaseHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Đăng nhập");

        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ed_tenDangNhap.length() == 0) {
                    ed_tenDangNhap.requestFocus();
                    ed_tenDangNhap.setError("Vui lòng không để trống tên");
                } else if (ed_passWord.length() == 0) {
                    ed_passWord.requestFocus();
                    ed_passWord.setError("Vui lòng không để trống mật khẩu");
                } else {
                    String user = ed_tenDangNhap.getText().toString();
                    String pass = ed_passWord.getText().toString();
                    Boolean aBoolean = myDatabaseHelper.checkMatKhau(user, pass);
                    if (aBoolean == true) {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        String[] info_admin = myDatabaseHelper.getInfo_Admin(user, pass);
                        //admin
                        SharedPreferences.Editor editor_Admin = getSharedPreferences(MY_PREFS_NAME_AD, MODE_PRIVATE).edit();
                        editor_Admin.putString("id_dk", info_admin[0]);
                        editor_Admin.putString("hoTen", info_admin[1]);
                        editor_Admin.putString("", info_admin[2]);
                        editor_Admin.putString("eMail", info_admin[3]);
                        editor_Admin.putString("SodienThoai", info_admin[4]);
                        editor_Admin.putString("", info_admin[5]);
                        editor_Admin.putString("", info_admin[6]);
                        editor_Admin.putString("NgaySinh", info_admin[7]);
                        editor_Admin.putString("GioiTinh", info_admin[8]);
                        editor_Admin.putString("avc", info_admin[9]);

                        editor_Admin.apply();
                        //end
                        //Lấy csdl bảng thêm nhân viên
                        String[] info_ThemNhanVien = myDatabaseHelper.getInfo_ThemNhanVien(user, pass);
                        SharedPreferences.Editor editor_ThemNhanVien = getSharedPreferences(MY_PREFS_NAME_NV, MODE_PRIVATE).edit();


                        editor_ThemNhanVien.putString("id11", info_ThemNhanVien[10]);
                        editor_ThemNhanVien.putString("id10", info_ThemNhanVien[9]);
                        editor_ThemNhanVien.putString("id9", info_ThemNhanVien[8]);
                        editor_ThemNhanVien.putString("gioi_tinh", info_ThemNhanVien[7]);
                        editor_ThemNhanVien.putString("ngaySinh_nv", info_ThemNhanVien[6]);
                        editor_ThemNhanVien.putString("sdt_nv", info_ThemNhanVien[5]);
                        editor_ThemNhanVien.putString("id5", info_ThemNhanVien[4]);
                        editor_ThemNhanVien.putString("email", info_ThemNhanVien[3]);
                        editor_ThemNhanVien.putString("id3", info_ThemNhanVien[2]);
                        editor_ThemNhanVien.putString("hoTen", info_ThemNhanVien[1]);
                        editor_ThemNhanVien.putString("id1", info_ThemNhanVien[0]);

                        editor_ThemNhanVien.apply();

                        //end
                        Intent intent = new Intent(MainActivityDangNhap.this, MainActivityQuanLyThuVien.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        tv_rsPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDangNhap.this, MainActivityLaylaiMatKhau.class);
                startActivity(intent);
            }
        });
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
}