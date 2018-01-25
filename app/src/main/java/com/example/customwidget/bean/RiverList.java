package com.example.customwidget.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by GR-GZ-ACER on 2017/12/29.
 */

public class RiverList {

    private int type;
    private DetailBean detail;
    private String title;
    private String content;

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
        private List<RiverListBean> riverList;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<RiverListBean> getRiverList() {
            return riverList;
        }

        public void setRiverList(List<RiverListBean> riverList) {
            this.riverList = riverList;
        }

        public static class RiverListBean implements Parcelable {

            private String rv_code;
            private String rv_name;
            private String idx;

            public String getIdx() {
                return idx;
            }

            public void setIdx(String idx) {
                this.idx = idx;
            }

            @Expose(serialize = false,deserialize = false)
            private boolean select;

            public RiverListBean() {
            }

            public RiverListBean(String rv_name, boolean select) {
                this.rv_name = rv_name;
                this.select = select;
            }

            public String getRv_code() {
                return rv_code;
            }

            public void setRv_code(String rv_code) {
                this.rv_code = rv_code;
            }

            public String getRv_name() {
                return rv_name;
            }

            public void setRv_name(String rv_name) {
                this.rv_name = rv_name;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.rv_code);
                dest.writeString(this.rv_name);
                dest.writeString(this.idx);
                dest.writeByte(this.select ? (byte) 1 : (byte) 0);
            }

            protected RiverListBean(Parcel in) {
                this.rv_code = in.readString();
                this.rv_name = in.readString();
                this.idx = in.readString();
                this.select = in.readByte() != 0;
            }

            public static final Creator<RiverListBean> CREATOR = new Creator<RiverListBean>() {
                @Override
                public RiverListBean createFromParcel(Parcel source) {
                    return new RiverListBean(source);
                }

                @Override
                public RiverListBean[] newArray(int size) {
                    return new RiverListBean[size];
                }
            };
        }
    }
}
