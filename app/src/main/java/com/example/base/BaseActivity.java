package com.example.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 抽象的activity基类
 * ID相同时会被替换为主应用中的
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static final int LOADING = 0x0101;//加载界面
    protected static final int LOAD_ERROR = LOADING + 1;//加载错误
    protected static final int LOAD_SUCCESS = LOADING + 2;//加载成功
    public static final int LOAD_FAILURE = LOADING + 3;//加载失败
    public static final int LOAD_NO_DATA = LOADING + 4;//无数据

    protected final static String TAG = "xxx";
    private View fl_failure, fl_no_data, fl_loading;
    protected LayoutInflater mInflater;
    Unbinder unbinder;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
        mInflater = getLayoutInflater();
        mContext = this;
        onBeforeSetContentLayout();
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        if (getContentViewId() != 0) {
            setContentView(getContentViewId());
        }
        if (getLoadFrameId() != 0) {
            bindLoadView((ViewGroup) findViewById(getLoadFrameId()));
        }
        // 通过注解绑定控件
        unbinder = ButterKnife.bind(this);
        init(savedInstanceState);
        initView();
        initData();
        requestData();
    }

    protected void getBundleExtras(Bundle extras) {

    }

    protected void onBeforeSetContentLayout() {

    }

    protected void init(Bundle savedInstanceState) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }


    /**
     * add fl_loading & error view
     */
    protected void bindLoadView(ViewGroup viewGroup) {
        if (fl_failure != null || viewGroup == null) {
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
        fl_no_data.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        //add listener
    }

    protected void updateLoadStatus(int status) {
        if (fl_failure == null) {
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
            fl_no_data.setVisibility(View.VISIBLE);
        } else if (status == LOAD_NO_DATA) {
            fl_loading.setVisibility(View.GONE);
            fl_failure.setVisibility(View.GONE);
            fl_no_data.setVisibility(View.VISIBLE);
        }
    }

    protected abstract int getContentViewId();

    protected int getLoadFrameId() {
        return 0;
    }


    protected abstract void initView();

    protected void initData() {

    }

    protected void requestData() {

    }

    private InputMethodManager mInputMethodManager;

    protected void hideKeyBoard() {
        if (mInputMethodManager == null) {
            mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void onFinishActivity(View view) {
        finish();
    }

}
