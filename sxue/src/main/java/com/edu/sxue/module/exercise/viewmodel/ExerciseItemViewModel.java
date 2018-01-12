package com.edu.sxue.module.exercise.viewmodel;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.app.App;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.exercise.model.ExerciseBean;
import com.edu.sxue.module.exercise.model.ExerciseSignBean;
import com.edu.sxue.module.exercise.view.ExerciseInfoActivity;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kelin.mvvmlight.command.ReplyCommand;

import base.lib.util.ActivityManager;
import base.lib.util.NavigateUtils;
import base.lib.util.PreferencesUtils;

/**
 * Administrator 在 2017/6/5 创建了它.
 */

public class ExerciseItemViewModel {
    private RequestApi mRequestApi;
    public ObservableField<String> img = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> inst_name = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("");
    public NormalDialog dialog;
    public ExerciseBean bean;

    public ExerciseItemViewModel(ExerciseBean bean, RequestApi api) {
        this.bean = bean;
        this.mRequestApi = api;
        this.img.set(bean.img);
        this.name.set(bean.name);
        this.inst_name.set(TextUtils.isEmpty(bean.host) ? "" : bean.host);
        this.time.set(App.getInstance().getResources().getString(R.string.range_time,
                TextUtils.isEmpty(bean.start_time) ? "" : bean.start_time,
                TextUtils.isEmpty(bean.end_time) ? "" : bean.end_time));
        dialog = new NormalDialog(ActivityManager.getActivity());
        dialog.isTitleShow(false)
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideBottomExit())
                .content("\n报名已成功，请在“我”中查看\n报名详情\n")
                .contentTextSize(18)
                .contentTextColor(ActivityManager.getActivity().getResources().getColor(R.color.text_normal))
                .contentGravity(Gravity.CENTER)
                .btnNum(1)
                .btnText("确定")
                .btnTextSize(18)
                .btnTextColor(ActivityManager.getActivity().getResources().getColor(R.color.blue));
        dialog.setOnBtnClickL((OnBtnClickL) () -> dialog.dismiss());
    }

    public ReplyCommand itemCommand = new ReplyCommand(() -> {
        Bundle bundle = new Bundle();
        bundle.putString("name", this.name.get());
        bundle.putString("id", this.bean.id);
        NavigateUtils.startActivity(ExerciseInfoActivity.class, bundle);
    });

    public ReplyCommand signCommand = new ReplyCommand(() ->
            mRequestApi.exerciseSign(HttpParams.getExerciseParam(this.bean.id, this.bean.host_id, PreferencesUtils.getString(Constants.sUser_userid, "")))
                    .compose(RetrofitService.applySchedulers())
                    .map(new RetrofitService.HttpResultFunc<>())
                    .subscribe(new ProgressSubscriber<ExerciseSignBean>() {
                        @Override
                        public void onNext(ExerciseSignBean exerciseSignBean) {
                            dialog.show();
                        }
                    }));

}
