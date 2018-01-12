package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.UserParentModule;
import com.edu.sxue.module.user.parent.view.UserParentActivity;

import dagger.Component;

/**
 * 王少岩 在 2017/7/13 创建了它
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = UserParentModule.class)
public interface UserParentComponent {
    void inject(UserParentActivity activity);
}
