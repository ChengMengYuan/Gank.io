package com.cmy.bigsnow.layout.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmy.bigsnow.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/7/19
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class WeekRecylerAdapter extends RecyclerView.Adapter<WeekRecylerAdapter.ViewHolder> {
    private ArrayList<String> tilelists = null;
    private ArrayList<String> timelists = null;
    private ArrayList<Uri> ImgUrllists = null;
    private CardView cardView;
    private Context context;

    /**
     * 存放当前的数据
     */

    public WeekRecylerAdapter(Context context,
                              CardView cardView,
                              ArrayList<String> tilelists,
                              ArrayList<String> timelists,
                              ArrayList<Uri> ImgUrllists) {
        this.context = context;
        this.cardView = cardView;
        this.tilelists = tilelists;
        this.timelists = timelists;
        this.ImgUrllists = ImgUrllists;
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
        holder.time.setText(timelists.get(position));
        holder.title.setText(tilelists.get(position));
        if (ImgUrllists.size() > 0) {
            holder.img.setImageURI(ImgUrllists.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return tilelists.size();
    }

    //自定义ViewHolder,包含item的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView time;
        public final TextView title;
        public final ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            img = (SimpleDraweeView) itemView.findViewById(R.id.iv_src);
        }
    }

}
