package com.cmy.bigsnow.app.welfare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmy.bigsnow.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/8/15
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class WelfareRecylerAdapter extends RecyclerView.Adapter<WelfareRecylerAdapter.ViewHolder> {
    private Context context;
    private List<String> dataList;
    private String ImgURL;

    public WelfareRecylerAdapter(Context context,
                                 List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public WelfareRecylerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.item_img_cardview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        params.height = (int) (600 + Math.random() * 300);//把随机的高度赋予item布局
        holder.itemView.setLayoutParams(params);//把params设置给item布局

        if (!dataList.isEmpty()) {
            ImgURL = dataList.get(position);
            holder.img.setImageURI(ImgURL);
        }
    }

    @Override
    public int getItemCount() {
        Log.d("size = ", dataList.size() + "");
        return dataList.size();
    }

    //自定义ViewHolder,包含item的所有界面元素
    static class ViewHolder extends RecyclerView.ViewHolder {
        public final SimpleDraweeView img;
        public final TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (SimpleDraweeView) itemView.findViewById(R.id.item_card_img);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
