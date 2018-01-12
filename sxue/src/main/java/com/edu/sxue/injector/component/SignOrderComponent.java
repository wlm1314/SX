package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.SignOrderModule;
import com.edu.sxue.module.user.signorder.view.SignOrderActivity;

import dagger.Component;

/**
 * 王少岩 在 2017/7/13 创建了它
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = SignOrderModule.class)
public interface SignOrderComponent {
    void inject(SignOrderActivity activity);
}
