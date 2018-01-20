package com.example.huanxinim.view;

/**
 * Created by Administrator on 2017/12/14.
 */

public interface IChatView {

    void onStartSendMessage();

    void onSendMessageSuccess();

    void onSendMessageFailed();

    void onMessagesLoaded();

    void onMoreMessagesLoaded(int size);

    void onNoMoreData();


}
