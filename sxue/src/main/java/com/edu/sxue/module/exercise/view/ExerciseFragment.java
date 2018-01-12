package com.edu.sxue.module.exercise.view;

import com.edu.sxue.R;
import com.edu.sxue.databinding.FragmentExerciseBinding;
import com.edu.sxue.injector.component.DaggerExerciseComponent;
import com.edu.sxue.injector.module.ExerciseModule;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseFragment;
import com.edu.sxue.module.base.FragmentAdapter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/6/4.
 */

public class ExerciseFragment extends BaseFragment<FragmentExerciseBinding> {
    @Inject
    AppBar mAppBar;
    @Inject
    FragmentAdapter mFragmentAdapter;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_exercise;
    }

    @Override
    protected void initInjector() {
        DaggerExerciseComponent.builder().exerciseModule(new ExerciseModule(getChildFragmentManager())).build().inject(this);
    }

    @Override
    protected void initViews() {
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.vpView.setAdapter(mFragmentAdapter);
        mBinding.tablayout.setupWithViewPager(mBinding.vpView);

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
