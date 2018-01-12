package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.user.profile.viewmodel.UserProfileViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/7/13 创建了它
 */
@Module
public class UserProfileModule {

    @PerActivity
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("个人资料", true);
    }

    @PerActivity
    @Provides
    public UserProfileViewModel provideViewModel(RequestApi api, RxBus rxBus) {
        return new UserProfileViewModel(api, rxBus);
    }
}
