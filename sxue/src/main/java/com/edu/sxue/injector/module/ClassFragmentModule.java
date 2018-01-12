package com.edu.sxue.injector.module;

import android.support.v4.app.Fragment;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerFragment;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.FragmentPagerAdapter;
import com.edu.sxue.module.classes.viewmodel.ClassViewModel;
import com.edu.sxue.rxbus.RxBus;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Administrator 在 2017/6/4 创建了它.
 */
@Module
public class ClassFragmentModule {

    @PerFragment
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("");
    }

    @PerFragment
    @Provides
    ArrayList<String> provideTitles() {
        return new ArrayList<>();
    }

    @PerFragment
    @Provides
    ArrayList<Fragment> provideFragments() {
        return new ArrayList<>();
    }

    @PerFragment
    @Provides
    public FragmentPagerAdapter provideAdapter(Fragment fragment, ArrayList<String> titles, ArrayList<Fragment> fragments) {
        return new FragmentPagerAdapter(fragment.getChildFragmentManager(), titles, fragments);
    }

    @PerFragment
    @Provides
    public ClassViewModel provideViewModel(RequestApi api, RxBus rxBus) {
        return new ClassViewModel(api, rxBus);
    }
}
