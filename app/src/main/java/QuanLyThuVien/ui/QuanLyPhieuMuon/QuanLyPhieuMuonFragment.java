package QuanLyThuVien.ui.QuanLyPhieuMuon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemPhieuMuon;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemPhieuMuon;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.RecyclerViewAdapter.ThemPhieuMuonAdapter;

import java.util.ArrayList;

public class QuanLyPhieuMuonFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    ThemPhieuMuonAdapter themPhieuMuonAdapter;
    DaoThemPhieuMuon daoThemPhieuMuon;
    RecyclerView recyclerView;
    ArrayList<MyThemPhieuMuon> arrayList_PhieuMuon;


    public QuanLyPhieuMuonFragment() {
        // Required empty public constructor
    }


    public static QuanLyPhieuMuonFragment newInstance(String param1, String param2) {
        QuanLyPhieuMuonFragment fragment = new QuanLyPhieuMuonFragment();
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
        return inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getView().findViewById(R.id.rcv_phieuMuon);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        daoThemPhieuMuon = new DaoThemPhieuMuon(getActivity());

        arrayList_PhieuMuon = new ArrayList<>();
        arrayList_PhieuMuon = daoThemPhieuMuon.LayDuLieuPhieuMuon();

        themPhieuMuonAdapter = new ThemPhieuMuonAdapter(getActivity(), getActivity(), arrayList_PhieuMuon);

        recyclerView.setAdapter(themPhieuMuonAdapter);
    }
}