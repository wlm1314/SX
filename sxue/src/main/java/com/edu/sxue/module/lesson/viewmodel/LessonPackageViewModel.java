package com.edu.sxue.module.lesson.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.lesson.model.CourseCardBean;
import com.edu.sxue.module.lesson.model.LessonBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/27.
 */
public class LessonPackageViewModel {
    private RequestApi mRequestApi;
    private BaseAdapter<LessonPackageItemViewModel> mAdapter;
    private ArrayList<LessonPackageItemViewModel> mList = new ArrayList<>();

    public LessonPackageViewModel(RequestApi api) {
        mRequestApi = api;
        mAdapter = new BaseAdapter<>(R.layout.adapter_lesson_package_item, mList);
    }

    public BaseAdapter<LessonPackageItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData(String id, LessonBean lessonBean) {
        mRequestApi.getCourseCard(HttpParams.getInfoParam(id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<CourseCardBean>() {
                    @Override
                    public void onNext(CourseCardBean courseCardBean) {
                        mList.addAll(LessonPackageItemViewModel.getList(courseCardBean, lessonBean, mRequestApi));
                        mAdapter.notifyDataSetChanged();
                    }
                });

    }
}
