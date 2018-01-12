package com.edu.sxue.module.user.schedule.viewmodel;

import android.text.TextUtils;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.home.view.HomeActivity;
import com.edu.sxue.module.user.main.model.UserInfoBean;
import com.edu.sxue.module.user.schedule.model.UserScheduleBean;
import com.edu.sxue.rxbus.IRxBusListener;
import com.edu.sxue.rxbus.RxBus;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import base.lib.util.ActivityManager;
import base.lib.util.CalendarUtil;
import base.lib.util.NavigateUtils;
import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class UserScheduleViewModel implements IRxBusListener {
    private RxBus mRxBus;
    private RequestApi mRequestApi;
    private BaseAdapter<ScheduleItemViewModel> mAdapter;
    private ArrayList<ScheduleItemViewModel> datas = new ArrayList<>();

    public UserScheduleViewModel(RxBus rxBus, RequestApi requestApi) {
        mRxBus = rxBus;
        mRequestApi = requestApi;
        mAdapter = new BaseAdapter<>(R.layout.adapter_user_schedule_item, datas);
    }

    public BaseAdapter<ScheduleItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData(int page,String time) {
        mRequestApi.getUserSchedule(HttpParams.getSchedule(PreferencesUtils.getString(Constants.sUser_userid, ""), page + "", "",time))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult<ArrayList<UserScheduleBean>>>() {
                    @Override
                    public void onNext(HttpResult<ArrayList<UserScheduleBean>> httpResult) {
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        if (page == 1)
                            datas.clear();
                        for (UserScheduleBean bean : httpResult.getData()) {
                            datas.add(new ScheduleItemViewModel(bean));
                        }
                        mAdapter.loadMoreComplete();
                        mAdapter.setEnableLoadMore(false);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mRxBus.post(new CommonEvent(CommonEvent.FLAG_COMPLETE));
                        datas.clear();
                        mAdapter.loadMoreComplete();
                        mAdapter.setEnableLoadMore(false);
                        mAdapter.notifyDataSetChanged();
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