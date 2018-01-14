package com.edu.sxue.module.classes.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.edu.sxue.R;
import com.edu.sxue.databinding.FragmentClassListBinding;
import com.edu.sxue.injector.component.DaggerClassFragmentComponent;
import com.edu.sxue.module.base.BaseFragment;
import com.edu.sxue.module.classes.viewmodel.ClassViewModel;
import com.edu.sxue.rxbus.event.CommonEvent;

import javax.inject.Inject;

import base.lib.widget.recyclerview.DividerLinearItemDecoration;

/**
 * 王少岩 在 2017/10/17 创建了它
 */

public class ClassListFragment extends BaseFragment<FragmentClassListBinding> {

    @Inject
    ClassViewModel mViewModel;
    private int page = 1;

    public static ClassListFragment newIntance(String flag, String type) {
        Bundle bundle = new Bundle();
        bundle.putString("flag", flag);
        bundle.putString("type", type);
        ClassListFragment fragment = new ClassListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_class_list;
    }

    @Override
    protected void initInjector() {
        DaggerClassFragmentComponent.builder().appComponent(getAppComponent()).fragmentComponent(getFragmentComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mBinding.setViewModel(mViewModel);
        mBinding.rvCommon.addItemDecoration(new DividerLinearItemDecoration(getActivity(), DividerLinearItemDecoration.VERTICAL_LIST, (int) getResources().getDimension(R.dimen.dimen_10), getResources().getColor(R.color.app_bg)));
        mViewModel.getAdapter().addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.layout_diver, null));
        mBinding.rvCommon.setAdapter(mViewModel.getAdapter());
        mViewModel.getData(getArguments().getString("flag"), getArguments().getString("type"), page++);
        setListener();
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
            mViewModel.getData(getArguments().getString("flag"), getArguments().getString("type"), page++);
        });
        View emptyLayout = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty, null);
        mViewModel.getAdapter().setEmptyView(emptyLayout);
        mViewModel.getAdapter().setOnLoadMoreListener(() -> mViewModel.getData(getArguments().getString("flag"), getArguments().getString("type"), page++));
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
