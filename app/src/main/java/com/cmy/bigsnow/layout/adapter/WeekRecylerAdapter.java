package com.cmy.bigsnow.layout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmy.bigsnow.R;
import com.cmy.bigsnow.bean.Results;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import static com.cmy.bigsnow.utils.ImgUtil.getRegexImgURL;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/7/19
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :RecyclerView的数据适配器,用来加载获取到的数据
 */

public class WeekRecylerAdapter extends RecyclerView.Adapter<WeekRecylerAdapter.ViewHolder> {
    private List<Results> resultsList = null;
    private Context context;
    private String ImgURL;
    private String title;
    private String time;

    /**
     * 存放当前的数据
     */

    public WeekRecylerAdapter(Context context,
                              List<Results> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }

    //创建View,被LayoutManager所用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.item_cardview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //数据的绑定
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (!resultsList.isEmpty()) {
            Results results = resultsList.get(position);
            //获取时间,并去掉各种无用字符串
            time = results.getPublishedAt()
                    .replace("T", "")
                    .replace("Z", "")
                    .replace(":00.0", "");
            //用正则表达式获取图片的URL
            ImgURL = getRegexImgURL(results.getContent());
            //获取标题,并去掉开头的“今日力推”字样
            title = results.getTitle().replace("今日力推：", "");
            Log.d("title", title);
        }
        holder.time.setText(time);
        holder.title.setText(title);
        holder.img.setImageURI(ImgURL);
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
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
