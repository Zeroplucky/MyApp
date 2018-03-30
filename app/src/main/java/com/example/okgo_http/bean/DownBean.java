package com.example.okgo_http.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/30.
 */

public class DownBean {

    /**
     * labelNames : [{"name":"官方","type":0},{"name":"无广告","type":0},{"name":"无病毒","type":0},{"name":"人工复检","type":0}]
     * stars : 5
     * labels : ["http://appimg.hicloud.com/hwmarket/files/appLabel/ccfcb74e8e0a46fa8a6be107eca45656_51H.png",
     * "http://appimg.hicloud.com/hwmarket/files/appLabel/novirus_51H.png",
     * "http://appimg.hicloud.com/hwmarket/files/appLabel/noad_51H.png"]
     * name : 微博
     * ctype : 0
     * icoUri : http://appimg.hicloud.com/hwmarket/files/application/icon144/522110c8bbf745b0afb6768c59291b3c.png
     * exIcons : {}
     * supportWatch : 0
     * safeLabels : [{"detectorDesc":"检测专员","name":"人工复检 √","detectorName":"泽仁","url":"http://appimg.hicloud.com/hwmarket/files/detectorInfoIcon/052eb0a78f5d4a65a1823b476921abdf/Round1472265966058.jpg"},{"name":"华为安全检测 √","url":"http://appimg.hicloud.com/hwmarket/files/safe/hw.png"},{"name":"金山安全检测 √","url":"http://appimg.hicloud.com/hwmarket/files/safe/js.png"},{"name":"腾讯手机管家安全检测 √","url":"http://appimg.hicloud.com/hwmarket/files/safe/tx.png"}]
     * intro : 13.2亿次安装
     */

    private int stars;
    private String name;
    private int ctype;
    private String icoUri;
    private ExIconsBean exIcons;
    private int supportWatch;
    private String intro;
    private List<LabelNamesBean> labelNames;
    private List<String> labels;
    private List<SafeLabelsBean> safeLabels;

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCtype() {
        return ctype;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    public String getIcoUri() {
        return icoUri;
    }

    public void setIcoUri(String icoUri) {
        this.icoUri = icoUri;
    }

    public ExIconsBean getExIcons() {
        return exIcons;
    }

    public void setExIcons(ExIconsBean exIcons) {
        this.exIcons = exIcons;
    }

    public int getSupportWatch() {
        return supportWatch;
    }

    public void setSupportWatch(int supportWatch) {
        this.supportWatch = supportWatch;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public List<LabelNamesBean> getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(List<LabelNamesBean> labelNames) {
        this.labelNames = labelNames;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<SafeLabelsBean> getSafeLabels() {
        return safeLabels;
    }

    public void setSafeLabels(List<SafeLabelsBean> safeLabels) {
        this.safeLabels = safeLabels;
    }

    public static class ExIconsBean {
    }

    public static class LabelNamesBean {
        /**
         * name : 官方
         * type : 0
         */

        private String name;
        private int type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class SafeLabelsBean {
        /**
         * detectorDesc : 检测专员
         * name : 人工复检 √
         * detectorName : 泽仁
         * url : http://appimg.hicloud.com/hwmarket/files/detectorInfoIcon/052eb0a78f5d4a65a1823b476921abdf/Round1472265966058.jpg
         */

        private String detectorDesc;
        private String name;
        private String detectorName;
        private String url;

        public String getDetectorDesc() {
            return detectorDesc;
        }

        public void setDetectorDesc(String detectorDesc) {
            this.detectorDesc = detectorDesc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetectorName() {
            return detectorName;
        }

        public void setDetectorName(String detectorName) {
            this.detectorName = detectorName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
