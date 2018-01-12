package com.edu.sxue.module.lesson.viewmodel;

import android.databinding.ObservableField;

import com.edu.sxue.module.lesson.model.CourseCardBean;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;

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

    public LessonPackageItemViewModel(CourseCardBean.CourseCard courseCard) {
        mCourseCard = courseCard;
        name.set(courseCard.name);
        validity.set(courseCard.validity);
        validity_ttimes.set(courseCard.validity_ttimes);
        gifts.set(courseCard.gifts + "次");
        total.set(Integer.valueOf(courseCard.gifts) + Integer.valueOf(courseCard.validity_ttimes) + "次");
        course_price.set(courseCard.course_price + "元");
        quantity.set("(低于" + courseCard.quantity + "次按" + courseCard.quantity + "次扣费)");
    }

    public static ArrayList<LessonPackageItemViewModel> getList(CourseCardBean courseCardBean) {
        ArrayList<LessonPackageItemViewModel> mList = new ArrayList<>();
        for (CourseCardBean.CourseCard courseCard : courseCardBean.card_bag) {
            mList.add(new LessonPackageItemViewModel(courseCard));
        }
        return mList;
    }

    public ReplyCommand buyCommand = new ReplyCommand(() -> {
    });

}
