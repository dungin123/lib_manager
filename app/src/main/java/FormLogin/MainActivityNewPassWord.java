package FormLogin;

import android.content.Intent;
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

public class MainActivityNewPassWord extends AppCompatActivity {

    private TextView tv_nameRS;
    private EditText ed_Pass;
    private EditText ed_PassXacThuc;
    private Button btn_ThayDoi;
    private Button btn_NhapLai;
    private Intent intent;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new_pass_word);

        tv_nameRS = findViewById(R.id.tv_name_rs);
        ed_Pass = findViewById(R.id.ed_pass_rs);
        ed_PassXacThuc = findViewById(R.id.ed_pass_rsxd);
        btn_ThayDoi = findViewById(R.id.btn_thayDoi);
        btn_NhapLai = findViewById(R.id.btn_nhapLai);

        myDatabaseHelper = new MyDatabaseHelper(this);

        intent = getIntent();

        tv_nameRS.setText(intent.getStringExtra("name"));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Đặt lại mật khẩu");

        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        btn_ThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tv_nameRS.length() == 0) {
                    tv_nameRS.requestFocus();
                    tv_nameRS.setError("Tên không được bỏ trống");
                } else if (ed_Pass.length() == 0) {
                    ed_Pass.requestFocus();
                    ed_Pass.setError("Mật khẩu không được bỏ trống");
                } else if (ed_PassXacThuc.length() == 0) {
                    ed_PassXacThuc.requestFocus();
                    ed_PassXacThuc.setError("Mật khẩu xác thực không được bỏ trống");
                } else {
                    String user = tv_nameRS.getText().toString();
                    String password = ed_Pass.getText().toString();
                    String passwordXacNhan = ed_PassXacThuc.getText().toString();
                    if (password.equals(passwordXacNhan)) {
                        Boolean aBoolean = myDatabaseHelper.CapNhapMatKhau(user, password);
                        Toast.makeText(getApplicationContext(), "Thay đổi mật thành công", Toast.LENGTH_LONG).show();
                        intent = new Intent(MainActivityNewPassWord.this, MainActivityDangNhap.class);
                        startActivity(intent);
                        finish();
                        if (aBoolean == true) {
                        } else {
                            Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu không khớp", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        btn_NhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_Pass.setText("");
                ed_PassXacThuc.setText("");
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