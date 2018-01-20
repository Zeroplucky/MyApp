package com.example.huanxinim.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.widget.ReceiveMessageItemView;
import com.example.widget.SendMessageItemView;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.DateUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 */

public class MessageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "xxx";

    private Context mContext;

    private List<EMMessage> mMessages;

    private static final int ITEM_TYPE_SEND_MESSAGE = 0;
    private static final int ITEM_TYPE_RECEIVE_MESSAGE = 1;
    public Handler handler;

    public MessageListAdapter(Context context, List<EMMessage> messages) {
        mContext = context;
        mMessages = messages;
        handler = new Handler();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_SEND_MESSAGE) {
            SendMessageItemView sendMessage = new SendMessageItemView(mContext, null, clickListener);
            sendMessage.setHandler(handler);
            return new SendItemViewHolder(sendMessage);
        } else {
            ReceiveMessageItemView receiveMessage = new ReceiveMessageItemView(mContext, null, clickListener);
            receiveMessage.setHandler(handler);
            return new ReceiveItemViewHolder(receiveMessage);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        boolean showTimestamp = false;
        if (position == 0 || shouldShowTimeStamp(position)) {
            showTimestamp = true;
        }
        if (holder instanceof SendItemViewHolder) {
            ((SendItemViewHolder) holder).mSendMessageItemView.bindView(mMessages.get(position), showTimestamp);
        } else {
            ((ReceiveItemViewHolder) holder).mReceiveMessageItemView.bindView(mMessages.get(position), showTimestamp);
        }
    }

    /**
     * 如果两个消息之间的时间太近，就不显示时间戳
     */
    private boolean shouldShowTimeStamp(int position) {
        long currentItemTimestamp = mMessages.get(position).getMsgTime();
        long preItemTimestamp = mMessages.get(position - 1).getMsgTime();
        boolean closeEnough = DateUtils.isCloseEnough(currentItemTimestamp, preItemTimestamp);
        return !closeEnough;
    }

    @Override
    public int getItemViewType(int position) {
        EMMessage message = mMessages.get(position);
        return message.direct() == EMMessage.Direct.SEND ? ITEM_TYPE_SEND_MESSAGE : ITEM_TYPE_RECEIVE_MESSAGE;
    }

    @Override
    public int getItemCount() {
        return mMessages == null ? 0 : mMessages.size();
    }

    public void addNewMessage(EMMessage emMessage) {
        mMessages.add(emMessage);
        notifyDataSetChanged();
    }

    public class ReceiveItemViewHolder extends RecyclerView.ViewHolder {

        public ReceiveMessageItemView mReceiveMessageItemView;

        public ReceiveItemViewHolder(ReceiveMessageItemView itemView) {
            super(itemView);
            mReceiveMessageItemView = itemView;
        }
    }

    public class SendItemViewHolder extends RecyclerView.ViewHolder {

        public SendMessageItemView mSendMessageItemView;

        public SendItemViewHolder(SendMessageItemView itemView) {
            super(itemView);
            mSendMessageItemView = itemView;
        }
    }

    public interface onItemClickListener {
        void onVoidClick(String fileName, ImageView chatItemVoice, int type);
    }

    public onItemClickListener clickListener;

    public void setClickListener(onItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}