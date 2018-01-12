package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.lesson.viewmodel.LessonHonorViewModel;
import com.edu.sxue.module.lesson.viewmodel.LessonInfoViewModel;
import com.edu.sxue.module.lesson.viewmodel.LessonIntroduceViewModel;
import com.edu.sxue.module.lesson.viewmodel.LessonPackageViewModel;
import com.edu.sxue.module.lesson.viewmodel.LessonPowerViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/10/12 创建了它
 */
@Module
public class LessonActivityModule {

    @PerActivity
    @Provides
    AppBar provideAppBar() {
        return new AppBar("");
    }

    @PerActivity
    @Provides
    LessonInfoViewModel provideLessonInfoViewModel(RequestApi api) {
        return new LessonInfoViewModel(api);
    }

    @PerActivity
    @Provides
    LessonIntroduceViewModel provideLessonIntroduceViewModel(RequestApi api) {
        return new LessonIntroduceViewModel(api);
    }

    @PerActivity
    @Provides
    public LessonPackageViewModel provideLessonPackageViewModel(RequestApi api) {
        return new LessonPackageViewModel(api);
    }

    @PerActivity
    @Provides
    public LessonPowerViewModel provideLessonPowerViewModel(RequestApi api) {
        return new LessonPowerViewModel(api);
    }

    @PerActivity
    @Provides
    public LessonHonorViewModel provideLessonHonorViewModel(RequestApi api) {
        return new LessonHonorViewModel(api);
    }
}
