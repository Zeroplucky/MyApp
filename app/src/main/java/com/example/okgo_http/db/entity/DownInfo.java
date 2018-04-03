package com.example.okgo_http.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/4/2.
 */


@Entity
public class DownInfo {
    @Id(autoincrement = true)
    private Long id;

    private String url;

    /*存储位置*/
    @Property
    private String savePath;
    /*文件总长度*/
    @Property
    private long countLength;
    /*下载长度*/
    @Property
    private long readLength;

    @Generated(hash = 713897535)
    public DownInfo(Long id, String url, String savePath, long countLength,
            long readLength) {
        this.id = id;
        this.url = url;
        this.savePath = savePath;
        this.countLength = countLength;
        this.readLength = readLength;
    }

    @Generated(hash = 928324469)
    public DownInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public long getCountLength() {
        return this.countLength;
    }

    public void setCountLength(long countLength) {
        this.countLength = countLength;
    }

    public long getReadLength() {
        return this.readLength;
    }

    public void setReadLength(long readLength) {
        this.readLength = readLength;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
