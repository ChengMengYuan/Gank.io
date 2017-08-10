package com.cmy.bigsnow.layout.listener;

import android.support.v7.widget.RecyclerView;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/8/8
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :RecyclerView的上拉事件监听器
 */

public abstract class RecyclerViewLoadMoreListener extends
        RecyclerView.OnScrollListener {
    /**
     * 当RecyclerView滑动时触发
     * 类似点击事件的MotionEvent.ACTION_MOVE
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

    }

    /**
     * 当RecyclerView的滑动状态改变时触发
     */
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    /**
     * 提供一个抽象方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     */
    public abstract void onLoadMore(int currentPage);
}
