package QuanLyThuVien.ui.ThemGhiChu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoGhiChu;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemGhiChu;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.RecyclerViewAdapter.GhiChuAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class ThemGhiChuFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    FloatingActionButton button ;
    ListView listView ;

    ArrayList<MyThemGhiChu> myThemGhiChus;
    GhiChuAdapter ghiChuAdapter ;
    DaoGhiChu daoGhiChu;

    public ThemGhiChuFragment() {
        // Required empty public constructor
    }

    public static ThemGhiChuFragment newInstance(String param1, String param2) {
        ThemGhiChuFragment fragment = new ThemGhiChuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_them_ghi_chu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        daoGhiChu = new DaoGhiChu(getContext());


        button=view.findViewById(R.id.mfab_ThemGhiChu);
        listView = view.findViewById(R.id.list_viewTGC);

        myThemGhiChus = daoGhiChu.getAll();
        ghiChuAdapter = new GhiChuAdapter(myThemGhiChus);
        listView.setAdapter(ghiChuAdapter);


        myThemGhiChus.clear();
        myThemGhiChus.addAll(daoGhiChu.getAll());
        ghiChuAdapter.notifyDataSetChanged();

        clickThemGhiChu();
    }

    public void clickThemGhiChu(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TaoGhiChuMainActivity.class);
                startActivity(intent);
            }
        });
    }
}