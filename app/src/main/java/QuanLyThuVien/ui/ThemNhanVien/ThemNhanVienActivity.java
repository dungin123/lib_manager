package QuanLyThuVien.ui.ThemNhanVien;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmautranhuuthangph12955.R;

import MyData.MyDatabaseHelper;
import QuanLyThuVien.MainActivityQuanLyThuVien;


public class ThemNhanVienActivity extends AppCompatActivity {

    EditText ed_hoTen;
    EditText ed_TenDangNhap;
    EditText ed_email;
    EditText ed_soDienThoai;
    EditText ed_namSinh;
    EditText ed_gioiTinh;
    EditText ed_maPin;
    EditText ed_XnMaPin;

    RadioGroup radioGroup;

    String vaiTro;

    MyDatabaseHelper myDatabaseHelper;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);

        ed_hoTen = findViewById(R.id.ed_hoTen_ThemNhanVien);
        ed_TenDangNhap = findViewById(R.id.ed_TenDangNhap_ThemNhanVien);
        ed_email = findViewById(R.id.ed_email_nv);
        ed_soDienThoai = findViewById(R.id.ed_sdt_nv);
        ed_namSinh = findViewById(R.id.ed_namSinh_nv);
        ed_gioiTinh = findViewById(R.id.ed_gioit_nv);
        ed_maPin = findViewById(R.id.ed_maPin);
        ed_XnMaPin = findViewById(R.id.ed_XacNhanMaPin);
        radioGroup = findViewById(R.id.rd_group_themNhanVien);


        myDatabaseHelper = new MyDatabaseHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tạo tài khoản nhân viên");

        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        vaiTro = "Quản lý";
                        break;
                    case R.id.radioButton2:
                        vaiTro = "Nhân viên kho";
                        break;
                    case R.id.radioButton3:
                        vaiTro = "Nhân viên bán hàng";

                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.item_iconsave_nv:
                if (ed_hoTen.length() == 0) {
                    ed_hoTen.requestFocus();
                    ed_hoTen.setError("Không bỏ trống");
                } else if (ed_TenDangNhap.length() == 0) {
                    ed_TenDangNhap.requestFocus();
                    ed_TenDangNhap.setError("Không bỏ trống");
                } else if (ed_email.length() == 0) {
                    ed_email.requestFocus();
                    ed_email.setError("Không bỏ trống");
                } else if (ed_email.length() == 0) {
                    ed_email.requestFocus();
                    ed_email.setError("Không bỏ trống");
                } else if (ed_soDienThoai.length() == 0) {
                    ed_soDienThoai.requestFocus();
                    ed_soDienThoai.setError("Không bỏ trống");
                } else if (ed_gioiTinh.length() == 0) {
                    ed_gioiTinh.requestFocus();
                    ed_gioiTinh.setError("Không bỏ trống");
                } else if (ed_namSinh.length() == 0) {
                    ed_namSinh.requestFocus();
                    ed_namSinh.setError("Không bỏ trống");
                } else if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa chọn vai trò", Toast.LENGTH_SHORT).show();
                } else if (ed_maPin.length() == 0) {
                    ed_maPin.requestFocus();
                    ed_maPin.setError("Không bỏ trống");
                } else if (ed_XnMaPin.length() == 0) {
                    ed_XnMaPin.requestFocus();
                    ed_XnMaPin.setError("Không bỏ trống");
                } else {
                    String hoTen = ed_hoTen.getText().toString().trim();
                    String tenTaiKhoan = ed_TenDangNhap.getText().toString().trim();
                    String email = ed_email.getText().toString().trim();
                    String soDienThoai = ed_soDienThoai.getText().toString().trim();
                    String namSinh = ed_namSinh.getText().toString().trim();
                    String gioiTinh = ed_gioiTinh.getText().toString().trim();
                    String maPin = ed_maPin.getText().toString().trim();
                    String XNmaPin = ed_XnMaPin.getText().toString().trim();

                    sharedPreferences = getSharedPreferences("MyPrefsFile_Admin", getApplication().MODE_PRIVATE);
                    String id_dk = sharedPreferences.getString("id_dk", "");
                    if (maPin.equals(XNmaPin)) {
                        Boolean aBoolean = myDatabaseHelper.checkTenDangNhap(tenTaiKhoan);
                        if (aBoolean == false) {
                            Boolean aBoolean1 = myDatabaseHelper.insertDataNhanVien(hoTen, tenTaiKhoan, email, soDienThoai, namSinh, gioiTinh, maPin, XNmaPin, vaiTro, id_dk);
                            if (aBoolean1 == true) {
                                Toast.makeText(getApplicationContext(), "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ThemNhanVienActivity.this, MainActivityQuanLyThuVien.class);
                                startActivity(intent);
                                fileList();
                            } else {
                                Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.item_delete_nv:
                ed_hoTen.setText("");
                ed_TenDangNhap.setText("");
                ed_email.setText("");
                ed_soDienThoai.setText("");
                ed_namSinh.setText("");
                ed_gioiTinh.setText("");
                ed_maPin.setText("");
                ed_XnMaPin.setText("");
                radioGroup.clearCheck();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu_themnv, menu);
        return true;
    }
}