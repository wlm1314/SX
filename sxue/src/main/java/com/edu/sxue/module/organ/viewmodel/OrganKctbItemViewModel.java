package com.edu.sxue.module.organ.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.module.organ.model.LessonsBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class OrganKctbItemViewModel {
    public ObservableField<String> imgUrl = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> institution_name = new ObservableField<>("");
    public ObservableField<String> course_time = new ObservableField<>("");

    public OrganKctbItemViewModel(LessonsBean lessonsBean) {
        imgUrl.set(lessonsBean.pic);
        name.set(lessonsBean.name);
        institution_name.set(lessonsBean.institution_name);
        course_time.set(lessonsBean.course_time);
    }

    public static ArrayList<OrganKctbItemViewModel> getList(ArrayList<LessonsBean> arrayList){
        ArrayList<OrganKctbItemViewModel> list = new ArrayList<>();
        for (LessonsBean bean:arrayList){
            list.add(new OrganKctbItemViewModel(bean));
        }
        return list;
    }
}
