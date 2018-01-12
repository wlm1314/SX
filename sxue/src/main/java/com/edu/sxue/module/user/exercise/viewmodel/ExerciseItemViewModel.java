package com.edu.sxue.module.user.exercise.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.edu.sxue.R;
import com.edu.sxue.app.App;
import com.edu.sxue.module.user.exercise.model.UserExerciseBean;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class ExerciseItemViewModel {
    public ObservableField<String> img = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> inst_name = new ObservableField<>();

    public ExerciseItemViewModel(UserExerciseBean bean) {
        this.img.set(bean.activity_img);
        this.name.set(bean.activity_name);
        this.time.set(App.getInstance().getResources().getString(R.string.range_time,
                TextUtils.isEmpty(bean.activity_start_time) ? "" : bean.activity_start_time,
                TextUtils.isEmpty(bean.activity_end_time) ? "" : bean.activity_end_time));
        //this.inst_name.set(bean.inst_name);
        if (TextUtils.isEmpty(bean.inst_name)){
            this.inst_name.set("");
        }else{
            this.inst_name.set(bean.inst_name);
        }
    }
}
