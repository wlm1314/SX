package com.edu.sxue.module.user.exercise.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.app.App;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.user.exercise.model.UserExerciseBean;
import com.kelin.mvvmlight.command.ReplyCommand;

import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class ExerciseItemViewModel {
    public ObservableField<String> img = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> inst_name = new ObservableField<>();
    public ObservableField<Boolean> show = new ObservableField<>(true);
    private UserExerciseBean mExerciseBean;
    private RequestApi mRequest;

    public ExerciseItemViewModel(RequestApi requestApi, UserExerciseBean bean) {
        mRequest = requestApi;
        mExerciseBean = bean;
        this.img.set(bean.activity_img);
        this.name.set(bean.activity_name);
        this.time.set(App.getInstance().getResources().getString(R.string.range_time,
                TextUtils.isEmpty(bean.activity_start_time) ? "" : bean.activity_start_time,
                TextUtils.isEmpty(bean.activity_end_time) ? "" : bean.activity_end_time));
        //this.inst_name.set(bean.inst_name);
        if (TextUtils.isEmpty(bean.inst_name)){
            this.inst_name.set("");
        }else{
            this.inst_name.set(bean.inst_name);
        }
    }

    public ReplyCommand cancelCommand = new ReplyCommand(() -> {
        mRequest.cancelActivity(HttpParams.cancelActivity(mExerciseBean.id, PreferencesUtils.getString(Constants.sUser_userid, "")))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult>() {
                    @Override
                    public void onNext(HttpResult httpResult) {
                        show.set(!httpResult.isSuccess());
                        ToastUtils.showToast(httpResult.getInfo());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtils.showToast("取消失败");
                    }
                });
    });
    

}
