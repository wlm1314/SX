package com.edu.sxue.injector.component;

import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.injector.module.LessonActivityModule;
import com.edu.sxue.module.lesson.view.info.LessonHonorActivity;
import com.edu.sxue.module.lesson.view.info.LessonInfoActivity;
import com.edu.sxue.module.lesson.view.info.LessonIntroduceActivity;
import com.edu.sxue.module.lesson.view.info.LessonPackageActivity;
import com.edu.sxue.module.lesson.view.info.LessonPowerActivity;

import dagger.Component;

/**
 * Administrator 在 2017/6/5 创建了它.
 */
@PerActivity
@Component(dependencies = {AppComponent.class, ActivityComponent.class}, modules = LessonActivityModule.class)
public interface LessonActivityComponent {
    void inject(LessonInfoActivity activity);
    void inject(LessonIntroduceActivity activity);
    void inject(LessonPackageActivity activity);
    void inject(LessonPowerActivity activity);
    void inject(LessonHonorActivity activity);
}
