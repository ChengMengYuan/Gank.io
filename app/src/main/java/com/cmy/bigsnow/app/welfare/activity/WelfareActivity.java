package com.cmy.bigsnow.app.welfare.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.app.index.bean.CategoryData;
import com.cmy.bigsnow.app.index.bean.DailyResults;
import com.cmy.bigsnow.app.welfare.adapter.WelfareRecylerAdapter;
import com.cmy.bigsnow.http.GankApi;
import com.cmy.bigsnow.http.ServiceFactory;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Welfare activity.
 */
// TODO: 2017/9/20 1#层叠效果,2#滑动时前后两张图片之间的交互动画,3#向下滑动时下载图片
public class WelfareActivity extends AppCompatActivity {
    private SwipeFlingAdapterView flingContainer;
    private List<DailyResults> dailyResultses = new ArrayList<>();
    private int pageIndex = 1;
    /**
     * The Datalist.
     */
    ArrayList datalist = new ArrayList();
    private ImageView likeImg, dislikeImg;

    /* ---------------------------------*/

    private WelfareRecylerAdapter welAdapter;

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
        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        likeImg = (ImageView) findViewById(R.id.iv_like_wel);
        dislikeImg = (ImageView) findViewById(R.id.iv_cancel_wel);
        datalist = getImgContent();
        //choose your favorite adapter
        welAdapter = new WelfareRecylerAdapter(this, datalist);
        //set the listener and the adapter
        flingContainer.setAdapter(welAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                //                Logger.d(datalist.get(0));
                Logger.d("removeFirstObjectInAdapter");
                datalist.remove(0);
                welAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                dislikeImg.setVisibility(View.INVISIBLE);
                Logger.d("onLeftCardExit");
            }

            @Override
            public void onRightCardExit(Object o) {
                likeImg.setVisibility(View.INVISIBLE);
                Logger.d("onRightCardExit");
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                if (i == 5) {
                    getImgContent();
                }
            }

            @Override
            public void onScroll(float v) {
                if (v > 0) {
                    likeImg.setVisibility(View.VISIBLE);
                    likeImg.setAlpha(v);
                } else if (v < 0) {
                    dislikeImg.setVisibility(View.VISIBLE);
                    dislikeImg.setAlpha(Math.abs(v));
                }
                if (v == 0) {
                    dislikeImg.setVisibility(View.INVISIBLE);
                    likeImg.setVisibility(View.INVISIBLE);
                }

            }
        });

    }

    private ArrayList getImgContent() {
        final ArrayList al = new ArrayList();
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
                        dailyResultses = value.getResults();
                        for (int i = 0; i < dailyResultses.size(); i++) {
                            datalist.add(dailyResultses.get(i).getUrl());
                        }
                        pageIndex++;
                        welAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
        return al;
    }
}
