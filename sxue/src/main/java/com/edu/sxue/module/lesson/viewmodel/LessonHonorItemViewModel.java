package com.edu.sxue.module.lesson.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.module.lesson.model.LessonHonorBean;

import java.util.ArrayList;

/**
 * 王少岩 在 2017/10/12 创建了它
 */

public class LessonHonorItemViewModel {
    public ObservableField<String> uri = new ObservableField<>("");

    public LessonHonorItemViewModel(String uri) {
        this.uri.set(uri);
    }

    public static ArrayList<LessonHonorItemViewModel> getList(LessonHonorBean lessonHonorBean){
        ArrayList<LessonHonorItemViewModel> list = new ArrayList<>();
        for (String uri:lessonHonorBean.credit_img){
            list.add(new LessonHonorItemViewModel(uri));
        }
        return list;
    }
}
