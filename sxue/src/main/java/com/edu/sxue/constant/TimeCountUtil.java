package com.edu.sxue.constant;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/27.
 */
public class TimeCountUtil extends CountDownTimer {
    private TextView textView;

    public TimeCountUtil(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // 按钮不可用
        textView.setEnabled(false);
        String showText = millisUntilFinished / 1000 + "秒后可重新发送";
        textView.setText(showText);
    }

    @Override
    public void onFinish() {
        // 按钮设置可用
        textView.setEnabled(true);
        textView.setText("获取验证码");
    }
}
