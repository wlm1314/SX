package com.edu.sxue.module.user.settings.view;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivitySettingBinding;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.settings.viewmodel.SettingViewModel;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class SettingActivity extends BaseActivity<ActivitySettingBinding> {

    private SettingViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        AppBar appBar = new AppBar("设置",true);
        appBar.navigation.set(R.mipmap.ic_arrow_left_white);
        mViewModel = new SettingViewModel();
        mBinding.appbar.setAppbar(appBar);
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
