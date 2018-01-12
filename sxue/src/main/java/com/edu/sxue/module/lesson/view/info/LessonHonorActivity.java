package com.edu.sxue.module.lesson.view.info;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityLessonHonorBinding;
import com.edu.sxue.injector.component.DaggerLessonActivityComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.lesson.model.LessonBean;
import com.edu.sxue.module.lesson.viewmodel.LessonHonorViewModel;

import javax.inject.Inject;

public class LessonHonorActivity extends BaseActivity<ActivityLessonHonorBinding> {
    @Inject
    AppBar mAppBar;
    @Inject
    LessonHonorViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_lesson_honor;
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
        mBinding.rvLessonHonor.setAdapter(mViewModel.getAdapter());
        mViewModel.getData(lessonBean.id);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

}

