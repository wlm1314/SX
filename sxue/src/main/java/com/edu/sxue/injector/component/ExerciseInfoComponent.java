package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.ExerciseInfoModule;
import com.edu.sxue.module.exercise.view.ExerciseInfoActivity;

import dagger.Component;

/**
 * Administrator 在 2017/6/5 创建了它.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ExerciseInfoModule.class)
public interface ExerciseInfoComponent {
    void inject(ExerciseInfoActivity activity);
}
