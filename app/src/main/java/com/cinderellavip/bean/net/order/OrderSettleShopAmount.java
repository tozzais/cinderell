package com.cinderellavip.bean.net.order;

import android.text.TextUtils;

public class OrderSettleShopAmount {




    public String goods;
    public String ship;
    public String total;
    public String coupon;
    public String active;

    /**
     * 是否选择优惠券
     * @return
     */
    public boolean isSelectCoupon() {
        return !(Double.parseDouble(coupon) > 0);
    }

    public boolean isActive() {
        if (TextUtils.isEmpty(active))
            return true;
        return  !(Double.parseDouble(active) > 0);
    }
}
