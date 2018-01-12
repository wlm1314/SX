package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.HomeModule;
import com.edu.sxue.module.home.view.HomeActivity;

import dagger.Component;

/**
 * Administrator 在 2017/6/4 创建了它.
 */
@PerActivity
@Component(dependencies = ActivityComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
}
