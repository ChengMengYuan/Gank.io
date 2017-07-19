package com.cmy.bigsnow.layout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.layout.adapter.WeekRecylerAdapter;

import java.util.ArrayList;

public class WeekFragment extends Fragment {
    private static final String ARG_TIMELINE_TYPE = "ARG_TIMELINE_TYPE";

    private View rootView;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<String> tiemlists,titlelist;

    public static WeekFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_TIMELINE_TYPE, type);
        WeekFragment fragment = new WeekFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_week, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //这里使用线性布局像ListView那样展示列表,第二个参数可以改为 HORIZONTAL实现水平展示
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_week);
        //设置布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);
        tiemlists = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            tiemlists.add("2017-7-" + i);
        }

        titlelist = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            titlelist.add("this is the=="+i+"==title");
        }

        final WeekRecylerAdapter adapter = new WeekRecylerAdapter(getContext(), tiemlists, titlelist);

        //设置adapter
        recyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(
//                getActivity(), DividerItemDecoration.HORIZONTAL));
    }
}
