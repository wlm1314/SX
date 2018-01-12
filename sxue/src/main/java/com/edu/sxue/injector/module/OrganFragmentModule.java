package com.edu.sxue.injector.module;

import android.support.v4.app.Fragment;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerFragment;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.FragmentAdapter;
import com.edu.sxue.module.organ.viewmodel.OrganViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/10/12 创建了它
 */
@Module
public class OrganFragmentModule {

    @PerFragment
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("");
    }

    @PerFragment
    @Provides
    public OrganViewModel provideViewModel(RequestApi api, RxBus rxBus) {
        return new OrganViewModel(api, rxBus);
    }

    @PerFragment
    @Provides
    public FragmentAdapter provideAdapter(Fragment fragment) {
        return new FragmentAdapter(fragment.getChildFragmentManager(), 1);
    }
}
