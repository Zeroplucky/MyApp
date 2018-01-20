package com.example.huanxinim.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.R;
import com.example.bean.ContactListItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/15.
 */

public class ContactListItemView extends RelativeLayout {

    @BindView(R.id.section)
    TextView mSection;
    @BindView(R.id.user_name)
    TextView mUserName;

    public ContactListItemView(Context context) {
        this(context, null);
    }

    public ContactListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_contact_item, this);
        ButterKnife.bind(this, this);

    }

    public void bindView(ContactListItem contactListItem) {
        mUserName.setText(contactListItem.userName);
        if (contactListItem.showFirstLetter) {
            mSection.setVisibility(VISIBLE);
            mSection.setText(contactListItem.getFirstLetterString());
        } else {
            mSection.setVisibility(GONE);
        }
    }
}
