package com.edu.sxue.module.lesson.viewmodel;

import android.databinding.ObservableField;

/**
 * 王少岩 在 2017/7/21 创建了它
 */

public class TeacherItemViewModel {
    public ObservableField<String> img = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> remarks = new ObservableField<>();

    public TeacherItemViewModel(String imgs, String name, String remarks) {
        this.img.set(imgs);
        this.name.set(name);
        this.remarks.set(remarks);
    }
}
