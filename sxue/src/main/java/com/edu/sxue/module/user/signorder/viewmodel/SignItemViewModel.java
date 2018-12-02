package com.edu.sxue.module.user.signorder.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.user.signorder.model.SignOrderBean;
import com.edu.sxue.module.user.vip.model.VipCardBean;
import com.kelin.mvvmlight.command.ReplyCommand;

import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class SignItemViewModel {
    private RequestApi mRequest;
    public ObservableField<String> pic = new ObservableField<>();
    public ObservableField<String> cour_name = new ObservableField<>();
    public ObservableField<String> inst_name = new ObservableField<>();
    public ObservableField<String> start_time = new ObservableField<>();
    public ObservableField<String> room_number = new ObservableField<>();
    public ObservableField<Boolean> show = new ObservableField<>(true);
    private SignOrderBean mSignBean;

    public SignItemViewModel(RequestApi requestApi, SignOrderBean bean) {
        mRequest = requestApi;
        mSignBean = bean;
        this.pic.set(bean.pic);
        this.cour_name.set(TextUtils.isEmpty(bean.courseName) ? "课程名:" : "课程名:" + bean.courseName);
        this.inst_name.set(TextUtils.isEmpty(bean.instName) ? "" : bean.instName);
//        this.time.set(App.getInstance().getResources().getString(R.string.time_length, TextUtils.isEmpty(bean.time) ? "" : bean.time));
        this.start_time.set(TextUtils.isEmpty(bean.time) ? "" : bean.time);
//        this.room_number.set(TextUtils.isEmpty(bean.room_number) ? "" : bean.room_number);
    }

    public ReplyCommand cancelCommand = new ReplyCommand(() -> {
        if (TextUtils.isEmpty(mSignBean.course_id)) {
            ToastUtils.showToast("课程Id为空");
            return;
        }
        mRequest.cancelCourse(HttpParams.cancelCourse(mSignBean.course_id, PreferencesUtils.getString(Constants.sUser_userid, ""), mSignBean.time))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult>() {
                    @Override
                    public void onNext(HttpResult httpResult) {
                        ToastUtils.showToast(httpResult.getInfo());
                        show.set(!httpResult.isSuccess());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtils.showToast("取消失败");
                    }
                });
    });
}
