package com.example.huanxinim.view;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.TaskStackBuilder;
import android.widget.FrameLayout;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.huanxinim.adapter.EMMessageListenerAdapter;
import com.example.huanxinim.fragment.ContactFragment;
import com.example.huanxinim.fragment.ConversationFragment;
import com.example.utils.ThreadUtils;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import butterknife.BindView;
import me.leolin.shortcutbadger.ShortcutBadger;

import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;

public class IMActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    private FragmentManager mFragmentManager;

    private int mDuanSound;
    private SoundPool mSoundPool;
    private Vibrator mVibrator;  //声明一个振动器对象

    @Override
    protected int getContentViewId() {
        return R.layout.activity_im;
    }

    @Override
    protected void initView() {

        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        mDuanSound = mSoundPool.load(this, R.raw.duan, 1);
        mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
        mFragmentManager = getSupportFragmentManager();
        mBottomBar.setOnTabSelectListener(mOnTabSelectListener);
        EMClient.getInstance().addConnectionListener(mEMConnectionListener);
        EMClient.getInstance().chatManager().addMessageListener(mEMMessageListenerAdapter);
    }

    private OnTabSelectListener mOnTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            if (tabId == R.id.conversations) {
                fragmentTransaction.replace(R.id.fragment_container, ConversationFragment.newInstance()).commit(); //聊天
            } else {
                fragmentTransaction.replace(R.id.fragment_container, ContactFragment.newInstance()).commit(); // 通信录
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        updateUnreadCount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().removeConnectionListener(mEMConnectionListener);
        EMClient.getInstance().chatManager().removeMessageListener(mEMMessageListenerAdapter);
    }

    private EMMessageListenerAdapter mEMMessageListenerAdapter = new EMMessageListenerAdapter() {
        //该回调在子线程中调用  //收到消息
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            updateUnreadCount();
            if (isForeground()) {
//                mSoundPool.play(mDuanSound, 1, 1, 0, 0, 1);
                mVibrator.vibrate(new long[]{10, 500}, -1);
                //停止10，开启震动1秒，不重复.
            } else {
//                mSoundPool.play(mDuanSound, 1, 1, 0, 0, 1);
                mVibrator.vibrate(new long[]{10, 500}, -1);
                showNotification(list.get(0));
            }
        }
    };

    private void updateUnreadCount() {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BottomBarTab bottomBar = mBottomBar.getTabWithId(R.id.conversations);
                int count = EMClient.getInstance().chatManager().getUnreadMsgsCount();
                bottomBar.setBadgeCount(count);
                ShortcutBadger.applyCount(mContext, count);
            }
        });
    }

    private EMConnectionListener mEMConnectionListener = new EMConnectionListener() {
        @Override
        public void onConnected() {

        }

        @Override
        public void onDisconnected(int i) {
            if (i == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toast(getString(R.string.user_login_another_device));
                        startActivity(new Intent(mContext, IMActivity.class));
                        finish();

                    }
                });
            }
        }
    };


    private void showNotification(EMMessage emMessage) {
        String contentText = "";
        if (emMessage.getBody() instanceof EMTextMessageBody) {
            contentText = ((EMTextMessageBody) emMessage.getBody()).getMessage();
        }
        Intent chat = new Intent(this, ChatActivity.class);
        chat.putExtra("user_name", emMessage.getUserName());
        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addParentStack(ChatActivity.class)
                .addNextIntent(chat)
                .getPendingIntent(1, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.drawable.ic_contact_selected_2)
                .setContentTitle(getString(R.string.receive_new_message))
                .setContentText(contentText)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1, notification);
    }

    /*
    * 判断是否在前台
    * */
    public boolean isForeground() {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo info : runningAppProcesses) {
            if (info.processName.equals(getPackageName()) && info.importance == IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }
}
