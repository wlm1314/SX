package com.edu.sxue.injector.module;

import android.app.Activity;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.home.viewmodel.HomeViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Administrator 在 2017/6/4 创建了它.
 */
@Module
public class HomeModule {

    @PerActivity
    @Provides
    public HomeViewModel provideViewModel(Activity activity){
        return new HomeViewModel(activity);
    }
}
