package com.edu.sxue.injector.module;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.PerActivity;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.user.resetpwd.viewmodel.ResetPwdViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/6/4 0004.
 */
@Module
public class ResetPwdModule {

    @PerActivity
    @Provides
    public ResetPwdViewModel provideViewModel(RequestApi api) {
        return new ResetPwdViewModel(api);
    }

    @PerActivity
    @Provides
    public AppBar provideAppBar() {
        return new AppBar("忘记密码", true);
    }


}
