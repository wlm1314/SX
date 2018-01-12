package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerFragment;
import com.edu.sxue.injector.module.LessonFragmentModule;
import com.edu.sxue.module.lesson.view.LessonFragment;
import com.edu.sxue.module.lesson.view.LessonListFragment;

import dagger.Component;

/**
 * 王少岩 在 2017/3/15 创建了它
 */
@PerFragment
@Component(dependencies = {AppComponent.class, FragmentComponent.class}, modules = LessonFragmentModule.class)
public interface LessonFragmentComponent {
    void inject(LessonFragment fragment);
    void inject(LessonListFragment fragment);
}
