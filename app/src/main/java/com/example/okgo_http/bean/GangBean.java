package com.example.okgo_http.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */

public class GangBean implements Parcelable {


    private List<CategoryOneArrayBean> categoryOneArray;

    public List<CategoryOneArrayBean> getCategoryOneArray() {
        return categoryOneArray;
    }

    public void setCategoryOneArray(List<CategoryOneArrayBean> categoryOneArray) {
        this.categoryOneArray = categoryOneArray;
    }

    public static class CategoryOneArrayBean implements Parcelable {
        /**
         * categoryTwoArray : [{"name":"处方药(RX)","imgsrc":"https://121.10.217.171:9002/_ui/desktop/common/cmyy/image/app_tongsufenlei_chufangyao.png","cacode":"chufangyao"},{"name":"非处方(OTC)","imgsrc":"https://121.10.217.171:9002/_ui/desktop/common/cmyy/image/app_tongsufenlei_feichufang.png","cacode":"feichufang"},{"name":"抗生素","imgsrc":"https://121.10.217.171:9002/_ui/desktop/common/cmyy/image/app_tongsufenlei_kangshengsu.png","cacode":"kangshengsu"},{"name":"维生素","imgsrc":"https://121.10.217.171:9002/_ui/desktop/common/cmyy/image/app_tongsufenlei_kangshengsu.png","cacode":"kangshengsu"}]
         * name : 通俗分类
         * imgsrc : https://121.10.217.171:9002/_ui/desktop/common/cmyy/image/app_0.png
         * cacode : tongsufenlei
         */

        private String name;
        private String imgsrc;
        private String cacode;
        private List<CategoryTwoArrayBean> categoryTwoArray;

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

        public List<CategoryTwoArrayBean> getCategoryTwoArray() {
            return categoryTwoArray;
        }

        public void setCategoryTwoArray(List<CategoryTwoArrayBean> categoryTwoArray) {
            this.categoryTwoArray = categoryTwoArray;
        }

        public static class CategoryTwoArrayBean implements Parcelable {
            /**
             * name : 处方药(RX)
             * imgsrc : https://121.10.217.171:9002/_ui/desktop/common/cmyy/image/app_tongsufenlei_chufangyao.png
             * cacode : chufangyao
             */

            private String name;
            private String imgsrc;
            private String cacode;

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.name);
                dest.writeString(this.imgsrc);
                dest.writeString(this.cacode);
            }

            public CategoryTwoArrayBean() {
            }

            protected CategoryTwoArrayBean(Parcel in) {
                this.name = in.readString();
                this.imgsrc = in.readString();
                this.cacode = in.readString();
            }

            public static final Creator<CategoryTwoArrayBean> CREATOR = new Creator<CategoryTwoArrayBean>() {
                @Override
                public CategoryTwoArrayBean createFromParcel(Parcel source) {
                    return new CategoryTwoArrayBean(source);
                }

                @Override
                public CategoryTwoArrayBean[] newArray(int size) {
                    return new CategoryTwoArrayBean[size];
                }
            };
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
            dest.writeList(this.categoryTwoArray);
        }

        public CategoryOneArrayBean() {
        }

        protected CategoryOneArrayBean(Parcel in) {
            this.name = in.readString();
            this.imgsrc = in.readString();
            this.cacode = in.readString();
            this.categoryTwoArray = new ArrayList<CategoryTwoArrayBean>();
            in.readList(this.categoryTwoArray, CategoryTwoArrayBean.class.getClassLoader());
        }

        public static final Creator<CategoryOneArrayBean> CREATOR = new Creator<CategoryOneArrayBean>() {
            @Override
            public CategoryOneArrayBean createFromParcel(Parcel source) {
                return new CategoryOneArrayBean(source);
            }

            @Override
            public CategoryOneArrayBean[] newArray(int size) {
                return new CategoryOneArrayBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.categoryOneArray);
    }

    public GangBean() {
    }

    protected GangBean(Parcel in) {
        this.categoryOneArray = new ArrayList<CategoryOneArrayBean>();
        in.readList(this.categoryOneArray, CategoryOneArrayBean.class.getClassLoader());
    }

    public static final Creator<GangBean> CREATOR = new Creator<GangBean>() {
        @Override
        public GangBean createFromParcel(Parcel source) {
            return new GangBean(source);
        }

        @Override
        public GangBean[] newArray(int size) {
            return new GangBean[size];
        }
    };
}
