package com.edu.sxue.module.lesson.viewmodel;

import android.view.Gravity;

import com.edu.sxue.R;
import com.edu.sxue.api.HttpParams;
import com.edu.sxue.api.ProgressSubscriber;
import com.edu.sxue.api.RequestApi;
import com.edu.sxue.api.RetrofitService;
import com.edu.sxue.constant.Constants;
import com.edu.sxue.module.base.BaseAdapter;
import com.edu.sxue.module.base.InfoItemViewModel;
import com.edu.sxue.module.lesson.model.LessonBean;
import com.edu.sxue.module.lesson.model.LessonTryBean;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;

import base.lib.util.ActivityManager;
import base.lib.util.PreferencesUtils;

/**
 * 王少岩 在 2017/7/14 创建了它
 */

public class LessonInfoViewModel {
    private RequestApi mRequestApi;
    private LessonBean mLessonBean;
    private BaseAdapter<InfoItemViewModel> mAdapter;
    private ArrayList<InfoItemViewModel> mList = new ArrayList<>();

    public LessonInfoViewModel(RequestApi requestApi) {
        mRequestApi = requestApi;
        mAdapter = new BaseAdapter<>(R.layout.adapter_info_item, mList);
    }

    public BaseAdapter<InfoItemViewModel> getAdapter() {
        return mAdapter;
    }

    public void setLessonBean(LessonBean bean) {
        mLessonBean = bean;
        mList.addAll(InfoItemViewModel.getLessonInfos(bean));
    }

    public ReplyCommand signCommand = new ReplyCommand(() -> {
        NormalDialog dialog = new NormalDialog(ActivityManager.getActivity());
        dialog.isTitleShow(false)
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideBottomExit())
                .content("\n您的报名信息以发送给相关机构\n稍后我们会跟您联系\n")
                .contentTextSize(18)
                .contentTextColor(ActivityManager.getActivity().getResources().getColor(R.color.text_normal))
                .contentGravity(Gravity.CENTER)
                .btnNum(1)
                .btnText("确定")
                .btnTextSize(18)
                .btnTextColor(ActivityManager.getActivity().getResources().getColor(R.color.blue));
        mRequestApi.lessonTry(HttpParams.getLessonParam(mLessonBean.id, mLessonBean.institution_id, PreferencesUtils.getString(Constants.sUser_userid, "")))
                .compose(RetrofitService.applySchedulers())
                .map(new RetrofitService.HttpResultFunc<>())
                .subscribe(new ProgressSubscriber<ArrayList<LessonTryBean>>() {
                    @Override
                    public void onNext(ArrayList<LessonTryBean> lessonTryBeen) {
                        dialog.show();
                    }
                });
        dialog.setOnBtnClickL((OnBtnClickL) () -> dialog.dismiss());
    });
    public ReplyCommand orderCommand = new ReplyCommand(() -> {
    });
}