package QuanLyThuVien.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmautranhuuthangph12955.HomeFragmentChild.HomeFragmentChild;
import com.example.duanmautranhuuthangph12955.NhapSach.NhapSachFragment;
import com.example.duanmautranhuuthangph12955.PhieuMuon_XuatSach.PhieuMuonFragment;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.TimKiemSach.TimKiemSachFragment;
import com.example.duanmautranhuuthangph12955.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import MyData.MyDatabaseHelper;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private BottomNavigationView view_1;

    MyDatabaseHelper myDatabaseHelper;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_temp, new HomeFragmentChild()).commit();


        return root;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view_1 = view.findViewById(R.id.mbottom_nav);

        myDatabaseHelper = new MyDatabaseHelper(getContext());

        view_1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.atc_trangChu:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_temp, new HomeFragmentChild()).commit();

                        break;
                    case R.id.atc_nhapSach:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_temp, new NhapSachFragment()).commit();

                        break;
                    case R.id.atc_xuatSach:
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_temp, new PhieuMuonFragment()).commit();
                        break;
                    case R.id.atc_thueSach:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_temp, new TimKiemSachFragment()).commit();

                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}