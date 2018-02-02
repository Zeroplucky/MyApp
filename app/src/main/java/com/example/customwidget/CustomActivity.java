package com.example.customwidget;

import android.util.Log;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class CustomActivity extends BaseActivity {

//    @BindView(R.id.custom01view)
//    Custom01View custom01view;
//    @BindView(R.id.anim_view)
//    MyAnimView animView;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_;
    }

    @Override
    protected void initView() {
        //
        OkGo.<String>get("http://120.79.90.233:8080/qxhzz/app/getRiverGisByRiverCode.action?uid=407&riverCode=")
                .tag(this)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("xxx", "onSuccess: ");

                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        Log.e("xxx", "onCacheSuccess: ");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        Log.e("xxx", "onFinish: ");
                    }
                });
    }

}
