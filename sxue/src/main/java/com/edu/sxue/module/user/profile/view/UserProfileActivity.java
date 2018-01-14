package com.edu.sxue.module.user.profile.view;

import android.content.Intent;
import android.view.View;

import com.bigkoo.pickerview.TimePickerView;
import com.edu.sxue.R;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.databinding.ActivityUserdataBinding;
import com.edu.sxue.injector.component.DaggerUserProfileComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.profile.model.UserProfileBean;
import com.edu.sxue.module.user.profile.viewmodel.UserProfileViewModel;
import com.edu.sxue.rxbus.event.CommonEvent;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.widget.NormalListDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import base.lib.util.PreferencesUtils;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class UserProfileActivity extends BaseActivity<ActivityUserdataBinding> implements View.OnClickListener {
    @Inject
    UserProfileViewModel mViewModel;
    @Inject
    AppBar mAppBar;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_userdata;
    }

    @Override
    protected void initInjector() {
        DaggerUserProfileComponent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mAppBar.setRight("确定", () ->
                mViewModel.updateUserProfile(PreferencesUtils.getString(Constants.sUser_userid, ""),
                        mViewModel.imgUrl.get(),
                        mBinding.cvNick.getInfoText(),
                        mBinding.cvSex.getInfoText().equals("男") ? "1" : "2",
                        mBinding.cvBirthday.getInfoText(),
                        mBinding.cvHeight.getInfoText(),
                        mBinding.cvAddress.getInfoText(),
                        mBinding.cvWeight.getInfoText(),
                        mBinding.cvPhone.getInfoText(),
                        mBinding.cvSchool.getInfoText()));
        mBinding.setViewModel(mViewModel);
        mBinding.appbar.setAppbar(mAppBar);
        setListener();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void setListener() {
        mViewModel.registerRxBus(CommonEvent.class, commonEvent -> {
            switch (commonEvent.eventType) {
                case CommonEvent.FLAG_COMPLETE:
                    UserProfileBean bean = mViewModel.getUserProfileBean();
                    mBinding.cvNick.setInfoText(bean.name);
                    mBinding.cvSex.setInfoText(bean.sex);
                    mBinding.cvBirthday.setInfoText(bean.birthdate);
                    mBinding.cvAddress.setInfoText(bean.address);
                    mBinding.cvHeight.setInfoText(bean.health);
                    mBinding.cvWeight.setInfoText(bean.weight);
                    mBinding.cvPhone.setInfoText(bean.phone);
                    mBinding.cvSchool.setInfoText(bean.school);
                    break;
            }
        });

        setOnClickListener(this, mBinding.cvSex, mBinding.cvBirthday);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.unregisterRxBus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_sex:
                ArrayList<DialogMenuItem> menuItems = new ArrayList<>();
                menuItems.add(new DialogMenuItem("男", R.mipmap.boy));
                menuItems.add(new DialogMenuItem("女", R.mipmap.girl));
                final NormalListDialog dialog = new NormalListDialog(this, menuItems);
                dialog.title("请选择")//
                        .showAnim(new BounceTopEnter())
                        .dismissAnim(new SlideBottomExit())
                        .show();
                dialog.setOnOperItemClickL((parent, view, position, id) -> {
                    mBinding.cvSex.setInfoText(menuItems.get(position).mOperName);
                    dialog.dismiss();
                });
                break;
            case R.id.cv_birthday:
                TimePickerView timePickerView = new TimePickerView(UserProfileActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
                timePickerView.setCyclic(false);
                timePickerView.setTime(new Date());
                timePickerView.setTitle("设置生日");
                timePickerView.setOnTimeSelectListener(date -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    mBinding.cvBirthday.setInfoText(sdf.format(date));
                });
                timePickerView.show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }
}
