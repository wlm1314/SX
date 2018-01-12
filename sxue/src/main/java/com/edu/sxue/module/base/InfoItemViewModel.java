package com.edu.sxue.module.base;

import android.databinding.ObservableField;
import android.os.Bundle;

import com.edu.sxue.R;
import com.edu.sxue.module.lesson.model.LessonBean;
import com.edu.sxue.module.lesson.view.info.LessonHonorActivity;
import com.edu.sxue.module.lesson.view.info.LessonIntroduceActivity;
import com.edu.sxue.module.lesson.view.info.LessonPackageActivity;
import com.edu.sxue.module.lesson.view.info.LessonPowerActivity;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;

import base.lib.util.NavigateUtils;

/**
 * 王少岩 在 2017/10/12 创建了它
 */

public class InfoItemViewModel {
    public ObservableField<String> text = new ObservableField<>("");
    public ObservableField<String> url = new ObservableField<>("");
    public ObservableField<Integer> placeholder = new ObservableField<>(R.mipmap.kcjs);
    private LessonBean mLessonBean;

    public InfoItemViewModel(String text, int placeholder) {
        this.text.set(text);
        this.placeholder.set(placeholder);
    }

    public InfoItemViewModel(LessonBean lessonBean, String text, int placeholder) {
        this.mLessonBean = lessonBean;
        this.text.set(text);
        this.placeholder.set(placeholder);
    }

    public static ArrayList<InfoItemViewModel> getLessonInfos(LessonBean lessonBean) {
        ArrayList<InfoItemViewModel> arrayList = new ArrayList<>();
        arrayList.add(new InfoItemViewModel(lessonBean, "课程介绍", R.mipmap.kcjs));
        arrayList.add(new InfoItemViewModel(lessonBean, "课程包订购", R.mipmap.kcbdg));
        arrayList.add(new InfoItemViewModel(lessonBean, "师资力量", R.mipmap.szll));
        arrayList.add(new InfoItemViewModel(lessonBean, "课程荣誉", R.mipmap.kcry));
        return arrayList;
    }

    public ReplyCommand itemCommand = new ReplyCommand(() -> {
        Bundle bundle = new Bundle();
        bundle.putSerializable("lesson", mLessonBean);
        switch (text.get()) {
            case "课程介绍":
                NavigateUtils.startActivity(LessonIntroduceActivity.class, bundle);
                break;
            case "课程包订购":
                NavigateUtils.startActivity(LessonPackageActivity.class, bundle);
                break;
            case "师资力量":
                NavigateUtils.startActivity(LessonPowerActivity.class, bundle);
                break;
            case "课程荣誉":
                NavigateUtils.startActivity(LessonHonorActivity.class, bundle);
                break;
        }
    });
}
