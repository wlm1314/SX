package com.edu.sxue.injector.module;

import android.support.v4.app.FragmentManager;

import com.edu.sxue.injector.PerFragment;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.FragmentAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Administrator 在 2017/6/4 创建了它.
 */
@Module
public class ExerciseModule {

    private FragmentManager mFragmentManager;

    public ExerciseModule(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    @PerFragment
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("活动", false);
    }

    @PerFragment
    @Provides
    public FragmentAdapter provideAdapter() {
        return new FragmentAdapter(mFragmentManager, 2);
    }
}
