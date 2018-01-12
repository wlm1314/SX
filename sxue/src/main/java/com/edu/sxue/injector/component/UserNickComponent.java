package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.UserNickModule;
import com.edu.sxue.module.user.profile.view.UserNickActivity;

import dagger.Component;

/**
 * 王少岩 在 2017/7/13 创建了它
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = UserNickModule.class)
public interface UserNickComponent {
    void inject(UserNickActivity activity);
}
