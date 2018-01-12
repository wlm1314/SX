package com.edu.sxue.module.user.login.view;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityLoginBinding;
import com.edu.sxue.injector.component.DaggerLoginCompoent;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.user.login.viewmodel.LoginViewModel;

import javax.inject.Inject;


/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    @Inject
    LoginViewModel mViewModel;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {
        DaggerLoginCompoent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        mBinding.setViewModel(mViewModel);
        mBinding.ivShowPwd.setOnClickListener(v->{
            TransformationMethod type = mBinding.etUserpass.getTransformationMethod();
            if (PasswordTransformationMethod.getInstance().equals(type)) {
                mBinding.etUserpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                mBinding.etUserpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }


    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
