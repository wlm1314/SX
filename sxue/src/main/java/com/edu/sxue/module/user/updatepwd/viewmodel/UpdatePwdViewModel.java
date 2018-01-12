package com.edu.sxue.module.user.updatepwd.viewmodel;

import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.user.login.view.LoginActivity;
import com.kelin.mvvmlight.command.ReplyCommand;

import base.lib.util.ActivityManager;
import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;
import rx.functions.Action0;

/**
 * Created by Administrator on 2017/6/1.
 */

public class UpdatePwdViewModel {
    private RequestApi mRequestApi;
    public ObservableField<String> oldPwd = new ObservableField<>("");
    public ObservableField<String> newPwd = new ObservableField<>("");
    public ObservableField<String> confirmPwd = new ObservableField<>("");

    public UpdatePwdViewModel(RequestApi api) {
        this.mRequestApi = api;
    }

    public Action0 updatePwd = () -> {
        if (TextUtils.isEmpty(oldPwd.get())) {
            ToastUtils.showToast("请输入您的旧密码");
            return;
        }
        if (TextUtils.isEmpty(newPwd.get())) {
            ToastUtils.showToast("请输入您的新密码");
            return;
        }
        if (!newPwd.get().equals(confirmPwd.get())) {
            ToastUtils.showToast("新密码和再次输入密码不一致");
            return;
        }
        mRequestApi.updatePwd(HttpParams.updatePwd(PreferencesUtils.getString(Constants.sUser_userid, ""), newPwd.get(), oldPwd.get()))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult>() {
                    @Override
                    public void onNext(HttpResult httpResult) {
                        ToastUtils.showToast(httpResult.getInfo());
                        if (httpResult.isSuccess()) {
                            PreferencesUtils.saveBoolean(Constants.sUser_loginFlag, false);
                            Intent intent = new Intent(ActivityManager.getActivity(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            ActivityManager.getActivity().startActivity(intent);
                        }

                    }
                });
    };
    public ReplyCommand changePwd = new ReplyCommand(() -> {
        if (TextUtils.isEmpty(oldPwd.get())) {
            ToastUtils.showToast("请输入您的旧密码");
            return;
        }
        if (TextUtils.isEmpty(newPwd.get())) {
            ToastUtils.showToast("请输入您的新密码");
            return;
        }
        if (!newPwd.get().equals(confirmPwd.get())) {
            ToastUtils.showToast("新密码和再次输入密码不一致");
            return;
        }
        mRequestApi.updatePwd(HttpParams.updatePwd(PreferencesUtils.getString(Constants.sUser_userid, ""), newPwd.get(), oldPwd.get()))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult>() {
                    @Override
                    public void onNext(HttpResult httpResult) {
                        ToastUtils.showToast(httpResult.getInfo());
                        if (httpResult.isSuccess()) {
                            PreferencesUtils.saveBoolean(Constants.sUser_loginFlag, false);
                            Intent intent = new Intent(ActivityManager.getActivity(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            ActivityManager.getActivity().startActivity(intent);
                        }

                    }
                });
    });
}
