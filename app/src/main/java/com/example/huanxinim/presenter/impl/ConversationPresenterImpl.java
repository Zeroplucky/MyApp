package com.example.huanxinim.presenter.impl;

import com.example.huanxinim.presenter.IConversationPresenter;
import com.example.huanxinim.view.IConversationView;
import com.example.utils.ThreadUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14.
 */

public class ConversationPresenterImpl implements IConversationPresenter {

    private IConversationView mConversationView;
    private List<EMConversation> mEMConversations;

    public ConversationPresenterImpl(IConversationView mConversationView) {
        this.mConversationView = mConversationView;
        mEMConversations = new ArrayList<EMConversation>();
    }

    @Override
    public void loadAllConversations() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
                mEMConversations.clear();
                mEMConversations.addAll(conversations.values());
                Collections.sort(mEMConversations, new Comparator<EMConversation>() {
                    @Override
                    public int compare(EMConversation o1, EMConversation o2) {
                        return (int) (o2.getLastMessage().getMsgTime() - o1.getLastMessage().getMsgTime());
                    }
                });
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mConversationView.onAllConversationsLoaded();
                    }
                });
            }
        });
    }

    @Override
    public List<EMConversation> getConversations() {
        return mEMConversations;
    }
}
