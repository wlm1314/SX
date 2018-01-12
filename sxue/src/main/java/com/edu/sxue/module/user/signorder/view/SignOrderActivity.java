package com.edu.sxue.module.user.signorder.view;

import android.view.LayoutInflater;
import android.view.View;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivitySignOrderBinding;
import com.edu.sxue.injector.component.DaggerSignOrderComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.signorder.viewmodel.SignOrderViewModel;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.edu.sxue.widget.EmptyLayout;

import javax.inject.Inject;

import base.lib.widget.recyclerview.DividerLinearItemDecoration;

import static com.edu.sxue.widget.EmptyLayout.STATUS_NO_DATA;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class SignOrderActivity extends BaseActivity<ActivitySignOrderBinding> {
    @Inject
    AppBar mAppBar;
    @Inject
    SignOrderViewModel mViewModel;
    private int page = 1;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_sign_order;
    }

    @Override
    protected void initInjector() {
        DaggerSignOrderComponent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.navigation.set(R.mipmap.ic_arrow_left_white);
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.setViewModel(mViewModel);
        mBinding.rvCommon.addItemDecoration(new DividerLinearItemDecoration(this, DividerLinearItemDecoration.VERTICAL_LIST, (int) getResources().getDimension(R.dimen.dimen_10), getResources().getColor(R.color.app_bg)));
        mBinding.rvCommon.setAdapter(mViewModel.getAdapter());
        setListener();
        mViewModel.getData(page++);
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
            mViewModel.getData(page++);
        });
        View emptyLayout = LayoutInflater.from(this).inflate(R.layout.layout_empty, null);
        mViewModel.getAdapter().setEmptyView(emptyLayout);
        mViewModel.getAdapter().setOnLoadMoreListener(() -> mViewModel.getData(page++), mBinding.rvCommon);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.unregisterRxBus();
    }
}
