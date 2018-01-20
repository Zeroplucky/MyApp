package com.example.aac.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GR-GZ-ACER on 2017/12/23.
 */

public class AdInfo extends SectionEntity {

    private int type;
    private DetailBean detail;
    private String title;
    private String content;

    public AdInfo(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public AdInfo(Object o) {
        super(o);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class DetailBean {

        private int status;
        private List<TownBean> adList;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<TownBean> getAdList() {
            return adList;
        }

        public void setAdList(List<TownBean> adList) {
            this.adList = adList;
        }

        public static class TownBean implements Parcelable {

            private String code;
            private String name;
            private String pcode;
            private String level;
            private List<TownBean> childAds;
            @Expose(serialize = false,deserialize = false)
            private boolean select;

            public TownBean() {
            }

            public TownBean(String name, boolean select) {
                this.name = name;
                this.select = select;
                childAds = new ArrayList<>();
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPcode() {
                return pcode;
            }

            public void setPcode(String pcode) {
                this.pcode = pcode;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public List<TownBean> getChildAds() {
                return childAds;
            }

            public void setChildAds(List<TownBean> childAds) {
                this.childAds = childAds;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.code);
                dest.writeString(this.name);
                dest.writeString(this.pcode);
                dest.writeString(this.level);
                dest.writeList(this.childAds);
                dest.writeByte(this.select ? (byte) 1 : (byte) 0);
            }

            protected TownBean(Parcel in) {
                this.code = in.readString();
                this.name = in.readString();
                this.pcode = in.readString();
                this.level = in.readString();
                this.childAds = new ArrayList<TownBean>();
                in.readList(this.childAds, TownBean.class.getClassLoader());
                this.select = in.readByte() != 0;
            }

            public static final Creator<TownBean> CREATOR = new Creator<TownBean>() {
                @Override
                public TownBean createFromParcel(Parcel source) {
                    return new TownBean(source);
                }

                @Override
                public TownBean[] newArray(int size) {
                    return new TownBean[size];
                }
            };
        }
    }
}
