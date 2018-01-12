package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerFragment;
import com.edu.sxue.injector.module.ExerciseModule;
import com.edu.sxue.module.exercise.view.ExerciseFragment;

import dagger.Component;

/**
 * Administrator 在 2017/6/5 创建了它.
 */
@PerFragment
@Component(modules = ExerciseModule.class)
public interface ExerciseComponent {
    void inject(ExerciseFragment fragment);
}
