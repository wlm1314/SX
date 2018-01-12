package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.user.exercise.viewmodel.UserExerciseViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/7/13 创建了它
 */
@Module
public class UserExerciseModule {

    @PerActivity
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("我的活动", true);
    }

    @PerActivity
    @Provides
    public UserExerciseViewModel provideViewModel(RequestApi api, RxBus rxBus) {
        return new UserExerciseViewModel(api, rxBus);
    }
}
