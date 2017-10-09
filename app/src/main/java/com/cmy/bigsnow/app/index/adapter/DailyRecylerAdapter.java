package com.cmy.bigsnow.app.index.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.app.index.ui.activity.DetailActivity;
import com.cmy.bigsnow.app.index.bean.DailyResults;
import com.cmy.bigsnow.utils.Event.MessageEvent;
import com.cmy.bigsnow.utils.SnackbarUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;

import static com.cmy.bigsnow.utils.ActivityUtil.BGResources;

/**
 * The type Daily recyler adapter.
 *
 * @Author : mengyuan.cheng
 * @Version : 2017/9/14
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */
public class DailyRecylerAdapter extends RecyclerView.Adapter<DailyRecylerAdapter.ViewHolder> {
    /**
     * The Daily results list.
     */
    protected List<DailyResults> dailyResultsList = null;
    private Context context;
    private String title;
    private String time;
    private String author;

    /**
     * Instantiates a new Daily recyler adapter.
     *
     * @param context          the context
     * @param dailyResultsList the daily results list
     */
    public DailyRecylerAdapter(Context context,
                               List<DailyResults> dailyResultsList) {
        this.context = context;
        this.dailyResultsList = dailyResultsList;
    }


    @Override
    public DailyRecylerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.item_daily_cardview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DailyRecylerAdapter.ViewHolder holder, final int position) {
        if (!dailyResultsList.isEmpty()) {
            DailyResults dailyResults = dailyResultsList.get(position);
            Logger.d(dailyResults.getPublishedAt());
            //获取时间,去掉各种无用字符串
            String timeDT = dailyResults.getPublishedAt();
            time = timeDT.substring(0, timeDT.indexOf("T"));
            //获取标题,去掉开头的“今日力推”字样
            title = dailyResults.getDesc().replace("今日力推：", "");
            author = dailyResults.getWho();

            Logger.d(author);
            if (author == null) {
                holder.author.setBackgroundColor(Color.WHITE);
            } else {
                int bottom = holder.author.getPaddingBottom();
                int top = holder.author.getPaddingTop();
                int right = holder.author.getPaddingRight();
                int left = holder.author.getPaddingLeft();
                holder.author.setBackgroundResource(randomImg());
                holder.author.setPadding(left, top, right, bottom);
            }

            holder.time.setText(time);
            holder.title.setText(title);
            holder.author.setText(author);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logger.t("onClick").d(dailyResultsList.get(position).getUrl());
                    EventBus.getDefault()
                            .postSticky(new MessageEvent("DailyRecylerAdapter",
                                    "DetailActivity",
                                    dailyResultsList.get(position).getUrl()));
                    Intent intent = new Intent(context.getApplicationContext(),
                            DetailActivity.class);
                    context.startActivity(intent);
                }
            });

            holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // TODO: 2017/8/15 收藏当前CardView,使用EventBus发送数据,接收后进行收藏操作。
                    EventBus.getDefault()
                            .postSticky(new MessageEvent("DailyRecylerAdapter",
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
        return dailyResultsList.size();
    }

    /**
     * The type View holder.
     */
    //自定义ViewHolder,包含item的所有界面元素
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Time.
         */
        public final TextView time;
        /**
         * The Title.
         */
        public final TextView title;
        /**
         * The Author.
         */
        public final TextView author;
        /**
         * The Card view.
         */
        public final CardView cardView;

        /**
         * Instantiates a new View holder.
         *
         * @param itemView the item view
         */
        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tv_time_daily);
            title = (TextView) itemView.findViewById(R.id.tv_title_daily);
            author = (TextView) itemView.findViewById(R.id.tv_author_daily);
            cardView = (CardView) itemView.findViewById(R.id.cv_daily);
        }
    }

    private int randomImg() {
        int resource, Num;
        if (BGResources.isEmpty()) {
            BGResources.add(R.drawable.corners);
            BGResources.add(R.drawable.corners2);
            BGResources.add(R.drawable.corners3);
            BGResources.add(R.drawable.corners4);
            BGResources.add(R.drawable.corners5);
            BGResources.add(R.drawable.corners6);
            BGResources.add(R.drawable.corners7);
            BGResources.add(R.drawable.corners8);
            BGResources.add(R.drawable.corners9);
            Logger.t("randomImg").d("isEmpty");
            return R.drawable.corners;
        } else {
            //生成随机数
            Num = new Random().nextInt(BGResources.size());
            resource = BGResources.get(Num);
            BGResources.remove(Num);
            Logger.t("randomImg").d(resource);
            return resource;
        }
    }
}
