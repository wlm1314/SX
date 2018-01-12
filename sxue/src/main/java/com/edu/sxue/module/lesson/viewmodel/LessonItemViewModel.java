package com.edu.sxue.module.lesson.viewmodel;

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
import com.edu.sxue.module.lesson.model.LessonBean;
import com.edu.sxue.module.lesson.model.LessonTryBean;
import com.edu.sxue.module.lesson.view.info.LessonInfoActivity;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;

import base.lib.util.ActivityManager;
import base.lib.util.NavigateUtils;
import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;

/**
 * Administrator 在 2017/6/5 创建了它.
 */

public class LessonItemViewModel {
    public ObservableField<String> pic = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> institution_name = new ObservableField<>("");
    public ObservableField<String> course_time = new ObservableField<>("");
    public ObservableField<String> single_time_price = new ObservableField<>("");
    private RequestApi mRequestApi;

    private LessonBean mLessonBean;

    public LessonItemViewModel(LessonBean lessonBean, RequestApi api) {
        this.mLessonBean = lessonBean;
        this.mRequestApi = api;
        this.pic.set(lessonBean.pic);
        this.name.set(lessonBean.name);
        this.institution_name.set(TextUtils.isEmpty(lessonBean.institution_name) ? "" : lessonBean.institution_name);
        this.course_time.set(App.getInstance().getResources().getString(R.string.time_length, TextUtils.isEmpty(lessonBean.course_time) ? "" : lessonBean.course_time));
        this.single_time_price.set(App.getInstance().getResources().getString(R.string.price_once, TextUtils.isEmpty(lessonBean.single_time_price) ? "" : lessonBean.single_time_price));
    }

    public ReplyCommand itemCommand = new ReplyCommand(() -> {
        Bundle bundle = new Bundle();
        bundle.putSerializable("lesson", mLessonBean);
        NavigateUtils.startActivity(LessonInfoActivity.class, bundle);
    });

    public ReplyCommand videoCommand = new ReplyCommand(() -> ToastUtils.showToast("视频地址为空"));

    public ReplyCommand signCommand = new ReplyCommand(() -> {
        NormalDialog dialog = new NormalDialog(ActivityManager.getActivity());
        dialog.isTitleShow(false)
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideBottomExit())
                .content("\n您的报名信息以发送给相关机构\n稍后我们会跟您联系\n")
                .contentTextSize(18)
                .contentTextColor(ActivityManager.getActivity().getResources().getColor(R.color.text_normal))
                .contentGravity(Gravity.CENTER)
                .btnNum(1)
                .btnText("确定")
                .btnTextSize(18)
                .btnTextColor(ActivityManager.getActivity().getResources().getColor(R.color.blue));
        mRequestApi.lessonTry(HttpParams.getLessonParam(mLessonBean.id, mLessonBean.institution_id, PreferencesUtils.getString(Constants.sUser_userid, "")))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<ArrayList<LessonTryBean>>() {
                    @Override
                    public void onNext(ArrayList<LessonTryBean> lessonTryBeen) {
                        dialog.show();
                    }
                });
        dialog.setOnBtnClickL((OnBtnClickL) () -> dialog.dismiss());
    });
}
