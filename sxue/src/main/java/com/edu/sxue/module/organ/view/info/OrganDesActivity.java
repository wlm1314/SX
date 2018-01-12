package com.edu.sxue.module.organ.view.info;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityOrganDesBinding;
import com.edu.sxue.injector.component.DaggerOrganActivityComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.organ.viewmodel.OrganDesViewModel;

import javax.inject.Inject;

public class OrganDesActivity extends BaseActivity<ActivityOrganDesBinding> {
    @Inject
    OrganDesViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_organ_des;
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
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
