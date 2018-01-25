package com.example.customwidget.presenter;

import android.content.Context;

import com.example.customwidget.bean.CountByAdcodeBean;
import com.example.customwidget.bean.RiverList;
import com.example.customwidget.view.ICustomView;
import com.example.customwidget.view.ISearchView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/23.
 */

public class CustomPresenter implements ICustomPresenter {

    private Context context;

    public CustomPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void getCountByAdcode(final ICustomView view) {
        if (view == null) {
            return;
        }
        view.onBegin();
        Map<String, String> map = new HashMap<>();
        map.put("uid", "" + 407);
        map.put("queryType", "" + 1);
        map.put("begin_date", "2018-01-01");
        map.put("end_date", "2018-01-24");

        OkGo.<String>post("http://202.96.98.106:8074/qxhzz/app/getRvCheckCountByAdcode.action")  //202.96.98.106:8074   120.79.90.233:8080
                .params(map)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CountByAdcodeBean bean = new Gson().fromJson(response.body(), CountByAdcodeBean.class);
                        view.getDetailBean(bean.getDetail());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        view.onEnd();
                    }
                });


    }

    @Override
    public void getSearchData(final ISearchView view) {
        if (view == null) {
            return;
        }
        view.onBegin();
        OkGo.<String>get("http://202.96.98.106:8074/qxhzz/app/getRiverList.action?uid=407")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        RiverList riverList = new Gson().fromJson(response.body(), RiverList.class);
                        List<RiverList.DetailBean.RiverListBean> listBeans = riverList.getDetail().getRiverList();
                        view.getDetailBean(listBeans);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        view.onEnd();
                    }
                });

    }
}
