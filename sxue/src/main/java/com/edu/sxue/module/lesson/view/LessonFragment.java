package com.edu.sxue.module.lesson.view;

import com.edu.sxue.R;
import com.edu.sxue.databinding.FragmentLessonBinding;
import com.edu.sxue.injector.component.DaggerLessonFragmentComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseFragment;
import com.edu.sxue.module.base.FragmentAdapter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/6/4.
 */

public class LessonFragment extends BaseFragment<FragmentLessonBinding> {
    @Inject
    AppBar mAppBar;
    @Inject
    FragmentAdapter mFragmentAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_lesson;
    }

    @Override
    protected void initInjector() {
        DaggerLessonFragmentComponent.builder().appComponent(getAppComponent()).fragmentComponent(getFragmentComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.setTitle("课程");
        mAppBar.showLeft(false);
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.vpView.setAdapter(mFragmentAdapter);
        mBinding.tablayout.setupWithViewPager(mBinding.vpView);

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
