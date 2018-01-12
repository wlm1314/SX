package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.exercise.viewmodel.ExerciseInfoViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/7/14 创建了它
 */
@Module
public class ExerciseInfoModule {

    @PerActivity
    @Provides
    public ExerciseInfoViewModel provideViewModel(RequestApi api, RxBus rxBus){
        return new ExerciseInfoViewModel(api, rxBus);
    }
}
