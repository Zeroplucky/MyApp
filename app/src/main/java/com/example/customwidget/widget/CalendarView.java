package com.example.customwidget.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CalendarView extends FrameLayout {

    public static final String TAG = "CalendarView";
    public static final String[] week = {"日", "一", "二", "三", "四", "五", "六"};
    private TextView view;
    private int paddingLeft;
    private int paddingRight;
    private int measuredWidth;
    private int eachWidth;
    private Paint paintWeek;
    private int statusBarHeight;
    private List<TextView> viewList;
    private int height;

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
        int modeW = MeasureSpec.getMode(w);

        int sizeH = MeasureSpec.getSize(h);
        int modeH = MeasureSpec.getMode(h);

        height = view.getLayoutParams().height;
        Log.e(TAG, "Measure: " + height);
        setMeasuredDimension(sizeW, height * 7);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        measuredWidth = getMeasuredWidth();
        statusBarHeight = getStatusBarHeight(getContext());

        eachWidth = measuredWidth / 7;
        Log.e(TAG, "onSizeChanged: " + measuredWidth);
        getMyCalendar();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");
        for (int i = 0; i < week.length; i++) {
            canvas.drawText(week[i], eachWidth / 2 + (i * eachWidth), height, paintWeek);
        }
    }


    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    private void getView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.calendar_view_item, this, false);
        view = (TextView) rootView.findViewById(R.id.textview);
    }

    private void getMyCalendar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse("2017-08-14");
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
        // 获取该月的最后一天是几号
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        // 每个月多需要6行7列即可显示完整
        int[] days = new int[6 * 7];
        // 为数组填充值
        for (int i = 1; i <= lastDay; i++) {
            days[i + (firstDayOfWeek - 1) - 1] = i;
        }
        int j = 1;
        for (int i = 0; i < days.length; i++) {
            System.out.print("\t");
            if ((i + 1) % 7 == 0) {
                System.out.println("");
                j++;
            }
            if (days[i] != 0) {
                if (days[i] == day) {
                    System.out.print("*");
                }
                System.out.print(days[i]);
                TextView textView = viewList.get(i);
                textView.layout((i * eachWidth), height * j - height / 2, eachWidth * (i + 1), height * j + height / 2);
                Log.e(TAG, "getMyCalendar: " + i * eachWidth + " " + (height * j - height / 2) + "  " + (eachWidth * (i + 1)) + " " +(height * j + height / 2));
                viewList.get(i).setText(days[i] + "");
                ViewGroup parent = (ViewGroup) textView.getParent();
                parent.removeAllViews();
                addView(textView);
            }
        }
    }
}
