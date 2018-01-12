package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.UserOrderModule;
import com.edu.sxue.module.user.order.view.UserOrderActivity;

import dagger.Component;

/**
 * 王少岩 在 2017/7/13 创建了它
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = UserOrderModule.class)
public interface UserOrderComponent {
    void inject(UserOrderActivity activity);
}
