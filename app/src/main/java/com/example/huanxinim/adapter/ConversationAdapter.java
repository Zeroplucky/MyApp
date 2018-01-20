package com.example.huanxinim.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.R;
import com.example.huanxinim.view.ChatActivity;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.EMVoiceMessageBody;
import com.hyphenate.util.DateUtils;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/14.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationItemViewHolder> {
    public static final String TAG = "xxx";

    public Context mContext;
    public List<EMConversation> mEMConversations;

    public ConversationAdapter(Context context, List<EMConversation> conversations) {
        mContext = context;
        mEMConversations = conversations;
    }

    @Override
    public ConversationItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ConversationItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_conversation_item, null));
    }

    @Override
    public void onBindViewHolder(ConversationItemViewHolder holder, int position) {
        holder.bindView(mEMConversations.get(position));
    }

    @Override
    public int getItemCount() {
        return mEMConversations.size();
    }


    public class ConversationItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_name)
        TextView mUserName;
        @BindView(R.id.last_message)
        TextView mLastMessage;
        @BindView(R.id.timestamp)
        TextView mTimestamp;
        @BindView(R.id.unread_count)
        TextView mUnreadCount;
        @BindView(R.id.conversation_item_container)
        RelativeLayout mConversationItemContainer;

        public ConversationItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(final EMConversation emConversation) {
            mUserName.setText(emConversation.conversationId());
            updateLastMessage(emConversation);
            updateUnreadCount(emConversation);
            mConversationItemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ChatActivity.class);
                    intent.putExtra("user_name", emConversation.conversationId());
                    mContext.startActivity(intent);
                }
            });
        }


        private void updateLastMessage(EMConversation emConversation) {
            EMMessage emMessage = emConversation.getLastMessage();
            if (emMessage.getBody() instanceof EMTextMessageBody) {
                mLastMessage.setText(((EMTextMessageBody) emMessage.getBody()).getMessage());
            } else if (emMessage.getBody() instanceof EMVoiceMessageBody){
                mLastMessage.setText(mContext.getString(R.string.void_message));
            }
            mTimestamp.setText(DateUtils.getTimestampString(new Date(emMessage.getMsgTime())));
        }

        private void updateUnreadCount(EMConversation emConversation) {
            int unreadMsgCount = emConversation.getUnreadMsgCount();
            if (unreadMsgCount > 0) {
                mUnreadCount.setVisibility(View.VISIBLE);
                mUnreadCount.setText(String.valueOf(unreadMsgCount));
            } else {
                mUnreadCount.setVisibility(View.GONE);
            }
        }
    }
}