package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.user.login.viewmodel.LoginViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/6/4 0004.
 */
@Module
public class LoginModule {

    @PerActivity
    @Provides
    public LoginViewModel provideViewModel(RequestApi api) {
        return new LoginViewModel(api);
    }

    @PerActivity
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("登录", true);
    }


}
