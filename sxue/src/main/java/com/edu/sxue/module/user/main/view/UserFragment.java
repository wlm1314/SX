package com.edu.sxue.module.user.main.view;

import android.view.View;

import com.edu.sxue.R;
import com.edu.sxue.databinding.FragmentUserBinding;
import com.edu.sxue.injector.component.DaggerUserInfoComponent;
import com.edu.sxue.module.base.BaseFragment;
import com.edu.sxue.module.user.MeAdapter;
import com.edu.sxue.module.user.exercise.view.UserExerciseActivity;
import com.edu.sxue.module.user.main.model.UserInfoBean;
import com.edu.sxue.module.user.main.viewmodel.UserViewModel;
import com.edu.sxue.module.user.order.view.UserOrderActivity;
import com.edu.sxue.module.user.parent.view.UserParentActivity;
import com.edu.sxue.module.user.profile.view.UserProfileActivity;
import com.edu.sxue.module.user.schedule.view.UserScheduleActivity;
import com.edu.sxue.module.user.settings.view.SettingActivity;
import com.edu.sxue.module.user.signorder.view.SignOrderActivity;
import com.edu.sxue.module.user.vip.view.VipActivity;
import com.edu.sxue.rxbus.event.CommonEvent;

import javax.inject.Inject;

import base.lib.util.NavigateUtils;

/**
 * Created by Administrator on 2017/6/4.
 */

public class UserFragment extends BaseFragment<FragmentUserBinding> implements View.OnClickListener {
    @Inject
    UserViewModel mViewModel;
    private UserInfoBean mBean;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initInjector() {
        DaggerUserInfoComponent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mBinding.setViewModel(mViewModel);
        mBinding.gvItem.setAdapter(new MeAdapter(getActivity()));
        mBinding.gvItem.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    NavigateUtils.startActivity(VipActivity.class);
                    break;
                case 1:
                    NavigateUtils.startActivity(SignOrderActivity.class);
                    break;
                case 2:
                    NavigateUtils.startActivity(UserOrderActivity.class);
                    break;
                case 3:
                    NavigateUtils.startActivity(UserScheduleActivity.class);
                    break;
                case 4:
                    NavigateUtils.startActivity(UserExerciseActivity.class);
                    break;
                case 5:
                    NavigateUtils.startActivity(UserParentActivity.class);
                    break;
                case 6:
                    NavigateUtils.startActivity(SettingActivity.class);
                    break;
            }
        });
        setListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getData();
    }

    private void setListener() {
        mViewModel.registerRxBus(CommonEvent.class, commonEvent -> {
            switch (commonEvent.eventType) {
                case CommonEvent.FLAG_COMPLETE:
                    mBean = mViewModel.getUserInfoBean();
                    break;
            }
        });
        setOnClickListener(this, mBinding.ivEdit);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_edit:
                NavigateUtils.startActivity(UserProfileActivity.class);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.unregisterRxBus();
    }
}
