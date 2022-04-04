package com.example.duanmautranhuuthangph12955;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import FormLogin.MainActivityDangKy;
import FormLogin.MainActivityDangNhap;

public class MainActivity extends AppCompatActivity {

    private Button btnDangNhap ;
    private Button btnDangKy ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy =findViewById(R.id.btnDangKy);

        ActionBar actionBar = getSupportActionBar();
     //   actionBar.setTitle("Chào mừng đến với quản lý thư viện");
        actionBar.hide();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MainActivity.this, MainActivityDangNhap.class);
                startActivity(intent);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MainActivity.this, MainActivityDangKy.class);
                startActivity(intent);
            }
        });
    }
}