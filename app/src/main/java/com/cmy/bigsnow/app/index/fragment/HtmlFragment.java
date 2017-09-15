package com.cmy.bigsnow.app.index.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmy.bigsnow.R;
import com.orhanobut.logger.Logger;

public class HtmlFragment extends BaseFragment {

    public HtmlFragment() {
    }

    public static HtmlFragment newInstance(String param1, String param2) {
        HtmlFragment fragment = new HtmlFragment();
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
        isPrepared = true;
        return inflater.inflate(R.layout.fragment_html, container, false);

    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {

            return;
        }
        //填充数据
        Logger.t("lazyLoad").d("HtmlFragment");
    }

    @Override
    protected void showData() {

    }

    @Override
    protected void recyclerViewRefresh() {

    }
}
