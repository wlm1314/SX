package com.edu.sxue.module.user.profile.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.api.RequestApi;
import com.edu.sxue.rxbus.RxBus;

import base.lib.util.ToastUtils;
import rx.functions.Action0;

/**
 * 王少岩 在 2017/7/21 创建了它
 */

public class UserNickViewModel {
    private RxBus mRxBus;
    private RequestApi mRequestApi;
    public ObservableField<String> nick = new ObservableField<>("");

    public UserNickViewModel(RequestApi requestApi, RxBus rxBus) {
        mRxBus = rxBus;
        mRequestApi = requestApi;
    }

    public Action0 commit = () -> {
        ToastUtils.showToast(nick.get());
    };

}
