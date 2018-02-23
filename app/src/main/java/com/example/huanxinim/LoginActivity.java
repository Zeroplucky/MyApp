package com.example.huanxinim;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.base.R;
import com.example.huanxinim.presenter.ILoginPresenter;
import com.example.huanxinim.presenter.impl.LoginPresenterImpl;
import com.example.huanxinim.view.ILoginView;
import com.example.huanxinim.view.IMActivity;
import com.example.huanxinim.widget.PSEditText;
import com.example.widget.WaitingView;
import com.jaeger.library.StatusBarUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.password)
    PSEditText mPassword;
    @BindView(R.id.login)
    TextView mLogin;
    @BindView(R.id.new_user)
    TextView mNewUser;
    private ILoginPresenter mLoginPresenter;
    private AutoRelativeLayout root;
    private TextView iv;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setColor(this,getResources().getColor(R.color.status_bar_color2));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
        }
        mLoginPresenter = new LoginPresenterImpl(this);
        root = (AutoRelativeLayout) findViewById(R.id.root_rl);
        iv = (TextView) findViewById(R.id.iv);
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View focus = root.findFocus();
                ScrollView sv = ((ScrollView) findViewById(R.id.sv));
                sv.fullScroll(ScrollView.FOCUS_DOWN);
                if (focus != null && focus instanceof EditText) {//保证滑动之后 焦点依然未变
                    focus.requestFocus();
                }
                int loc[] = new int[2];
                View view = findViewById(R.id.regist_area);
                view.getLocationOnScreen(loc);
                if (getScreenHeight(LoginActivity.this) - loc[1] < Math.abs(getScreenHeight(LoginActivity.this) / 2 - loc[1])) {//无压缩状态
                    view.setVisibility(View.VISIBLE);
                    iv.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.INVISIBLE);

                }
            }
        });
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    @OnClick({R.id.login, R.id.new_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                startLogin();
                break;
            case R.id.new_user:

                break;
        }
    }

    private void startLogin() {
        if (hasWriteExternalStoragePermission()) {
            login();
        } else {
            applyPermission();
        }
    }

    private void login() {
        hideKeyBoard();
        String userName = mUserName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
//        EMClient.getInstance().logout(true);
        mLoginPresenter.login(userName, password);
    }

    private boolean hasWriteExternalStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED;
    }

    private void applyPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }

    /**
     * 申请权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                    login();
                } else {
                    Toast.makeText(mContext, "权限被拒", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onUserNameError() {
        mUserName.setError(getString(R.string.user_name_error));
    }

    @Override
    public void onPasswordError() {
        mPassword.setError(getString(R.string.user_password_error));
    }

    @Override
    public void onStartLogin() {
        WaitingView.startLoading(mContext, "正在登陆");
    }


    @Override
    public void onLoginSuccess() {
        WaitingView.stopLoading();
        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(mContext, IMActivity.class));
        finish();
    }

    @Override
    public void onLoginFailed() {
        WaitingView.stopLoading();
        Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
    }
}
