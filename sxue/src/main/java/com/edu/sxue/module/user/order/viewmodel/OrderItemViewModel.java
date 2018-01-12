package com.edu.sxue.module.user.order.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.edu.sxue.R;
import com.edu.sxue.app.App;
import com.edu.sxue.module.user.order.model.UserOrderBean;
import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class OrderItemViewModel {
    public ObservableField<String> pic = new ObservableField<>();
    public ObservableField<String> cour_name = new ObservableField<>();
    public ObservableField<String> inst_name = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> used_times = new ObservableField<>();
    public ObservableField<String> total_degree = new ObservableField<>();

    public OrderItemViewModel(UserOrderBean bean) {
        this.pic.set(bean.pic);
        this.cour_name.set(TextUtils.isEmpty(bean.cour_name) ? "" : bean.inst_name);
        this.inst_name.set(TextUtils.isEmpty(bean.inst_name) ? "" : bean.inst_name);
        this.time.set(App.getInstance().getResources().getString(R.string.time_length, TextUtils.isEmpty(bean.time) ? "" : bean.time));
        this.used_times.set(TextUtils.isEmpty(bean.used_times) ? "0" +"次": bean.used_times+"次");
        this.total_degree.set(TextUtils.isEmpty(bean.total_degree) ? "0"+"次" : bean.total_degree+"次");
    }

    public ReplyCommand itemCommand = new ReplyCommand(() -> {
    });
}
