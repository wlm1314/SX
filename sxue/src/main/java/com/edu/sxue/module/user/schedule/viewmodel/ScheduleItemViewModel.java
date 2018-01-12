package com.edu.sxue.module.user.schedule.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.R;
import com.edu.sxue.app.App;
import com.edu.sxue.module.user.schedule.model.UserScheduleBean;
import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class ScheduleItemViewModel {
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> address = new ObservableField<>();
    public ObservableField<String> inst_name= new ObservableField<>();
    public ObservableField<String> cour_name = new ObservableField<>();
    public ScheduleItemViewModel(UserScheduleBean bean) {
        this.time.set(App.getInstance().getResources().getString(R.string.range_time, bean.start_time, bean.end_time));
        this.address.set(bean.position);
        this.inst_name.set(bean.inst_name);
        this.cour_name.set("课程名称:"+bean.cour_name);
    }

    public ReplyCommand itemCommand = new ReplyCommand(() -> {
    });
}
