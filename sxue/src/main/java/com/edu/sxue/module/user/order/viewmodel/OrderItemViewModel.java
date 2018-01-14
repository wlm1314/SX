package com.edu.sxue.module.user.order.viewmodel;

import android.databinding.ObservableField;

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
        this.cour_name.set(bean.courseName);
        this.inst_name.set(bean.instName);
        this.time.set(App.getInstance().getResources().getString(R.string.time_length, bean.courseTime));
        this.used_times.set(bean.useNumber + "次");
        this.total_degree.set(bean.unUseNumber + "次");
    }

    public ReplyCommand itemCommand = new ReplyCommand(() -> {
    });
}
