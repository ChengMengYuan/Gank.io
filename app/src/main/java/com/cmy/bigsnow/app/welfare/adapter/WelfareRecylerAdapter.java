package com.cmy.bigsnow.app.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cmy.bigsnow.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;


/**
 * The type Welfare recyler adapter.
 *
 * @Author : mengyuan.cheng
 * @Version : 2017/8/15
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */
public class WelfareRecylerAdapter extends BaseAdapter {
    private Context context;//上下文
    //    private List<DailyResults> dataList = null;//数据
    private String ImgURL = "";//图片地址
    private ArrayList<String> dataList = null;


    /**
     * Instantiates a new Welfare recyler adapter.
     *
     * @param context  the context
     * @param dataList the data list
     */
    public WelfareRecylerAdapter(Context context,
                                 ArrayList<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_img_cardview, null);
        final SimpleDraweeView sv = (SimpleDraweeView) view.findViewById(R.id.item_card_img);
        sv.setImageURI(dataList.get(position));
        return sv;
    }


}
