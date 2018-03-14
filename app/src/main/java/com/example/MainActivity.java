package com.example;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aac.AACActivity;
import com.example.base.BaseActivity;
import com.example.base.BaseApp;
import com.example.base.R;
import com.example.coordinators.CoordinatorActivity;
import com.example.customwidget.CustomActivity;
import com.example.huanxinim.LoginActivity;
import com.example.okgo_http.Demo1Activity;
import com.example.superfileview.SuperFileViewActivity;
import com.example.tencentqq.QQActivity;
import com.example.video.VideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.grid_menu)
    GridView gridView;
    private MainMenuAdapter adapter;
    private int[] menuIcons = {
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example};
    private int[] menuText = {
            R.string.demo0,
            R.string.demo1,
            R.string.demo2,
            R.string.demo3,
            R.string.demo4,
            R.string.demo5,
            R.string.demo6,
            R.string.demo7
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        adapter = new MainMenuAdapter(this, menuIcons, menuText);
        gridView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(mContext, Demo1Activity.class);
                        break;
                    case 1:
                        intent = new Intent(mContext, VideoActivity.class);
                        break;
                    case 2:
                        intent = new Intent(mContext, LoginActivity.class);
                        break;
                    case 3:
                        intent = new Intent(mContext, CoordinatorActivity.class);
                        break;
                    case 4:
                        intent = new Intent(mContext, CustomActivity.class);
                        break;
                    case 5:
                        intent = new Intent(mContext, SuperFileViewActivity.class);
                        break;
                    case 6:
                        intent = new Intent(mContext, AACActivity.class);
                        break;
                    case 7:
                        intent = new Intent(mContext, QQActivity.class);
                        break;
                }
                if (intent == null) {
                    return;
                }
                startActivity(intent);
            }
        });
    }


    class MainMenuAdapter extends BaseAdapter {

        Context mContext;
        int[] menuIcons;
        int[] menuText;

        public MainMenuAdapter(Context mContext, int[] menuIcons, int[] menuText) {
            this.mContext = mContext;
            this.menuIcons = menuIcons;
            this.menuText = menuText;
        }

        @Override
        public int getCount() {
            int sw = BaseApp.getWidth();
            int scale = 4;
            if (sw > 1080) {
                scale = 5;
            }
            int num = menuText.length % scale;
            return num == 0 ? menuText.length : menuText.length + (scale - num);
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.main_menu_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position < menuText.length) {
                holder.imageIcon.setImageResource(menuIcons[position]);
                holder.textMenu.setText(menuText[position]);
            }
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.image_icon)
            ImageView imageIcon;
            @BindView(R.id.text_menu)
            TextView textMenu;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
