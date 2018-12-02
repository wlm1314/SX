package com.edu.sxue.module.lesson.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.lesson.model.LessonHonorBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/27.
 */
public class LessonHonorViewModel {
    private RequestApi mRequestApi;
    private BaseAdapter<LessonHonorItemViewModel> mAdapter;
    private ArrayList<LessonHonorItemViewModel> mList = new ArrayList<>();

    public LessonHonorViewModel(RequestApi api) {
        mRequestApi = api;
        mAdapter = new BaseAdapter<>(R.layout.adapter_lesson_honor_item, mList);
    }

    public BaseAdapter<LessonHonorItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData(String id) {
        mRequestApi.getCourseHonor(HttpParams.getInfoParam(id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<LessonHonorBean>() {
                    @Override
                    public void onNext(LessonHonorBean lessonHonorBean) {
                        mList.addAll(LessonHonorItemViewModel.getList(lessonHonorBean));
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }
}
