package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.user.parent.viewmodel.UserParentViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/7/13 创建了它
 */
@Module
public class UserParentModule {

    @PerActivity
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("我的家人", true);
    }

    @PerActivity
    @Provides
    public UserParentViewModel provideViewModel(RequestApi api, RxBus rxBus) {
        return new UserParentViewModel(api, rxBus);
    }
}
