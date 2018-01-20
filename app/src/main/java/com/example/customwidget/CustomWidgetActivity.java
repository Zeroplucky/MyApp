package com.example.customwidget;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.customwidget.widget.VerticalTextview;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CustomWidgetActivity extends BaseActivity {

    @BindView(R.id.mTextView)
    VerticalTextview mTextView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_widget_;
    }

    @Override
    protected void initView() {

        Observable.interval(1, 10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
