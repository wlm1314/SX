package com.edu.sxue.module.lesson.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.lesson.model.TeacherBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/27.
 */
public class LessonPowerViewModel {
    private RequestApi mRequestApi;
    private BaseAdapter<LessonTeacherItemViewModel> mAdapter;
    private ArrayList<LessonTeacherItemViewModel> mList = new ArrayList<>();

    public LessonPowerViewModel(RequestApi api) {
        mRequestApi = api;
        mAdapter = new BaseAdapter<>(R.layout.adapter_lesson_teacher_item, mList);
    }

    public BaseAdapter<LessonTeacherItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData(String id) {
        mRequestApi.getCourseTeacher(HttpParams.getInfoParam(id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<TeacherBean>() {
                    @Override
                    public void onNext(TeacherBean teacherBean) {
                        mList.addAll(LessonTeacherItemViewModel.getList(teacherBean));
                        mAdapter.notifyDataSetChanged();
                    }
                });

    }
}
