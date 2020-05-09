package com.cinderellavip.bean.local;

import com.cinderellavip.util.ArithmeticUtil;

public class HomeGoods {






    public int id;
    public String name;
    public String thumb;
    public double price;
    public double old_price;
    public int number;
    public double buy_price;
    public double group_price;

    public String getPrice() {
        return ArithmeticUtil.convert(price);
    }

    public String getOld_price() {
        return ArithmeticUtil.convert(old_price);
    }

    public String getBuy_price() {
        return ArithmeticUtil.convert(buy_price);
    }

    public String getGroup_price() {
        return ArithmeticUtil.convert(group_price);
    }
}
