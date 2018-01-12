package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.UpdatePwdModule;
import com.edu.sxue.module.user.updatepwd.view.UpdatePwdActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/6/4 0004.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = UpdatePwdModule.class)
public interface UpdatePwdCompoent {
    void inject(UpdatePwdActivity activity);
}
