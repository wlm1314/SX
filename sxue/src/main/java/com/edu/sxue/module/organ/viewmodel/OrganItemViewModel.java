package com.edu.sxue.module.organ.viewmodel;

import android.databinding.ObservableField;
import android.os.Bundle;

import com.edu.sxue.module.organ.model.OrganBean;
import com.edu.sxue.module.organ.view.info.OrganInfoActivity;
import com.kelin.mvvmlight.command.ReplyCommand;

import base.lib.util.NavigateUtils;

/**
 * 王少岩 在 2017/7/14 创建了它
 */

public class OrganItemViewModel {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> logo = new ObservableField<>();
    public ObservableField<String> category = new ObservableField<>();
    public ObservableField<String> address = new ObservableField<>();
    private OrganBean mOrganBean;

    public OrganItemViewModel(OrganBean bean) {
        mOrganBean = bean;
        this.name.set(bean.name);
        this.phone.set(bean.phone);
        this.logo.set(bean.logo);
        this.category.set(bean.category);
        this.address.set(bean.address);
    }

    public ReplyCommand itemCommand = new ReplyCommand(() -> {
        Bundle bundle = new Bundle();
        bundle.putString("id", mOrganBean.id);
        bundle.putString("name", name.get());
        NavigateUtils.startActivity(OrganInfoActivity.class, bundle);
    });

}
