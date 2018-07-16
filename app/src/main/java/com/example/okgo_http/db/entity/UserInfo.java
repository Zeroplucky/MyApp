package com.example.okgo_http.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/6/13.
 */


@Entity
public class UserInfo {

    @Property(nameInDb = "ID")
    @Id(autoincrement = true)
    private Long id;

    private String NUMBER;
    private String RIGHTS;
    private String TX; //用户图片
    private String SFZ;
    private String IP;
    private String PHONE; //手机
    private String SFZTP1;
    private String USER_ID;//id
    private String SFZTP2;
    private String LAST_LOGIN;
    private String EMAIL; //邮箱
    private String NAME; //昵称
    private String STATUS;
    private String PASSWORD;//密码
    private String XM;
    private String SFZTP3;
    private String BZ;
    private String USERNAME;//用户名
    private String ROLE_ID;
    private String SKIN;
    private String JF;//积分
    @Generated(hash = 1861298716)
    public UserInfo(Long id, String NUMBER, String RIGHTS, String TX, String SFZ,
            String IP, String PHONE, String SFZTP1, String USER_ID, String SFZTP2,
            String LAST_LOGIN, String EMAIL, String NAME, String STATUS,
            String PASSWORD, String XM, String SFZTP3, String BZ, String USERNAME,
            String ROLE_ID, String SKIN, String JF) {
        this.id = id;
        this.NUMBER = NUMBER;
        this.RIGHTS = RIGHTS;
        this.TX = TX;
        this.SFZ = SFZ;
        this.IP = IP;
        this.PHONE = PHONE;
        this.SFZTP1 = SFZTP1;
        this.USER_ID = USER_ID;
        this.SFZTP2 = SFZTP2;
        this.LAST_LOGIN = LAST_LOGIN;
        this.EMAIL = EMAIL;
        this.NAME = NAME;
        this.STATUS = STATUS;
        this.PASSWORD = PASSWORD;
        this.XM = XM;
        this.SFZTP3 = SFZTP3;
        this.BZ = BZ;
        this.USERNAME = USERNAME;
        this.ROLE_ID = ROLE_ID;
        this.SKIN = SKIN;
        this.JF = JF;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNUMBER() {
        return this.NUMBER;
    }
    public void setNUMBER(String NUMBER) {
        this.NUMBER = NUMBER;
    }
    public String getRIGHTS() {
        return this.RIGHTS;
    }
    public void setRIGHTS(String RIGHTS) {
        this.RIGHTS = RIGHTS;
    }
    public String getTX() {
        return this.TX;
    }
    public void setTX(String TX) {
        this.TX = TX;
    }
    public String getSFZ() {
        return this.SFZ;
    }
    public void setSFZ(String SFZ) {
        this.SFZ = SFZ;
    }
    public String getIP() {
        return this.IP;
    }
    public void setIP(String IP) {
        this.IP = IP;
    }
    public String getPHONE() {
        return this.PHONE;
    }
    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
    public String getSFZTP1() {
        return this.SFZTP1;
    }
    public void setSFZTP1(String SFZTP1) {
        this.SFZTP1 = SFZTP1;
    }
    public String getUSER_ID() {
        return this.USER_ID;
    }
    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
    public String getSFZTP2() {
        return this.SFZTP2;
    }
    public void setSFZTP2(String SFZTP2) {
        this.SFZTP2 = SFZTP2;
    }
    public String getLAST_LOGIN() {
        return this.LAST_LOGIN;
    }
    public void setLAST_LOGIN(String LAST_LOGIN) {
        this.LAST_LOGIN = LAST_LOGIN;
    }
    public String getEMAIL() {
        return this.EMAIL;
    }
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }
    public String getNAME() {
        return this.NAME;
    }
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    public String getSTATUS() {
        return this.STATUS;
    }
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
    public String getPASSWORD() {
        return this.PASSWORD;
    }
    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
    public String getXM() {
        return this.XM;
    }
    public void setXM(String XM) {
        this.XM = XM;
    }
    public String getSFZTP3() {
        return this.SFZTP3;
    }
    public void setSFZTP3(String SFZTP3) {
        this.SFZTP3 = SFZTP3;
    }
    public String getBZ() {
        return this.BZ;
    }
    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
    public String getUSERNAME() {
        return this.USERNAME;
    }
    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }
    public String getROLE_ID() {
        return this.ROLE_ID;
    }
    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }
    public String getSKIN() {
        return this.SKIN;
    }
    public void setSKIN(String SKIN) {
        this.SKIN = SKIN;
    }
    public String getJF() {
        return this.JF;
    }
    public void setJF(String JF) {
        this.JF = JF;
    }

}
