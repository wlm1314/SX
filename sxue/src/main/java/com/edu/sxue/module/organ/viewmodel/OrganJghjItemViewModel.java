package com.edu.sxue.module.organ.viewmodel;

import android.databinding.ObservableField;

/**
 * Created by Administrator on 2017/10/14 0014.
 */

public class OrganJghjItemViewModel {
    public ObservableField<String> imgUrl = new ObservableField<>();

    public OrganJghjItemViewModel(String img) {
        imgUrl.set(img);
    }
}
