package com.edu.sxue.module.home.view;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityHomeBinding;
import com.edu.sxue.injector.component.DaggerHomeComponent;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.home.viewmodel.HomeViewModel;

import javax.inject.Inject;

/**
 * 王少岩 在 2017/6/2 创建了它
 */

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {
    @Inject
    HomeViewModel mHomeViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initInjector() {
        DaggerHomeComponent.builder().activityComponent(getActivityComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mBinding.bottom.setViewModel(mHomeViewModel);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
