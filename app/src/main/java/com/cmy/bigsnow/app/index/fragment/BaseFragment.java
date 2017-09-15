package com.cmy.bigsnow.app.index.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.cmy.bigsnow.app.index.adapter.DailyRecylerAdapter;
import com.cmy.bigsnow.app.index.bean.DailyResults;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/9/13
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public abstract class BaseFragment extends Fragment {
    protected boolean isVisible;
    protected boolean isPrepared;

    protected View rootView;
    protected RecyclerView recyclerView;
    protected LinearLayoutManager linearLayoutManager;
    /**
     * waiting progressBar
     */
    protected ProgressBar pbWait;
    /**
     * 刷新控件
     */
    protected RefreshLayout mRefreshLayout;
    /**
     * 获取到的数据集合
     */
    protected List<DailyResults> dailyResultsList;
    /**
     * 通用适配器
     */
    protected DailyRecylerAdapter adapter;

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 懒加载
     */
    protected abstract void lazyLoad();

    /**
     * 绑定adapter来展示数据
     */
    protected abstract void showData();

    protected void onInvisible() {
    }

    protected abstract void recyclerViewRefresh();

}
