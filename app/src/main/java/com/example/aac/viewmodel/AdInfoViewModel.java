package com.example.aac.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.aac.bean.AdInfo;
import com.example.aac.http.IApiRepository;
import com.example.aac.http.RepositoryFactory;
import com.example.aac.lifecycle.BaseViewModel;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2018/1/16.
 */

public class AdInfoViewModel extends BaseViewModel<AdInfo> {

    public AdInfoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<AdInfo> getAdInfo(final String adCode) {
//        try {
//            Callable<AdInfo> callable = new Callable<AdInfo>() {
//                @Override
//                public AdInfo call() throws Exception {
//                    return new ApiRepositoryImpl(AdInfoViewModel.this.getApplication()).getAdInfo(adCode);
//                }
//            };
//            MutableLiveData<AdInfo> liveData = new MutableLiveData<>();
//            loadData(liveData, callable);
//            return liveData;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
        final IApiRepository apiRepository = RepositoryFactory.getInstance(getApplication()).getApiRepository();
        Callable<AdInfo> callable = new Callable<AdInfo>() {
            @Override
            public AdInfo call() throws Exception {
                return apiRepository.getAdInfo("");
            }
        };
        MutableLiveData<AdInfo> liveData = new MutableLiveData<>();
        loadData(liveData, callable);
        return liveData;

    }
}
