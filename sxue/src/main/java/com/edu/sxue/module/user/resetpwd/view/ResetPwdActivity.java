package com.edu.sxue.module.user.resetpwd.view;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityResetpwdBinding;
import com.edu.sxue.injector.component.DaggerResetPwdCompoent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.resetpwd.viewmodel.ResetPwdViewModel;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/6/1.
 */

public class ResetPwdActivity extends BaseActivity<ActivityResetpwdBinding> {
    @Inject
    ResetPwdViewModel mViewModel;
    @Inject
    AppBar mAppBar;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_resetpwd;
    }

    @Override
    protected void initInjector() {
        DaggerResetPwdCompoent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        //mAppBar.setRight("确定", mViewModel.updatePwd);
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
