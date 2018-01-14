package com.edu.sxue.module.user.vip.view;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityVipBinding;
import com.edu.sxue.injector.component.DaggerVipCardCompoent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.vip.viewmedel.VipViewModel;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.edu.sxue.widget.EmptyLayout;

import javax.inject.Inject;

import static com.edu.sxue.widget.EmptyLayout.STATUS_NO_DATA;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class VipActivity extends BaseActivity<ActivityVipBinding> {
    @Inject
    VipViewModel mViewModel;
    @Inject
    AppBar mAppBar;
    private int page = 1;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_vip;
    }

    @Override
    protected void initInjector() {
        DaggerVipCardCompoent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.navigation.set(R.mipmap.ic_arrow_left_white);
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.setViewModel(mViewModel);
        mBinding.rvConsume.setAdapter(mViewModel.getAdapter());
        setListener();
        mViewModel.getConseme(page++);
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
            mViewModel.getConseme(page++);
        });
        EmptyLayout emptyLayout = new EmptyLayout(this);
        emptyLayout.setEmptyMessage("暂无数据");
        emptyLayout.setEmptyStatus(STATUS_NO_DATA);
        mViewModel.getAdapter().setEmptyView(emptyLayout);
        mViewModel.getAdapter().setOnLoadMoreListener(() -> mViewModel.getConseme(page++), mBinding.rvConsume);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.unregisterRxBus();
    }
}
