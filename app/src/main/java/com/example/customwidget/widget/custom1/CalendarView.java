package com.example.customwidget.widget.custom1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.base.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class CalendarView extends FrameLayout implements View.OnClickListener {

    public static final String TAG = "CalendarView";
    public static final String[] week = {"日", "一", "二", "三", "四", "五", "六"};
    private int[] days = new int[6 * 7];
    private TextView view;

    private int measuredWidth;
    private int eachWidth;
    private Paint paintWeek;
    private List<TextView> viewList;
    private int height;
    private String time = "2018-02-01";

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paintWeek = new Paint();
        paintWeek.setColor(Color.RED);
        paintWeek.setStyle(Paint.Style.FILL);
        paintWeek.setTextSize(30);
        setWillNotDraw(false);//设置为false ，否则onDraw方法不调用

        viewList = new ArrayList<>();

        for (int i = 0; i < 42; i++) {
            getView();
            viewList.add(view);
        }

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Measure(widthMeasureSpec, heightMeasureSpec);
    }

    private void Measure(int w, int h) {
        int sizeW = MeasureSpec.getSize(w);
        height = view.getLayoutParams().height;
//        Log.e(TAG, "Measure: " + height);
        setMeasuredDimension(sizeW, height * 7);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        measuredWidth = getMeasuredWidth();
        eachWidth = measuredWidth / 7;
//        Log.e(TAG, "onSizeChanged: " + measuredWidth);
        removeAllViews();
        for (int i = 0; i < viewList.size(); i++) {
            TextView textView = viewList.get(i);
            addView(textView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int childCount = getChildCount();
        getMyCalendar();
        Log.e(TAG, "onLayout: ------------------");
        int j = 0;
        for (int i = 0; i < childCount; i++) {
//            Log.e(TAG, "onLayout: " + days[i]);
            if (i % 7 == 0) {
                j++;
            }
            if (days[i] != 0) {
                TextView view = (TextView) getChildAt(i);
                view.layout(((i % 7) * eachWidth), height * j - height / 3, eachWidth * ((i % 7) + 1), height * j + height / 2 + 20);
                view.setText(days[i] + "");
                view.setGravity(Gravity.CENTER);
                view.setTag(i);
                view.setOnClickListener(this);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.e(TAG, "onDraw: ");
        for (int i = 0; i < week.length; i++) {
            canvas.drawText(week[i], eachWidth / 2 + (i * eachWidth) - 10, height / 2, paintWeek);
        }
    }

    private void getView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.calendar_view_item, this, false);
        view = (TextView) rootView.findViewById(R.id.textview);
    }

    private void getMyCalendar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 获取给定的时间是几号
        int day = calendar.get(Calendar.DATE);
        // 将日期改为该月1号
        calendar.set(Calendar.DATE, 1);
        // 获取该月1号是本周第几天
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        Log.e(TAG, "getMyCalendar: " + firstDayOfWeek);
        // 获取该月的最后一天是几号
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        // 为数组填充值
        for (int i = 1; i <= lastDay; i++) {
            days[i + (firstDayOfWeek - 1) - 1] = i;
        }
    }

    @Override
    public void onClick(View v) {
        if (click != null) {
            click.click((TextView) v, (Integer) v.getTag());
        }
    }

    public interface OnClick {
        void click(TextView view, int position);
    }

    private OnClick click;

    public OnClick getClick() {
        return click;
    }

    public void setClick(OnClick click) {
        this.click = click;
    }

    public String getTime() {
        return time;
    }

    public void setTimeAndLayout(String time) {
        this.time = time;
        removeAllViews();
        for (int i = 0; i < viewList.size(); i++) {
            TextView textView = viewList.get(i);
            addView(textView);
        }
        this.requestLayout();
    }
}
