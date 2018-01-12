package com.edu.sxue.module.exercise.viewmodel;

import android.databinding.ObservableField;
import android.view.Gravity;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.exercise.model.ExerciseInfoBean;
import com.edu.sxue.module.exercise.model.ExerciseSignBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.orhanobut.logger.Logger;

import base.lib.util.ActivityManager;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class ExerciseInfoViewModel implements IRxBusListener {
    private RequestApi mRequestApi;
    private RxBus mRxBus;
    private ExerciseInfoBean mExerciseInfoBean;
    public ObservableField<String> imgUrl = new ObservableField<>("");
    public NormalDialog dialog;

    public ExerciseInfoViewModel(RequestApi api, RxBus rxBus) {
        mRequestApi = api;
        mRxBus = rxBus;
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

    public ExerciseInfoBean getExerciseInfoBean(){
        return mExerciseInfoBean;
    }

    public void getData(String id) {
        mRequestApi.getExerciseInfo(HttpParams.getInfoParam(id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<ExerciseInfoBean>() {
                    @Override
                    public void onNext(ExerciseInfoBean exerciseInfoBean) {
                        mExerciseInfoBean = exerciseInfoBean;
                        imgUrl.set(exerciseInfoBean.img);
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                    }
                });
    }

    public void sign(String activity_id, String institution_id, String member_id){
        mRequestApi.exerciseSign(HttpParams.getExerciseParam(activity_id, institution_id, member_id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<ExerciseSignBean>() {
                    @Override
                    public void onNext(ExerciseSignBean exerciseSignBean) {
                        dialog.show();
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