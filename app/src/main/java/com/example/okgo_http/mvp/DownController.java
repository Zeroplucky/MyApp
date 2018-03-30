package com.example.okgo_http.mvp;

import com.example.base2.BasePresenter;
import com.example.base2.BaseView;
import com.example.okgo_http.bean.DownBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/3/30.
 */

public class DownController {


    public static interface DownView extends BaseView {

        void getData(DownBean bean, String url);

        void onError(Exception e);

    }


    public static class DownPresenter extends BasePresenter<DownView> {

        public static final String URL = "http://112.124.22.238:8081/appstore/app/introduce?packageName=com.sina.weibo";

        public void getData() {
            final DownView view = getView();
            if (view == null) {
                return;
            }

            OkGo.<String>get(URL)
                    .tag(URL)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {

                            String body = response.body();
                            if (body != null) {
                                try {
                                    JSONObject jsonObject = new JSONObject(body);
                                    JSONArray layoutData = jsonObject.getJSONArray("layoutData");
                                    JSONObject dataList = layoutData.getJSONObject(0).getJSONArray("dataList")
                                            .getJSONObject(0);
                                    String downUrl = layoutData.getJSONObject(layoutData.length() - 1)
                                            .getJSONArray("dataList").getJSONObject(0).getString("downurl");
                                    DownBean downBean = new Gson().fromJson(dataList.toString(), DownBean.class);
                                    view.getData(downBean, downUrl);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    view.onError(e);
                                }


                            }
                        }
                    });


        }


    }
}
