package com.edu.sxue.module.home.view;


import com.edu.sxue.R;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.databinding.ActivitySplashBinding;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.login.view.LoginActivity;

import base.lib.util.NavigateUtils;
import base.lib.util.PreferencesUtils;
import base.lib.util.RxHelper;
import rx.Subscriber;

/**
 * 王少岩 在 2017/3/10 创建了它
 */

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    private boolean mIsSkip = false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        RxHelper.countdown(3)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        doSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        doSkip();
                    }

                    @Override
                    public void onNext(Integer integer) {
                    }
                });
    }

    private void doSkip() {
        if (!mIsSkip) {
            mIsSkip = true;
            if (PreferencesUtils.getBoolean(Constants.sUser_loginFlag, false)) {
                NavigateUtils.startActivity(HomeActivity.class);
            }else {
                NavigateUtils.startActivity(LoginActivity.class);
            }
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        // 不响应后退键
        return;
    }
}
