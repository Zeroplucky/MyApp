package com.example.okgo_http.mvp;

import com.example.base2.BaseView;
import com.example.okgo_http.bean.GangRightBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */

public interface GangView extends BaseView {

    void getData(List<String> listName);


    void getRight(ArrayList<GangRightBean> rightBeanList);

    void getEmpty();

    void onError(IOException e);
}
