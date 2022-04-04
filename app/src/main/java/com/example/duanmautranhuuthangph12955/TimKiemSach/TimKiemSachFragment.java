package com.example.duanmautranhuuthangph12955.TimKiemSach;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemKeSach;
import com.example.duanmautranhuuthangph12955.ClassDao.DaoThemSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemKeSach;
import com.example.duanmautranhuuthangph12955.MyModel.MyThemSach;
import com.example.duanmautranhuuthangph12955.R;
import com.example.duanmautranhuuthangph12955.RecyclerViewAdapter.ThemSachAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class TimKiemSachFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    SearchView searchView ;

    SharedPreferences sharedPreferences_KeSach;
    RecyclerView recyclerView;

    DaoThemSach daoThemSach;
    ArrayList<MyThemSach> myThemSaches;
    ThemSachAdapter themSachAdapter;
    DaoThemKeSach daoThemKeSach;

    MyThemSach myThemSach;
    MyThemKeSach myThemKeSach;

    public TimKiemSachFragment() {
        // Required empty public constructor
    }


    public static TimKiemSachFragment newInstance(String param1, String param2) {
        TimKiemSachFragment fragment = new TimKiemSachFragment();
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
        return inflater.inflate(R.layout.fragment_tim_kiem_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.search_viewSach);
        recyclerView =view.findViewById(R.id.rcv_search);

        daoThemSach = new DaoThemSach(getActivity());
        daoThemKeSach = new DaoThemKeSach(getActivity());

        myThemSach = new MyThemSach();
        myThemKeSach = new MyThemKeSach();

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(GridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(manager);


        myThemSaches = new ArrayList<>();
        myThemSaches = daoThemSach._LayDuLieuSach();

        themSachAdapter = new ThemSachAdapter(getActivity(), myThemSaches);

        recyclerView.setAdapter(themSachAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String ed_searchView =searchView.getQuery().toString();
                Log.d("chhhhh",ed_searchView);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                themSachAdapter.getFilter().filter(newText.toString());
                return false;
            }
        });
    }
}
