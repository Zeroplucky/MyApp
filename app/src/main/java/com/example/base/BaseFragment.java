package com.example.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hebing on 2016/3/30.
 */
public abstract class BaseFragment extends Fragment {

    protected final static String TAG = "xxx";
    protected LayoutInflater mInflater;
    protected View mView;
    private Unbinder unbinder;
    protected static final int LOADING = 0x0101;//加载界面
    protected static final int LOAD_ERROR = LOADING + 1;//加载错误
    protected static final int LOAD_SUCCESS = LOADING + 2;//加载成功
    public static final int LOAD_FAILURE = LOADING + 3;//加载失败
    public static final int LOAD_NO_DATA = LOADING + 4;//无数据
    private View fl_failure, fl_loading, fl_no_data;
    //懒加载
    //Fragment的View加载完毕的标记//Fragment对用户可见的标记
    private boolean isViewCreated, isUIVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mInflater = inflater;
        if (mView == null && getContentViewId() > 0) {
            mView = mInflater.inflate(getContentViewId(), container, false);
            if (getLoadFrameId() != 0) {
                bindLoadView((ViewGroup) mView.findViewById(getLoadFrameId()));
            }
            unbinder = ButterKnife.bind(this, mView);
            init(savedInstanceState);
            initView(mView);
            initData();
            requestData();
            //懒加载
            isViewCreated = true;
            lazyLoad();
        }
        return mView;
    }

    protected void bindLoadView(ViewGroup viewGroup) {
        if (fl_failure != null || fl_loading != null || viewGroup == null) {
            return;
        }
        fl_failure = mInflater.inflate(R.layout.static_network_error, viewGroup, false);
        viewGroup.addView(fl_failure, new ViewGroup.LayoutParams(-1, -1));
        fl_loading = mInflater.inflate(R.layout.static_loading, viewGroup, false);
        viewGroup.addView(fl_loading, new ViewGroup.LayoutParams(-1, -1));
        fl_no_data = mInflater.inflate(R.layout.static_no_data, viewGroup, false);
        viewGroup.addView(fl_no_data, new ViewGroup.LayoutParams(-1, -1));
        fl_failure.findViewById(R.id.network_error_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
        fl_failure.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        fl_loading.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        fl_no_data.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    protected void updateLoadStatus(int status) {
        if (fl_failure == null || fl_loading == null) {
            return;
        }
        if (status == LOADING) {
            fl_loading.setVisibility(View.VISIBLE);
            fl_failure.setVisibility(View.GONE);
            fl_no_data.setVisibility(View.GONE);
        } else if (status == LOAD_FAILURE) {
            fl_loading.setVisibility(View.GONE);
            fl_failure.setVisibility(View.VISIBLE);
            fl_no_data.setVisibility(View.GONE);
        } else if (status == LOAD_SUCCESS) {
            fl_loading.setVisibility(View.GONE);
            fl_failure.setVisibility(View.GONE);
            fl_no_data.setVisibility(View.GONE);
        } else if (status == LOAD_ERROR) {
            fl_loading.setVisibility(View.GONE);
            fl_failure.setVisibility(View.GONE);
            fl_no_data.setVisibility(View.GONE);
        } else if (status == LOAD_NO_DATA) {
            fl_loading.setVisibility(View.GONE);
            fl_failure.setVisibility(View.GONE);
            fl_no_data.setVisibility(View.VISIBLE);
        }
    }


    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    protected abstract int getContentViewId();

    protected abstract void initView(View view);

    /*
    *
    * */
    protected void initData() {

    }

    /*
    *
    * */
    protected void requestData() {

    }


    public int getLoadFrameId() {
        return 0;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        if (isViewCreated && isUIVisible) {
            onLazyLoad();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    /*
    * 懒加载
    * */
    protected void onLazyLoad() {
    }

}
