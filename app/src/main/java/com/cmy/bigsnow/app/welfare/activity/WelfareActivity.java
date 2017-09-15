package com.cmy.bigsnow.app.welfare.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.app.index.bean.CategoryData;
import com.cmy.bigsnow.app.welfare.adapter.WelfareRecylerAdapter;
import com.cmy.bigsnow.http.GankApi;
import com.cmy.bigsnow.http.ServiceFactory;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.cmy.bigsnow.R.id.recyclerView;

public class WelfareActivity extends AppCompatActivity {
    //刷新控件
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private WelfareRecylerAdapter adapter;
    private CardView mCardview;
    public List<String> dataList = new ArrayList<>();
    //想要加载的页数
    private int pageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);

        //浅色状态栏设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            WelfareActivity.this
                    .getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        mRefreshLayout = (RefreshLayout) findViewById(R.id.layout_swipe_refresh_week);
        mRecyclerView = (RecyclerView) findViewById(recyclerView);
        mCardview = (CardView) findViewById(R.id.card_img);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL));
        getData();
    }

    private void getData() {
        ServiceFactory.getInstance()
                .createService(GankApi.class)
                .getCommonDate("福利", 10, pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CategoryData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CategoryData value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    /**
     * 将获取的到的数据给到adapter进行数据的展示.
     */
    private void showData() {
        adapter = new WelfareRecylerAdapter(
                getApplicationContext(),
                dataList);

        //设置adapter
        mRecyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


}
