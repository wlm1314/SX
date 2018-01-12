package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.OrganActivityModule;
import com.edu.sxue.module.organ.view.info.OrganDesActivity;
import com.edu.sxue.module.organ.view.info.OrganEnvironmentActivity;
import com.edu.sxue.module.organ.view.info.OrganHyzzActivity;
import com.edu.sxue.module.organ.view.info.OrganInfoActivity;
import com.edu.sxue.module.organ.view.info.OrganLessonsActivity;
import com.edu.sxue.module.organ.view.info.OrganTeacherActivity;

import dagger.Component;

/**
 * 王少岩 在 2017/10/16 创建了它
 */

@PerActivity
@Component(dependencies = {AppComponent.class, ActivityComponent.class}, modules = OrganActivityModule.class)
public interface OrganActivityComponent {
    void inject(OrganTeacherActivity activity);
    void inject(OrganDesActivity activity);
    void inject(OrganEnvironmentActivity activity);
    void inject(OrganInfoActivity activity);
    void inject(OrganLessonsActivity activity);
    void inject(OrganHyzzActivity activity);
}
