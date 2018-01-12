package com.edu.sxue.module.organ.view.info;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityOrganHyzzBinding;
import com.edu.sxue.injector.component.DaggerOrganActivityComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.organ.viewmodel.OrganHyzzViewModel;

import javax.inject.Inject;

public class OrganHyzzActivity extends BaseActivity<ActivityOrganHyzzBinding> {
    @Inject
    OrganHyzzViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_organ_hyzz;
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
        mBinding.rvHyzz.setAdapter(mViewModel.getAdapter());
    }


    @Override
    protected void updateViews(boolean isRefresh) {

    }
}