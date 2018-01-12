package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.LoginModule;
import com.edu.sxue.module.user.login.view.LoginActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/6/4 0004.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginCompoent {
    void inject(LoginActivity activity);
}
