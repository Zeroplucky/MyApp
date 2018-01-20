package com.example.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Environment;
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

public class SendMessageItemView extends RelativeLayout {

    @BindView(R.id.send_message)
    GifTextView mSendMessage;
    @BindView(R.id.send_message_progress)
    ImageView mSendMessageProgress;
    @BindView(R.id.timestamp)
    TextView mTimestamp;
    public Handler handler;
    @BindView(R.id.chat_item_voice)
    ImageView chatItemVoice;
    @BindView(R.id.chat_item_layout_content)
    LinearLayout chatItemLayout;
    private MessageListAdapter.onItemClickListener clickListener;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public SendMessageItemView(Context context) {
        this(context, null, null);
    }

    public SendMessageItemView(Context context, AttributeSet attrs, MessageListAdapter.onItemClickListener mclickListener) {
        super(context, attrs);
        clickListener = mclickListener;
        init();
    }

    private void init() {

        LayoutInflater.from(getContext()).inflate(R.layout.view_send_message_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(EMMessage emMessage, boolean showTimestamp) {
        updateTimestamp(emMessage, showTimestamp);
        updateMessageBody(emMessage);
        updateSendingStatus(emMessage);
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
            mSendMessage.setSpanText(handler, message, true);
        } else if (body instanceof EMVoiceMessageBody) {
            chatItemVoice.setVisibility(VISIBLE);
            final String fileName = ((EMVoiceMessageBody) body).getFileName();
            int length = ((EMVoiceMessageBody) body).getLength();
            mSendMessage.setSpanText(handler, "时长 " + Utils.long2String(length), true);
            chatItemLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onVoidClick(Environment.getExternalStorageDirectory() + "/record/" + fileName, chatItemVoice,0);
                    }
                }
            });
        }
    }

    private void updateSendingStatus(EMMessage emMessage) {
        switch (emMessage.status()) {
            case INPROGRESS:
                mSendMessageProgress.setVisibility(VISIBLE);
                mSendMessageProgress.setImageResource(R.drawable.send_message_progress);
                AnimationDrawable drawable = (AnimationDrawable) mSendMessageProgress.getDrawable();
                drawable.start();
                break;
            case SUCCESS:
                mSendMessageProgress.setVisibility(GONE);
                break;
            case FAIL:
                mSendMessageProgress.setImageResource(R.drawable.msg_error);
                break;
        }
    }
}