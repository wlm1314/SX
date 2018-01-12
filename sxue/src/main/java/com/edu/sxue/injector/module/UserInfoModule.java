package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerFragment;
import com.edu.sxue.module.user.main.viewmodel.UserViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/7/13 创建了它
 */
@Module
public class UserInfoModule {

    @PerFragment
    @Provides
    public UserViewModel provideViewModel(RequestApi api, RxBus rxBus) {
        return new UserViewModel(rxBus, api);
    }
}
