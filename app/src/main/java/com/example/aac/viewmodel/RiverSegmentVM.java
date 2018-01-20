package com.example.aac.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.aac.bean.RiverSegment;
import com.example.aac.http.IApiRepository;
import com.example.aac.http.RepositoryFactory;
import com.example.aac.lifecycle.BaseViewModel;

import java.util.concurrent.Callable;

/**
 * Created by GR-GZ-ACER on 2017/12/21.
 */

public class RiverSegmentVM extends BaseViewModel<RiverSegment> {

    public RiverSegmentVM(@NonNull Application application) {
        super(application);
    }

    public LiveData<RiverSegment> getRiverSegment(final int queryType, final String code) {
        final IApiRepository apiRepository = RepositoryFactory.getInstance(getApplication())
                .getApiRepository();
        Callable<RiverSegment> callable = new Callable<RiverSegment>() {
            @Override
            public RiverSegment call() throws Exception {
                return apiRepository.getRiverSegment(queryType, code);
            }
        };
        MutableLiveData<RiverSegment> liveData = new MutableLiveData<>();
        loadData(liveData, callable);
        return liveData;
    }
}
