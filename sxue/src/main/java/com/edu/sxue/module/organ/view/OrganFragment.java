package com.edu.sxue.module.organ.view;

import com.edu.sxue.R;
import com.edu.sxue.databinding.FragmentOrganBinding;
import com.edu.sxue.injector.component.DaggerOrganFragmentComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseFragment;
import com.edu.sxue.module.base.FragmentAdapter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/6/4.
 */

public class OrganFragment extends BaseFragment<FragmentOrganBinding> {
    @Inject
    AppBar mAppBar;
    @Inject
    FragmentAdapter mFragmentAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_organ;
    }

    @Override
    protected void initInjector() {
        DaggerOrganFragmentComponent.builder().appComponent(getAppComponent()).fragmentComponent(getFragmentComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.setTitle("机构");
        mAppBar.showLeft(false);
        mBinding.appbar.setAppbar(mAppBar);
        mBinding.vpView.setAdapter(mFragmentAdapter);
        mBinding.tablayout.setupWithViewPager(mBinding.vpView);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
