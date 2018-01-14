package com.edu.sxue.module.classes.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.classes.model.ClassBean;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;

import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;

/**
 * 王少岩 在 2017/10/17 创建了它
 */

public class ClassItemViewModel {
    public ReplyCommand itemCommand = new ReplyCommand(() -> {
    });
    public ObservableField<String> pic = new ObservableField<>("");
    public ObservableField<String> course = new ObservableField<>("");
    public ObservableField<String> institution_name = new ObservableField<>("");
    public ObservableField<String> start_end = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("");
    public ObservableField<String> member = new ObservableField<>("");
    public ObservableField<String> address = new ObservableField<>("");
    public ObservableField<Boolean> isclass = new ObservableField<>(true);
    public ObservableField<Boolean> isConfirm = new ObservableField<>(true);
    public ObservableField<Boolean> isCancel = new ObservableField<>(false);
    public ObservableField<String> confirmTxt = new ObservableField<>("");

    private String mType = "";
    private ClassBean mClassBean;
    private RequestApi mRequestApi;

    public ClassItemViewModel(RequestApi requestApi, ClassBean bean, String type) {
        mType = type;
        mClassBean = bean;
        mRequestApi = requestApi;
        pic.set(bean.pic);
        course.set("课程名：" + (TextUtils.isEmpty(bean.course) ? "" : bean.course));
        institution_name.set(bean.institution);
        String[] start = bean.start_time.split(" ");
        start_end.set(bean.start_time + "-" + bean.end_time.replace(start[0], ""));
        time.set(bean.time + " 分钟");
        member.set(bean.member);
        address.set(bean.room);
        isclass.set(!TextUtils.isEmpty(bean.member));
        isConfirm.set(!TextUtils.isEmpty(bean.member));
        isCancel.set(TextUtils.isEmpty(bean.member));
        confirmTxt.set(type.equals("class") ? "我要上课" : "报名试听");
    }

    public static ArrayList<ClassItemViewModel> getList(RequestApi requestApi, ArrayList<ClassBean> classBeens, String type) {
        ArrayList<ClassItemViewModel> list = new ArrayList<>();
        for (ClassBean bean : classBeens) {
            list.add(new ClassItemViewModel(requestApi, bean, type));
        }
        return list;
    }

    public ReplyCommand confirmCommand = new ReplyCommand(() -> {
        if (mType.equals("class"))
            mRequestApi.addReserve(HttpParams.addReserve(mClassBean.course_id, mClassBean.institution_id, PreferencesUtils.getString(Constants.sUser_userid, ""), mClassBean.start_time, mClassBean.end_time))
                    .compose(RetrofitService.applySchedulers())
                    .subscribe(new ProgressSubscriber<HttpResult>() {
                        @Override
                        public void onNext(HttpResult httpResult) {
                            if (httpResult.isSuccess()) {
                                ToastUtils.showToast("报名成功");
                                isConfirm.set(false);
                            } else
                                ToastUtils.showToast(httpResult.getInfo());
                        }
                    });
        else{
            mRequestApi.lessonTry(HttpParams.lessonTry(mClassBean.course_id, mClassBean.room_reserve_id, mClassBean.institution_id, PreferencesUtils.getString(Constants.sUser_userid, "")))
                    .compose(RetrofitService.applySchedulers())
                    .subscribe(new ProgressSubscriber<HttpResult>() {
                        @Override
                        public void onNext(HttpResult httpResult) {
                            if (httpResult.isSuccess()) {
                                ToastUtils.showToast("报名成功");
                                isConfirm.set(false);
                            } else
                                ToastUtils.showToast(httpResult.getInfo());
                        }
                    });

        }
    });
    public ReplyCommand cancelCommand = new ReplyCommand(() -> {
        mRequestApi.delReserve(HttpParams.getLessonParam(mClassBean.course_id, mClassBean.institution_id, PreferencesUtils.getString(Constants.sUser_userid, "")))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult>() {
                    @Override
                    public void onNext(HttpResult httpResult) {
                        if (httpResult.isSuccess()) {
                            ToastUtils.showToast("取消成功");
                            isCancel.set(false);
                        }
                    }
                });
    });
}
