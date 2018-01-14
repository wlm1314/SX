package com.edu.sxue.module.user.vip.viewmedel;

import android.databinding.ObservableField;

import com.edu.sxue.R;
import com.edu.sxue.app.App;
import com.edu.sxue.module.user.vip.model.ConsumeBean;

/**
 * 王少岩 在 2017/10/11 创建了它
 */

public class ConsumeItemViewModel {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> price = new ObservableField<>();

    public ConsumeItemViewModel(ConsumeBean bean) {
            name.set(bean.cost_content_name);
        time.set(bean.card_rechargetime);
        price.set("￥"+ App.getInstance().getResources().getString(R.string.price, bean.card_rechargenum));
    }
}
