package com.example.huanxinim.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.base.R;
import com.example.huanxinim.adapter.ConversationAdapter;
import com.example.huanxinim.adapter.EMMessageListenerAdapter;
import com.example.huanxinim.presenter.IConversationPresenter;
import com.example.huanxinim.presenter.impl.ConversationPresenterImpl;
import com.example.huanxinim.view.IConversationView;
import com.example.utils.ThreadUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;

import butterknife.BindView;


public class ConversationFragment extends BaseFragment implements IConversationView {

    @BindView(R.id.img_back)
    ImageButton imgBack;
    @BindView(R.id.toor_bar)
    TextView toorBar;
    private IConversationPresenter mConversationPresenter;
    private ConversationAdapter mConversationAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static ConversationFragment newInstance() {
        ConversationFragment mMessageFragment = new ConversationFragment();
        return mMessageFragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_conversation;
    }

    @Override
    protected void initView(View view) {
        mConversationPresenter = new ConversationPresenterImpl(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mConversationAdapter = new ConversationAdapter(getContext(), mConversationPresenter.getConversations());
        mRecyclerView.setAdapter(mConversationAdapter);

        mConversationPresenter.loadAllConversations();
        EMClient.getInstance().chatManager().addMessageListener(mEMMessageListenerAdapter);
    }


    private EMMessageListenerAdapter mEMMessageListenerAdapter = new EMMessageListenerAdapter() {

        @Override
        public void onMessageReceived(List<EMMessage> list) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mConversationPresenter.loadAllConversations();
                    mRecyclerView.scrollToPosition(0);
                }
            });
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mConversationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAllConversationsLoaded() {
        mConversationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        EMClient.getInstance().chatManager().removeMessageListener(mEMMessageListenerAdapter);
        super.onDestroy();
    }


}
