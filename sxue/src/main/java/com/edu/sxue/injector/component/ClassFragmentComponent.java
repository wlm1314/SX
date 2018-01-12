package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerFragment;
import com.edu.sxue.injector.module.ClassFragmentModule;
import com.edu.sxue.module.classes.view.ClassFragment;
import com.edu.sxue.module.classes.view.ClassListFragment;

import dagger.Component;

/**
 * Administrator 在 2017/6/5 创建了它.
 */
@PerFragment
@Component(dependencies = {AppComponent.class, FragmentComponent.class}, modules = ClassFragmentModule.class)
public interface ClassFragmentComponent {
    void inject(ClassFragment fragment);
    void inject(ClassListFragment fragment);
}
