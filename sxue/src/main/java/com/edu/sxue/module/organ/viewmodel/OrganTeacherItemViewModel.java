package com.edu.sxue.module.organ.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.module.organ.model.OrganTeacherBean;

import java.util.ArrayList;

/**
 * 王少岩 在 2017/10/12 创建了它
 */

public class OrganTeacherItemViewModel {
    public ObservableField<String> uri = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> des = new ObservableField<>("");

    private OrganTeacherBean.Teacher mTeacher;

    public OrganTeacherItemViewModel(OrganTeacherBean.Teacher teacher) {
        mTeacher = teacher;
        uri.set(teacher.pic);
        des.set(teacher.remarks);
        name.set(teacher.name);
    }

    public static ArrayList<OrganTeacherItemViewModel> getList(OrganTeacherBean teacherBean){
        ArrayList<OrganTeacherItemViewModel> list = new ArrayList<>();
        for (OrganTeacherBean.Teacher teacher:teacherBean.teacher){
            list.add(new OrganTeacherItemViewModel(teacher));
        }
        return list;
    }
}
