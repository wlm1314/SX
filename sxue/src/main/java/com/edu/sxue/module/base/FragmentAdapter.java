package com.edu.sxue.module.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edu.sxue.module.exercise.view.ExerciseListFragment;
import com.edu.sxue.module.lesson.view.LessonListFragment;
import com.edu.sxue.module.organ.view.OrganListFragment;

/**
 * 王少岩 在 2017/7/14 创建了它
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 1;
    private int mFlag;

    public FragmentAdapter(FragmentManager fm, int flag) {
        super(fm);
        this.mFlag = flag;
    }

    @Override
    public Fragment getItem(int position) {
        switch (mFlag) {
            case 0:
                return LessonListFragment.newInstance(position + "");
            case 1:
                return OrganListFragment.newInstance(position + "");
            case 2:
                return ExerciseListFragment.newInstance(position + "");
            default:
                return LessonListFragment.newInstance(position + "");
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "全部";
            case 1:
                return "青年";
            case 2:
                return "成人教育";
            case 3:
                return "管理";
            default:
                return "全部";
        }
    }
}