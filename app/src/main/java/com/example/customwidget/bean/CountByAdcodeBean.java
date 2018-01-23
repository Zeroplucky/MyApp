package com.example.customwidget.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class CountByAdcodeBean {


    /**
     * type : 1
     * detail : {"RvEvCountList":[{"code":"441803102","level":"4","pcode":"441803","cmplNum":"0","totalNum":"713","num":"50","noCmplNum":"0","name":"太和镇","child":[{"code":"441803102201","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"黄坑村"},{"code":"441803102202","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"乐园村"},{"code":"441803102203","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"4","noCmplNum":"0","name":"周田村"},{"code":"441803102211","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"22","noCmplNum":"0","name":"飞水村"},{"code":"441803102212","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"4","noCmplNum":"0","name":"塔脚村"},{"code":"441803102213","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"5","noCmplNum":"0","name":"告星村"},{"code":"441803102217","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"6","noCmplNum":"0","name":"井塘村"},{"code":"441803102218","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新洲村"},{"code":"441803102219","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"白莲村"},{"code":"441803102220","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"万寿村"}]},{"code":"441803105","level":"4","pcode":"441803","cmplNum":"0","totalNum":"1242","num":"13","noCmplNum":"0","name":"山塘镇","child":[{"code":"441803105204","level":"5","pcode":"441803105","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"胜利村"},{"code":"441803105206","level":"5","pcode":"441803105","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"塘湾村"},{"code":"441803105211","level":"5","pcode":"441803105","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新兴村"},{"code":"441803105220","level":"5","pcode":"441803105","cmplNum":"0","totalNum":"23","num":"8","noCmplNum":"0","name":"回正村"}]},{"code":"441803113","level":"4","pcode":"441803","cmplNum":"0","totalNum":"1380","num":"60","noCmplNum":"0","name":"禾云镇","child":[{"code":"441803113002","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"鱼坝社区"},{"code":"441803113203","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"云街村"},{"code":"441803113215","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"16","noCmplNum":"0","name":"坝仔村"},{"code":"441803113216","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"14","noCmplNum":"0","name":"新塘村"},{"code":"441803113220","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"7","noCmplNum":"0","name":"下迳村"},{"code":"441803113227","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新国村"},{"code":"441803113228","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"义合村"},{"code":"441803113208","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"14","noCmplNum":"0","name":"六田村"}]},{"code":"441803112","level":"4","pcode":"441803","cmplNum":"0","totalNum":"1679","num":"91","noCmplNum":"0","name":"龙颈镇","child":[{"code":"441803112205","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"龙北村"},{"code":"441803112206","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"4","noCmplNum":"0","name":"石崇村"},{"code":"441803112207","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"4","noCmplNum":"0","name":"共和村"},{"code":"441803112214","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"12","noCmplNum":"0","name":"珠坑村"},{"code":"441803112215","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"16","noCmplNum":"0","name":"石岗村"},{"code":"441803112248","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"石东村"},{"code":"441803112250","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"6","noCmplNum":"0","name":"黄岗村"},{"code":"441803112253","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"立坑村"},{"code":"441803112254","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"红立村"},{"code":"441803112256","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"7","noCmplNum":"0","name":"南星村"},{"code":"441803112257","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"何东村"},{"code":"441803112259","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"佛市村"},{"code":"441803112262","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"龙东村"},{"code":"441803112264","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"27","noCmplNum":"0","name":"车头村"}]},{"code":"441803117","level":"4","pcode":"441803","cmplNum":"0","totalNum":"1242","num":"32","noCmplNum":"0","name":"浸潭镇","child":[{"code":"441803117203","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"11","noCmplNum":"0","name":"芦苞村"},{"code":"441803117212","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"高华塘村"},{"code":"441803117213","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"7","noCmplNum":"0","name":"留良洞村"},{"code":"441803117216","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"黄田村"},{"code":"441803117217","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新寨村"},{"code":"441803117218","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"6","noCmplNum":"0","name":"独石村"},{"code":"441803117219","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"大湾岗村"},{"code":"441803117220","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新围村"},{"code":"441803117237","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"龙须带水库移民村"}]}],"status":1}
     * title : 操作结果
     * content : 操作成功
     */

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
        /**
         * RvEvCountList : [{"code":"441803102","level":"4","pcode":"441803","cmplNum":"0","totalNum":"713","num":"50","noCmplNum":"0","name":"太和镇","child":[{"code":"441803102201","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"黄坑村"},{"code":"441803102202","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"乐园村"},{"code":"441803102203","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"4","noCmplNum":"0","name":"周田村"},{"code":"441803102211","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"22","noCmplNum":"0","name":"飞水村"},{"code":"441803102212","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"4","noCmplNum":"0","name":"塔脚村"},{"code":"441803102213","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"5","noCmplNum":"0","name":"告星村"},{"code":"441803102217","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"6","noCmplNum":"0","name":"井塘村"},{"code":"441803102218","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新洲村"},{"code":"441803102219","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"白莲村"},{"code":"441803102220","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"万寿村"}]},{"code":"441803105","level":"4","pcode":"441803","cmplNum":"0","totalNum":"1242","num":"13","noCmplNum":"0","name":"山塘镇","child":[{"code":"441803105204","level":"5","pcode":"441803105","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"胜利村"},{"code":"441803105206","level":"5","pcode":"441803105","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"塘湾村"},{"code":"441803105211","level":"5","pcode":"441803105","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新兴村"},{"code":"441803105220","level":"5","pcode":"441803105","cmplNum":"0","totalNum":"23","num":"8","noCmplNum":"0","name":"回正村"}]},{"code":"441803113","level":"4","pcode":"441803","cmplNum":"0","totalNum":"1380","num":"60","noCmplNum":"0","name":"禾云镇","child":[{"code":"441803113002","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"鱼坝社区"},{"code":"441803113203","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"云街村"},{"code":"441803113215","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"16","noCmplNum":"0","name":"坝仔村"},{"code":"441803113216","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"14","noCmplNum":"0","name":"新塘村"},{"code":"441803113220","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"7","noCmplNum":"0","name":"下迳村"},{"code":"441803113227","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新国村"},{"code":"441803113228","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"义合村"},{"code":"441803113208","level":"5","pcode":"441803113","cmplNum":"0","totalNum":"23","num":"14","noCmplNum":"0","name":"六田村"}]},{"code":"441803112","level":"4","pcode":"441803","cmplNum":"0","totalNum":"1679","num":"91","noCmplNum":"0","name":"龙颈镇","child":[{"code":"441803112205","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"龙北村"},{"code":"441803112206","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"4","noCmplNum":"0","name":"石崇村"},{"code":"441803112207","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"4","noCmplNum":"0","name":"共和村"},{"code":"441803112214","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"12","noCmplNum":"0","name":"珠坑村"},{"code":"441803112215","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"16","noCmplNum":"0","name":"石岗村"},{"code":"441803112248","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"石东村"},{"code":"441803112250","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"6","noCmplNum":"0","name":"黄岗村"},{"code":"441803112253","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"立坑村"},{"code":"441803112254","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"红立村"},{"code":"441803112256","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"7","noCmplNum":"0","name":"南星村"},{"code":"441803112257","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"何东村"},{"code":"441803112259","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"佛市村"},{"code":"441803112262","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"龙东村"},{"code":"441803112264","level":"5","pcode":"441803112","cmplNum":"0","totalNum":"23","num":"27","noCmplNum":"0","name":"车头村"}]},{"code":"441803117","level":"4","pcode":"441803","cmplNum":"0","totalNum":"1242","num":"32","noCmplNum":"0","name":"浸潭镇","child":[{"code":"441803117203","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"11","noCmplNum":"0","name":"芦苞村"},{"code":"441803117212","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"高华塘村"},{"code":"441803117213","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"7","noCmplNum":"0","name":"留良洞村"},{"code":"441803117216","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"黄田村"},{"code":"441803117217","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新寨村"},{"code":"441803117218","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"6","noCmplNum":"0","name":"独石村"},{"code":"441803117219","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"大湾岗村"},{"code":"441803117220","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新围村"},{"code":"441803117237","level":"5","pcode":"441803117","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"龙须带水库移民村"}]}]
         * status : 1
         */

        private int status;
        private List<RvEvCountListBean> RvEvCountList;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<RvEvCountListBean> getRvEvCountList() {
            return RvEvCountList;
        }

        public void setRvEvCountList(List<RvEvCountListBean> RvEvCountList) {
            this.RvEvCountList = RvEvCountList;
        }


        public static class RvEvCountListBean extends AbstractExpandableItem<RvEvCountListBean.ChildBean> implements MultiItemEntity {
            /**
             * code : 441803102
             * level : 4
             * pcode : 441803
             * cmplNum : 0
             * totalNum : 713
             * num : 50
             * noCmplNum : 0
             * name : 太和镇
             * child : [{"code":"441803102201","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"黄坑村"},{"code":"441803102202","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"2","noCmplNum":"0","name":"乐园村"},{"code":"441803102203","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"4","noCmplNum":"0","name":"周田村"},{"code":"441803102211","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"22","noCmplNum":"0","name":"飞水村"},{"code":"441803102212","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"4","noCmplNum":"0","name":"塔脚村"},{"code":"441803102213","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"5","noCmplNum":"0","name":"告星村"},{"code":"441803102217","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"6","noCmplNum":"0","name":"井塘村"},{"code":"441803102218","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"新洲村"},{"code":"441803102219","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"1","noCmplNum":"0","name":"白莲村"},{"code":"441803102220","level":"5","pcode":"441803102","cmplNum":"0","totalNum":"23","num":"3","noCmplNum":"0","name":"万寿村"}]
             */

            private String code;
            @SerializedName("level")
            private String levelx;
            private String pcode;
            private String cmplNum;
            private String totalNum;
            private String num;
            private String noCmplNum;
            private String name;
            private List<ChildBean> child;

            public boolean hasChild() {
                return child == null ? false : (child.size() == 0 ? false : true);
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getLevelx() {
                return levelx;
            }

            public void setLevelx(String level) {
                this.levelx = level;
            }

            public String getPcode() {
                return pcode;
            }

            public void setPcode(String pcode) {
                this.pcode = pcode;
            }

            public String getCmplNum() {
                return cmplNum;
            }

            public void setCmplNum(String cmplNum) {
                this.cmplNum = cmplNum;
            }

            public String getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(String totalNum) {
                this.totalNum = totalNum;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getNoCmplNum() {
                return noCmplNum;
            }

            public void setNoCmplNum(String noCmplNum) {
                this.noCmplNum = noCmplNum;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            @Override
            public int getItemType() {
                return 0;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            public static class ChildBean implements MultiItemEntity {
                /**
                 * code : 441803102201
                 * level : 5
                 * pcode : 441803102
                 * cmplNum : 0
                 * totalNum : 23
                 * num : 2
                 * noCmplNum : 0
                 * name : 黄坑村
                 */

                private String code;
                private String level;
                private String pcode;
                private String cmplNum;
                private String totalNum;
                private String num;
                private String noCmplNum;
                private String name;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getPcode() {
                    return pcode;
                }

                public void setPcode(String pcode) {
                    this.pcode = pcode;
                }

                public String getCmplNum() {
                    return cmplNum;
                }

                public void setCmplNum(String cmplNum) {
                    this.cmplNum = cmplNum;
                }

                public String getTotalNum() {
                    return totalNum;
                }

                public void setTotalNum(String totalNum) {
                    this.totalNum = totalNum;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public String getNoCmplNum() {
                    return noCmplNum;
                }

                public void setNoCmplNum(String noCmplNum) {
                    this.noCmplNum = noCmplNum;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                @Override
                public int getItemType() {
                    return 1;
                }
            }
        }
    }
}
