package com.edu.sxue.module.lesson.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.lesson.model.IntroduceBean;
/**
 * Created by Administrator on 2017/9/27.
 */
public class LessonIntroduceViewModel {
    private RequestApi mRequestApi;
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> des = new ObservableField<>();

    public LessonIntroduceViewModel(RequestApi api) {
        mRequestApi = api;
    }

    public void getData(String id) {
        mRequestApi.getCourseContent(HttpParams.getInfoParam(id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<IntroduceBean>() {
                    @Override
                    public void onNext(IntroduceBean introduceBean) {
                        name.set(introduceBean.name);
                        des.set(introduceBean.content);
                    }
                });

    }
}
