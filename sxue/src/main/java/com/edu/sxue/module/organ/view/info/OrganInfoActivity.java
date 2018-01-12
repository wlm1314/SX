package com.edu.sxue.module.organ.view.info;

import android.os.Bundle;
import android.view.View;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityOrganInfoBinding;
import com.edu.sxue.injector.component.DaggerOrganActivityComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.organ.viewmodel.OrganInfoViewModel;

import javax.inject.Inject;

import base.lib.util.NavigateUtils;

/**
 * 王少岩 在 2017/7/17 创建了它
 */

public class OrganInfoActivity extends BaseActivity<ActivityOrganInfoBinding> implements View.OnClickListener {
    private String course_id;
    private String course_catname;
    @Inject
    OrganInfoViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_organ_info;
    }

    @Override
    protected void initInjector() {
        DaggerOrganActivityComponent.builder().appComponent(getAppComponent()).activityComponent(getActivityComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        course_id = getIntent().getStringExtra("id");
        course_catname = getIntent().getStringExtra("name");
        mBinding.appbar.setAppbar(new AppBar(course_catname, true));
        mBinding.setViewModel(mViewModel);
        setOnClickListener(this, mBinding.layoutHyzz, mBinding.layoutJghj, mBinding.layoutJgjs, mBinding.layoutSzll, mBinding.layoutKctb);

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.layout_jgjs:
                bundle.putString("name", course_catname);
                bundle.putString("id", course_id);
                NavigateUtils.startActivity(OrganDesActivity.class, bundle);
                break;
            case R.id.layout_kctb:
                bundle.putString("name", course_catname);
                bundle.putString("id", course_id);
                NavigateUtils.startActivity(OrganLessonsActivity.class, bundle);
                break;
            case R.id.layout_szll:
                bundle.putString("name", course_catname);
                bundle.putString("id", course_id);
                NavigateUtils.startActivity(OrganTeacherActivity.class, bundle);
                break;
            case R.id.layout_jghj:
                bundle.putString("name", course_catname);
                bundle.putString("id", course_id);
                NavigateUtils.startActivity(OrganEnvironmentActivity.class, bundle);
                break;
            case R.id.layout_hyzz:
                bundle.putString("name", course_catname);
                bundle.putString("id", course_id);
                NavigateUtils.startActivity(OrganHyzzActivity.class, bundle);
                break;
        }
    }
}
