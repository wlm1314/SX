package com.edu.sxue.module.organ.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.organ.model.OrganInfoBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import rx.Subscription;
import rx.functions.Action1;

/**
 * 王少岩 在 2017/7/14 创建了它
 */

public class OrganInfoViewModel implements IRxBusListener {
    private RequestApi mRequestApi;
    private RxBus mRxBus;
    private OrganInfoBean mOrganInfoBean;
    public ObservableField<String> imgUrl = new ObservableField<>("");

    public OrganInfoViewModel(RequestApi requestApi, RxBus rxBus) {
        mRxBus = rxBus;
        mRequestApi = requestApi;
    }

    public OrganInfoBean getOrganInfoBean() {
        return mOrganInfoBean;
    }

    public void getData(String id) {
        mRequestApi.getOrganInfo(HttpParams.getInfoParam(id))
                .map(new RetrofitService.HttpResultFunc<>())
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<ArrayList<OrganInfoBean>>() {
                    @Override
                    public void onNext(ArrayList<OrganInfoBean> organInfoBean) {
                        mOrganInfoBean = organInfoBean.get(0);
                        imgUrl.set(mOrganInfoBean.logo);
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
