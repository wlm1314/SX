package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.UserExerciseModule;
import com.edu.sxue.module.user.exercise.view.UserExerciseActivity;

import dagger.Component;

/**
 * 王少岩 在 2017/7/13 创建了它
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = UserExerciseModule.class)
public interface UserExerciseComponent {
    void inject(UserExerciseActivity activity);
}
