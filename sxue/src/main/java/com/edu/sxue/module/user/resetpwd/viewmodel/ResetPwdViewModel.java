package com.edu.sxue.module.user.resetpwd.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.kelin.mvvmlight.command.ReplyCommand;

import base.lib.util.ToastUtils;
import rx.functions.Action0;

/**
 * Created by Administrator on 2017/6/1.
 */

public class ResetPwdViewModel {
    private RequestApi mRequestApi;
    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> code = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    public ResetPwdViewModel(RequestApi api) {
        this.mRequestApi = api;
    }
    //TimeCountUtil mTimeCountUtil= new TimeCountUtil(, 5000, 1000);


    public Action0 updatePwd = () -> {
        if (TextUtils.isEmpty(phone.get())) {
            ToastUtils.showToast("请输入联系人手机");
            return;
        }
        if (TextUtils.isEmpty(code.get())) {
            ToastUtils.showToast("请输入收到的验证码");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            ToastUtils.showToast("请设置新密码");
            return;
        }
    };

    public ReplyCommand getCode = new ReplyCommand(() -> {
        if (TextUtils.isEmpty(phone.get())) {
            ToastUtils.showToast("请输入联系人手机");
            return;
        }

        mRequestApi.getPhoneCode(HttpParams.getCheckCode(phone.get()))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult>() {
                    @Override
                    public void onNext(HttpResult httpResult) {
                        Log.i("TAG",httpResult.getInfo()+"");
                        if (httpResult.isSuccess()) {
                            ToastUtils.showToast("6666666");
                           /* PreferencesUtils.saveBoolean(Constants.sUser_loginFlag, false);
                            Intent intent = new Intent(ActivityManager.getActivity(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            ActivityManager.getActivity().startActivity(intent);*/
                        }
                    }
                });
    });
}
