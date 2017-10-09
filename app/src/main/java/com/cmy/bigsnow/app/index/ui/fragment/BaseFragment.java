package com.cmy.bigsnow.app.index.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.app.index.adapter.DailyRecylerAdapter;
import com.cmy.bigsnow.app.index.ui.other.DividerItemDecoration;
import com.cmy.bigsnow.app.index.bean.DailyResults;
import com.cmy.bigsnow.utils.IntenetUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import static com.cmy.bigsnow.utils.IntenetUtil.NETWORN_NONE;

/**
 * The type Base fragment.
 *
 * @Author : mengyuan.cheng
 * @Version : 2017/9/13
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */
public abstract class BaseFragment extends Fragment {
    /**
     * The Is visible.
     */
    protected boolean isVisible;
    /**
     * The Is prepared.
     */
    protected boolean isPrepared;

    /**
     * The Root view.
     */
    protected View rootView;
    /**
     * The Recycler view.
     */
    protected RecyclerView recyclerView;
    /**
     * The Linear layout manager.
     */
    protected LinearLayoutManager linearLayoutManager;
    /**
     * 无网络连接时显示的rl
     */
    protected RelativeLayout waitRl;
    /**
     * The Wait iv.
     */
    protected ImageView waitIv;
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

    /**
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * fragment可见时加载数据.
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 懒加载
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {

            return;
        }
        int CONNECTIVITY_SERVICE = IntenetUtil.getNetworkState(getContext());
        switch (CONNECTIVITY_SERVICE) {
            case NETWORN_NONE:
                pbWait.setVisibility(View.INVISIBLE);
                waitRl.setVisibility(View.VISIBLE);
                break;
        }
        getData();
    }

    /**
     * 加载数据
     */
    protected abstract void getData();

    /**
     * 绑定adapter来展示数据
     */
    protected void showData() {
        adapter = new DailyRecylerAdapter(getContext(), dailyResultsList);
        //设置adapter
        recyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));
        //取消progressBar
        pbWait.setVisibility(View.GONE);
    }

    /**
     * fragment不可见
     */
    protected void onInvisible() {

    }

    /**
     * 实现下拉刷新和上滑加载更多
     */
    protected abstract void recyclerViewRefresh();

    /**
     * 初始化控件
     */
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
        //无网络连接
        //        waitRl = (RelativeLayout) rootView.findViewById(R.id.rl_no_intent);
        //        waitIv = (ImageView) rootView.findViewById(R.id.iv_error);
    }

}
