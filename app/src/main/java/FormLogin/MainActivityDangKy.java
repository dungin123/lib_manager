package FormLogin;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmautranhuuthangph12955.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import MyData.MyDatabaseHelper;

public class MainActivityDangKy extends AppCompatActivity {

    private EditText ed_hoTen;
    private EditText ed_tenDangNhap;
    private EditText ed_Email;
    private EditText ed_SoDienThoai;
    private EditText ed_MatKhau;
    private EditText ed_MatKhauXacThuc;
    private EditText ed_Date;
    private EditText ed_VaiTro;
    private RadioGroup radioGroup;
    private RadioButton nam;
    private RadioButton nu;
    private Button btnDangKy;
    MyDatabaseHelper myDatabaseHelper;

    String gioitinh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);


        ed_hoTen = findViewById(R.id.ed_HoTen_fromDangKy);
        ed_tenDangNhap = findViewById(R.id.ed_TenDangNhap_fromDangKy);
        ed_Email = findViewById(R.id.ed_Email_fromDangKy);
        ed_SoDienThoai = findViewById(R.id.ed_SoDienThoai_fromDangKy);
        ed_MatKhau = findViewById(R.id.ed_PassWord_fromDangKy);
        ed_MatKhauXacThuc = findViewById(R.id.ed_XacThucPassWord_fromDangKy);
        ed_Date = findViewById(R.id.ed_NgayThangNam_fromDangKy);
        ed_VaiTro = findViewById(R.id.ed_vaiTro);
        radioGroup = findViewById(R.id.rd_group);
        nam = findViewById(R.id.rd_Nam_fromDangKy);
        nu = findViewById(R.id.rd_nu_fromDangKy);
        btnDangKy = findViewById(R.id.btn_from_dangKy);


        myDatabaseHelper = new MyDatabaseHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Đăng ký tài khoản");

        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_Nam_fromDangKy:
                        gioitinh = "Nam";
                        break;
                    case R.id.rd_nu_fromDangKy:
                        gioitinh = "Nữ";
                }
            }
        });

        clickDangKyTaiKhoan();
    }

    public void clickDangKyTaiKhoan() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = ed_hoTen.getText().toString().trim();
                String tenTaiKhoan = ed_tenDangNhap.getText().toString().trim();
                String email = ed_SoDienThoai.getText().toString().trim();
                String sdt = ed_Email.getText().toString().trim();
                String matkhau = ed_MatKhau.getText().toString().trim();
                String xacthuc = ed_MatKhauXacThuc.getText().toString().trim();
                String namsinh = ed_Date.getText().toString().trim();
                String vaitro = ed_VaiTro.getText().toString().trim();
                if (ed_hoTen.length() == 0) {
                    ed_hoTen.requestFocus();
                    ed_hoTen.setError("Vui lòng không để trống họ tên");
                } else if (ed_tenDangNhap.length() == 0) {
                    ed_tenDangNhap.requestFocus();
                    ed_tenDangNhap.setError("Vui lòng không để trống tên đăng nhập");
                } else if (ed_Email.length() == 0) {
                    ed_Email.requestFocus();
                    ed_Email.setError("Vui lòng kiểm tra lại E-Mail");
                } else if (ed_SoDienThoai.length() == 0) {
                    ed_SoDienThoai.requestFocus();
                    ed_SoDienThoai.setError("Vui lòng kiểm tra lại số điện thoại");
                } else if (ed_MatKhau.length() == 0) {
                    ed_MatKhau.requestFocus();
                    ed_MatKhau.setError("Vui lòng không để trống mật khẩu");
                } else if (ed_MatKhauXacThuc.length() == 0) {
                    ed_MatKhauXacThuc.requestFocus();
                    ed_MatKhauXacThuc.setError("Vui lòng không để trống mật khẩu");
                } else if (ed_Date.length() == 0) {
                    ed_Date.requestFocus();
                    ed_Date.setError("Ngày sinh không được bỏ trống");
                } else if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getBaseContext(), "Bạn chưa chọn giới tính", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkNgaySinh(ed_Date)) {
                        if (matkhau.equals(xacthuc)) {
                            Boolean aBoolean = myDatabaseHelper.checkTenDangNhap(tenTaiKhoan);
                            if (aBoolean == false) {
                                Boolean aBoolean1 = myDatabaseHelper.insertData(hoTen, tenTaiKhoan, email, sdt, matkhau, xacthuc, namsinh, gioitinh, vaitro);
                                if (aBoolean1 == true) {
                                    Toast.makeText(getApplicationContext(), "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivityDangKy.this, MainActivityDangNhap.class);
                                    startActivity(intent);
                                    finish();
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
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean checkNgaySinh(EditText editText) {
        Date date = null;
        String s = editText.getText().toString();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = simpleDateFormat.parse(s);
            if (!s.equals(simpleDateFormat.format(date))) {
                date = null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Sai định dạng ngày \n dd/MM/yyyy ", Toast.LENGTH_SHORT).show();

        }
        return date != null;
    }
}