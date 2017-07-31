package com.cmy.bigsnow.layout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.bean.CallBack;
import com.cmy.bigsnow.bean.Results;
import com.cmy.bigsnow.http.GankApi;
import com.cmy.bigsnow.layout.adapter.WeekRecylerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeekFragment extends Fragment {
    private static final String ARG_TIMELINE_TYPE = "ARG_TIMELINE_TYPE";

    private View rootView;
    private RecyclerView recyclerView;
    private CardView cardView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<String> tiemlists, titlelist;
    private ProgressBar pbWait;
    private WeekRecylerAdapter adapter;

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
        // TODO: 2017/7/20 在这里获取7日的数据信息并展示
        //这里使用线性布局像ListView那样展示列表,第二个参数可以改为 HORIZONTAL实现水平展示
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_week);
        pbWait = (ProgressBar) rootView.findViewById(R.id.pb_wait);
        pbWait.setVisibility(View.VISIBLE);
        cardView = (CardView) rootView.findViewById(R.id.cv_week);
        //设置布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);
        //                tiemlists = new ArrayList<>();
        //                for (int i = 1; i < 8; i++) {
        //                    tiemlists.add("2017-7-" + i);
        //                }
        //
        //                titlelist = new ArrayList<>();
        //                for (int i = 1; i < 8; i++) {
        //                    titlelist.add("this is the==" + i + "==title");
        //                }
        getWeekData();
    }


    private void getWeekData() {
        tiemlists = new ArrayList<>();
        titlelist = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                // 设置网络请求的Url地址
                .baseUrl("http://gank.io/api/")
                // 设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        GankApi gankApi = retrofit.create(GankApi.class);

        //对发送请求进行封装
        Call<CallBack> call = gankApi.getWeekData();
        //发送网络请求(异步)
        call.enqueue(new Callback<CallBack>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<CallBack> call, Response<CallBack> response) {
                List<Results> resultsList = response.body().getResults();
                for (int j = 0; j < resultsList.size(); j++) {
                    tiemlists.add(resultsList.get(j).getPublishedAt());
                    titlelist.add(resultsList.get(j).getTitle());
                    Log.d("title", resultsList.get(j).getTitle());
                }

                adapter = new WeekRecylerAdapter(
                        getContext(),
                        cardView,
                        tiemlists,
                        titlelist);

                //设置adapter
                recyclerView.setAdapter(adapter);
                //设置Item增加、移除动画
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                //添加分割线
                //        recyclerView.addItemDecoration(new DividerItemDecoration(
                //                getActivity(), DividerItemDecoration.HORIZONTAL));
                pbWait.setVisibility(View.GONE);
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<CallBack> call, Throwable t) {

            }
        });
    }

}
