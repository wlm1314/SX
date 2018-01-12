package com.edu.sxue.injector.component;

import android.support.v4.app.Fragment;

import com.edu.sxue.injector.module.FragmentModule;

import dagger.Component;

/**
 * 王少岩 在 2017/3/15 创建了它
 */
@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    // provide
    Fragment getFragMent();
}
