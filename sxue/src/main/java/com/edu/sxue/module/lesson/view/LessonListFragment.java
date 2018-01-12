package com.edu.sxue.module.lesson.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.edu.sxue.R;
import com.edu.sxue.databinding.FragmentLessonListBinding;
import com.edu.sxue.injector.component.DaggerLessonFragmentComponent;
import com.edu.sxue.module.base.BaseFragment;
import com.edu.sxue.module.lesson.viewmodel.LessonViewModel;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.edu.sxue.widget.EmptyLayout;

import javax.inject.Inject;

import base.lib.widget.recyclerview.DividerLinearItemDecoration;

import static com.edu.sxue.widget.EmptyLayout.STATUS_NO_DATA;

/**
 * Administrator 在 2017/6/4 创建了它.
 */

public class LessonListFragment extends BaseFragment<FragmentLessonListBinding> {
    @Inject
    LessonViewModel mViewModel;
    private String keyword;
    private int page = 1;

    public static LessonListFragment newInstance(String keyword) {
        LessonListFragment fragment = new LessonListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_lesson_list;
    }

    @Override
    protected void initInjector() {
        DaggerLessonFragmentComponent.builder().appComponent(getAppComponent()).fragmentComponent(getFragmentComponent()).build().inject(this);
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
