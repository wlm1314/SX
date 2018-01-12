package com.edu.sxue.module.organ.view.info;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityOrganEnvironmentBinding;
import com.edu.sxue.injector.component.DaggerOrganActivityComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.organ.viewmodel.OrganEnvironmentViewModel;

import javax.inject.Inject;

public class OrganEnvironmentActivity extends BaseActivity<ActivityOrganEnvironmentBinding> {
    @Inject
    OrganEnvironmentViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_organ_environment;
    }

    @Override
    protected void initInjector() {
        DaggerOrganActivityComponent.builder().appComponent(getAppComponent()).activityComponent(getActivityComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mBinding.appbar.setAppbar(new AppBar(getIntent().getStringExtra("name"), true));
        mBinding.setViewModel(mViewModel);
        mViewModel.getData(getIntent().getStringExtra("id"));
        mBinding.rvJghj.setAdapter(mViewModel.getAdapter());
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}