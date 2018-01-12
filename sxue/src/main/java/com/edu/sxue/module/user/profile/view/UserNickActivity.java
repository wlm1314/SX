package com.edu.sxue.module.user.profile.view;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityUserNickBinding;
import com.edu.sxue.injector.component.DaggerUserNickComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.profile.viewmodel.UserNickViewModel;

import javax.inject.Inject;

/**
 * 王少岩 在 2017/7/21 创建了它
 */

public class UserNickActivity extends BaseActivity<ActivityUserNickBinding> {
    @Inject
    AppBar mAppBar;
    @Inject
    UserNickViewModel mViewModel;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_user_nick;
    }

    @Override
    protected void initInjector() {
        DaggerUserNickComponent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.setRight("确定", mViewModel.commit);
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
