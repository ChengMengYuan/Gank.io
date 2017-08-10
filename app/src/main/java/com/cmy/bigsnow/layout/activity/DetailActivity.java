package com.cmy.bigsnow.layout.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.bean.Results;
import com.cmy.bigsnow.utils.Event.MessageEvent;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailActivity extends AppCompatActivity {
    private SimpleDraweeView pic;
    private Results results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        pic = (SimpleDraweeView) findViewById(R.id.detail_iv_src);
        //在需要订阅事件的地方注册事件
        EventBus.getDefault().register(this);
    }

    /**
     * 接受 WeekFragment传递过来的results,用来加载数据
     *
     * @param messageEvent 消息事件；
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveResults(MessageEvent messageEvent) {
        if (messageEvent.getPublish().equals("WeekFragment") &&
                messageEvent.getSubscriber().equals("DetailActivity")) {
            this.results = (Results) messageEvent.getMessage();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }
}
