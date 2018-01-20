package com.example.huanxinim.presenter.impl;

import com.example.huanxinim.presenter.IChatPresenter;
import com.example.huanxinim.view.IChatView;
import com.example.utils.ThreadUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */

public class ChatPresenterImpl implements IChatPresenter {

    private IChatView mChatView;
    private String userName;
    private List<EMMessage> mEMMessageList;
    private boolean hasMoreData = true;

    public ChatPresenterImpl(IChatView chatView) {
        this.mChatView = chatView;
        mEMMessageList = new ArrayList();
    }

    @Override
    public void sendTextMessage(final String userName, final String message) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                EMMessage emMessage = EMMessage.createTxtSendMessage(message, userName);
                emMessage.setStatus(EMMessage.Status.INPROGRESS);
                emMessage.setMessageStatusCallback(mEMCallBackAdapter);
                mEMMessageList.add(emMessage);
                EMClient.getInstance().chatManager().sendMessage(emMessage);
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.onStartSendMessage();
                    }
                });
            }
        });
    }

    @Override
    public void sendVoidMessage(final String userName, final String filePath, final int length) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                EMMessage emMessage = EMMessage.createVoiceSendMessage(filePath, length, userName);
                emMessage.setStatus(EMMessage.Status.INPROGRESS);
                emMessage.setMessageStatusCallback(mEMCallBackAdapter);
                mEMMessageList.add(emMessage);
                EMClient.getInstance().chatManager().sendMessage(emMessage);
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.onStartSendMessage(); //开始发送消息
                    }
                });
            }
        });
    }

    private EMCallBack mEMCallBackAdapter = new EMCallBack() {
        @Override
        public void onSuccess() {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mChatView.onSendMessageSuccess(); // 发送消息成功
                }
            });
        }

        @Override
        public void onError(int i, String s) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mChatView.onSendMessageFailed();// 发送消息失败
                }
            });
        }

        @Override
        public void onProgress(int i, String s) {

        }
    };

    @Override
    public List<EMMessage> getMessages() {
        return mEMMessageList;
    }

    @Override
    public void loadMessages(final String userName) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                EMConversation conversation = EMClient.getInstance().chatManager().getConversation(userName);
                if (conversation != null) {
                    //获取此会话的所有消息
                    List<EMMessage> messages = conversation.getAllMessages();
                    mEMMessageList.addAll(messages);
                    //指定会话消息未读数清零
                    conversation.markAllMessagesAsRead();
                }
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.onMessagesLoaded();
                    }
                });
            }
        });
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void loadMoreMessages(final String userName) {
        if (hasMoreData) {
            ThreadUtils.runOnBackgroundThread(new Runnable() {
                @Override
                public void run() {
                    EMConversation conversation = EMClient.getInstance().chatManager().getConversation(userName);
                    EMMessage firstMessage = mEMMessageList.get(0);
                    //SDK初始化加载的聊天记录为20条，到顶时需要去DB里获取更多
                    //获取startMsgId之前的pagesize条消息，此方法获取的messages SDK会自动存入到此会话中，APP中无需再次把获取到的messages添加到会话中
                    final List<EMMessage> messages = conversation.loadMoreMsgFromDB(firstMessage.getMsgId(), 20);
                    hasMoreData = (messages.size() == 20);
                    mEMMessageList.addAll(0, messages);
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mChatView.onMoreMessagesLoaded(messages.size());
                        }
                    });
                }
            });
        } else {
            mChatView.onNoMoreData();
        }
    }

    @Override
    public void makeMessageRead(final String userName) {
        this.userName = userName;
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                EMConversation conversation = EMClient.getInstance().chatManager().getConversation(userName);
                conversation.markAllMessagesAsRead();
            }
        });
    }
}
