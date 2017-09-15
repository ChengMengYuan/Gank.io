package com.cmy.bigsnow.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.bean.HistoryResults;
import com.cmy.bigsnow.utils.Event.MessageEvent;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

// TODO: 2017/9/15 增加toolbar,webview加载时显示进度条，优化webview加载
public class DetailActivity extends AppCompatActivity {
    private HistoryResults historyResults;
    private String url;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //在需要订阅事件的地方注册事件
        EventBus.getDefault().register(this);


    }

    /**
     * @param messageEvent 消息事件；
     *                     接收Fragment传递过来的results,用来加载数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveResults(MessageEvent messageEvent) {

        if (messageEvent.getPublish().equals("NewFragment") &&
                messageEvent.getSubscriber().equals("DetailActivity")) {
            this.historyResults = (HistoryResults) messageEvent.getMessage();


            //去除多余的字符
            String time = historyResults.getUpdated_at();
            String times[] = time.split("T");
            String time1 = times[0];
            String time2 = time1.replace("-", "/");

            webView = (WebView) findViewById(R.id.webView);
            webView.loadUrl("http://gank.io/" + time2);
        }
        if (messageEvent.getPublish().equals("DailyRecylerAdapter") &&
                messageEvent.getSubscriber().equals("DetailActivity")) {
            this.url = (String) messageEvent.getMessage();
            Logger.d(url);

            webView = (WebView) findViewById(R.id.webView);
            webView.loadUrl(url);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }
}
