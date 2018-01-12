package com.edu.sxue.module.user.updatepwd.view;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityUpdatepwdBinding;
import com.edu.sxue.injector.component.DaggerUpdatePwdCompoent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.updatepwd.viewmodel.UpdatePwdViewModel;

import javax.inject.Inject;

/**
 * 王少岩 在 2017/9/6 创建了它
 */

public class UpdatePwdActivity extends BaseActivity<ActivityUpdatepwdBinding> {
    @Inject
    UpdatePwdViewModel mViewModel;
    @Inject
    AppBar mAppBar;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_updatepwd;
    }

    @Override
    protected void initInjector() {
        DaggerUpdatePwdCompoent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.navigation.set(R.mipmap.ic_arrow_left_white);
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
