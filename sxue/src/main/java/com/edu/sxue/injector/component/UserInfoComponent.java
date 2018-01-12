package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerFragment;
import com.edu.sxue.injector.module.UserInfoModule;
import com.edu.sxue.module.user.main.view.UserFragment;

import dagger.Component;

/**
 * 王少岩 在 2017/7/13 创建了它
 */

@PerFragment
@Component(dependencies = AppComponent.class, modules = UserInfoModule.class)
public interface UserInfoComponent {
    void inject(UserFragment fragment);
}
