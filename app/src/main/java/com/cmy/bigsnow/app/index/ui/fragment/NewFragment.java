package com.cmy.bigsnow.app.index.ui.fragment;

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
import com.cmy.bigsnow.app.index.adapter.NewRecylerAdapter;
import com.cmy.bigsnow.app.index.bean.CallBack;
import com.cmy.bigsnow.bean.HistoryResults;
import com.cmy.bigsnow.http.GankApi;
import com.cmy.bigsnow.http.ServiceFactory;
import com.cmy.bigsnow.utils.IntenetUtil;
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

import static com.cmy.bigsnow.utils.IntenetUtil.NETWORN_NONE;

/**
 * The type New fragment.
 */
// TODO: 2017/9/15 确定无数据时取消ProgressBar,加载无网络的background,单击可重新访问网络
// TODO: 2017/9/15 更改 NewFragment的详情界面,使用正则表达式抓取需要的信息。
// TODO: 2017/9/15 添加缓存，无网络情况也能访问
public class NewFragment extends BaseFragment {
    private static final String ARG_TIMELINE_TYPE = "ARG_TIMELINE_TYPE";

    private View rootView;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar pbWait;
    //获取到的数据集合
    private List<HistoryResults> historyResultsList;
    //RecyclerView适配器
    private NewRecylerAdapter adapter;
    //刷新控件
    private RefreshLayout mRefreshLayout;
    //想要加载的页数
    private int pageIndex = 1;

    /**
     * New instance new fragment.
     *
     * @param type the type
     * @return the new fragment
     */
    public static NewFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_TIMELINE_TYPE, type);
        NewFragment fragment = new NewFragment();
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
        rootView = inflater.inflate(R.layout.fragment_new, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        //获取数据
        getData();
        //下拉刷新
        recyclerViewRefresh();
    }

    /**
     * 初始化View
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
//        waitRl = (RelativeLayout) rootView.findViewById(R.id.rl_no_intent);
    }

    /**
     * 上拉加载/下滑刷新
     */
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
                                List<HistoryResults> list = value.getResults();
                                for (int i = 0; i < list.size(); i++) {
                                    historyResultsList.add(list.get(i));
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
     * 利用RxJava2+Retrofit2来获取网络数据
     */
    protected void getData() {
        int CONNECTIVITY_SERVICE = IntenetUtil.getNetworkState(getContext());
        switch (CONNECTIVITY_SERVICE) {
            case NETWORN_NONE:
                pbWait.setVisibility(View.GONE);
                waitRl.setVisibility(View.VISIBLE);
                break;
        }
        ServiceFactory.getInstance()
                .createService(GankApi.class)
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
                        historyResultsList = value.getResults();
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
    protected void showData() {
        adapter = new NewRecylerAdapter(
                getContext(),
                "history",
                historyResultsList);

        //设置adapter
        recyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        //        recyclerView.addItemDecoration(new DividerItemDecoration(
        //                getActivity(), DividerItemDecoration.HORIZONTAL));
        pbWait.setVisibility(View.GONE);
    }

}
