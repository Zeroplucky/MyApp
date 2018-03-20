package com.example.okgo_http.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/3/20.
 */

public class GangRightBean implements Parcelable, MultiItemEntity {

    private String name;
    private String imgsrc;
    private String cacode;

    private int isTitle;

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

    public int getIsTitle() {
        return isTitle;
    }

    public void setIsTitle(int isTitle) {
        this.isTitle = isTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imgsrc);
        dest.writeString(this.cacode);
        dest.writeInt(this.isTitle);
    }

    public GangRightBean() {
    }

    public GangRightBean(String name, int isTitle) {
        this.name = name;
        this.isTitle = isTitle;
    }

    protected GangRightBean(Parcel in) {
        this.name = in.readString();
        this.imgsrc = in.readString();
        this.cacode = in.readString();
        this.isTitle = in.readInt();
    }

    public static final Parcelable.Creator<GangRightBean> CREATOR = new Parcelable.Creator<GangRightBean>() {
        @Override
        public GangRightBean createFromParcel(Parcel source) {
            return new GangRightBean(source);
        }

        @Override
        public GangRightBean[] newArray(int size) {
            return new GangRightBean[size];
        }
    };

    @Override
    public int getItemType() {
        return isTitle;
    }
}
