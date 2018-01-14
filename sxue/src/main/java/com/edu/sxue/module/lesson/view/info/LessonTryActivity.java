package com.edu.sxue.module.lesson.view.info;

import android.support.v4.app.Fragment;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityLessonTryClassBinding;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.base.FragmentPagerAdapter;
import com.edu.sxue.module.classes.view.ClassListFragment;
import com.edu.sxue.utils.NavBarUtils;

import java.util.ArrayList;

/**
 * Administrator 在 2018/1/14 创建了它.
 */

public class LessonTryActivity extends BaseActivity<ActivityLessonTryClassBinding> {
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_lesson_try_class;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        AppBar appBar = new AppBar("报名试听", true);
        appBar.navigation.set(R.mipmap.ic_arrow_left_white);
        mBinding.appbar.setAppbar(appBar);
        mTitles.add("未报名试听");
        mTitles.add("已报名试听");
        mFragments.add(ClassListFragment.newIntance("0", "try"));
        mFragments.add(ClassListFragment.newIntance("1", "try"));
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mTitles, mFragments);
        mBinding.vpView.setAdapter(mPagerAdapter);
        NavBarUtils.setTabs(mBinding.magicIndicator, mTitles, mBinding.vpView);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
