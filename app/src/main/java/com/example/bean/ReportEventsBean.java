package com.example.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */

public class ReportEventsBean implements Serializable {


    /**
     * type : 1
     * detail : {"success":[{"enterprise_type":"4","code":"564379263","city":"654002","latitude":"43.934722","county":"654002100","scale":"2","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"伊宁市","GDP":"714500.000000","waste_water_discharge":"2303141.000000","water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"硫氰酸红梅毒2670吨","waterZone":"伊宁市伊犁河","sewageplant":null,"longitude":"81.165556","industry_name":"化学药品原料药制造","address":"新疆伊犁州霍尔果斯经济开发区伊宁园区阿拉木图亚村516号","legal_per":"邓旭衡","basin":"伊宁市伊犁河","county_name":"英也尔乡","pollutant":null,"sewageplant_name":null,"phone":"0999-8077777","channel_distance":null,"pollutant_name":null,"waste_water_produce":"2303141.000000","idx":"19","enterprise_name":"伊犁川宁生物技术有限公司","risk_level_1":null},{"enterprise_type":"6","code":"560542369","city":"654021","latitude":"44.229167","county":"654021202","scale":"5","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"654021","GDP":"11000.000000","waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"硫酸93%43909吨","waterZone":null,"sewageplant":null,"longitude":"81.520278","industry_name":"金矿采选","address":"新疆伊犁州伊宁县卡拉亚尕奇乡新疆阿希金矿矿区内","legal_per":"刘朝辉","basin":"皮里其沟","county_name":"喀拉亚尕奇乡","pollutant":null,"sewageplant_name":null,"phone":"0999-4253005","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"23","enterprise_name":"西部黄金伊犁有限责任公司选矿厂 ","risk_level_1":null},{"enterprise_type":"1","code":"099978745","city":"654028","latitude":"43.709722","county":"654028404","scale":"5","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"尼勒克县","GDP":"5000.000000","waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"焦油6476吨","waterZone":null,"sewageplant":null,"longitude":"83.284722","industry_name":"炼焦","address":"新疆伊犁州尼勒克县布拉克工业园区","legal_per":"朱金卫","basin":"尼勒克喀什河","county_name":"种蜂场","pollutant":null,"sewageplant_name":"","phone":"0999-4850226","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"24","enterprise_name":"尼勒克县瑞祥焦化有限责任公司 ","risk_level_1":null},{"enterprise_type":"4","code":"568851641","city":"654002","latitude":"43.931944","county":"654002200","scale":"3","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"伊宁市","GDP":null,"waste_water_discharge":"361926.000000","water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"L-笨丙氨酸3278吨","waterZone":null,"sewageplant":"4","longitude":"81.111111","industry_name":"其他调味品、发酵制品制造","address":"伊宁市英也尔乡六七段","legal_per":"许小初","basin":"伊宁市伊犁河","county_name":"英也尔乡","pollutant":null,"sewageplant_name":"伊宁市西区污水处理厂","phone":"0999-8077859","channel_distance":null,"pollutant_name":null,"waste_water_produce":"361926.000000","idx":"34","enterprise_name":"新疆苏源生物工程有限公司","risk_level_1":null},{"enterprise_type":"1","code":"722330853","city":"654002","latitude":"43.943611","county":"654002200","scale":"6","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"伊宁市","GDP":null,"waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"油墨12吨","waterZone":null,"sewageplant":"4","longitude":"81.273333","industry_name":"包装装潢及其他印刷","address":"上海路286号","legal_per":"宋崇东","basin":"伊犁河伊宁段","county_name":"英也尔乡","pollutant":null,"sewageplant_name":"伊宁市西区污水处理厂","phone":"0999-8136889","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"35","enterprise_name":"伊犁伊力特印务有限责任公司","risk_level_1":null},{"enterprise_type":"5","code":"722364797","city":"654002","latitude":"43.946389","county":"654002200","scale":"6","industry":"1","enterprise_type_name":null,"scale_name":null,"city_name":"伊宁市","GDP":null,"waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"润滑油溜分油6.5吨","waterZone":null,"sewageplant":"4","longitude":"81.279722","industry_name":"城市轨道交通设备制造","address":"辽宁路1389号","legal_per":"王帅","basin":"伊犁河伊宁段","county_name":"英也尔乡","pollutant":null,"sewageplant_name":"伊宁市西区污水处理厂","phone":"0999-8122920","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"36","enterprise_name":"伊犁天阳农业机械设备有限责任公司","risk_level_1":null},{"enterprise_type":"1","code":"230571888","city":"654021","latitude":"43.943889","county":"654021100","scale":"6","industry":"1","enterprise_type_name":null,"scale_name":null,"city_name":"伊宁县","GDP":null,"waste_water_discharge":"860.000000","water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"甲醛2800吨、尿素100吨","waterZone":null,"sewageplant":null,"longitude":"81.839444","industry_name":"纤维板制造","address":"伊吉公路13号","legal_per":"兰东利","basin":"伊宁县伊犁河","county_name":"吉里于孜镇","pollutant":null,"sewageplant_name":null,"phone":"0999-4050988","channel_distance":null,"pollutant_name":null,"waste_water_produce":"860.000000","idx":"37","enterprise_name":"伊犁华蓝木业有限公司","risk_level_1":null},{"enterprise_type":"6","code":"748685361","city":"654021","latitude":"44.325000","county":"654021202","scale":"5","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"654021","GDP":null,"waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"氰化钠577.17吨、盐酸69.76吨","waterZone":null,"sewageplant":null,"longitude":"81.525000","industry_name":"金矿采选","address":null,"legal_per":"潘剑云","basin":"小吉尔格朗河","county_name":"喀拉亚尕奇乡","pollutant":null,"sewageplant_name":null,"phone":"0999-7863335","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"38","enterprise_name":"新疆金川矿业有限公司","risk_level_1":null},{"enterprise_type":"6","code":"715564811","city":"654022","latitude":"43.711389","county":"654022203","scale":"5","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"654022","GDP":null,"waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"硫酸锌50吨、硫酸铜21吨","waterZone":null,"sewageplant":null,"longitude":"81.174722","industry_name":"铅锌矿采选","address":"纳达齐乡伊昭公路","legal_per":"石磊","basin":"伊犁河察布查尔段","county_name":"纳达齐牛录乡","pollutant":null,"sewageplant_name":null,"phone":"0999-3630061","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"39","enterprise_name":"察布查尔县金威铅锌矿业有限责任公司","risk_level_1":null},{"enterprise_type":"1","code":"75165753X","city":"654023","latitude":"40.165000","county":"654023101","scale":"5","industry":"1","enterprise_type_name":null,"scale_name":null,"city_name":"霍城县","GDP":null,"waste_water_discharge":"315320.000000","water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"硫磺71643万吨","waterZone":null,"sewageplant":"4","longitude":"80.743333","industry_name":"铅锌矿采选","address":"清水河村","legal_per":"朱殿德","basin":"霍城伊犁河","county_name":"清水河镇","pollutant":null,"sewageplant_name":"伊宁市西区污水处理厂","phone":"0999-6591010","channel_distance":null,"pollutant_name":null,"waste_water_produce":"315320.000000","idx":"40","enterprise_name":"伊犁恒辉淀粉有限公司","risk_level_1":null}],"status":1}
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
         * success : [{"enterprise_type":"4","code":"564379263","city":"654002","latitude":"43.934722","county":"654002100","scale":"2","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"伊宁市","GDP":"714500.000000","waste_water_discharge":"2303141.000000","water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"硫氰酸红梅毒2670吨","waterZone":"伊宁市伊犁河","sewageplant":null,"longitude":"81.165556","industry_name":"化学药品原料药制造","address":"新疆伊犁州霍尔果斯经济开发区伊宁园区阿拉木图亚村516号","legal_per":"邓旭衡","basin":"伊宁市伊犁河","county_name":"英也尔乡","pollutant":null,"sewageplant_name":null,"phone":"0999-8077777","channel_distance":null,"pollutant_name":null,"waste_water_produce":"2303141.000000","idx":"19","enterprise_name":"伊犁川宁生物技术有限公司","risk_level_1":null},{"enterprise_type":"6","code":"560542369","city":"654021","latitude":"44.229167","county":"654021202","scale":"5","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"654021","GDP":"11000.000000","waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"硫酸93%43909吨","waterZone":null,"sewageplant":null,"longitude":"81.520278","industry_name":"金矿采选","address":"新疆伊犁州伊宁县卡拉亚尕奇乡新疆阿希金矿矿区内","legal_per":"刘朝辉","basin":"皮里其沟","county_name":"喀拉亚尕奇乡","pollutant":null,"sewageplant_name":null,"phone":"0999-4253005","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"23","enterprise_name":"西部黄金伊犁有限责任公司选矿厂 ","risk_level_1":null},{"enterprise_type":"1","code":"099978745","city":"654028","latitude":"43.709722","county":"654028404","scale":"5","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"尼勒克县","GDP":"5000.000000","waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"焦油6476吨","waterZone":null,"sewageplant":null,"longitude":"83.284722","industry_name":"炼焦","address":"新疆伊犁州尼勒克县布拉克工业园区","legal_per":"朱金卫","basin":"尼勒克喀什河","county_name":"种蜂场","pollutant":null,"sewageplant_name":"","phone":"0999-4850226","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"24","enterprise_name":"尼勒克县瑞祥焦化有限责任公司 ","risk_level_1":null},{"enterprise_type":"4","code":"568851641","city":"654002","latitude":"43.931944","county":"654002200","scale":"3","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"伊宁市","GDP":null,"waste_water_discharge":"361926.000000","water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"L-笨丙氨酸3278吨","waterZone":null,"sewageplant":"4","longitude":"81.111111","industry_name":"其他调味品、发酵制品制造","address":"伊宁市英也尔乡六七段","legal_per":"许小初","basin":"伊宁市伊犁河","county_name":"英也尔乡","pollutant":null,"sewageplant_name":"伊宁市西区污水处理厂","phone":"0999-8077859","channel_distance":null,"pollutant_name":null,"waste_water_produce":"361926.000000","idx":"34","enterprise_name":"新疆苏源生物工程有限公司","risk_level_1":null},{"enterprise_type":"1","code":"722330853","city":"654002","latitude":"43.943611","county":"654002200","scale":"6","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"伊宁市","GDP":null,"waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"油墨12吨","waterZone":null,"sewageplant":"4","longitude":"81.273333","industry_name":"包装装潢及其他印刷","address":"上海路286号","legal_per":"宋崇东","basin":"伊犁河伊宁段","county_name":"英也尔乡","pollutant":null,"sewageplant_name":"伊宁市西区污水处理厂","phone":"0999-8136889","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"35","enterprise_name":"伊犁伊力特印务有限责任公司","risk_level_1":null},{"enterprise_type":"5","code":"722364797","city":"654002","latitude":"43.946389","county":"654002200","scale":"6","industry":"1","enterprise_type_name":null,"scale_name":null,"city_name":"伊宁市","GDP":null,"waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"润滑油溜分油6.5吨","waterZone":null,"sewageplant":"4","longitude":"81.279722","industry_name":"城市轨道交通设备制造","address":"辽宁路1389号","legal_per":"王帅","basin":"伊犁河伊宁段","county_name":"英也尔乡","pollutant":null,"sewageplant_name":"伊宁市西区污水处理厂","phone":"0999-8122920","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"36","enterprise_name":"伊犁天阳农业机械设备有限责任公司","risk_level_1":null},{"enterprise_type":"1","code":"230571888","city":"654021","latitude":"43.943889","county":"654021100","scale":"6","industry":"1","enterprise_type_name":null,"scale_name":null,"city_name":"伊宁县","GDP":null,"waste_water_discharge":"860.000000","water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"甲醛2800吨、尿素100吨","waterZone":null,"sewageplant":null,"longitude":"81.839444","industry_name":"纤维板制造","address":"伊吉公路13号","legal_per":"兰东利","basin":"伊宁县伊犁河","county_name":"吉里于孜镇","pollutant":null,"sewageplant_name":null,"phone":"0999-4050988","channel_distance":null,"pollutant_name":null,"waste_water_produce":"860.000000","idx":"37","enterprise_name":"伊犁华蓝木业有限公司","risk_level_1":null},{"enterprise_type":"6","code":"748685361","city":"654021","latitude":"44.325000","county":"654021202","scale":"5","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"654021","GDP":null,"waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"氰化钠577.17吨、盐酸69.76吨","waterZone":null,"sewageplant":null,"longitude":"81.525000","industry_name":"金矿采选","address":null,"legal_per":"潘剑云","basin":"小吉尔格朗河","county_name":"喀拉亚尕奇乡","pollutant":null,"sewageplant_name":null,"phone":"0999-7863335","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"38","enterprise_name":"新疆金川矿业有限公司","risk_level_1":null},{"enterprise_type":"6","code":"715564811","city":"654022","latitude":"43.711389","county":"654022203","scale":"5","industry":"2","enterprise_type_name":null,"scale_name":null,"city_name":"654022","GDP":null,"waste_water_discharge":null,"water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"硫酸锌50吨、硫酸铜21吨","waterZone":null,"sewageplant":null,"longitude":"81.174722","industry_name":"铅锌矿采选","address":"纳达齐乡伊昭公路","legal_per":"石磊","basin":"伊犁河察布查尔段","county_name":"纳达齐牛录乡","pollutant":null,"sewageplant_name":null,"phone":"0999-3630061","channel_distance":null,"pollutant_name":null,"waste_water_produce":null,"idx":"39","enterprise_name":"察布查尔县金威铅锌矿业有限责任公司","risk_level_1":null},{"enterprise_type":"1","code":"75165753X","city":"654023","latitude":"40.165000","county":"654023101","scale":"5","industry":"1","enterprise_type_name":null,"scale_name":null,"city_name":"霍城县","GDP":null,"waste_water_discharge":"315320.000000","water_cost":null,"basin_name":null,"risk_level_name_1":null,"main_risk":"硫磺71643万吨","waterZone":null,"sewageplant":"4","longitude":"80.743333","industry_name":"铅锌矿采选","address":"清水河村","legal_per":"朱殿德","basin":"霍城伊犁河","county_name":"清水河镇","pollutant":null,"sewageplant_name":"伊宁市西区污水处理厂","phone":"0999-6591010","channel_distance":null,"pollutant_name":null,"waste_water_produce":"315320.000000","idx":"40","enterprise_name":"伊犁恒辉淀粉有限公司","risk_level_1":null}]
         * status : 1
         */

        private int status;
        private List<SuccessBean> success;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<SuccessBean> getSuccess() {
            return success;
        }

        public void setSuccess(List<SuccessBean> success) {
            this.success = success;
        }

        public static class SuccessBean {
            /**
             * enterprise_type : 4
             * code : 564379263
             * city : 654002
             * latitude : 43.934722
             * county : 654002100
             * scale : 2
             * industry : 2
             * enterprise_type_name : null
             * scale_name : null
             * city_name : 伊宁市
             * GDP : 714500.000000
             * waste_water_discharge : 2303141.000000
             * water_cost : null
             * basin_name : null
             * risk_level_name_1 : null
             * main_risk : 硫氰酸红梅毒2670吨
             * waterZone : 伊宁市伊犁河
             * sewageplant : null
             * longitude : 81.165556
             * industry_name : 化学药品原料药制造
             * address : 新疆伊犁州霍尔果斯经济开发区伊宁园区阿拉木图亚村516号
             * legal_per : 邓旭衡
             * basin : 伊宁市伊犁河
             * county_name : 英也尔乡
             * pollutant : null
             * sewageplant_name : null
             * phone : 0999-8077777
             * channel_distance : null
             * pollutant_name : null
             * waste_water_produce : 2303141.000000
             * idx : 19
             * enterprise_name : 伊犁川宁生物技术有限公司
             * risk_level_1 : null
             */

            private String enterprise_type;
            private String code;
            private String city;
            private String latitude;
            private String county;
            private String scale;
            private String industry;
            private Object enterprise_type_name;
            private Object scale_name;
            private String city_name;
            private String GDP;
            private String waste_water_discharge;
            private Object water_cost;
            private Object basin_name;
            private Object risk_level_name_1;
            private String main_risk;
            private String waterZone;
            private Object sewageplant;
            private String longitude;
            private String industry_name;
            private String address;
            private String legal_per;
            private String basin;
            private String county_name;
            private Object pollutant;
            private Object sewageplant_name;
            private String phone;
            private Object channel_distance;
            private Object pollutant_name;
            private String waste_water_produce;
            private String idx;
            private String enterprise_name;
            private Object risk_level_1;

            public String getEnterprise_type() {
                return enterprise_type;
            }

            public void setEnterprise_type(String enterprise_type) {
                this.enterprise_type = enterprise_type;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getCounty() {
                return county;
            }

            public void setCounty(String county) {
                this.county = county;
            }

            public String getScale() {
                return scale;
            }

            public void setScale(String scale) {
                this.scale = scale;
            }

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public Object getEnterprise_type_name() {
                return enterprise_type_name;
            }

            public void setEnterprise_type_name(Object enterprise_type_name) {
                this.enterprise_type_name = enterprise_type_name;
            }

            public Object getScale_name() {
                return scale_name;
            }

            public void setScale_name(Object scale_name) {
                this.scale_name = scale_name;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public String getGDP() {
                return GDP;
            }

            public void setGDP(String GDP) {
                this.GDP = GDP;
            }

            public String getWaste_water_discharge() {
                return waste_water_discharge;
            }

            public void setWaste_water_discharge(String waste_water_discharge) {
                this.waste_water_discharge = waste_water_discharge;
            }

            public Object getWater_cost() {
                return water_cost;
            }

            public void setWater_cost(Object water_cost) {
                this.water_cost = water_cost;
            }

            public Object getBasin_name() {
                return basin_name;
            }

            public void setBasin_name(Object basin_name) {
                this.basin_name = basin_name;
            }

            public Object getRisk_level_name_1() {
                return risk_level_name_1;
            }

            public void setRisk_level_name_1(Object risk_level_name_1) {
                this.risk_level_name_1 = risk_level_name_1;
            }

            public String getMain_risk() {
                return main_risk;
            }

            public void setMain_risk(String main_risk) {
                this.main_risk = main_risk;
            }

            public String getWaterZone() {
                return waterZone;
            }

            public void setWaterZone(String waterZone) {
                this.waterZone = waterZone;
            }

            public Object getSewageplant() {
                return sewageplant;
            }

            public void setSewageplant(Object sewageplant) {
                this.sewageplant = sewageplant;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getIndustry_name() {
                return industry_name;
            }

            public void setIndustry_name(String industry_name) {
                this.industry_name = industry_name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLegal_per() {
                return legal_per;
            }

            public void setLegal_per(String legal_per) {
                this.legal_per = legal_per;
            }

            public String getBasin() {
                return basin;
            }

            public void setBasin(String basin) {
                this.basin = basin;
            }

            public String getCounty_name() {
                return county_name;
            }

            public void setCounty_name(String county_name) {
                this.county_name = county_name;
            }

            public Object getPollutant() {
                return pollutant;
            }

            public void setPollutant(Object pollutant) {
                this.pollutant = pollutant;
            }

            public Object getSewageplant_name() {
                return sewageplant_name;
            }

            public void setSewageplant_name(Object sewageplant_name) {
                this.sewageplant_name = sewageplant_name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getChannel_distance() {
                return channel_distance;
            }

            public void setChannel_distance(Object channel_distance) {
                this.channel_distance = channel_distance;
            }

            public Object getPollutant_name() {
                return pollutant_name;
            }

            public void setPollutant_name(Object pollutant_name) {
                this.pollutant_name = pollutant_name;
            }

            public String getWaste_water_produce() {
                return waste_water_produce;
            }

            public void setWaste_water_produce(String waste_water_produce) {
                this.waste_water_produce = waste_water_produce;
            }

            public String getIdx() {
                return idx;
            }

            public void setIdx(String idx) {
                this.idx = idx;
            }

            public String getEnterprise_name() {
                return enterprise_name;
            }

            public void setEnterprise_name(String enterprise_name) {
                this.enterprise_name = enterprise_name;
            }

            public Object getRisk_level_1() {
                return risk_level_1;
            }

            public void setRisk_level_1(Object risk_level_1) {
                this.risk_level_1 = risk_level_1;
            }
        }
    }
}
