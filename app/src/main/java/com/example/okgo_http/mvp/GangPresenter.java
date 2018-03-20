package com.example.okgo_http.mvp;

import android.content.Context;

import com.example.base2.BasePresenter;
import com.example.okgo_http.bean.GangBean;
import com.example.okgo_http.bean.GangRightBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */

public class GangPresenter extends BasePresenter<GangView> {

    private Context context;

    public GangPresenter(Context context) {
        this.context = context;
    }


    //从资源文件中获取分类json
    public void getData(String path) {
        GangView view = getView();
        if (view == null) {
            return;
        }
        String result = "";
        try {
            //获取输入流
            InputStream mAssets = context.getAssets().open(path);
            //获取文件的字节数
            int lenght = mAssets.available();
            //创建byte数组
            byte[] buffer = new byte[lenght];
            //将文件中的数据写入到字节数组中
            mAssets.read(buffer);
            mAssets.close();
            result = new String(buffer);
            if (result != null) {
                ArrayList<GangRightBean> rightBeanList = new ArrayList<>();
                List<String> listName = new ArrayList<>();
                GangBean gangBean = new Gson().fromJson(result, GangBean.class);
                List<GangBean.CategoryOneArrayBean> categoryOneArray = gangBean.getCategoryOneArray();
                //获取左边
                String name = "";
                String nameRight = "";
                for (int i = 0; i < categoryOneArray.size(); i++) {
                    name = categoryOneArray.get(i).getName();
                    listName.add(name);
                    rightBeanList.add(new GangRightBean(name, true));
                    List<GangBean.CategoryOneArrayBean.CategoryTwoArrayBean> categoryTwoArray = categoryOneArray.get(i).getCategoryTwoArray();
                    for (int j = 0; j < categoryTwoArray.size(); j++) {
                        nameRight = categoryTwoArray.get(j).getName();
                        rightBeanList.add(new GangRightBean(nameRight, false));
                    }
                }
                view.getData(listName);
                view.getRight(rightBeanList);
            } else {
                view.getEmpty();
            }
        } catch (IOException e) {
            view.onError(e);
        }
    }
}
