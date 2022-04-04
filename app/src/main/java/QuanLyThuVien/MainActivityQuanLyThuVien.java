package QuanLyThuVien;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.databinding.ActivityMainQuanLyThuVienBinding;
import com.google.android.material.navigation.NavigationView;

import MyData.MyDatabaseHelper;

public class MainActivityQuanLyThuVien extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainQuanLyThuVienBinding binding;
    private NavigationView navigationView;

    DrawerLayout drawer;
    private MyDatabaseHelper myDatabaseHelper;

    private SharedPreferences sharedPreferences_admin;
    private SharedPreferences sharedPreferences_nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainQuanLyThuVienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMainActivityQuanLyThuVien.toolbar);

        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_TrangChu, R.id.nav_ThongKe, R.id.nav_GhiChu, R.id.nav_HuongDan, R.id.nav_hoiDap, R.id.nav_UngDung, R.id.nav_ThongTinCaNhan, R.id.nav_ThemNhanVien, R.id.nav_CaiDat, R.id.nav_DangXuat)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_quan_ly_thu_vien);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        myDatabaseHelper = new MyDatabaseHelper(this);
        sharedPreferences_admin = getSharedPreferences("MyPrefsFile_Admin", this.MODE_PRIVATE);
        sharedPreferences_nv = getSharedPreferences("MyPrefsFile_NhanVien", this.MODE_PRIVATE);

        Menu nav_menu = navigationView.getMenu();

        String vaiTro = sharedPreferences_nv.getString("id10", "");

        String vaiTro1 = sharedPreferences_nv.getString("id1", "");
        String vaiTro2 = sharedPreferences_nv.getString("id2", "");
        String vaiTro3 = sharedPreferences_nv.getString("id3", "");
        String vaiTro4 = sharedPreferences_nv.getString("email", "");
        String vaiTro5 = sharedPreferences_nv.getString("id5", "");
        String vaiTro6 = sharedPreferences_nv.getString("id6", "");
        String vaiTro7 = sharedPreferences_nv.getString("id7", "");
        String vaiTro8 = sharedPreferences_nv.getString("id8", "");
        String vaiTro9 = sharedPreferences_nv.getString("id9", "");

        String vaiTro11 = sharedPreferences_nv.getString("id11", "");

        if (vaiTro.equals("Quản lý") || vaiTro.equals("Nhân viên kho") || vaiTro.equals("Nhân viên bán hàng")) {
            nav_menu.findItem(R.id.nav_ThemNhanVien).setVisible(false);
        } else {
            nav_menu.findItem(R.id.nav_ThemNhanVien).setVisible(true);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.my_menu_custom_delete_all, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_quan_ly_thu_vien);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }

}