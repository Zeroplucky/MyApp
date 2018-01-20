package com.example.bean;

import com.example.base.R;

/**
 * Created by Administrator on 2017/12/15.
 */

public class ContactListItem {

    public int avatar = R.drawable.icon_touxiang01;

    public String userName;

    public boolean showFirstLetter = true;

    public char getFirstLetter() {
        return userName.charAt(0);
    }

    public String getFirstLetterString() {
        return String.valueOf(getFirstLetter()).toUpperCase();
    }
}
