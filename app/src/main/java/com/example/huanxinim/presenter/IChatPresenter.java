package com.example.huanxinim.presenter;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */

public interface IChatPresenter {

    void sendTextMessage(String userName, String message); //发送文本消息

    void sendVoidMessage(String userName, String filePath,int length); //发送文本消息

    List<EMMessage> getMessages();

    void loadMessages(String userName);

    String getUserName();

    void loadMoreMessages(String userName);

    void makeMessageRead(String userName);
}
