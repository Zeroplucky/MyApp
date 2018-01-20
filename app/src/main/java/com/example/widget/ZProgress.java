package com.example.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.R;

public class ZProgress extends Dialog {

    public static final int FADED_ROUND_SPINNER = 0;
    static ZProgress instance;
    private View view;
    private TextView tvMessage;
    private ImageView ivSuccess;
    private ImageView ivFailure;
    private ImageView ivProgressSpinner;
    private AnimationDrawable adProgressSpinner;
    private Context context;

    public static ZProgress getInstance(Context context) {
        instance = new ZProgress(context);
        return instance;
    }

    public ZProgress(Context context) {
        super(context, R.style.DialogTheme);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);
        this.context = context;
        view = getLayoutInflater().inflate(R.layout.__dialog_progress, null);
        tvMessage = (TextView) view.findViewById(R.id.textview_message);
        ivSuccess = (ImageView) view.findViewById(R.id.imageview_success);
        ivFailure = (ImageView) view.findViewById(R.id.imageview_failure);
        ivProgressSpinner = (ImageView) view.findViewById(R.id.imageview_progress_spinner);
        setSpinnerType(FADED_ROUND_SPINNER);
        this.setContentView(view);
    }

    public void setSpinnerType(int spinnerType) {
        switch (spinnerType) {
            default:
                ivProgressSpinner.setImageResource(R.drawable.round_spinner_fade);
        }
        adProgressSpinner = (AnimationDrawable) ivProgressSpinner.getDrawable();
    }

    public void setMessage(String message) {
        tvMessage.setText(message);
    }

    @Override
    public void show() {
        if (!((Activity) context).isFinishing()) {
            super.show();
        } else {
            instance = null;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        ivProgressSpinner.post(new Runnable() {
            @Override
            public void run() {
                adProgressSpinner.start();

            }
        });
    }


}
