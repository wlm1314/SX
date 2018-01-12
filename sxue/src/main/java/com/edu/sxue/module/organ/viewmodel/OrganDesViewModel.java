package com.edu.sxue.module.organ.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.module.organ.model.OrgenDesBean;

/**
 * 王少岩 在 2017/7/14 创建了它
 */

public class OrganDesViewModel {
    private RequestApi mRequestApi;
    public ObservableField<String> des = new ObservableField<>("");
    public ObservableField<String> address = new ObservableField<>("");
    public ObservableField<String> website = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> phone = new ObservableField<>("");

    public OrganDesViewModel(RequestApi requestApi) {
        mRequestApi = requestApi;
    }

    public void getData(String id) {
        mRequestApi.getOrganInstContent(HttpParams.getInfoParam(id))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<OrgenDesBean>() {
                    @Override
                    public void onNext(OrgenDesBean orgenDesBean) {
                        des.set(orgenDesBean.about);
                        address.set(orgenDesBean.address);
                        website.set(orgenDesBean.web_site);
                        name.set(orgenDesBean.incharge_person);
                        phone.set(orgenDesBean.phone);
                    }
                });
    }

}