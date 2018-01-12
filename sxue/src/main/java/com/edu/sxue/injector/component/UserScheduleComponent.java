package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.UserScheduleModule;
import com.edu.sxue.module.user.schedule.view.UserScheduleActivity;

import dagger.Component;

/**
 * 王少岩 在 2017/7/13 创建了它
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = UserScheduleModule.class)
public interface UserScheduleComponent {
    void inject(UserScheduleActivity activity);
}
