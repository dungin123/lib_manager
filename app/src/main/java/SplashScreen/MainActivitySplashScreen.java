package SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmautranhuuthangph12955.MainActivity;
import com.example.duanmautranhuuthangph12955.R;

public class MainActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash_screen);


        //Đặt toàn màn hình
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Khởi tạo hoạt ảnh hàng đầu,Dưới,
//        Animation animation_1 = AnimationUtils.loadAnimation(this, R.anim.animation_top1);
//        imgTop.setAnimation(animation_1);
//
//        Animation animation_2 = AnimationUtils.loadAnimation(this, R.anim.animation_bottom);
//        imgBottom.setAnimation(animation_2);
        ActionBar  actionBar = getSupportActionBar();
        actionBar.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivitySplashScreen.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                );
                finish();
            }
        }, 1200);

    }
}