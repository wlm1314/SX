package com.edu.sxue.module.user.signorder.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.user.signorder.model.SignOrderBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import base.lib.util.PreferencesUtils;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class SignOrderViewModel implements IRxBusListener {
    private RxBus mRxBus;
    private RequestApi mRequestApi;
    private BaseAdapter<SignItemViewModel> mAdapter;
    private ArrayList<SignItemViewModel> datas = new ArrayList<>();

    public SignOrderViewModel(RxBus rxBus, RequestApi requestApi) {
        mRxBus = rxBus;
        mRequestApi = requestApi;
        mAdapter = new BaseAdapter<>(R.layout.adapter_sign_item, datas);
    }

    public BaseAdapter<SignItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData() {
        mRequestApi.getSignOrder(HttpParams.getMemberIdParam(PreferencesUtils.getString(Constants.sUser_userid, "")))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult<ArrayList<SignOrderBean>>>() {
                    @Override
                    public void onNext(HttpResult<ArrayList<SignOrderBean>> httpResult) {
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        datas.clear();
                        for (SignOrderBean bean : httpResult.getData()) {
                            datas.add(new SignItemViewModel(mRequestApi, bean));
                        }
                        mAdapter.loadMoreComplete();
                        mAdapter.setEnableLoadMore(false);
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
