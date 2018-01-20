package com.example.aac.lifecycle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;

import java.util.concurrent.Callable;

/**
 * 线程切换帮助类,注意每次执行任务，都需要new一个实例,类似AsyncTask
 * Created by cai.jia on 2017/8/28 0028.
 */

public class ThreadSwitchHelper<T> {

    private static final int MSG_SUCCESS = 200;
    private static final int MSG_FAILURE = 404;
    private InternalHandler<T> handler;
    private Callback<T> callback;
    private Runnable task;

    public ThreadSwitchHelper() {
        handler = new InternalHandler<>();
    }

    public ThreadSwitchHelper<T> task(final Callable<T> callable) {
        return task(callable, 0);
    }

    /**
     * 当一个任务依赖别的结果时用Callable包装
     *
     * @param callable
     * @return
     */
    public ThreadSwitchHelper<T> task(final Callable<T> callable, final long delay) {
        task = new Runnable() {
            @Override
            public void run() {
                MessageResult<T> messageResult = new MessageResult<>();
                messageResult.helper = ThreadSwitchHelper.this;
                try {
                    if (delay > 0) {
                        Thread.sleep(delay);
                    }
                    messageResult.data = callable.call();
                    handler.sendMessage(Message.obtain(handler, MSG_SUCCESS, messageResult));

                } catch (Exception e) {
                    e.printStackTrace();
                    messageResult.errorMsg = e.getMessage();
                    handler.sendMessage(Message.obtain(handler, MSG_FAILURE, messageResult));
                }
            }
        };
        return this;
    }

    public void exexute() {
        execute(null);
    }

    public void execute(@Nullable Callback<T> callback) {
        this.callback = callback;
        if (task != null) {
            ThreadPoolManager.getInstance().execute(task);
        }
    }

    private void dispatchSuccessEvent(T result) {
        if (callback != null) {
            callback.onSuccess(result);
        }
    }

    private void dispatchFailureEvent(String errorMsg) {
        if (callback != null) {
            callback.onFailure(errorMsg);
        }
    }

    private static class InternalHandler<T> extends Handler {

        InternalHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUCCESS: {
                    MessageResult<T> result = (MessageResult<T>) msg.obj;
                    if (result != null && result.helper != null) {
                        T data = result.data;
                        if (data == null) {
                            result.helper.dispatchFailureEvent("error");
                            return;
                        }
                        result.helper.dispatchSuccessEvent(data);
                    }
                    break;
                }

                case MSG_FAILURE: {
                    MessageResult<T> result = (MessageResult<T>) msg.obj;
                    if (result != null && result.helper != null) {
                        result.helper.dispatchFailureEvent(result.errorMsg == null ? "error" : result.errorMsg);
                    }
                    break;
                }
            }
        }
    }

    public interface Callback<E> {

        void onSuccess(E result);

        void onFailure(String error);
    }

    private static class MessageResult<T> {

        private ThreadSwitchHelper<T> helper;
        private T data;
        private String errorMsg;

    }
}
