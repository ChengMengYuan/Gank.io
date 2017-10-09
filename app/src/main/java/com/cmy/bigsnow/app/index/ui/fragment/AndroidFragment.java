package com.cmy.bigsnow.app.index.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmy.bigsnow.R;
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


/**
 * The type Android fragment.
 */
public class AndroidFragment extends BaseFragment {
    //想要加载的页数
    private int pageIndex = 1;

    /**
     * Instantiates a new Android fragment.
     */
    public AndroidFragment() {
        // Required empty public constructor
    }

    /**
     * New instance android fragment.
     *
     * @param param1 the param 1
     * @param param2 the param 2
     * @return the android fragment
     */
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
        rootView = inflater.inflate(R.layout.fragment_daily, container, false);
        isPrepared = true;
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.initView();
        //下拉刷新
        recyclerViewRefresh();
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

//    /**
//     * 销毁与Fragment有关的视图，但未与Activity解除绑定，
//     * 依然可以通过onCreateView方法重新创建视图
//     */
//    @Override
//    public void onDestroyView() {
//        pageIndex = 1;
//        super.onDestroyView();
//    }
}

