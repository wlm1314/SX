package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.user.updatepwd.viewmodel.UpdatePwdViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/6/4 0004.
 */
@Module
public class UpdatePwdModule {

    @PerActivity
    @Provides
    public UpdatePwdViewModel provideViewModel(RequestApi api) {
        return new UpdatePwdViewModel(api);
    }

    @PerActivity
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("修改密码", true);
    }


}
