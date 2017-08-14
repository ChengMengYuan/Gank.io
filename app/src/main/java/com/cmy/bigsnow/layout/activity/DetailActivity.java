package com.cmy.bigsnow.layout.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.bean.Results;
import com.cmy.bigsnow.utils.Event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailActivity extends AppCompatActivity {
    private Results results;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //在需要订阅事件的地方注册事件
        EventBus.getDefault().register(this);
        //去除多余的字符
        String time = results.getUpdated_at();
        String times[] = time.split("T");
        String time1 = times[0];
        String time2 = time1.replace("-", "/");

        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://gank.io/" + time2);
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
