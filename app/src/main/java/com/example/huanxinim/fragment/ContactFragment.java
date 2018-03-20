package com.example.huanxinim.fragment;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseFragment;
import com.example.base.R;
import com.example.bean.ContactListItem;
import com.example.huanxinim.view.ChatActivity;
import com.example.huanxinim.adapter.ContactListAdapter;
import com.example.huanxinim.adapter.EMContactListenerAdapter;
import com.example.huanxinim.presenter.IContactPresenter;
import com.example.huanxinim.presenter.impl.ContactPresenterImpl;
import com.example.huanxinim.view.IContactView;
import com.example.widget.SlideBar;
import com.example.widget.refreshview.CustomRefreshView;
import com.hyphenate.chat.EMClient;

import java.util.List;

import butterknife.BindView;
//通讯录
public class ContactFragment extends BaseFragment implements IContactView {


    @BindView(R.id.titleName)
    TextView toorBar;
    @BindView(R.id.recycler_view)
    CustomRefreshView mRecyclerView;
    @BindView(R.id.slide_bar)
    SlideBar mSlideBar;
    @BindView(R.id.section)
    TextView mSection;
    private static final int POSITION_NOT_FOUND = -1;

    private IContactPresenter mContactPresenter;
    private ContactListAdapter mContactListAdapter;

    public static ContactFragment newInstance() {
        ContactFragment mContactFragment = new ContactFragment();
        return mContactFragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initView(View view) {
        toorBar.setText("通讯录");
        mContactPresenter = new ContactPresenterImpl(this);
        mRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.getRecyclerView().setHasFixedSize(true);
        mContactListAdapter = new ContactListAdapter(getContext(), mContactPresenter.getContactList());
        mContactListAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mContactListAdapter);
        mRecyclerView.setRefreshing(true);
        mRecyclerView.setLoadMoreEnable(false);
        mSlideBar.setOnSlidingBarChangeListener(mOnSlideBarChangeListener);
        EMClient.getInstance().contactManager().setContactListener(mEMContactListener);
        mContactPresenter.getContactsFromServer();
        mRecyclerView.getSwipeRefreshLayout().setOnRefreshListener(mOnRefreshListener);
        mRecyclerView.getRecyclerView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mRecyclerView.isRefreshing()) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private ContactListAdapter.OnItemClickListener mOnItemClickListener = new ContactListAdapter.OnItemClickListener() {
        /**
         * 单击跳转到聊天界面
         */
        @Override
        public void onItemClick(String name) {
            Intent intent = new Intent(getContext(), ChatActivity.class);
            intent.putExtra("user_name", name);
            getActivity().startActivity(intent);
        }
    };

    private EMContactListenerAdapter mEMContactListener = new EMContactListenerAdapter() {

        @Override
        public void onContactAdded(String s) {
            mContactPresenter.refreshContactList();
        }

        @Override
        public void onContactDeleted(String s) {
            mContactPresenter.refreshContactList();
        }
    };

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mContactPresenter.refreshContactList();
        }
    };

    private SlideBar.OnSlideBarChangeListener mOnSlideBarChangeListener = new SlideBar.OnSlideBarChangeListener() {
        @Override
        public void onSectionChange(int index, String section) {
            mSection.setVisibility(View.VISIBLE);
            mSection.setText(section);
            scrollToSection(section);
        }

        @Override
        public void onSlidingFinish() {
            mSection.setVisibility(View.GONE);
        }
    };

    /**
     * RecyclerView滚动直到界面出现对应section的联系人
     *
     * @param section 首字符
     */
    private void scrollToSection(String section) {
        int sectionPosition = getSectionPosition(section);
        if (sectionPosition != POSITION_NOT_FOUND) {
            mRecyclerView.getRecyclerView().smoothScrollToPosition(sectionPosition);
        }
    }

    /**
     * @param section 首字符
     * @return 在联系人列表中首字符是section的第一个联系人在联系人列表中的位置
     */
    private int getSectionPosition(String section) {
        List<ContactListItem> contactListItems = mContactListAdapter.getContactListItems();
        for (int i = 0; i < contactListItems.size(); i++) {
            if (section.equals(contactListItems.get(i).getFirstLetterString())) {
                return i;
            }
        }
        return POSITION_NOT_FOUND;
    }

    @Override
    public void onSuccess() {
        mContactListAdapter.notifyDataSetChanged();
        if (mRecyclerView != null)
            mRecyclerView.complete();
    }

    @Override
    public void onSuccessNoData() {
        if (mRecyclerView != null) {
            mRecyclerView.setEmptyView("没有联系人");
            mRecyclerView.complete();
        }
    }

    @Override
    public void onGetContactListFailed() {
        Toast.makeText(getContext(), "获取联系人失败", Toast.LENGTH_SHORT).show();
        if (mRecyclerView != null) {
            mRecyclerView.setErrorView();
            mRecyclerView.complete();
        }
    }

    @Override
    public void onDestroy() {
        EMClient.getInstance().contactManager().removeContactListener(mEMContactListener);
        super.onDestroy();
    }
}
