package com.edu.sxue.module.user.exercise.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.user.exercise.model.UserExerciseBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import base.lib.util.PreferencesUtils;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 王少岩 在 2017/7/20 创建了它
 */

public class UserExerciseViewModel implements IRxBusListener {
    private RequestApi mRequestApi;
    private RxBus mRxBus;
    private ArrayList<ExerciseItemViewModel> datas = new ArrayList<>();
    private BaseAdapter<ExerciseItemViewModel> mAdapter;

    public UserExerciseViewModel(RequestApi requestApi, RxBus rxBus) {
        mRequestApi = requestApi;
        mRxBus = rxBus;
        mAdapter = new BaseAdapter<>(R.layout.adapter_user_exercise_item, datas);
    }

    public BaseAdapter<ExerciseItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData(int page) {
        mRequestApi.getUserExercise(HttpParams.getPageParam(PreferencesUtils.getString(Constants.sUser_userid, ""), page + "", ""))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult<ArrayList<UserExerciseBean>>>() {
                    @Override
                    public void onNext(HttpResult<ArrayList<UserExerciseBean>> httpResult) {
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        if (page == 1)
                            datas.clear();
                        for (UserExerciseBean bean : httpResult.getData()) {
                            datas.add(new ExerciseItemViewModel(bean));
                        }
                        mAdapter.loadMoreComplete();
                        mAdapter.setEnableLoadMore(page < Integer.valueOf(httpResult.getPage_count()));
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
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
