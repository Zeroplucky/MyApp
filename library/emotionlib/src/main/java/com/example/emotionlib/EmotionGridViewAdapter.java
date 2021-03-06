package com.example.emotionlib;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */

public class EmotionGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> emotionNames;
    private int itemWidth;

    public EmotionGridViewAdapter(Context context, List<String> emotionNames, int itemWidth) {
        this.context = context;
        this.emotionNames = emotionNames;
        this.itemWidth = itemWidth;
    }

    @Override
    public int getCount() {
        // +1 最后一个为删除按钮
        return emotionNames.size() + 1;
    }

    @Override
    public String getItem(int position) {
        return emotionNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv_emotion = new ImageView(context);
        // 设置内边距
        iv_emotion.setPadding(itemWidth / 8, itemWidth / 8, itemWidth / 8, itemWidth / 8);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(itemWidth, itemWidth);
        iv_emotion.setLayoutParams(params);

        //判断是否为最后一个item
        if (position == getCount() - 1) {
            iv_emotion.setImageResource(R.drawable.compose_emotion_delete);
        } else {
            String emotionName = emotionNames.get(position);
            iv_emotion.setImageResource(EmotionUtils.EMOTION_STATIC_MAP.get(emotionName));
        }

        return iv_emotion;
    }

}