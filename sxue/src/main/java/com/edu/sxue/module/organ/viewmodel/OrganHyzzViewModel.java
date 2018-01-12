package com.edu.sxue.module.organ.viewmodel;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.organ.model.OrganCertBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/29.
 */
public class OrganHyzzViewModel {
    private RequestApi mRequestApi;
    private ArrayList<OrganJghjItemViewModel> mList = new ArrayList<>();
    private BaseAdapter<OrganJghjItemViewModel> mAdapter;

    public OrganHyzzViewModel(RequestApi requestApi) {
        mAdapter = new BaseAdapter<>(R.layout.adapter_organ_hyzz, mList);
        mRequestApi = requestApi;
    }

    public BaseAdapter<OrganJghjItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void getData(String id) {
        mRequestApi.getInst_certificate(HttpParams.getInfoParam(id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<OrganCertBean>() {
                    @Override
                    public void onNext(OrganCertBean organCertBean) {
                        for (String img : organCertBean.certificate_img) {
                            mList.add(new OrganJghjItemViewModel(img));
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

}