package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.UserProfileModule;
import com.edu.sxue.module.user.profile.view.UserProfileActivity;

import dagger.Component;

/**
 * 王少岩 在 2017/7/13 创建了它
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = UserProfileModule.class)
public interface UserProfileComponent {
    void inject(UserProfileActivity activity);
}
