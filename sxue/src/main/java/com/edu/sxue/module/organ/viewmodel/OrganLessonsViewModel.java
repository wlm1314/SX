package com.edu.sxue.module.organ.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.organ.model.LessonsBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/29.
 */
public class OrganLessonsViewModel {
    private RequestApi mRequestApi;
    private BaseAdapter<OrganKctbItemViewModel> mAdapter;
    private ArrayList<OrganKctbItemViewModel> mList = new ArrayList<>();

    public OrganLessonsViewModel(RequestApi requestApi) {
        mRequestApi = requestApi;
        mAdapter = new BaseAdapter<>(R.layout.adapter_organ_lesson, mList);
    }

    public BaseAdapter<OrganKctbItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData(String id) {
        mRequestApi.getOrganAppCourseApi(HttpParams.getInfoParam(id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<ArrayList<LessonsBean>>() {
                    @Override
                    public void onNext(ArrayList<LessonsBean> arrayList) {
                        mList.addAll(OrganKctbItemViewModel.getList(arrayList));
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

}