package com.edu.sxue.module.exercise.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.exercise.model.ExerciseBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class ExerciseViewModel implements IRxBusListener {
    private RequestApi mRequestApi;
    private RxBus mRxBus;
    private ArrayList<ExerciseItemViewModel> datas = new ArrayList<>();
    private BaseAdapter<ExerciseItemViewModel> mAdapter;

    public ExerciseViewModel(RequestApi api, RxBus rxBus) {
        mRequestApi = api;
        mRxBus = rxBus;
        mAdapter = new BaseAdapter<>(R.layout.adapter_exercise_item, datas);
    }

    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    public void getData(String keyword, int page) {
        mRequestApi.getExerciseIndex(HttpParams.getIndexParam(keyword, page + ""))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult<ArrayList<ExerciseBean>>>() {
                    @Override
                    public void onNext(HttpResult<ArrayList<ExerciseBean>> httpResult) {
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        if (page == 1)
                            datas.clear();
                        for (ExerciseBean bean : httpResult.getData()) {
                            datas.add(new ExerciseItemViewModel(bean, mRequestApi));
                        }
                        mAdapter.loadMoreComplete();
                        mAdapter.setEnableLoadMore(page < Integer.valueOf(httpResult.getPage_count()));
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