package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.user.schedule.viewmodel.UserScheduleViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/7/13 创建了它
 */
@Module
public class UserScheduleModule {

    @PerActivity
    @Provides
    public AppBar provideAppBar(){
        return new AppBar("我的课程表", true);
    }

    @PerActivity
    @Provides
    public UserScheduleViewModel provideViewModel(RxBus rxBus, RequestApi api) {
        return new UserScheduleViewModel(rxBus, api);
    }
}
