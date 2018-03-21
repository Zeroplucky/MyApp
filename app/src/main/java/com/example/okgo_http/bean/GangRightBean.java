package com.example.okgo_http.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/3/20.
 */

public class GangRightBean implements Parcelable, MultiItemEntity {

    private String titleName;
    private String name;
    private String imgsrc;
    private String cacode;
    private String tag;
    private int type;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    private boolean isTitle;

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getCacode() {
        return cacode;
    }

    public void setCacode(String cacode) {
        this.cacode = cacode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public GangRightBean(String titleName, String name, String tag, int type, boolean isTitle) {
        this.name = name;
        this.titleName = titleName;
        this.tag = tag;
        this.type = type;
        this.isTitle = isTitle;
    }

    @Override
    public int getItemType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.titleName);
        dest.writeString(this.imgsrc);
        dest.writeString(this.cacode);
        dest.writeInt(this.type);
        dest.writeByte(this.isTitle ? (byte) 1 : (byte) 0);
        dest.writeString(tag);
    }

    protected GangRightBean(Parcel in) {
        this.name = in.readString();
        this.titleName = in.readString();
        this.imgsrc = in.readString();
        this.cacode = in.readString();
        this.type = in.readInt();
        this.isTitle = in.readByte() != 0;
        this.tag = in.readString();
    }

    public static final Creator<GangRightBean> CREATOR = new Creator<GangRightBean>() {
        @Override
        public GangRightBean createFromParcel(Parcel source) {
            return new GangRightBean(source);
        }

        @Override
        public GangRightBean[] newArray(int size) {
            return new GangRightBean[size];
        }
    };
}
