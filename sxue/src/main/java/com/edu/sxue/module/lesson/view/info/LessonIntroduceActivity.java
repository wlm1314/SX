package com.edu.sxue.module.lesson.view.info;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityLessonIntroduceBinding;
import com.edu.sxue.injector.component.DaggerLessonActivityComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.lesson.model.LessonBean;
import com.edu.sxue.module.lesson.viewmodel.LessonIntroduceViewModel;

import javax.inject.Inject;


public class LessonIntroduceActivity extends BaseActivity<ActivityLessonIntroduceBinding> {
    @Inject
    AppBar mAppBar;
    @Inject
    LessonIntroduceViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_lesson_introduce;
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
        mViewModel.getData(lessonBean.id);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
