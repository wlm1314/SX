package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.user.order.viewmodel.UserOrderViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/7/13 创建了它
 */
@Module
public class UserOrderModule {

    @PerActivity
    @Provides
    public AppBar provideAppBar(){
        return new AppBar("我的订购", true);
    }

    @PerActivity
    @Provides
    public UserOrderViewModel provideViewModel(RxBus rxBus, RequestApi api) {
        return new UserOrderViewModel(rxBus, api);
    }
}
