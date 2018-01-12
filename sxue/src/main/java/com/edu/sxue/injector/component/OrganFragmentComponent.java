package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerFragment;
import com.edu.sxue.injector.module.OrganFragmentModule;
import com.edu.sxue.module.organ.view.OrganFragment;
import com.edu.sxue.module.organ.view.OrganListFragment;

import dagger.Component;

/**
 * 王少岩 在 2017/3/15 创建了它
 */
@PerFragment
@Component(dependencies = {AppComponent.class, FragmentComponent.class}, modules = OrganFragmentModule.class)
public interface OrganFragmentComponent {
    void inject(OrganFragment fragment);
    void inject(OrganListFragment fragment);
}
