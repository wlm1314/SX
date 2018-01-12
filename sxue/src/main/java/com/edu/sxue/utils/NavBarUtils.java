package com.edu.sxue.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.edu.sxue.R;
import com.edu.sxue.app.App;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.List;

/**
 * 王少岩 在 2017/9/14 创建了它
 */

public class NavBarUtils {

    //tab导航栏
    public static void setTabs(MagicIndicator magicIndicator, final List<String> tabTitles, final ViewPager viewPager) {
        CommonNavigator commonNavigator = new CommonNavigator(App.getInstance());
        commonNavigator.setAdjustMode(true);  // 自适应模式
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabTitles == null ? 0 : tabTitles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(App.getInstance().getResources().getColor(R.color.text_normal));
                colorTransitionPagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, App.getInstance().getResources().getDimension(R.dimen.text_size_14));
                colorTransitionPagerTitleView.setSelectedColor(App.getInstance().getResources().getColor(R.color.blue));
                colorTransitionPagerTitleView.setText(tabTitles.get(index));
                colorTransitionPagerTitleView.setOnClickListener(view -> viewPager.setCurrentItem(index));
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setLineHeight(UIUtil.dip2px(App.getInstance(), 2));
                indicator.setColors(App.getInstance().getResources().getColor(R.color.blue));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

}
