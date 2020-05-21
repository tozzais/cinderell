package com.cinderellavip.bean.net.order;

import com.cinderellavip.bean.net.NetCityBean;

import java.util.List;

public class OrderInfo {




    public List<OrderGoodsInfo> goods;
    public NetCityBean address;


  

    public int id;
    public String order_no;
    public String goods_amount;
    public String ship_amount;
    public String dis_amount;
    public String total_amount;
    public String payment;
    public int status;
    public String status_txt;
    public String pay_sn;
    public String pay_at;
    public String create_at;

    public String send_at;
    public String send_sn;
    public String send_company;
    public String receipt_at;
    public String commit_at;
    public int store_id;
    public String store_name;
    //拼团参数
    public long end_time;
    public long timestamp;
    public String  group_access;
    public int  total_user;
    public List<String> group_users;

    public boolean virtual;//是否是虚拟订单
    public String send_remark;//是否是虚拟订单



}
