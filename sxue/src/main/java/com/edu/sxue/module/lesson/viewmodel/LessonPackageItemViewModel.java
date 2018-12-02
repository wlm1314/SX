package com.edu.sxue.module.lesson.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.api.entity.HttpResult;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.lesson.model.CourseCardBean;
import com.edu.sxue.module.lesson.model.LessonBean;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;

import base.lib.util.PreferencesUtils;
import base.lib.util.ToastUtils;

/**
 * 王少岩 在 2017/10/12 创建了它
 */

public class LessonPackageItemViewModel {
    public ObservableField<String> name = new ObservableField<>("");//课程名
    public ObservableField<String> validity = new ObservableField<>("");//有效期
    public ObservableField<String> validity_ttimes = new ObservableField<>("");//卡次数
    public ObservableField<String> gifts = new ObservableField<>("");//赠送次数
    public ObservableField<String> total = new ObservableField<>("");//总次数
    public ObservableField<String> course_price = new ObservableField<>("");//价格
    public ObservableField<String> quantity = new ObservableField<>("");//最低次数

    private CourseCardBean.CourseCard mCourseCard;
    private LessonBean mLessonBean;
    private RequestApi mRequest;

    public LessonPackageItemViewModel(CourseCardBean.CourseCard courseCard, LessonBean lessonBean, RequestApi requestApi) {
        mRequest = requestApi;
        mLessonBean = lessonBean;
        mCourseCard = courseCard;
        name.set(courseCard.name);
        validity.set(courseCard.validity);
        validity_ttimes.set(courseCard.validity_ttimes);
        gifts.set(courseCard.gifts + "次");
        total.set(Integer.valueOf(courseCard.gifts) + Integer.valueOf(courseCard.validity_ttimes) + "次");
        course_price.set(courseCard.course_price + "元");
        quantity.set("(低于" + courseCard.quantity + "次按" + courseCard.quantity + "次扣费)");
    }

    public static ArrayList<LessonPackageItemViewModel> getList(CourseCardBean courseCardBean, LessonBean lessonBean, RequestApi requestApi) {
        ArrayList<LessonPackageItemViewModel> mList = new ArrayList<>();
        for (CourseCardBean.CourseCard courseCard : courseCardBean.card_bag) {
            mList.add(new LessonPackageItemViewModel(courseCard, lessonBean, requestApi));
        }
        return mList;
    }

    public ReplyCommand buyCommand = new ReplyCommand(() -> {
        mRequest.orderLesson(HttpParams.orderLesson(
                mLessonBean.institution_id, mLessonBean.institution_name,
                mLessonBean.id, mCourseCard.course_price, mLessonBean.name,
                mCourseCard.id, mCourseCard.name, PreferencesUtils.getString(Constants.sUser_userid, ""),
                PreferencesUtils.getString(Constants.sUser_phone, "")))
                .compose(RetrofitService.applySchedulers())
                .subscribe(new ProgressSubscriber<HttpResult>() {
                    @Override
                    public void onNext(HttpResult httpResult) {
                        ToastUtils.showToast(httpResult.getInfo());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtils.showToast("订购失败");
                    }
                });

    });

}
