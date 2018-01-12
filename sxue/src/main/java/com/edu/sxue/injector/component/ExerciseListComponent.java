package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerFragment;
import com.edu.sxue.injector.module.ExerciseListModule;
import com.edu.sxue.module.exercise.view.ExerciseListFragment;

import dagger.Component;

/**
 * 王少岩 在 2017/7/13 创建了它
 */

@PerFragment
@Component(dependencies = AppComponent.class, modules = ExerciseListModule.class)
public interface ExerciseListComponent {
    void inject(ExerciseListFragment fragment);
}
