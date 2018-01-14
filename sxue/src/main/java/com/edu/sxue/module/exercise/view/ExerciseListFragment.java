package com.edu.sxue.module.exercise.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.edu.sxue.R;
import com.edu.sxue.databinding.FragmentExerciseListBinding;
import com.edu.sxue.injector.component.DaggerExerciseListComponent;
import com.edu.sxue.module.base.BaseFragment;
import com.edu.sxue.module.exercise.viewmodel.ExerciseViewModel;
import com.edu.sxue.rxbus.event.CommonEvent;

import javax.inject.Inject;

import base.lib.widget.recyclerview.DividerLinearItemDecoration;

/**
 * Administrator 在 2017/6/4 创建了它.
 */

public class ExerciseListFragment extends BaseFragment<FragmentExerciseListBinding> {
    @Inject
    ExerciseViewModel mViewModel;
    private String keyword;
    private int page = 1;

    public static ExerciseListFragment newInstance(String keyword) {
        ExerciseListFragment fragment = new ExerciseListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_exercise_list;
    }

    @Override
    protected void initInjector() {
        DaggerExerciseListComponent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        keyword = getArguments().getString("keyword");
        mBinding.setViewModel(mViewModel);
        mBinding.rvCommon.addItemDecoration(new DividerLinearItemDecoration(getActivity(), DividerLinearItemDecoration.VERTICAL_LIST, (int) getResources().getDimension(R.dimen.dimen_10), getResources().getColor(R.color.app_bg)));
        mViewModel.getAdapter().addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.layout_diver, null));
        mBinding.rvCommon.setAdapter(mViewModel.getAdapter());
        setListener();
        mViewModel.getData(keyword, page++);
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
            mViewModel.getData(keyword, page++);
        });
        View emptyLayout = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty, null);
        mViewModel.getAdapter().setEmptyView(emptyLayout);
        mViewModel.getAdapter().setOnLoadMoreListener(() -> mViewModel.getData(keyword, page++), mBinding.rvCommon);
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
