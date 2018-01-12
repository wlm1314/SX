package com.edu.sxue.module.home.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.edu.sxue.R;
import com.edu.sxue.module.classes.view.ClassFragment;
import com.edu.sxue.module.exercise.view.ExerciseFragment;
import com.edu.sxue.module.lesson.view.LessonFragment;
import com.edu.sxue.module.organ.view.OrganFragment;
import com.edu.sxue.module.user.main.view.UserFragment;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */

public class HomeViewModel {
    private FragmentActivity mHomeActivity;
    private List<Fragment> mFragments = new ArrayList<>();
    /**
     * fragments
     */
    private LessonFragment mLessonFragment = new LessonFragment();
    private OrganFragment mOrganFragment = new OrganFragment();
    private ExerciseFragment mExerciseFragment = new ExerciseFragment();
    private UserFragment mUserFragment = new UserFragment();
    private ClassFragment mClassFragment=new ClassFragment();

    /**
     * data
     */
    public final ObservableField<Integer> check_index = new ObservableField<>();

    //command
    public ReplyCommand lessonCommand = new ReplyCommand(() ->
            switchPages(0));
    public ReplyCommand organCommand = new ReplyCommand(() ->
            switchPages(1));
    public ReplyCommand classCommand = new ReplyCommand(() ->
            switchPages(2));
    public ReplyCommand exerciseCommand = new ReplyCommand(() ->
            switchPages(3));
    public ReplyCommand myCommand = new ReplyCommand(() ->
            switchPages(4));

    public HomeViewModel(Activity activity) {
        mHomeActivity = (FragmentActivity) activity;
        check_index.set(0);
        mFragments.add(mLessonFragment);
        mFragments.add(mOrganFragment);
        mFragments.add(mClassFragment);
        mFragments.add(mExerciseFragment);
        mFragments.add(mUserFragment);
        switchPages(0);
    }

    private void switchPages(int index) {
        FragmentManager fragmentManager = mHomeActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        for (int i = 0, j = mFragments.size(); i < j; i++) {
            if (i == index) {
                continue;
            }
            fragment = mFragments.get(i);
            if (fragment.isAdded()) {
                fragmentTransaction.hide(fragment);
            }
        }
        fragment = mFragments.get(index);
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.main_container, fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}
