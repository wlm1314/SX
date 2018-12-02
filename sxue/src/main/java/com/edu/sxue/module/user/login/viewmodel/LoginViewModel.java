package com.edu.sxue.module.user.login.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.home.view.HomeActivity;
import com.edu.sxue.module.user.main.model.UserInfoBean;
import com.edu.sxue.module.user.resetpwd.view.ResetPwdActivity;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;

import base.lib.util.ActivityManager;
import base.lib.util.NavigateUtils;
import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;

/**
 * Created by Administrator on 2017/6/1.
 */

public class LoginViewModel {
    private RequestApi mRequestApi;
    public ObservableField<String> account = new ObservableField<>(PreferencesUtils.getString(Constants.sUser_useraccount, ""));
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> pic = new ObservableField<>(PreferencesUtils.getString(Constants.sUser_pic, ""));

    public ReplyCommand loginCommand = new ReplyCommand(() -> {
        if (TextUtils.isEmpty(account.get())){
            ToastUtils.showToast("请输入会员号");
            return;
        }
        if (TextUtils.isEmpty(password.get())){
            ToastUtils.showToast("请输入密码");
            return;
        }
        mRequestApi.userLogin(HttpParams.userLogin(account.get(), password.get()))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<ArrayList<UserInfoBean>>() {
                    @Override
                    public void onNext(ArrayList<UserInfoBean> list) {
                        UserInfoBean bean = list.get(0);
                        PreferencesUtils.saveString(Constants.sUser_userid, bean.id);
                        PreferencesUtils.saveString(Constants.sUser_useraccount, account.get());
                        PreferencesUtils.saveString(Constants.sUser_username, bean.name);
                        PreferencesUtils.saveString(Constants.sUser_pic, bean.pic);
                        PreferencesUtils.saveString(Constants.sUser_phone, account.get());
                        PreferencesUtils.saveBoolean(Constants.sUser_loginFlag, true);
                        NavigateUtils.startActivity(HomeActivity.class);
                        ActivityManager.getActivity().finish();
                    }
                });
    });
    public ReplyCommand settingCommand = new ReplyCommand(() -> NavigateUtils.startActivity(ResetPwdActivity.class));

    public LoginViewModel(RequestApi api) {
        this.mRequestApi = api;
    }
}
