package com.edu.sxue.module.user.parent.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.user.parent.model.UserParentBean;
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

public class UserParentViewModel implements IRxBusListener {
    private RequestApi mRequestApi;
    private RxBus mRxBus;
    private ArrayList<ParentItemViewModel> datas = new ArrayList<>();
    private BaseAdapter<ParentItemViewModel> mAdapter;

    public UserParentViewModel(RequestApi requestApi, RxBus rxBus) {
        mRequestApi = requestApi;
        mRxBus = rxBus;
        mAdapter = new BaseAdapter<>(R.layout.adapter_user_parent_item, datas);
    }

    public BaseAdapter<ParentItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData() {
        mRequestApi.getUserParent(HttpParams.getPageParam(PreferencesUtils.getString(Constants.sUser_userid, ""), "1", ""))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult<ArrayList<UserParentBean>>>() {
                    @Override
                    public void onNext(HttpResult<ArrayList<UserParentBean>> httpResult) {
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        datas.clear();
                        for (UserParentBean bean : httpResult.getData()) {
                            datas.add(new ParentItemViewModel(bean));
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