package com.example;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.view.WindowManager;

import com.bolex.autoEx.AutoEx;
import com.example.base.BuildConfig;
import com.example.okgo_http.db.utils.DBUtils;
import com.example.utils.Logger;
import com.example.utils.NetworkUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.tencent.smtt.sdk.QbSdk;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

import static com.huawei.android.pushselfshow.richpush.html.HtmlViewer.TAG;

/**
 * Created by Administrator on 2017/12/11.
 */

public class BaseApp extends MultiDexApplication {


    static WindowManager mWindowManager;
    static Context mAppContext;
//    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        AutoEx.apply();
        super.onCreate();
        mAppContext = this;
//        BlockCanary.install(this, new AppContext()).start();
//        refWatcher = setupLeakCanary();
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        initOkGo();
        initHuanXin();
        initX5();


        NetworkUtils.init(this);
        Logger.enable();

        initDb();
    }

    private void initDb() {
        DBUtils.initDataBase(getAppContext());
    }

    private void initX5() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。

            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }


    private void initHuanXin() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(true);
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        if (processAppName == null || !processAppName.equalsIgnoreCase(getPackageName())) {
            Log.e(TAG, "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        //初始化
        EMClient.getInstance().init(getApplicationContext(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        if (BuildConfig.DEBUG) {
            EMClient.getInstance().setDebugMode(true);
        }
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("xxxx");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.SEVERE);                   //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build());               //建议设置OkHttpClient，不设置会使用默认的
    }


    public static int getWidth() {
        return mWindowManager.getDefaultDisplay().getWidth();
    }

//    private RefWatcher setupLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return RefWatcher.DISABLED;
//        }
//        return LeakCanary.install(this);
//    }

//    public static RefWatcher getRefWatcher(Context context) {
//        BaseApp leakApplication = (BaseApp) context.getApplicationContext();
//        return leakApplication.refWatcher;
//    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
