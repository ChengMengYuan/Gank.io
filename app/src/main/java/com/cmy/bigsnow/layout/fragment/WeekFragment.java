package com.cmy.bigsnow.layout.fragment;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.cmy.bigsnow.http.ServiceFactory;
import com.cmy.bigsnow.layout.adapter.WeekRecylerAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.cmy.bigsnow.utils.ImgUtil.getRegexImgURL;

public class WeekFragment extends Fragment {
    private static final String ARG_TIMELINE_TYPE = "ARG_TIMELINE_TYPE";

    private View rootView;
    private RecyclerView recyclerView;
    private CardView cardView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<String> tiemlists, titlelist, ImgURLlist;
    private ArrayList<Uri> Imglist;
    private ProgressBar pbWait;
    //RecyclerView适配器
    private WeekRecylerAdapter adapter;
    //刷新控件
    private SwipeRefreshLayout mRefreshLayout;

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
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_week);
        pbWait = (ProgressBar) rootView.findViewById(R.id.pb_wait);
        pbWait.setVisibility(View.VISIBLE);
        cardView = (CardView) rootView.findViewById(R.id.cv_week);
        //设置RecyclerView 布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);
        getWeekData();
        /**-----我是分割线-----**/
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.layout_swipe_refresh_week);
        //下拉刷新
        //设置进度条的颜色
        mRefreshLayout.setColorSchemeColors(
                Color.RED,
                Color.BLUE,
                Color.GREEN);
        //设置进度条的背景颜色
        mRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        //设置大小
        //        mRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWeekData();
                //刷新数据
                adapter.notifyDataSetChanged();
                //刷新完毕，关闭下拉刷新的组件
                mRefreshLayout.setRefreshing(false);
            }
        });

    }


    /**
     * 获取本周的数据
     */
    private void getWeekData() {
        //初始化容器
        tiemlists = new ArrayList<>();
        titlelist = new ArrayList<>();
        Imglist = new ArrayList<>();
        ImgURLlist = new ArrayList<>();

        ServiceFactory.getInstance().createService(GankApi.class)
                .getWeekData(10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CallBack>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CallBack value) {
                        String str = "今日力推：";
                        Log.d("", value.getResults().get(1).getTitle());
                        List<Results> resultsList = value.getResults();
                        String ImgURL;
                        for (int i = 0; i < resultsList.size(); i++) {
                            //获取时间,并去掉各种无用字符串
                            tiemlists.add(resultsList.get(i).getPublishedAt()
                                    .replace("T", "")
                                    .replace("Z", "")
                                    .replace(":00.0", "")
                            );
                            //获取标题,并去掉开头的“今日力推”字样
                            titlelist.add(resultsList.get(i).getTitle().replace(str, ""));
                            //用正则表达式获取图片的URL
                            ImgURL = getRegexImgURL(resultsList.get(i).getContent());
                            Imglist.add(Uri.parse(ImgURL));
                            Log.d("ImgURLlist--size", "" + ImgURLlist.size());
                            Log.d("URL", ImgURL);
                            showData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }


    /**
     * 进行数据的展示.
     */
    private void showData() {
        adapter = new WeekRecylerAdapter(
                getContext(),
                cardView,
                titlelist,
                tiemlists,
                Imglist);

        //设置adapter
        recyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        //        recyclerView.addItemDecoration(new DividerItemDecoration(
        //                getActivity(), DividerItemDecoration.HORIZONTAL));
        pbWait.setVisibility(View.GONE);
    }


    //         * TODO: 2017/8/1 1-能不能利用RxJava2+Retrofit2来实现！ √√√√√
    //         * TODO: 2017/8/1 2-判断网络情况,无网络或网络不好时给出提示.
    //         * TODO: 2017/8/1 5-下拉刷新√√√√√,上拉加载更多.
    //         * TODO: 2017/8/7 6-上拉隐藏搜索按钮,下拉显示

}
