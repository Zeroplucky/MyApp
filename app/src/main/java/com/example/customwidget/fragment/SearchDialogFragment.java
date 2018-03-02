package com.example.customwidget.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.math.MathUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.R;
import com.example.customwidget.adapter.SearchAdapter;
import com.example.customwidget.bean.RiverList;
import com.example.customwidget.presenter.CustomPresenter;
import com.example.customwidget.presenter.ICustomPresenter;
import com.example.customwidget.view.ISearchView;
import com.example.utils.DisplayUtils;
import com.example.utils.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2018/1/16.
 */

public class SearchDialogFragment extends Fragment implements ISearchView {

    private static final String SEARCH_TYPE = "params:searchType";

    public static SearchDialogFragment newInstance() {
        SearchDialogFragment f = new SearchDialogFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.v_shadow)
    View vShadow;
    private ICustomPresenter presenter;
    private SearchAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_search_river_condition, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new SearchAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setAlpha(0);
        //
        presenter = new CustomPresenter(getContext());
        presenter.getSearchData(this);
    }

    private void computeRecyclerViewHeight() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                int height = recyclerView.getHeight();
                animatorHeight = (int) Math.min(height, ScreenUtils.getScreenHeight(getContext()) * 0.6f);
                if (height > animatorHeight) {
                    recyclerView.getLayoutParams().height = animatorHeight;
                    recyclerView.requestLayout();
                }
                toggle();
            }
        }, 500);
    }

    ValueAnimator valueAnimator;
    private int animatorHeight;
    private static final float SPEED = 1.5f; //1dp - 1.2ms

    public boolean toggle() {
        return toggle(null);
    }

    public boolean toggle(Animator.AnimatorListener listener) {
        if (recyclerView == null) {
            return true;
        }
        final boolean expand = recyclerView.getAlpha() == 0;
        if (valueAnimator != null && valueAnimator.isRunning() || animatorHeight == 0) {
            return expand;
        }
        int start = expand ? -animatorHeight : 0;
        int end = expand ? 0 : -animatorHeight;
        int duration = (int) (DisplayUtils.px2dip(getContext(), Math.abs(end - start)) * SPEED);
        duration = MathUtils.clamp(duration, 300, 500);
        valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                recyclerView.setTranslationY(value);
                float fraction = animation.getAnimatedFraction();
                float alpha = expand ? fraction : 1 - fraction;
                recyclerView.setAlpha(alpha);
                vShadow.setAlpha(alpha);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                if (expand) {
                    vShadow.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!expand) {
                    vShadow.setVisibility(View.GONE);
                }
            }
        });
        valueAnimator.start();
        return expand;
    }


    @OnClick(R.id.v_shadow)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.v_shadow:
                toggle();
                break;
        }
    }


    @Override
    public void onBegin() {

    }

    @Override
    public void getDetailBean(List<RiverList.DetailBean.RiverListBean> listBeans) {
        if (listBeans.size() != 0) {
            adapter.setNewData(listBeans);
            computeRecyclerViewHeight();
        }

    }

    @Override
    public void onEnd() {

    }
}
