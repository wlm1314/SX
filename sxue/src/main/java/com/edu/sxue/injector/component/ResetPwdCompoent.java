package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.ResetPwdModule;
import com.edu.sxue.module.user.resetpwd.view.ResetPwdActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/6/4 0004.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ResetPwdModule.class)
public interface ResetPwdCompoent {
    void inject(ResetPwdActivity activity);
}
