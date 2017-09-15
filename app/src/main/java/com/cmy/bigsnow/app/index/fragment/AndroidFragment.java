package com.cmy.bigsnow.app.index.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.app.index.adapter.DailyRecylerAdapter;
import com.cmy.bigsnow.app.index.bean.CategoryData;
import com.cmy.bigsnow.app.index.bean.DailyResults;
import com.cmy.bigsnow.http.GankApi;
import com.cmy.bigsnow.http.ServiceFactory;
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


public class AndroidFragment extends BaseFragment {
    //想要加载的页数
    private int pageIndex = 1;

    public AndroidFragment() {
        // Required empty public constructor
    }

    public static AndroidFragment newInstance(String param1, String param2) {
        AndroidFragment fragment = new AndroidFragment();
        Bundle args = new Bundle();
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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_android, container, false);
        isPrepared = true;
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        //下拉刷新
        recyclerViewRefresh();
    }

    protected void initView() {
        //这里使用线性布局像ListView那样展示列表,第二个参数可以改为 HORIZONTAL实现水平展示
        linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        pbWait = (ProgressBar) rootView.findViewById(R.id.pb_wait);
        mRefreshLayout = (RefreshLayout) rootView.findViewById(R.id.layout_swipe_refresh_week);
        pbWait.setVisibility(View.VISIBLE);
        //设置RecyclerView 布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void getData() {
        ServiceFactory.getInstance()
                .createService(GankApi.class)
                .getCommonDate("Android", 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CategoryData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CategoryData value) {
                        dailyResultsList = value.getResults();
                        pageIndex++;
                        showData();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        //填充数据
        getData();
    }

    @Override
    protected void showData() {
        adapter = new DailyRecylerAdapter(getContext(),dailyResultsList);
        //设置adapter
        recyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        //        recyclerView.addItemDecoration(new DividerItemDecoration(
        //                getActivity(), DividerItemDecoration.HORIZONTAL));
        pbWait.setVisibility(View.GONE);
    }

    protected void recyclerViewRefresh() {
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
            public void onLoadmore(final RefreshLayout refreshlayout) {
                ServiceFactory.getInstance()
                        .createService(GankApi.class)
                        .getCommonDate("Android", 10, pageIndex)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<CategoryData>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(CategoryData value) {
                                //将数据放入容器
                                List<DailyResults> list = value.getResults();
                                for (int i = 0; i < list.size(); i++) {
                                    dailyResultsList.add(list.get(i));
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
     * 销毁与Fragment有关的视图，但未与Activity解除绑定，
     * 依然可以通过onCreateView方法重新创建视图
     */
    @Override
    public void onDestroyView() {
        pageIndex = 1;
        super.onDestroyView();
    }
}

