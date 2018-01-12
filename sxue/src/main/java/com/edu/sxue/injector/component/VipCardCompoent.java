package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.VipCardModule;
import com.edu.sxue.module.user.vip.view.VipActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/6/4 0004.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = VipCardModule.class)
public interface VipCardCompoent {
    void inject(VipActivity activity);
}
