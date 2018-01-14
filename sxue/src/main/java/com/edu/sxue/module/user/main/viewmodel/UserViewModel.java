package com.edu.sxue.module.user.main.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.user.main.model.UserInfoBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import base.lib.util.PreferencesUtils;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class UserViewModel implements IRxBusListener {
    public ObservableField<String> pic = new ObservableField<>(PreferencesUtils.getString(Constants.sUser_pic, ""));
    public ObservableField<String> name = new ObservableField<>(PreferencesUtils.getString(Constants.sUser_username, ""));
    private RxBus mRxBus;
    private RequestApi mRequestApi;
    private UserInfoBean mUserInfoBean;

    public UserViewModel(RxBus rxBus, RequestApi requestApi) {
        mRxBus = rxBus;
        mRequestApi = requestApi;
    }

    public UserInfoBean getUserInfoBean() {
        return mUserInfoBean;
    }

    public void getData() {
        mRequestApi.getUserInfo(HttpParams.getInfoParam(PreferencesUtils.getString(Constants.sUser_userid, "1")))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<ArrayList<UserInfoBean>>() {
                    @Override
                    public void onNext(ArrayList<UserInfoBean> userInfoBean) {
                        mUserInfoBean = userInfoBean.get(0);
                        pic.set(mUserInfoBean.pic);
                        name.set(mUserInfoBean.name);
                        PreferencesUtils.saveString(Constants.sUser_userid, mUserInfoBean.id);
                        PreferencesUtils.saveString(Constants.sUser_username, mUserInfoBean.name);
                        PreferencesUtils.saveString(Constants.sUser_pic, mUserInfoBean.pic);
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
//                        super.onError(e);
                    }
                });
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
