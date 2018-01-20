package com.example.aac.lifecycle;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.concurrent.Callable;

/**
 * Created by cai.jia on 2017/11/24.
 */

public class BaseViewModel<T> extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    protected void loadData(final MutableLiveData<T> liveData, Callable<T> callable) {
        if (!NetStateUtil.isConnected(getApplication())) {
            Toast.makeText(this.getApplication(), "暂无网络", Toast.LENGTH_SHORT).show();
            return;
        }
        new ThreadSwitchHelper<T>()
                .task(callable)
                .execute(new ThreadSwitchHelper.Callback<T>() {

                    @Override
                    public void onSuccess(T result) {
                        liveData.setValue(result);
                    }

                    @Override
                    public void onFailure(String error) {
                        liveData.setValue(null);
                    }
                });
    }
}
