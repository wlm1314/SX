package com.edu.sxue.module.exercise.view;

import android.view.View;

import com.edu.sxue.R;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.databinding.ActivityExerciseInfoBinding;
import com.edu.sxue.injector.component.DaggerExerciseInfoComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.exercise.model.ExerciseInfoBean;
import com.edu.sxue.module.exercise.viewmodel.ExerciseInfoViewModel;
import com.edu.sxue.rxbus.event.CommonEvent;

import javax.inject.Inject;

import base.lib.util.PreferencesUtils;

/**
 * 王少岩 在 2017/7/20 创建了它
 */

public class ExerciseInfoActivity extends BaseActivity<ActivityExerciseInfoBinding> implements View.OnClickListener {
    @Inject
    ExerciseInfoViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_exercise_info;
    }

    @Override
    protected void initInjector() {
        DaggerExerciseInfoComponent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mBinding.setViewModel(mViewModel);
        mBinding.appbar.setAppbar(new AppBar(getIntent().getExtras().getString("name", ""), true));
        setListener();
        mViewModel.getData(getIntent().getExtras().getString("id", ""));
    }

    private void setListener() {
        mViewModel.registerRxBus(CommonEvent.class, commonEvent -> {
            switch (commonEvent.eventType) {
                case CommonEvent.FLAG_COMPLETE:
                    ExerciseInfoBean bean = mViewModel.getExerciseInfoBean();
                    mBinding.cvExerciseName.setText(bean.host_id);
                    mBinding.cvOrganTime.setText(getResources().getString(R.string.range_time, bean.start_time, bean.end_time));
                    mBinding.cvOrganAddress.setText(bean.place);
                    mBinding.cvOrganPerson.setText(bean.contact);
                    mBinding.cvOrganPhone.setText(bean.phone);
                    mBinding.tvExercise.setText(bean.detail);
                    break;
            }
        });
        setOnClickListener(this, mBinding.tvSign);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign:
                ExerciseInfoBean bean = mViewModel.getExerciseInfoBean();
                mViewModel.sign(bean.id, bean.host_id, PreferencesUtils.getString(Constants.sUser_userid, ""));
                break;
        }
    }
}
