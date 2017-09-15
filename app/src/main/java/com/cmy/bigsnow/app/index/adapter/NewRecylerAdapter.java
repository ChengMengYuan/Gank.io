package com.cmy.bigsnow.app.index.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.app.DetailActivity;
import com.cmy.bigsnow.bean.HistoryResults;
import com.cmy.bigsnow.utils.Event.MessageEvent;
import com.cmy.bigsnow.utils.SnackbarUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static com.cmy.bigsnow.utils.ImgUtil.getRegexImgURL;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/7/19
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :RecyclerView的数据适配器,用来加载获取到的数据
 */

public class NewRecylerAdapter extends RecyclerView.Adapter<NewRecylerAdapter.ViewHolder> {
    private List<HistoryResults> historyResultsList = null;
    private Context context;
    private String type;
    private String ImgURL;
    private String title;
    private String time;


    /**
     * @param context            上下文
     * @param type               类别
     * @param historyResultsList 数据集
     */
    public NewRecylerAdapter(Context context,
                             String type,
                             List<HistoryResults> historyResultsList) {
        this.context = context;
        this.type = type;
        this.historyResultsList = historyResultsList;
    }

    //创建View,被LayoutManager所用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.item_new_cardview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //数据的绑定
    @Override
    public void onBindViewHolder(final ViewHolder holder,
                                 final int position) {
        if (!historyResultsList.isEmpty()) {
            HistoryResults historyResults = historyResultsList.get(position);
            //获取时间,去掉各种无用字符串
            String timeDT = historyResults.getPublishedAt();
            time = timeDT.substring(0, timeDT.indexOf("T"));
            //获取图片,正则表达式获取图片的URL
            ImgURL = getRegexImgURL(historyResults.getContent());
            //获取标题,去掉开头的“今日力推”字样
            title = historyResults.getTitle().replace("今日力推：", "");

            holder.time.setText(time);
            holder.title.setText(title);
            holder.img.setImageURI(ImgURL);
            //点击事件(跳转)
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logger.t("onClick").d(historyResultsList.get(position).getTitle());
                    EventBus.getDefault()
                            .postSticky(new MessageEvent("NewFragment",
                                    "DetailActivity",
                                    historyResultsList.get(position)));
                    Intent intent = new Intent(context.getApplicationContext(),
                            DetailActivity.class);
                    context.startActivity(intent);
                }
            });
            //长按事件(收藏)
            holder.img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // TODO: 2017/8/15 收藏当前CardView,使用EventBus发送数据,接收后进行收藏操作。
                    EventBus.getDefault()
                            .postSticky(new MessageEvent("NewRecylerAdapter",
                                    "接收数据的类", "数据"));
                    SnackbarUtil.ShortSnackbar(v,
                            "收藏成功" + "" + position,
                            SnackbarUtil.red)
                            .setActionTextColor(Color.WHITE)
                            .show();
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return historyResultsList.size();
    }

    //自定义ViewHolder,包含item的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView time;
        public final TextView title;
        public final SimpleDraweeView img;

        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            img = (SimpleDraweeView) itemView.findViewById(R.id.iv_src);
        }
    }

}
