package com.edu.sxue.module.organ.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.organ.model.OrganJghjBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/29.
 */
public class OrganEnvironmentViewModel {
    private RequestApi mRequestApi;
    private BaseAdapter<OrganJghjItemViewModel> mAdapter;
    private ArrayList<OrganJghjItemViewModel> mList = new ArrayList<>();

    public OrganEnvironmentViewModel(RequestApi requestApi) {
        mAdapter = new BaseAdapter<>(R.layout.adapter_organ_jghj, mList);
        mRequestApi = requestApi;
    }

    public BaseAdapter<OrganJghjItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData(String id) {
        mRequestApi.getOrgan_envoronment(HttpParams.getInfoParam(id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<OrganJghjBean>() {
                    @Override
                    public void onNext(OrganJghjBean organJghjBean) {
                        for (String img : organJghjBean.imgs) {
                            mList.add(new OrganJghjItemViewModel(img));
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

}