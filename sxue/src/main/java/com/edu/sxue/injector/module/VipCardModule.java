package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.user.vip.viewmedel.VipViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/6/4 0004.
 */
@Module
public class VipCardModule {

    @PerActivity
    @Provides
    public VipViewModel provideViewModel(RxBus rxBus, RequestApi api) {
        return new VipViewModel(rxBus, api);
    }

    @PerActivity
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("我的会员卡", true);
    }


}
