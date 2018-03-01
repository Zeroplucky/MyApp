package com.example.okgo_http;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.R;
import com.example.base.mvpframe.factory.CreatePresenter;
import com.example.base.mvpframe.view.BaseMvpAppCompatActivity;
import com.example.bean.HeadlineBean;
import com.example.okgo_http.mvp.Demo1Presenter;
import com.example.okgo_http.mvp.IDemo1View;
import com.example.widget.TaoHeadline;
import com.lzy.okgo.model.Progress;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


@CreatePresenter(Demo1Presenter.class)
public class Demo1Activity extends BaseMvpAppCompatActivity<IDemo1View, Demo1Presenter> implements IDemo1View {


    @BindView(R.id.file_path)
    TextView filePath;
    @BindView(R.id.text_requst)
    Button textRequst;
    @BindView(R.id.toor_bar)
    TextView toorBar;
    @BindView(R.id.tao_header)
    TaoHeadline taoHeader;
    @BindView(R.id.text)
    TextView textView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_demo1;
    }

    @Override
    protected void initView() {
        super.initView();
        //rxpermissions1 的用法。
//        RxPermissions rxPermissions = new RxPermissions(this);
//        RxView.clicks(findViewById(R.id.file_path))
//                .compose(rxPermissions.ensureEach(Manifest.permission.WRITE_EXTERNAL_STORAGE))
//                .subscribe(new Action1<Permission>() {
//                    @Override
//                    public void call(Permission permission) {
//                        Log.e(TAG, "Permission result " + permission);
//                        if (permission.granted) {
//                            getMvpPresenter().downLoadFile(mContext);
//                        } else {
//                            Toast.makeText(mContext, "没有下载权限", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
        //rxpermissions2 的用法
        new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            Toast.makeText(mContext, "相关权限被拒，部分功能不可使用", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

        //
        List<HeadlineBean> data = new ArrayList<>();
        data.add(new HeadlineBean("热门", "袜子裤子只要998"));
        data.add(new HeadlineBean("推荐", "韩流服饰，一折起"));
        data.add(new HeadlineBean("好货", "品牌二手车"));
        data.add(new HeadlineBean("省钱", "游戏鼠标键盘套装"));
        taoHeader.setData(data);

        taoHeader.setHeadlineClickListener(new TaoHeadline.HeadlineClickListener() {
            @Override
            public void onHeadlineClick(HeadlineBean bean) {
                Toast.makeText(mContext, "" + bean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = BaseApp.getRefWatcher(this);
//        refWatcher.watch(this);
    }

    @OnClick({R.id.text_requst, R.id.file_path})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_requst:
                startActivity(new Intent(mContext, Demo2Activity.class));
                break;
            case R.id.file_path:
                getMvpPresenter().downLoadFile(mContext);
                break;
        }
    }


    @Override
    public void onProgress(Progress progress) {
        filePath.setText(progress.currentSize * 100 / progress.totalSize + "%");
    }

    @Override
    public void success(File file) {
        String absolutePath = file.getAbsolutePath();
        Log.e("xxx", "success: ----" + absolutePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(mContext, "com.example.base.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }


    @Override
    public void onstart() {
        filePath.setText("开始下载");
    }

    @Override
    public void onFinish() {
        filePath.setText("点击下载apk");
    }

    @Override
    public void weather(String s) {
        textView.setText(s);
    }

}
