package com.example.superfileview.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.base.R;
import com.example.superfileview.widget.SuperFileView2;
import com.example.utils.Logger;
import com.example.utils.Md5Tool;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;

public class FileDisplayActivity extends AppCompatActivity {

    private String TAG = "xxx";
    SuperFileView2 mSuperFileView;
    String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_display_);
        init();
    }


    public void init() {
        mSuperFileView = (SuperFileView2) findViewById(R.id.mSuperFileView);
        mSuperFileView.setOnGetFilePathListener(new SuperFileView2.OnGetFilePathListener() {
            @Override
            public void onGetFilePath(SuperFileView2 mSuperFileView2) {
                getFilePathAndShowFile(mSuperFileView2);
            }
        });

        Intent intent = this.getIntent();
        String path = (String) intent.getSerializableExtra("path");

        if (!TextUtils.isEmpty(path)) {
            Logger.d(TAG, "文件path:" + path);
            setFilePath(path);
        }
        mSuperFileView.show();

    }

    private void getFilePathAndShowFile(SuperFileView2 mSuperFileView2) {
        if (getFilePath().contains("http")) {//网络地址要先下载
            downLoadFromNet(getFilePath(), mSuperFileView2);
        } else {
            mSuperFileView2.displayFile(new File(getFilePath()));
        }
    }

    public void setFilePath(String fileUrl) {
        this.filePath = fileUrl;
    }

    private String getFilePath() {
        return filePath;
    }


    private void downLoadFromNet(final String url, final SuperFileView2 mSuperFileView2) {
        //1.网络下载、存储路径、
        File cacheFile = getCacheFile(url);
        if (cacheFile.exists()) {
            if (cacheFile.length() <= 0) {
                Logger.d(TAG, "删除空文件！！");
                cacheFile.delete();
                return;
            }
        }
        OkGo.<File>get(url)
                .execute(new FileCallback() {
                    @Override
                    public void onSuccess(Response<File> response) {
                        File fileN = response.body();
                        mSuperFileView2.displayFile(fileN);
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                    }
                });

    }


    /***
     * 绝对路径获取缓存文件
     */
    private File getCacheFile(String url) {
        File cacheFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/007/"
                + getFileName(url));
        Logger.d(TAG, "缓存文件 = " + cacheFile.toString());
        return cacheFile;
    }

    /***
     * 根据链接获取文件名（带类型的），具有唯一性
     */
    private String getFileName(String url) {
        String fileName = Md5Tool.hashKey(url) + "." + getFileType(url);
        return fileName;
    }

    /***
     * 获取文件类型
     */
    private String getFileType(String paramString) {
        String str = "";
        if (TextUtils.isEmpty(paramString)) {
            Logger.d(TAG, "paramString---->null");
            return str;
        }
        Logger.d(TAG, "paramString:" + paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            Logger.d(TAG, "i <= -1");
            return str;
        }
        str = paramString.substring(i + 1);
        Logger.d(TAG, "paramString.substring(i + 1)------>" + str);
        return str;
    }

    public static void show(Context context, String url) {
        Intent intent = new Intent(context, FileDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("path", url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("FileDisplayActivity-->onDestroy");
        if (mSuperFileView != null) {
            mSuperFileView.onStopDisplay();
        }
    }

}
