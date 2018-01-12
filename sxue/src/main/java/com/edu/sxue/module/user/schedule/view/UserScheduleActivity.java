package com.edu.sxue.module.user.schedule.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityUserScheduleBinding;
import com.edu.sxue.injector.component.DaggerUserScheduleComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.schedule.viewmodel.UserScheduleViewModel;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.edu.sxue.widget.EmptyLayout;

import javax.inject.Inject;

import base.lib.util.CalendarUtil;
import base.lib.util.DateUtil;
import base.lib.util.IDateInterface;
import base.lib.util.ToastUtils;
import base.lib.widget.recyclerview.DividerLinearItemDecoration;

import static com.edu.sxue.widget.EmptyLayout.STATUS_NO_DATA;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class UserScheduleActivity extends BaseActivity<ActivityUserScheduleBinding> implements IDateInterface{
    @Inject
    UserScheduleViewModel mViewModel;
    @Inject
    AppBar mAppBar;
    private int page = 1;
    private String currDay;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_user_schedule;
    }

    @Override
    protected void initInjector() {
        DaggerUserScheduleComponent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.navigation.set(R.mipmap.ic_arrow_left_white);
        IDateInterface dateInterface=this;
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.setViewModel(mViewModel);
        currDay=DateUtil.year()+"-"+DateUtil.month()+"-"+ DateUtil.day();
        mBinding.tvTime.setText(currDay);
        mBinding.rvCommon.addItemDecoration(new DividerLinearItemDecoration(this, DividerLinearItemDecoration.VERTICAL_LIST, (int) getResources().getDimension(R.dimen.dimen_10), getResources().getColor(R.color.app_bg)));
        mBinding.rvCommon.setAdapter(mViewModel.getAdapter());
        setListener();
        mViewModel.getData(page++,currDay);
        mBinding.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtil calendarUtil=new CalendarUtil(UserScheduleActivity.this,currDay,dateInterface);
                calendarUtil.setCalendarDialog(mBinding.tvTime);
            }
        });
    }

    private void setListener() {
        mViewModel.registerRxBus(CommonEvent.class, commonEvent -> {
            switch (commonEvent.eventType) {
                case CommonEvent.FLAG_COMPLETE:
                    mBinding.swipeLayout.setRefreshing(false);
                    break;
                case CommonEvent.FLAG_NOCOMPLETE:
                    break;
            }
        });
        mBinding.swipeLayout.setOnRefreshListener(() -> {
            page = 1;
            mViewModel.getData(page++,  mBinding.tvTime.getText().toString().trim());
        });
        View emptyLayout = LayoutInflater.from(this).inflate(R.layout.layout_empty, null);
        mViewModel.getAdapter().setEmptyView(emptyLayout);
        mViewModel.getAdapter().setOnLoadMoreListener(() -> mViewModel.getData(page++,mBinding.tvTime.getText().toString().trim()), mBinding.rvCommon);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.unregisterRxBus();
    }
    @Override
    public void onDateOnClickListerner() {
        page = 1;
        mViewModel.getData(page++,mBinding.tvTime.getText().toString().trim());
    }
}