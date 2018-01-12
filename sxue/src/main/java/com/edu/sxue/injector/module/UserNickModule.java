package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.user.profile.viewmodel.UserNickViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/7/13 创建了它
 */
@Module
public class UserNickModule {

    @PerActivity
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("修改昵称", true);
    }

    @PerActivity
    @Provides
    public UserNickViewModel provideViewModel(RequestApi api, RxBus rxBus) {
        return new UserNickViewModel(api, rxBus);
    }
}
