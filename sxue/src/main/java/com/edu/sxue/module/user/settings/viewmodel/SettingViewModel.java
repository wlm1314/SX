package com.edu.sxue.module.user.settings.viewmodel;

import android.content.Intent;

import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.user.login.view.LoginActivity;
import com.edu.sxue.module.user.updatepwd.view.UpdatePwdActivity;
import com.kelin.mvvmlight.command.ReplyCommand;

import base.lib.util.ActivityManager;
import base.lib.util.NavigateUtils;
import base.lib.util.PreferencesUtils;

/**
 * Created by Administrator on 2017/6/1.
 */

public class SettingViewModel {
    public ReplyCommand settingCommand = new ReplyCommand(() -> NavigateUtils.startActivity(UpdatePwdActivity.class));
    public ReplyCommand logoutCommand = new ReplyCommand(() -> {
        PreferencesUtils.saveBoolean(Constants.sUser_loginFlag, false);
        Intent intent = new Intent(ActivityManager.getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        ActivityManager.getActivity().startActivity(intent);
    });

}
