package com.edu.sxue.injector.module;

import android.content.Context;

import com.edu.sxue.api.HttpConsts;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.app.App;
import com.edu.sxue.rxbus.RxBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.edu.sxue.api.RetrofitService.sLoggingInterceptor;
import static com.edu.sxue.api.RetrofitService.sParamInterceptor;

/**
 * 王少岩 在 2017/3/10 创建了它
 */
@Module
public class AppModule {
    private static App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Context provideAppContext(){
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    RxBus provideRxBus(){
        return new RxBus();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(sParamInterceptor)
                .addInterceptor(sLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HttpConsts.getServer())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    RequestApi provideRequestApi(Retrofit retrofit){
        return retrofit.create(RequestApi.class);
    }
}
