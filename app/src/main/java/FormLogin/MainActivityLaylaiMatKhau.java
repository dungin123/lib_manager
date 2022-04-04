package FormLogin;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmautranhuuthangph12955.R;

import MyData.MyDatabaseHelper;

public class MainActivityLaylaiMatKhau extends AppCompatActivity {

    private EditText ed_name ;
    private Button btn_TiepTuc ;
    MyDatabaseHelper myDatabaseHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_laylai_mat_khau);

        ed_name = findViewById(R.id.ed_timKiemTen);
        btn_TiepTuc = findViewById(R.id.btn_tiepTuc);

        myDatabaseHelper = new MyDatabaseHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lấy lại mật khẩu");

        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);


        btn_TiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTaiKhoan = ed_name.getText().toString();
                Boolean checkName = myDatabaseHelper.checkTenDangNhap(tenTaiKhoan);
                if (ed_name.length()==0){
                    ed_name.requestFocus();
                    ed_name.setError("Không được bỏ trống tên");
                }else {
                    if (checkName==true){
                        Intent  intent = new Intent(MainActivityLaylaiMatKhau.this,MainActivityNewPassWord.class);
                        intent.putExtra("name",tenTaiKhoan);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"Tên không có trong hệ thống",Toast.LENGTH_LONG).show();
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
}