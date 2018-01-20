package com.example.widget;

import android.content.Context;

public class WaitingView {

    private static ZProgress progressDialog;
    private static Context mContext;

    public static void startLoading(Context mContext) {
        WaitingView.mContext = mContext;
        if (progressDialog == null || progressDialog.getWindow() == null) {
            progressDialog = new ZProgress(WaitingView.mContext);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        try {
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startLoading(Context mContext, String msg) {
        WaitingView.mContext = mContext;
        if (progressDialog == null || progressDialog.getWindow() == null) {
            progressDialog = new ZProgress(WaitingView.mContext);
            progressDialog.setMessage(msg);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        try {
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopLoading() {
        WaitingView.mContext = null;
        if (progressDialog != null && progressDialog.getWindow() != null) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        progressDialog = null;
    }

}
