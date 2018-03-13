package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.R;
import com.example.emotionlib.Utils;
import com.example.huanxinim.presenter.IChatPresenter;
import com.example.widget.NoScrollViewPager;

/**
 * 作者： chenZY
 * 时间： 2017/8/26 18:12
 * 描述：
 */
public class EmojiKeyboard {

    private static EmojiKeyboard keyboard;

    private Activity activity;

    //文本输入框
    private EditText editText;

    //表情面板
    private View emojiLayout;

    //内容View,即除了表情布局和输入框布局以外的布局
    //用于固定输入框一行的高度以防止跳闪
    private View contentView;

    private InputMethodManager inputMethodManager;

    private SharedPreferences sharedPreferences;

    private static final String EMOJI_KEYBOARD = "EmojiKeyboard";

    private static final String KEY_SOFT_KEYBOARD_HEIGHT = "SoftKeyboardHeight";

    private static final int SOFT_KEYBOARD_HEIGHT_DEFAULT = 654;

    private Handler handler;

    private View mSendButton;

    private IChatPresenter mChatPresenter;

    private TextView mVoiceText;
    private AudioRecoderUtils mAudioRecoderUtils;
    private PopupWindowFactory mVoicePop;
    private TextView mPopVoiceText;
    private NoScrollViewPager viewPager;
    private View mAddButton;
    private Boolean isShowEmoji = false;
    private Boolean isShowAdd = false;

    public static EmojiKeyboard newInstance(Activity activity, EditText editText, View emojiPanelView, ImageView emojiPanelSwitchView, View contentView, NoScrollViewPager viewPager, View mAddButton) {
        keyboard = new EmojiKeyboard(activity, editText, emojiPanelView, emojiPanelSwitchView, contentView, viewPager, mAddButton);
        return keyboard;
    }

    private EmojiKeyboard(Activity activity, EditText editText, View emojiLayout, ImageView emojiPanelSwitchView, View contentView, NoScrollViewPager viewPager, View mAddButton) {
        init(activity, editText, emojiLayout, emojiPanelSwitchView, contentView, viewPager, mAddButton);
    }

    public EmojiKeyboard bindToPresenter(IChatPresenter presenter) {
        this.mChatPresenter = presenter;
        return keyboard;
    }

    public EmojiKeyboard bindToSendButton(View sendButton) {
        mSendButton = sendButton;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSendButton.setVisibility(View.GONE);
                String mUserName = (String) SPUtil.instance(activity).get("mUserName", "");
                if (!TextUtils.isEmpty(mUserName)) {
                    mChatPresenter.sendTextMessage(mUserName, editText.getText().toString().trim());
                    editText.setText("");
                } else {
                    Toast.makeText(activity, "名字为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return keyboard;
    }


    private void init(Activity activity, EditText editText, View emojiPanelView, final ImageView emojiView, View contentView, final NoScrollViewPager viewPager, final View mAddButton) {
        this.activity = activity;
        this.editText = editText; //消息框
        this.emojiLayout = emojiPanelView; // root
        this.contentView = contentView; //recyclerView
        this.mAddButton = mAddButton;
        this.editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && EmojiKeyboard.this.emojiLayout.isShown()) {
                    lockContentViewHeight();
                    hideEmojiPanel(true);
                    unlockContentViewHeight();
                }
                return false;
            }
        });
        //
        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    mAddButton.setVisibility(View.GONE);
                    mSendButton.setVisibility(View.VISIBLE);
                } else {
                    mAddButton.setVisibility(View.VISIBLE);
                    mSendButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        this.contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (EmojiKeyboard.this.emojiLayout.isShown()) {
                        hideEmojiPanel(false);
                    } else if (isSoftKeyboardShown()) {
                        hideSoftKeyboard();
                    }
                    emojiView.setImageResource(R.drawable.icon_chat_expression);
                }
                return false;
            }
        });
        //用于弹出表情面板的View
        emojiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVoiceText.setVisibility(mVoiceText.getVisibility() == View.VISIBLE ? View.GONE : View.GONE);
                EmojiKeyboard.this.editText.setVisibility(mVoiceText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                if (EmojiKeyboard.this.emojiLayout.isShown()) {
                    if (isShowEmoji) {
                        lockContentViewHeight();
                        hideEmojiPanel(true);
                        unlockContentViewHeight();
                        emojiView.setImageResource(R.drawable.icon_chat_expression);
                    } else {
                        viewPager.setCurrentItem(0);
                        emojiView.setImageResource(R.drawable.jianpan);
                        isShowEmoji = true;
                    }
                    isShowAdd = false;
                } else {
                    if (isSoftKeyboardShown()) {
                        lockContentViewHeight();
                        showEmojiPanel();
                        viewPager.setCurrentItem(0);
                        unlockContentViewHeight();
                        emojiView.setImageResource(R.drawable.jianpan);
                        isShowAdd = false;
                        isShowEmoji = true;
                    } else {
                        isShowAdd = false;
                        isShowEmoji = true;
                        showEmojiPanel();
                        viewPager.setCurrentItem(0);
                        emojiView.setImageResource(R.drawable.jianpan);
                    }
                }
                if (show != null) {
                    show.onShow(emojiView, EmojiKeyboard.this.emojiLayout.isShown());
                }
            }
        });
        //用于面板的View
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVoiceText.setVisibility(mVoiceText.getVisibility() == View.VISIBLE ? View.GONE : View.GONE);
                EmojiKeyboard.this.editText.setVisibility(mVoiceText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                if (EmojiKeyboard.this.emojiLayout.isShown()) {
                    if (isShowAdd) {
                        lockContentViewHeight();
                        hideEmojiPanel(true);
                        unlockContentViewHeight();
                    } else {
                        viewPager.setCurrentItem(1);
                        isShowAdd = true;
                        emojiView.setImageResource(R.drawable.icon_chat_expression);
                    }
                    isShowEmoji = false;
                } else {
                    emojiView.setImageResource(R.drawable.icon_chat_expression);
                    if (isSoftKeyboardShown()) {
                        lockContentViewHeight();
                        showEmojiPanel();
                        viewPager.setCurrentItem(1);
                        unlockContentViewHeight();
                        isShowEmoji = false;
                        isShowAdd = true;
                    } else {
                        showEmojiPanel();
                        viewPager.setCurrentItem(1);
                        isShowEmoji = false;
                        isShowAdd = true;
                    }
                }
                if (show != null) {
                    show.onShow(emojiView, EmojiKeyboard.this.emojiLayout.isShown());
                }

            }
        });
        this.inputMethodManager = (InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.sharedPreferences = this.activity.getSharedPreferences(EMOJI_KEYBOARD, Context.MODE_PRIVATE);
        this.activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.handler = new Handler();
        init();
    }

    //
    public interface EmojiShow {
        void onShow(View view, boolean f);
    }

    private EmojiShow show;

    public void setShow(EmojiShow show) {
        this.show = show;
    }

    /**
     * 如果之前没有保存过键盘高度值
     * 则在进入Activity时自动打开键盘，并把高度值保存下来
     */
    private void init() {
        if (!sharedPreferences.contains(KEY_SOFT_KEYBOARD_HEIGHT)) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showSoftKeyboard(true);
                }
            }, 200);
        }
    }

    /**
     * 当点击返回键时需要先隐藏表情面板
     */
    public boolean interceptBackPress() {
        if (emojiLayout.isShown()) {
            hideEmojiPanel(false);
            return true;
        }
        return false;
    }

    /**
     * 锁定内容View以防止跳闪
     */
    private void lockContentViewHeight() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) contentView.getLayoutParams();
        layoutParams.height = contentView.getHeight();
        layoutParams.weight = 0;
    }

    /**
     * 释放锁定的内容View
     */
    private void unlockContentViewHeight() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((LinearLayout.LayoutParams) contentView.getLayoutParams()).weight = 1;
            }
        }, 200);
    }

    /**
     * 获取键盘的高度
     */
    private int getSoftKeyboardHeight() {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //屏幕当前可见高度，不包括状态栏
        int displayHeight = rect.bottom - rect.top;
        //屏幕可用高度
        int availableHeight = ScreenUtils.getAvailableScreenHeight(activity);
        //用于计算键盘高度
        int softInputHeight = availableHeight - displayHeight - ScreenUtils.getStatusBarHeight(activity);
        if (softInputHeight != 0) {
            // 因为考虑到用户可能会主动调整键盘高度，所以只能是每次获取到键盘高度时都将其存储起来
            sharedPreferences.edit().putInt(KEY_SOFT_KEYBOARD_HEIGHT, softInputHeight).apply();
        }
        return softInputHeight;
    }

    /**
     * 获取本地存储的键盘高度值或者是返回默认值
     */
    private int getSoftKeyboardHeightLocalValue() {
        return sharedPreferences.getInt(KEY_SOFT_KEYBOARD_HEIGHT, SOFT_KEYBOARD_HEIGHT_DEFAULT);
    }

    /**
     * 判断是否显示了键盘
     */
    private boolean isSoftKeyboardShown() {
        return getSoftKeyboardHeight() != 0;
    }

    /**
     * 令编辑框获取焦点并显示键盘
     */
    private void showSoftKeyboard(boolean saveSoftKeyboardHeight) {
        editText.requestFocus();
        inputMethodManager.showSoftInput(editText, 0);
        if (saveSoftKeyboardHeight) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSoftKeyboardHeight();
                }
            }, 200);
        }
    }

    /**
     * 隐藏键盘
     */
    private void hideSoftKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 显示表情面板
     */
    private void showEmojiPanel() {
        int softKeyboardHeight = getSoftKeyboardHeight();
        if (softKeyboardHeight == 0) {
            softKeyboardHeight = getSoftKeyboardHeightLocalValue();
        } else {
            hideSoftKeyboard();
        }
        emojiLayout.getLayoutParams().height = softKeyboardHeight;
        emojiLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏表情面板，同时指定是否随后开启键盘
     */
    private void hideEmojiPanel(boolean showSoftKeyboard) {
        if (emojiLayout.isShown()) {
            emojiLayout.setVisibility(View.GONE);
            if (showSoftKeyboard) {
                showSoftKeyboard(false);
            }
        }
    }

    //
    public EmojiKeyboard bindToVoiceButton(View voiceButton) {
        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVoiceText.setVisibility(mVoiceText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                editText.setVisibility(mVoiceText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideEmojiPanel(false);
                        hideSoftKeyboard();
                    }
                }, 10);
            }
        });
        return keyboard;
    }

    public EmojiKeyboard bindToVoiceText(TextView voiceText) {
        mVoiceText = voiceText;
        mVoiceText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 获得x轴坐标
                int x = (int) event.getX();
                // 获得y轴坐标
                int y = (int) event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mVoicePop.showAtLocation(v, Gravity.CENTER, 0, 0);
                        mVoiceText.setText("松开结束");
                        mPopVoiceText.setText("手指上滑，取消发送");
                        mVoiceText.setTag("1");
                        mAudioRecoderUtils.startRecord(activity);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (wantToCancle(x, y)) {
                            mVoiceText.setText("松开结束");
                            mPopVoiceText.setText("松开手指，取消发送");
                            mVoiceText.setTag("2");
                        } else {
                            mVoiceText.setText("松开结束");
                            mPopVoiceText.setText("手指上滑，取消发送");
                            mVoiceText.setTag("1");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mVoicePop.dismiss();
                        if (mVoiceText.getTag().equals("2")) {
                            //取消录音（删除录音文件）
                            mAudioRecoderUtils.cancelRecord();
                        } else {
                            //结束录音（保存录音文件）
                            mAudioRecoderUtils.stopRecord();
                        }
                        mVoiceText.setText("按住说话");
                        mVoiceText.setTag("3");
                        mVoiceText.setVisibility(View.GONE);
                        editText.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });
        return keyboard;
    }

    private boolean wantToCancle(int x, int y) {
        // 超过按钮的宽度
        if (x < 0 || x > mVoiceText.getWidth()) {
            return true;
        }
        // 超过按钮的高度
        if (y < -50 || y > mVoiceText.getHeight() + 50) {
            return true;
        }
        return false;
    }

    public void build() {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        hideSoftKeyboard();
        mAudioRecoderUtils = new AudioRecoderUtils();

        View view = View.inflate(activity, R.layout.layout_microphone, null);
        mVoicePop = new PopupWindowFactory(activity, view);

        //PopupWindow布局文件里面的控件
        final ImageView mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
        final TextView mTextView = (TextView) view.findViewById(R.id.tv_recording_time);
        mPopVoiceText = (TextView) view.findViewById(R.id.tv_recording_text);
        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(Utils.long2String(time));
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(long length, String filePath) {
                mTextView.setText(Utils.long2String(0));
                //filePath为语音文件路径，length为录音时间(秒)
                String mUserName = (String) SPUtil.instance(activity).get("mUserName", "");
                mChatPresenter.sendVoidMessage(mUserName, filePath, (int) length);
            }

            @Override
            public void onError() {
                mVoiceText.setVisibility(View.GONE);
                editText.setVisibility(View.VISIBLE);
            }
        });
    }

}
