package com.example.okgo_http.db.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.okgo_http.db.DaoMaster;
import com.example.okgo_http.db.DaoSession;
import com.example.okgo_http.db.DownInfoDao;
import com.example.okgo_http.db.UserInfoDao;

/**
 * Created by Administrator on 2018/4/2.
 */

public class DBUtils {

    private static DaoSession daoSession;
    private static SQLiteDatabase database;

    /**
     * 初始化数据库
     * 建议放在Application中执行
     */
    public static void initDataBase(Context context) {
        //通过DaoMaster的内部类DevOpenHelper，可得到一个SQLiteOpenHelper对象。
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "base.db", null); //数据库名称
        database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static SQLiteDatabase getDatabase() {
        return database;
    }

    public static DownInfoDao getDownInfoDao() {
        return daoSession.getDownInfoDao();
    }

    public static UserInfoDao getUserInfoDao() {
        return daoSession.getUserInfoDao();
    }

}
