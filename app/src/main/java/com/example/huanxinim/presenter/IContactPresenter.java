package com.example.huanxinim.presenter;

import com.example.bean.ContactListItem;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public interface IContactPresenter {

    void getContactsFromServer();

    List<ContactListItem> getContactList();

    void refreshContactList();

}
