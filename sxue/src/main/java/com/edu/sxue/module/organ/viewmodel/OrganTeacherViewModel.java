package com.edu.sxue.module.organ.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.organ.model.OrganTeacherBean;

import java.util.ArrayList;

/**
 * 王少岩 在 2017/10/16 创建了它
 */

public class OrganTeacherViewModel {
    private RequestApi mRequestApi;
    private BaseAdapter<OrganTeacherItemViewModel> mAdapter;
    private ArrayList<OrganTeacherItemViewModel> mList = new ArrayList<>();

    public OrganTeacherViewModel(RequestApi api) {
        mRequestApi = api;
        mAdapter = new BaseAdapter<>(R.layout.adapter_organ_teacher_item, mList);
    }

    public BaseAdapter<OrganTeacherItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData(String id) {
        mRequestApi.getInst_teacher(HttpParams.getInfoParam(id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<OrganTeacherBean>() {
                    @Override
                    public void onNext(OrganTeacherBean teacherBean) {
                        mList.addAll(OrganTeacherItemViewModel.getList(teacherBean));
                        mAdapter.notifyDataSetChanged();
                    }
                });

    }
}
