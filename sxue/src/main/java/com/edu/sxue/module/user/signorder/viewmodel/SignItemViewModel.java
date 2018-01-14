package com.edu.sxue.module.user.signorder.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.edu.sxue.module.user.signorder.model.SignOrderBean;
import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class SignItemViewModel {
    public ObservableField<String> pic = new ObservableField<>();
    public ObservableField<String> cour_name = new ObservableField<>();
    public ObservableField<String> inst_name = new ObservableField<>();
    public ObservableField<String> start_time = new ObservableField<>();
    public ObservableField<String> room_number = new ObservableField<>();
    public SignItemViewModel(SignOrderBean bean) {
        this.pic.set(bean.pic);
        this.cour_name.set(TextUtils.isEmpty(bean.courseName) ? "课程名:" : "课程名:"+bean.courseName);
        this.inst_name.set(TextUtils.isEmpty(bean.instName) ? "" : bean.instName);
//        this.time.set(App.getInstance().getResources().getString(R.string.time_length, TextUtils.isEmpty(bean.time) ? "" : bean.time));
        this.start_time.set(TextUtils.isEmpty(bean.time) ? "" : bean.time);
//        this.room_number.set(TextUtils.isEmpty(bean.room_number) ? "" : bean.room_number);
    }

    public ReplyCommand itemCommand = new ReplyCommand(() -> {
    });
}
