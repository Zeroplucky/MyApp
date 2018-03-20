package com.example.okgo_http.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/3/20.
 */

public class GangRightBean implements Parcelable {

    private String name;
    private String imgsrc;
    private String cacode;

    private boolean isTitle;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imgsrc);
        dest.writeString(this.cacode);
        dest.writeByte(this.isTitle ? (byte) 1 : (byte) 0);
    }

    public GangRightBean() {
    }

    public GangRightBean(String name, boolean isTitle) {
        this.name = name;
        this.isTitle = isTitle;
    }

    protected GangRightBean(Parcel in) {
        this.name = in.readString();
        this.imgsrc = in.readString();
        this.cacode = in.readString();
        this.isTitle = in.readByte() != 0;
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
}
