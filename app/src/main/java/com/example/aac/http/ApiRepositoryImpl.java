package com.example.aac.http;

import android.content.Context;
import android.text.TextUtils;

import com.example.aac.bean.AdInfo;

/**
 * Created by Administrator on 2018/1/16.
 */

public class ApiRepositoryImpl implements IApiRepository {

    private IApiService IApiService;
    private Context context;

    public ApiRepositoryImpl(Context context) {
        this.context = context;
        IApiService = HttpClientManager.getInstance(context).create(IApiService.class);
    }

    public AdInfo getAdInfo(String adCode) throws Exception {
        String url = "http://202.96.98.106:8074/qxhzz/app/getAdInfo.action";
        return IApiService.getAdInfo(url, "" + 402, adCode).execute().body();
    }

    private String filterNull(String s) {
        return TextUtils.isEmpty(s) ? "" : s;
    }
}
