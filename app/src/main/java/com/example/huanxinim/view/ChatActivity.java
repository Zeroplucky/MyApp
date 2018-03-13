package com.example.huanxinim.view;

import android.Manifest;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.emotionlib.ChatEmotionFragment;
import com.example.emotionlib.GlobalOnItemClickManagerUtils;
import com.example.huanxinim.adapter.CommonFragmentPagerAdapter;
import com.example.huanxinim.adapter.EMMessageListenerAdapter;
import com.example.huanxinim.adapter.MessageListAdapter;
import com.example.huanxinim.fragment.FunctionFragment;
import com.example.huanxinim.presenter.IChatPresenter;
import com.example.huanxinim.presenter.impl.ChatPresenterImpl;
import com.example.utils.EmojiKeyboard;
import com.example.utils.MediaManager;
import com.example.utils.SPUtil;
import com.example.utils.ThreadUtils;
import com.example.widget.NoScrollViewPager;
import com.example.widget.StateButton;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChatActivity extends BaseActivity implements IChatView {


    @BindView(R.id.rv_messageList)
    RecyclerView mRecyclerView;
    @BindView(R.id.voice_text_switch_iv)
    ImageView emotionVoice;
    @BindView(R.id.et_inputMessage)
    EditText etInputMessage;
    @BindView(R.id.iv_add)
    ImageView emotionAdd;
    @BindView(R.id.bt_emotion)
    ImageView bt_emotion;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewpager;
    @BindView(R.id.panel_content)
    LinearLayout panelContent;
    @BindView(R.id.ll_rootEmojiPanel)
    LinearLayout llRootEmojiPanel;
    @BindView(R.id.img_back)
    ImageButton imgBack;
    @BindView(R.id.toor_bar)
    TextView toorBar;
    @BindView(R.id.emotion_send)
    StateButton emotionSend;
    @BindView(R.id.root_ll)
    LinearLayout root_ll;
    @BindView(R.id.voice_text)
    TextView voiceText;

    private CommonFragmentPagerAdapter adapter;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private FunctionFragment functionFragment;
    private EmojiKeyboard emojiKeyboard;
    private String mUserName;
    private IChatPresenter mChatPresenter;
    private MessageListAdapter mMessageListAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    //录音相关
    private AnimationDrawable animDrawable = null;
    int animationRes = 0;
    int res = 0;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_chat2;
    }

    @Override
    protected void initView() {
        mUserName = getIntent().getStringExtra("user_name");
        SPUtil.instance(mContext).put("mUserName", mUserName);
        String title = String.format(getString(R.string.chat_with), mUserName);
        toorBar.setText(title);
        imgBack.setVisibility(View.VISIBLE);
        mChatPresenter = new ChatPresenterImpl(this);
        //表情的ViewPager
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        functionFragment = FunctionFragment.newInstance();
        fragments.add(functionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageListAdapter = new MessageListAdapter(this, mChatPresenter.getMessages());
        mRecyclerView.setAdapter(mMessageListAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        EMClient.getInstance().chatManager().addMessageListener(mEMMessageListener);
        mChatPresenter.loadMessages(mUserName);// 获取消息
        emojiKeyboard = EmojiKeyboard.newInstance(this, etInputMessage, llRootEmojiPanel, bt_emotion, mRecyclerView, viewpager, emotionAdd);
        emojiKeyboard.bindToPresenter(mChatPresenter)
                .bindToSendButton(emotionSend)
                .bindToVoiceButton(emotionVoice)
                .bindToVoiceText(voiceText)
                .build();

        //绑定editText
        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(etInputMessage);

        root_ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = root_ll.getRootView().getHeight() - root_ll.getHeight();
                if (heightDiff > 400) { // 当键盘或者表情布局弹出来时，让消息列表滚动到最低部
                    root_ll.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            smoothScrollToBottom();
                        }
                    }, 10);
                }
            }
        });

        emojiKeyboard.setShow(new EmojiKeyboard.EmojiShow() {
            @Override
            public void onShow(View view, boolean f) {
                if (f) {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            smoothScrollToBottom();
                        }
                    }, 10);
                }
            }
        });

        //权限相关
        if (hasPermission()) {
            //有权限
        } else {
            //没有权限，申请权限
            applyPermission();
        }

        //播放语音
        mMessageListAdapter.setClickListener(new MessageListAdapter.onItemClickListener() {
            @Override
            public void onVoidClick(String file, final ImageView view, int type) {
                switch (type) {
                    case 1:
                        animationRes = R.drawable.voice_left;
                        res = R.drawable.icon_voice_left3;
                        break;
                    case 0:
                        animationRes = R.drawable.voice_right;
                        res = R.drawable.icon_voice_right3;
                        break;
                }
                view.setImageResource(animationRes);
                animDrawable = (AnimationDrawable) view.getDrawable();
                animDrawable.start();
                MediaManager.playSound(file, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        animDrawable.stop();
                        view.setImageResource(res);
                    }
                });
            }
        });
    }

    private EMMessageListenerAdapter mEMMessageListener = new EMMessageListenerAdapter() {
        @Override
        public void onMessageReceived(final List<EMMessage> list) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final EMMessage emMessage = list.get(0);
                    if (emMessage.getUserName().equals(mUserName)) {
                        mChatPresenter.makeMessageRead(mUserName); //设置消息已读
                        mMessageListAdapter.addNewMessage(emMessage);
                        smoothScrollToBottom();
                    }
                }
            });
        }
    };

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition == 0) {
//                    mChatPresenter.loadMoreMessages(mUserName); //获取更多消息
                }
            }
        }
    };

    private void smoothScrollToBottom() {
        mRecyclerView.smoothScrollToPosition(mMessageListAdapter.getItemCount());
    }

    @Override
    public void onBackPressed() {
        if (!emojiKeyboard.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onStartSendMessage() {
        updateList();
    }

    //消息发送成功
    @Override
    public void onSendMessageSuccess() {
        updateList();
    }

    private void updateList() {
        mMessageListAdapter.notifyDataSetChanged();
        smoothScrollToBottom();
    }


    @Override
    public void onSendMessageFailed() {

    }

    @Override
    public void onMessagesLoaded() {
        mMessageListAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(mMessageListAdapter.getItemCount() - 1);
    }

    //下拉获取更多消息 ，有20条
    @Override
    public void onMoreMessagesLoaded(int size) {

    }

    //下拉获取更多消息 ，无
    @Override
    public void onNoMoreData() {

    }

    @Override
    protected void onDestroy() {
        EMClient.getInstance().chatManager().removeMessageListener(mEMMessageListener);
        MediaManager.release();
        super.onDestroy();
    }


    /*
    * 权限检查
    * */
    private boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PermissionChecker.PERMISSION_GRANTED;
    }

    /*
    * 权限申请
    * */
    private void applyPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
    }

    /**
     * 申请权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                    //得到权限后的操作
                } else {
                    Toast.makeText(mContext, "没有录音权限，无法语音", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
