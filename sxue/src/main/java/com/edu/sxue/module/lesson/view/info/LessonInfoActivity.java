package com.edu.sxue.module.lesson.view.info;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.edu.sxue.R;
import com.edu.sxue.databinding.ActivityLessonInfoBinding;
import com.edu.sxue.databinding.LayoutHeaderImgBinding;
import com.edu.sxue.injector.component.DaggerLessonActivityComponent;
import com.edu.sxue.module.base.AppBar;
import com.edu.sxue.module.base.BaseActivity;
import com.edu.sxue.module.base.InfoItemViewModel;
import com.edu.sxue.module.lesson.model.LessonBean;
import com.edu.sxue.module.lesson.viewmodel.LessonInfoViewModel;

import javax.inject.Inject;

import base.lib.widget.recyclerview.DividerLinearItemDecoration;

/**
 * Administrator 在 2017/6/12 创建了它.
 */

public class LessonInfoActivity extends BaseActivity<ActivityLessonInfoBinding> {
    @Inject
    LessonInfoViewModel mViewModel;
    private LessonBean lessonBean;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_lesson_info;
    }

    @Override
    protected void initInjector() {
        DaggerLessonActivityComponent.builder().appComponent(getAppComponent()).activityComponent(getActivityComponent()).build().inject(this);
    }

    @Override
    protected void initViews() {
        lessonBean = (LessonBean) getIntent().getSerializableExtra("lesson");
        mBinding.appbar.setAppbar(new AppBar(lessonBean.name, true));
        mBinding.setViewModel(mViewModel);
        mViewModel.setLessonBean(lessonBean);
        mViewModel.getAdapter().addHeaderView(getHeaderView());
        mBinding.rvLessonInfo.setLayoutManager(new GridLayoutManager(this, 2));
        mBinding.rvLessonInfo.setAdapter(mViewModel.getAdapter());
        mBinding.rvLessonInfo.addItemDecoration(new DividerLinearItemDecoration(this, DividerLinearItemDecoration.VERTICAL_LIST, (int) getResources().getDimension(R.dimen.dimen_12), getResources().getColor(R.color.white)));
    }

    private View getHeaderView() {
        LayoutHeaderImgBinding databinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_header_img, null, false);
        InfoItemViewModel itemViewModel = new InfoItemViewModel("课程详情", R.mipmap.lesson_placeholder1);
        itemViewModel.url.set(lessonBean.pic);
        databinding.setViewModel(itemViewModel);
        return databinding.getRoot();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

}
