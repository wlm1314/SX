package com.edu.sxue.module.user.profile.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.user.profile.model.UserProfileBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import base.lib.util.ActivityManager;
import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class UserProfileViewModel implements IRxBusListener {
    private RequestApi mRequestApi;
    private RxBus mRxBus;
    private UserProfileBean mUserProfileBean;
    public ObservableField<String> imgUrl = new ObservableField<>("");

    public UserProfileViewModel(RequestApi requestApi, RxBus rxBus) {
        mRequestApi = requestApi;
        mRxBus = rxBus;
        mRequestApi.getUserProfile(HttpParams.getInfoParam(PreferencesUtils.getString(Constants.sUser_userid, "1")))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<ArrayList<UserProfileBean>>() {
                    @Override
                    public void onNext(ArrayList<UserProfileBean> userProfileBeen) {
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        mUserProfileBean = userProfileBeen.get(0);
                        imgUrl.set(mUserProfileBean.pic);
                    }
                });
    }

    public void updateUserProfile(String id, String pic, String name, String sex, String birthdate, String health, String address, String weight, String phone, String school){
        mRequestApi.updateUserProfile(HttpParams.updateProfile(id, pic, name, sex, birthdate, health, address, weight, phone, school))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult>() {
                    @Override
                    public void onNext(HttpResult httpResult) {
                        ToastUtils.showToast(httpResult.getInfo());
                        if (httpResult.isSuccess())
                            ActivityManager.getActivity().finish();
                    }
                });
    }

    public UserProfileBean getUserProfileBean() {
        return mUserProfileBean;
    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = mRxBus.doSubscribe(eventType, action, throwable -> Logger.e(throwable.toString()));
        mRxBus.addSubscription(this, subscription);
    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }

}