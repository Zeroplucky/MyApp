package com.example.superfileview;

import android.Manifest;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.superfileview.view.FileDisplayActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class SuperFileViewActivity extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private String filePath;
    private List<String> datas = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_super_file_view_;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                return new RecyclerView.ViewHolder(LayoutInflater.from(SuperFileViewActivity.this)
                        .inflate(R.layout.super_file_view_list_item, parent, false)) {
                };

            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                holder.itemView.findViewById(R.id.item_root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] perms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        filePath = getFilePath(position);
                        RxPermissions permissions = new RxPermissions(SuperFileViewActivity.this);
                        permissions.request(perms)
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (aBoolean) {
                                            FileDisplayActivity.show(SuperFileViewActivity.this, filePath);
                                        } else {
                                            Toast.makeText(mContext, "需要访问手机存储权限", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });
                ((TextView) holder.itemView.findViewById(R.id.item_tv)).setText(getDatas().get(position));
            }

            @Override
            public int getItemCount() {
                return getDatas().size();
            }
        });

    }


    private List<String> getDatas() {
        if (datas != null && datas.size() > 0) {
            return datas;
        } else {
            datas = new ArrayList<>();
            initDatas();
            return datas;
        }
    }


    private void initDatas() {
        datas.add("网络获取并打开doc文件");
        datas.add("打开本地doc文件");
        datas.add("打开本地txt文件");
        datas.add("打开本地excel文件");
        datas.add("打开本地ppt文件");
        datas.add("打开本地pdf文件");
    }


    private String getFilePath(int position) {
        String path = null;
        switch (position) {
            case 0:
                path = "http://www.hrssgz.gov.cn/bgxz/sydwrybgxz/201101/P020110110748901718161.doc";
                break;
            case 1:
                path = "/storage/emulated/0/test.docx";
                break;
            case 2:
                path = "/storage/emulated/0/test.txt";
                break;
            case 3:
                path = "/storage/emulated/0/test.xlsx";
                break;
            case 4:
                path = "/storage/emulated/0/demo.ppt";
                break;
            case 5:
                path = "/storage/emulated/0/C基础总.pdf";
                break;
        }
        return path;
    }
}
