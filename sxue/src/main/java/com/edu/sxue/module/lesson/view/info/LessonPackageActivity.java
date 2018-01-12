package com.edu.sxue.module.lesson.view.info;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityLessonPackageBinding;
import com.edu.sxue.injector.component.DaggerLessonActivityComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.lesson.model.LessonBean;
import com.edu.sxue.module.lesson.viewmodel.LessonPackageViewModel;

import javax.inject.Inject;

public class LessonPackageActivity extends BaseActivity<ActivityLessonPackageBinding> {
    @Inject
    AppBar mAppBar;
    @Inject
    LessonPackageViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_lesson_package;
    }

    @Override
    protected void initInjector() {
        DaggerLessonActivityComponent.builder().appComponent(getAppComponent()).activityComponent(getActivityComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        LessonBean lessonBean = (LessonBean) getIntent().getSerializableExtra("lesson");
        mAppBar.setTitle(lessonBean.name);
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.setViewModel(mViewModel);
        mBinding.rvLessonPackage.setAdapter(mViewModel.getAdapter());
        mViewModel.getData(lessonBean.id);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

