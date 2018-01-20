package com.example.huanxinim.presenter.impl;

import com.example.bean.ContactListItem;
import com.example.huanxinim.presenter.IContactPresenter;
import com.example.huanxinim.view.IContactView;
import com.example.utils.ThreadUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public class ContactPresenterImpl implements IContactPresenter {


    private IContactView mContactView;

    private List<ContactListItem> mContactListItems;

    public ContactPresenterImpl(IContactView contactView) {
        mContactView = contactView;
        mContactListItems = new ArrayList<ContactListItem>();
    }


    @Override
    public void getContactsFromServer() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    startGetContactList();
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (getContactList().size() != 0) {
                                mContactView.onSuccess();
                            } else {
                                mContactView.onSuccessNoData();
                            }

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mContactView.onGetContactListFailed();
                        }
                    });
                }
            }
        });
    }

    /**
     * 获取联系人列表数据
     *
     * @throws HyphenateException
     */
    private void startGetContactList() throws HyphenateException {
        List<String> contacts = EMClient.getInstance().contactManager().getAllContactsFromServer();
        Collections.sort(contacts, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.charAt(0) - o2.charAt(0);
            }
        });
        if (!contacts.isEmpty()) {
            for (int i = 0; i < contacts.size(); i++) {
                ContactListItem item = new ContactListItem();
                item.userName = contacts.get(i);
                if (itemInSameGroup(i, item)) {
                    item.showFirstLetter = false;
                }
                mContactListItems.add(item);
            }
        }
    }

    /**
     * 当前联系人跟上个联系人比较，如果首字符相同则返回true
     *
     * @param i    当前联系人下标
     * @param item 当前联系人数据模型
     * @return true 表示当前联系人和上一联系人在同一组
     */
    private boolean itemInSameGroup(int i, ContactListItem item) {
        return i > 0 && (item.getFirstLetter() == mContactListItems.get(i - 1).getFirstLetter());
    }

    @Override
    public List<ContactListItem> getContactList() {
        return mContactListItems;
    }

    @Override
    public void refreshContactList() {
        mContactListItems.clear();
        getContactsFromServer();
    }
}
