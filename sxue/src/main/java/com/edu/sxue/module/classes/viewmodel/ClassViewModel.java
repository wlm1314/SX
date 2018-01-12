package com.edu.sxue.module.classes.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.classes.model.ClassBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import base.lib.util.PreferencesUtils;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 王少岩 在 2017/10/17 创建了它
 */

public class ClassViewModel implements IRxBusListener {
    private RequestApi mRequestApi;
    private RxBus mRxBus;
    private BaseAdapter<ClassItemViewModel> mAdapter;
    private ArrayList<ClassItemViewModel> mList = new ArrayList<>();

    public ClassViewModel(RequestApi requestApi, RxBus rxBus) {
        mRxBus = rxBus;
        mRequestApi = requestApi;
        mAdapter = new BaseAdapter<>(R.layout.adapter_class_item, mList);
    }

    public void getData(String flag) {
        if (flag.equals("0"))
            mRequestApi.getCoursePlanList(HttpParams.getMemberIdParam(PreferencesUtils.getString(Constants.sUser_userid, "")))
                    .compose(RetrofitService.applySchedulers())
                    .map(new RetrofitService.HttpResultFunc<>())
                    .subscribe(new ProgressSubscriber<ArrayList<ClassBean>>() {
                        @Override
                        public void onNext(ArrayList<ClassBean> classBeen) {
                            mList.clear();
                            mList.addAll(ClassItemViewModel.getList(mRequestApi, classBeen));
                            mAdapter.notifyDataSetChanged();
                            mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        }
                    });
        else
            mRequestApi.CoursePlanList(HttpParams.getMemberIdParam(PreferencesUtils.getString(Constants.sUser_userid, "")))
                    .compose(RetrofitService.applySchedulers())
                    .map(new RetrofitService.HttpResultFunc<>())
                    .subscribe(new ProgressSubscriber<ArrayList<ClassBean>>() {
                        @Override
                        public void onNext(ArrayList<ClassBean> classBeen) {
                            mList.clear();
                            mList.addAll(ClassItemViewModel.getList(mRequestApi, classBeen));
                            mAdapter.notifyDataSetChanged();
                            mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        }
                    });
    }

    public BaseAdapter<ClassItemViewModel> getAdapter() {
        return mAdapter;
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
