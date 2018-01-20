package com.example.huanxinim.presenter;

import com.hyphenate.chat.EMConversation;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */

public interface IConversationPresenter {

    void loadAllConversations();

    List<EMConversation> getConversations();
}
