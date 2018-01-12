package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.organ.viewmodel.OrganDesViewModel;
import com.edu.sxue.module.organ.viewmodel.OrganEnvironmentViewModel;
import com.edu.sxue.module.organ.viewmodel.OrganHyzzViewModel;
import com.edu.sxue.module.organ.viewmodel.OrganInfoViewModel;
import com.edu.sxue.module.organ.viewmodel.OrganLessonsViewModel;
import com.edu.sxue.module.organ.viewmodel.OrganTeacherViewModel;
import com.edu.sxue.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * 王少岩 在 2017/10/12 创建了它
 */
@Module
public class OrganActivityModule {

    @PerActivity
    @Provides
    AppBar provideAppBar() {
        return new AppBar("");
    }

    @PerActivity
    @Provides
    OrganTeacherViewModel provideOrganTeacherViewModel(RequestApi api) {
        return new OrganTeacherViewModel(api);
    }

    @PerActivity
    @Provides
    public OrganDesViewModel provideOrganDesViewModel(RequestApi api){
        return new OrganDesViewModel(api);
    }
    @PerActivity
    @Provides
    public OrganEnvironmentViewModel provideOrganEnvironmentViewModel(RequestApi api){
        return new OrganEnvironmentViewModel(api);
    }

    @PerActivity
    @Provides
    public OrganInfoViewModel provideOrganInfoViewModel(RequestApi api, RxBus rxBus){
        return new OrganInfoViewModel(api, rxBus);
    }

    @PerActivity
    @Provides
    public OrganLessonsViewModel provideOrganLessonsViewModel(RequestApi api) {
        return new OrganLessonsViewModel(api);
    }
    @PerActivity
    @Provides
    public OrganHyzzViewModel provideOrganHyzzViewModel(RequestApi api){
        return new OrganHyzzViewModel(api);
    }
}
