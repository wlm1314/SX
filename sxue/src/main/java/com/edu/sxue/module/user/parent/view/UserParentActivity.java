package com.edu.sxue.module.user.parent.view;

import android.view.LayoutInflater;
import android.view.View;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityUserParentBinding;
import com.edu.sxue.injector.component.DaggerUserParentComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.parent.viewmodel.UserParentViewModel;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.edu.sxue.widget.EmptyLayout;

import javax.inject.Inject;

import base.lib.widget.recyclerview.DividerLinearItemDecoration;

import static base.lib.util.ActivityManager.getActivity;
import static com.edu.sxue.widget.EmptyLayout.STATUS_NO_DATA;

/**
 * 王少岩 在 2017/7/20 创建了它
 */

public class UserParentActivity extends BaseActivity<ActivityUserParentBinding> {
    @Inject
    UserParentViewModel mViewModel;
    @Inject
    AppBar mAppBar;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_user_parent;
    }

    @Override
    protected void initInjector() {
        DaggerUserParentComponent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.navigation.set(R.mipmap.ic_arrow_left_white);
        mBinding.setViewModel(mViewModel);
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.rvCommon.addItemDecoration(new DividerLinearItemDecoration(getActivity(), DividerLinearItemDecoration.VERTICAL_LIST, (int) getResources().getDimension(R.dimen.dimen_10), getResources().getColor(R.color.app_bg)));
        mBinding.rvCommon.setAdapter(mViewModel.getAdapter());
        setListener();
        mViewModel.getData();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

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
        mBinding.swipeLayout.setOnRefreshListener(() -> mViewModel.getData());
        View emptyLayout = LayoutInflater.from(this).inflate(R.layout.layout_empty, null);
        mViewModel.getAdapter().setEmptyView(emptyLayout);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.unregisterRxBus();
    }
}