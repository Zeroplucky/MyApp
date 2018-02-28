package com.example.aac.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 */

public class RiverSegment {



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
        private List<RiverSegmentsBean> riverSegments;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<RiverSegmentsBean> getRiverSegments() {
            return riverSegments;
        }

        public void setRiverSegments(List<RiverSegmentsBean> riverSegments) {
            this.riverSegments = riverSegments;
        }

        public static class RiverSegmentsBean extends AbstractExpandableItem<RiverSegmentsBean.ChildBean> implements MultiItemEntity {
            /**
             * rv_code : UBJ102TH000RT0R
             * pcode : UBJ000RT000RT0R
             * rv_name : 北江太和镇段
             * idx : 2
             * child : [{"rv_code":"UBJ102TH212TJ0R","pcode":"UBJ102TH000RT0R","rv_name":"北江塔脚村段","idx":"3"},{"rv_code":"UBJ102TH213GX0R","pcode":"UBJ102TH000RT0R","rv_name":"北江告星村段","idx":"4"}]
             */

            private String rv_code;
            private String pcode;
            private String rv_name;
            private String idx;
            private List<ChildBean> child;

            public String getRv_code() {
                return rv_code;
            }

            public void setRv_code(String rv_code) {
                this.rv_code = rv_code;
            }

            public String getPcode() {
                return pcode;
            }

            public void setPcode(String pcode) {
                this.pcode = pcode;
            }

            public String getRv_name() {
                return rv_name;
            }

            public void setRv_name(String rv_name) {
                this.rv_name = rv_name;
            }

            public String getIdx() {
                return idx;
            }

            public void setIdx(String idx) {
                this.idx = idx;
            }

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public int getItemType() {
                return 0;
            }

            public static class ChildBean implements MultiItemEntity {
                /**
                 * rv_code : UBJ102TH212TJ0R
                 * pcode : UBJ102TH000RT0R
                 * rv_name : 北江塔脚村段
                 * idx : 3
                 */

                private String rv_code;
                private String pcode;
                private String rv_name;
                private String idx;

                public String getRv_code() {
                    return rv_code;
                }

                public void setRv_code(String rv_code) {
                    this.rv_code = rv_code;
                }

                public String getPcode() {
                    return pcode;
                }

                public void setPcode(String pcode) {
                    this.pcode = pcode;
                }

                public String getRv_name() {
                    return rv_name;
                }

                public void setRv_name(String rv_name) {
                    this.rv_name = rv_name;
                }

                public String getIdx() {
                    return idx;
                }

                public void setIdx(String idx) {
                    this.idx = idx;
                }

                @Override
                public int getItemType() {
                    return 1;
                }
            }
        }
    }
}
