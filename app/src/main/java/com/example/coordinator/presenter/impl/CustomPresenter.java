package com.example.coordinator.presenter.impl;

import android.text.TextUtils;

import com.example.coordinator.presenter.ICustomPresenter;
import com.example.coordinator.view.ICustomView;
import com.example.bean.CustomItemBean;
import com.example.bean.CustomListBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/12/26.
 */

public class CustomPresenter implements ICustomPresenter {
    private ICustomView customView;

    public CustomPresenter(ICustomView customView) {
        this.customView = customView;
    }

    @Override
    public void getList(String type, int prePage, int page) {
        customView.start();
        OkGo.<String>get("http://gank.io/api/data/" + type + "/" + prePage + "/" + page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CustomListBean customListBean = new Gson().fromJson(response.body(), CustomListBean.class);
                        List<CustomItemBean> list = new ArrayList<CustomItemBean>();
                        for (CustomItemBean bean : customListBean.getResults()) {
                            if (bean.getType().equals("福利")) {
                                bean.itemType = CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_IMAGE;
                            } else if (bean.getImages() != null) {
                                if (bean.getImages().size() > 0 && !TextUtils.isEmpty(bean
                                        .getImages().get(0)))
                                    bean.itemType = CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NORMAL;
                            } else {
                                bean.itemType = CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE;
                            }
                            list.add(bean);
                        }
                        customView.updateContentList(list);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        customView.onFinish();
                    }
                });


    }

    @Override
    public void loadMoreList(int page) {
        OkGo.<String>get("http://gank.io/api/data/all/10/" + page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CustomListBean customListBean = new Gson().fromJson(response.body(), CustomListBean.class);
                        List<CustomItemBean> list = new ArrayList<CustomItemBean>();
                        for (CustomItemBean bean : customListBean.getResults()) {
                            if (bean.getType().equals("福利")) {
                                bean.itemType = CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_IMAGE;
                            } else if (bean.getImages() != null) {
                                if (bean.getImages().size() > 0 && !TextUtils.isEmpty(bean
                                        .getImages().get(0)))
                                    bean.itemType = CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NORMAL;
                            } else {
                                bean.itemType = CustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE;
                            }
                            list.add(bean);
                        }
//排序
//                        Collections.sort(list, new Comparator<CustomItemBean>() {
//                            @Override
//                            public int compare(CustomItemBean o1, CustomItemBean o2) {
//                                String createdAt01 = o1.getCreatedAt();
//                                long l01 = string2Millis(createdAt01.substring(0, 9));
//                                String createdAt02 = o2.getCreatedAt();
//                                long l02 = string2Millis(createdAt02.substring(0, 9));
//                                return (int) (l01 - l02);
//                            }
//                        });

                        customView.loadMoreList(list);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        customView.onFinish();
                    }
                });
    }

    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static long string2Millis(final String time) {
        return string2Millis(time, DEFAULT_FORMAT);
    }

    public static long string2Millis(final String time, final DateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
