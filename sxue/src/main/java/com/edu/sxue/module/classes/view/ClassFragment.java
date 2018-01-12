package com.edu.sxue.module.classes.view;

import android.support.v4.app.Fragment;

import com.edu.sxue.R;
import com.edu.sxue.databinding.FragmentClassBinding;
import com.edu.sxue.injector.component.DaggerClassFragmentComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseFragment;
import com.edu.sxue.module.base.FragmentPagerAdapter;
import com.edu.sxue.utils.NavBarUtils;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * 王少岩 在 2017/10/17 创建了它
 */

public class ClassFragment extends BaseFragment<FragmentClassBinding> {
    @Inject
    AppBar mAppBar;
    @Inject
    ArrayList<String> mTitles;
    @Inject
    ArrayList<Fragment> mFragments;
    @Inject
    FragmentPagerAdapter mPagerAdapter;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_class;
    }

    @Override
    protected void initInjector() {
        DaggerClassFragmentComponent.builder().appComponent(getAppComponent()).fragmentComponent(getFragmentComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.setTitle("上课");
        mAppBar.showLeft(false);
        mBinding.appbar.setAppbar(mAppBar);
        mTitles.add("未报名上课");
        mTitles.add("已报名上课");
        mFragments.add(ClassListFragment.newIntance("0"));
        mFragments.add(ClassListFragment.newIntance("1"));
        mBinding.vpView.setAdapter(mPagerAdapter);
        NavBarUtils.setTabs(mBinding.magicIndicator, mTitles, mBinding.vpView);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
