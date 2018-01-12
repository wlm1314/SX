package com.edu.sxue.module.lesson.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.module.lesson.model.TeacherBean;

import java.util.ArrayList;

/**
 * 王少岩 在 2017/10/12 创建了它
 */

public class LessonTeacherItemViewModel {
    public ObservableField<String> uri = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> des = new ObservableField<>("");

    private TeacherBean.Teacher mTeacher;

    public LessonTeacherItemViewModel(TeacherBean.Teacher teacher) {
        mTeacher = teacher;
        uri.set(teacher.pic);
        des.set(teacher.remarks);
        name.set(teacher.name);
    }

    public static ArrayList<LessonTeacherItemViewModel> getList(TeacherBean teacherBean){
        ArrayList<LessonTeacherItemViewModel> list = new ArrayList<>();
        for (TeacherBean.Teacher teacher:teacherBean.teacher){
            list.add(new LessonTeacherItemViewModel(teacher));
        }
        return list;
    }
}
