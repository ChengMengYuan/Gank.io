package com.cmy.bigsnow.layout.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.cmy.bigsnow.http.ServiceFactory;
import com.cmy.bigsnow.layout.adapter.WeekRecylerAdapter;
import com.cmy.bigsnow.layout.listener.RecyclerViewOnClickListener;
import com.cmy.bigsnow.utils.SnackbarUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeekFragment extends Fragment {
    private static final String ARG_TIMELINE_TYPE = "ARG_TIMELINE_TYPE";

    private View rootView;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar pbWait;
    //获取到的数据集合
    private List<Results> resultsList;
    //RecyclerView适配器
    private WeekRecylerAdapter adapter;
    //刷新控件
    private RefreshLayout mRefreshLayout;
    //想要加载的页数
    private int pageIndex = 1;

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
        //初始化图片加载框架
        Fresco.initialize(getContext());
        //这里使用线性布局像ListView那样展示列表,第二个参数可以改为 HORIZONTAL实现水平展示
        linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_week);
        pbWait = (ProgressBar) rootView.findViewById(R.id.pb_wait);
        mRefreshLayout = (RefreshLayout) rootView.findViewById(R.id.layout_swipe_refresh_week);
        pbWait.setVisibility(View.VISIBLE);
        //设置RecyclerView 布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);
        //获取数据
        getData();
        //下拉刷新的分割线
        recyclerViewRefresh();
        //recyclerView点击事件
        recyclerViewOnItemTouchListener();
    }

    /**
     * 上拉加载/下滑刷新
     */
    private void recyclerViewRefresh() {
        //下拉刷新 贝塞尔雷达 特效
        mRefreshLayout.setRefreshHeader(new SmartRefreshLayout(getContext())
                .getRefreshHeader());
        //上拉加载 球脉冲 特效
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).
                setSpinnerStyle(SpinnerStyle.Scale));

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                getData();
                refreshlayout.finishRefresh();
            }
        });

        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                ServiceFactory.getInstance().createService(GankApi.class)
                        .getWeekData(10, pageIndex)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<CallBack>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(CallBack value) {
                                //将数据放入容器
                                List<Results> list = value.getResults();
                                for (int i = 0; i < list.size(); i++) {
                                    resultsList.add(list.get(i));
                                }
                                pageIndex++;
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }
                        });
                refreshlayout.finishLoadmore();
            }
        });

    }

    /**
     * 实现了recyclerView的item的点击事件以及长按事件
     */

    private void recyclerViewOnItemTouchListener() {
        //recyclerView点击事件
        recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(getContext(),
                new RecyclerViewOnClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        SnackbarUtil.ShortSnackbar(view,
                                "我被点击拉~" + "" + position,
                                SnackbarUtil.red)
                                .setActionTextColor(Color.WHITE)
                                .show();
                        Log.d("点击事件", resultsList.get(position).getTitle());
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        SnackbarUtil.ShortSnackbar(view,
                                "啊啊啊,不要按住我不放~" + "" + position,
                                SnackbarUtil.red)
                                .setActionTextColor(Color.WHITE)
                                .show();
                    }
                }));
    }

    /**
     * 利用RxJava2+Retrofit2来获取网络数据
     */
    private void getData() {
        ServiceFactory.getInstance().createService(GankApi.class)
                .getWeekData(10, pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CallBack>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CallBack value) {
                        //将数据放入容器
                        resultsList = value.getResults();
                        pageIndex++;
                        showData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * 将获取的到的数据给到adapter进行数据的展示.
     */
    private void showData() {
        adapter = new WeekRecylerAdapter(
                getContext(),
                resultsList);

        //设置adapter
        recyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        //        recyclerView.addItemDecoration(new DividerItemDecoration(
        //                getActivity(), DividerItemDecoration.HORIZONTAL));
        pbWait.setVisibility(View.GONE);
    }


    //         * TODO: 2017/8/1 2-判断网络情况,无网络或网络不好时给出提示.
    //         * TODO: 2017/8/7 6-上拉隐藏搜索按钮,下拉显示
    //          https://github.com/scwang90/SmartRefreshLayout
    //          https://segmentfault.com/a/1190000010066071

}
