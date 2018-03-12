package com.example.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.R;
import com.example.emotionlib.Utils;
import com.example.huanxinim.adapter.MessageListAdapter;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.EMVoiceMessageBody;
import com.hyphenate.util.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/14.
 */

public class ReceiveMessageItemView extends RelativeLayout {

    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.receive_message)
    GifTextView mReceiveMessage;
    @BindView(R.id.chat_item_voice)
    ImageView chatItemVoice;
    @BindView(R.id.chat_item_layout_content)
    LinearLayout chatItemLayout;
    public Handler handler;
    private MessageListAdapter.onItemClickListener clickListener;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ReceiveMessageItemView(Context context) {
        this(context, null, null);
    }

    public ReceiveMessageItemView(Context context, AttributeSet attrs, MessageListAdapter.onItemClickListener mclickListener) {
        super(context, attrs);
        clickListener = mclickListener;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_receive_message_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(EMMessage emMessage, boolean showTimestamp) {
        updateTimestamp(emMessage, showTimestamp);
        updateMessageBody(emMessage);
    }

    private void updateTimestamp(EMMessage emMessage, boolean showTimestamp) {
        if (showTimestamp) {
            mTimestamp.setVisibility(VISIBLE);
            String time = DateUtils.getTimestampString(new Date(emMessage.getMsgTime()));
            mTimestamp.setText(time);
        } else {
            mTimestamp.setVisibility(GONE);
        }
    }

    private void updateMessageBody(EMMessage emMessage) {
        EMMessageBody body = emMessage.getBody();
        if (body instanceof EMTextMessageBody) {
            String message = ((EMTextMessageBody) body).getMessage();
            chatItemVoice.setVisibility(GONE);
            mReceiveMessage.setSpanText(handler, message, true);
        } else if (body instanceof EMVoiceMessageBody) {
            chatItemVoice.setVisibility(VISIBLE);
            int length = ((EMVoiceMessageBody) body).getLength();
            final String remoteUrl = ((EMVoiceMessageBody) body).getRemoteUrl();
            mReceiveMessage.setSpanText(handler, "时长 " + Utils.long2String(length), true);
            chatItemLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onVoidClick(remoteUrl, chatItemVoice, 1);
                    }
                }
            });
        }
    }
}