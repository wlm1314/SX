package com.edu.sxue.module.user.vip.viewmedel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.user.vip.model.ConsumeBean;
import com.edu.sxue.module.user.vip.model.VipCardBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import base.lib.util.PreferencesUtils;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Administrator 在 2017/6/8 0008 创建了它
 */

public class VipViewModel implements IRxBusListener {
    public ObservableField<String> balance = new ObservableField<>("");
    private RequestApi mRequestApi;
    private RxBus mRxBus;
    private BaseAdapter<ConsumeItemViewModel> mAdapter;
    private ArrayList<ConsumeItemViewModel> datas = new ArrayList<>();

    public VipViewModel(RxBus rxBus, RequestApi requestApi) {
        mRxBus = rxBus;
        mRequestApi = requestApi;
        mAdapter = new BaseAdapter<>(R.layout.adapter_user_consume_item, datas);
        getData();
    }

    public BaseAdapter<ConsumeItemViewModel> getAdapter() {
        return mAdapter;
    }

    private void getData() {
        mRequestApi.getBalance(HttpParams.getMemberIdParam(PreferencesUtils.getString(Constants.sUser_userid, "")))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<VipCardBean>() {
                    @Override
                    public void onNext(VipCardBean vipCardBeen) {
                        VipCardBean bean = vipCardBeen;
                        if (TextUtils.isEmpty(bean.balance)) {
                            balance.set("0");
                        } else {
                            balance.set(bean.balance);
                        }
                    }
                });
    }

    public void getConseme(int page) {
        mRequestApi.getConsume(HttpParams.getPageMemberParam(PreferencesUtils.getString(Constants.sUser_userid, ""), page + ""))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult<ArrayList<ConsumeBean>>>() {
                    @Override
                    public void onNext(HttpResult<ArrayList<ConsumeBean>> httpResult) {
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        if (page == 1)
                            datas.clear();
                        for (ConsumeBean bean : httpResult.getData()) {
                            datas.add(new ConsumeItemViewModel(bean));
                        }
                        mAdapter.loadMoreComplete();
                        mAdapter.setEnableLoadMore(httpResult.getData().size() < 10);
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
