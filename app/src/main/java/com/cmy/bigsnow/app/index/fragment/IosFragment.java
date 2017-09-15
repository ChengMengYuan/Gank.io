package com.cmy.bigsnow.app.index.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmy.bigsnow.R;
import com.orhanobut.logger.Logger;

public class IosFragment extends BaseFragment {

    public IosFragment() {
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
        isPrepared = true;
        return inflater.inflate(R.layout.fragment_ios, container, false);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {

            return;
        }
        //填充数据
        Logger.t("lazyLoad").d("IosFragment");
    }

    @Override
    protected void showData() {

    }

    @Override
    protected void recyclerViewRefresh() {

    }
}
