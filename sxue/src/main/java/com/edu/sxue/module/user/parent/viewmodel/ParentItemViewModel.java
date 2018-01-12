package com.edu.sxue.module.user.parent.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.module.user.parent.model.UserParentBean;

/**
 * 王少岩 在 2017/7/19 创建了它
 */

public class ParentItemViewModel {
    public ObservableField<String> img = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> relation = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();

    public ParentItemViewModel(UserParentBean bean) {
        this.img.set(bean.pic);
        this.name.set(bean.name);
        this.relation.set("关系：" + bean.relation);
        this.phone.set(bean.phone);
    }
}
