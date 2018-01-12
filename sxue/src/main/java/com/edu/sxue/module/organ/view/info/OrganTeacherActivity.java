package com.edu.sxue.module.organ.view.info;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityOrganTeacherBinding;
import com.edu.sxue.injector.component.DaggerOrganActivityComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.organ.viewmodel.OrganTeacherViewModel;

import javax.inject.Inject;

/**
 * 王少岩 在 2017/10/16 创建了它
 */

public class OrganTeacherActivity extends BaseActivity<ActivityOrganTeacherBinding> {
    @Inject
    AppBar mAppBar;
    @Inject
    OrganTeacherViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_organ_teacher;
    }

    @Override
    protected void initInjector() {
        DaggerOrganActivityComponent.builder().appComponent(getAppComponent()).activityComponent(getActivityComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.setTitle(getIntent().getStringExtra("name"));
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.setViewModel(mViewModel);
        mViewModel.getData(getIntent().getStringExtra("id"));
        mBinding.rvOrganTeacher.setAdapter(mViewModel.getAdapter());
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
