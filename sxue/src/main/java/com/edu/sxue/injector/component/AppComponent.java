package com.edu.sxue.injector.component;

import android.content.Context;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.injector.module.AppModule;
import com.edu.sxue.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * 王少岩 在 2017/3/10 创建了它
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    // provide
    Context getContext();
    RxBus getRxBus();
    Retrofit getRetrofit();
    RequestApi getRequestApi();
}
