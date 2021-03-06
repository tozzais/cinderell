package com.cinderellavip.toast;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cinderellavip.R;
import com.cinderellavip.adapter.recycleview.CouponReceiveDialogAdapter;
import com.cinderellavip.adapter.recycleview.CouponReceiveDialogForServiceAdapter;
import com.cinderellavip.bean.local.CouponsBean;
import com.cinderellavip.bean.net.PhoneResult;
import com.cinderellavip.bean.net.SpecialItem;
import com.cinderellavip.bean.net.goods.GoodsInfo;
import com.cinderellavip.bean.net.goods.GoodsResult;
import com.cinderellavip.bean.net.life.LifeCoupon;
import com.cinderellavip.global.GlobalParam;
import com.cinderellavip.global.ImageUtil;
import com.cinderellavip.ui.BigImageActivity;
import com.cinderellavip.ui.activity.find.PublishPostActivity;
import com.cinderellavip.ui.activity.find.PublishTopicActivity;
import com.cinderellavip.util.KeyboardUtils;
import com.cinderellavip.weight.CartNumberView;
import com.cinderellavip.weight.SquareRoundImageView;
import com.nex3z.flowlayout.FlowLayout;
import com.tozzais.baselibrary.util.CommonUtils;

import java.util.List;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class DialogUtil {
    private static Dialog dialog;






    @SuppressLint("ClickableViewAccessibility")
    public static void showSpeciSpecialDialog(Context context, GoodsResult goodsResult, boolean isLeft, onNormSelectListener listener) {
        View view = View.inflate(context, R.layout.pop_bottom_specification, null);
        dialog = DialogUtils.getBottomDialog(context, view);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        LinearLayout ll_root = view.findViewById(R.id.ll_root);
        NestedScrollView scrollview = view.findViewById(R.id.scrollview);
        TextView tv_sure = view.findViewById(R.id.tv_sure);
        SquareRoundImageView iv_image = view.findViewById(R.id.iv_image);
        CartNumberView cart_view = view.findViewById(R.id.cart_view);
        EditText tv_number = cart_view.getTv_number();
        scrollview.setOnTouchListener((v, event) -> {
            tv_number.clearFocus();
            KeyboardUtils.hideKeyboard(tv_number);
            return false;
        });
        ll_root.setOnClickListener(v -> {
            tv_number.clearFocus();
            KeyboardUtils.hideKeyboard(tv_number);
        });
        cart_view.setNumber(1);
        tv_number.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus){
                String content = tv_number.getText().toString().trim();
                if (content.length()>4){
                    tv_number.setText("9999");
                }else if (TextUtils.isEmpty(content)  || "0".equals(content)){
                    tv_number.setText("1");
                }
            }
        });
        TextView tv_unit = view.findViewById(R.id.tv_unit);
        TextView tv_price = view.findViewById(R.id.tv_price);
        TextView tv_former_price = view.findViewById(R.id.tv_former_price);
        TextView tv_specification = view.findViewById(R.id.tv_specification);
        //商品规格
        FlowLayout fl_special = view.findViewById(R.id.fl_flag);
        List<SpecialItem> list1 = goodsResult.product_norm;
        GoodsInfo product_info = goodsResult.product_info;
        //设置默认规格
        SpecialItem specialItem2 = list1.get(0);

        if (product_info.hasGroup && !isLeft){
              list1 = goodsResult.group_info.group_norms;
                specialItem2 = list1.get(0);
            tv_unit.setText("拼团价");
            tv_price.setText("￥"+specialItem2.getGroupPrice());
            tv_former_price.setText("原价￥"+specialItem2.getPrice());
        }else if (product_info.hasSpike){
              list1 = goodsResult.spike_info.spike_norms;
                specialItem2 = list1.get(0);
            tv_unit.setText("秒杀价");
            tv_price.setText("￥"+specialItem2.getSpikePrice());
            tv_former_price.setText("原价￥"+specialItem2.getPrice());
        }else {
            if (goodsResult.user_is_vip){
                //如果是灰姑娘
                tv_unit.setText("会员");
                tv_price.setText("￥"+specialItem2.getPrice());
                tv_former_price.setText("非会员￥"+specialItem2.getOld_price());
            }else {
                tv_unit.setText("非会员");
                tv_price.setText("￥"+specialItem2.getOld_price());
                tv_former_price.setText("会员￥"+specialItem2.getPrice());
            }
        }
        ImageUtil.loadNet(context,iv_image,specialItem2.thumb);
        tv_specification.setText("已选：“"+specialItem2.name+"”");

//        iv_image.setTag(specialItem2.thumb);



        final SpecialItem specialItem = specialItem2;
        final  List<SpecialItem> list = list1;
        for (int i=0;i<list.size();i++){
            SpecialItem s = list.get(i);
            s.isCheck = i==0;
        }

        specialItem.isCheck = true;

        iv_image.setOnClickListener(v -> {
            String[] s = new String[]{(specialItem.thumb)};
            BigImageActivity.launch(context,s,0);
        });

        for (int i=0;i<list.size();i++) {
            SpecialItem text = list.get(i);
            View normView = View.inflate(context,R.layout.item_service_order_comment_flag,null);
            TextView tv = normView.findViewById(R.id.tv_title);
            if (text.isCheck){
                tv.setTextColor(context.getResources().getColor(R.color.white));
                tv.setBackgroundResource(R.drawable.shape_basecolor50);
            }else {
                tv.setBackgroundResource(R.drawable.shape_line_gray50);
                tv.setTextColor(context.getResources().getColor(R.color.black_title_color));
            }
            tv.setOnClickListener(v -> {
                for (int j=0;j<list.size();j++){
                    View childAt = fl_special.getChildAt(j);
                    TextView textView = childAt.findViewById(R.id.tv_title);
                    SpecialItem specialItem1 = list.get(j);
                    //text 是点击的  specialItem1是遍历的
                    if (specialItem1 == text){
                        //只是为了做传递给后台用。只能去id。其他参数不能取
                        specialItem.id = text.id;
                        //为了显示大图
                        specialItem.thumb = text.thumb;
                        specialItem1.isCheck = true;
                    }else {
                        specialItem1.isCheck = false;
                    }
                    if (specialItem1.isCheck){
                        ImageUtil.loadNet(context,iv_image,text.thumb);
                        if (product_info.hasGroup && !isLeft){
                            //如果是灰姑娘
                            tv_unit.setText("拼团价");
                            tv_price.setText("￥"+text.getGroupPrice());
                            tv_former_price.setText("原价￥"+text.getPrice());
                        }else if (product_info.hasSpike){
                            //如果是灰姑娘
                            tv_unit.setText("秒杀价");
                            tv_price.setText("￥"+text.getSpikePrice());
                            tv_former_price.setText("原价￥"+text.getPrice());
                        }else {
                            if (goodsResult.user_is_vip){
                                //如果是灰姑娘
                                tv_unit.setText("会员");
                                tv_price.setText("￥"+text.getPrice());
                                tv_former_price.setText("非会员￥"+text.getOld_price());
                            }else {
                                tv_unit.setText("非会员");
                                tv_price.setText("￥"+text.getOld_price());
                                tv_former_price.setText("会员￥"+text.getPrice());
                            }
                        }

                        tv_specification.setText("已选：“"+text.name+"”");

                        textView.setTextColor(context.getResources().getColor(R.color.white));
                        textView.setBackgroundResource(R.drawable.shape_basecolor50);
                    }else {
                        textView.setBackgroundResource(R.drawable.shape_line_gray50);
                        textView.setTextColor(context.getResources().getColor(R.color.black_title_color));
                    }
                }

            });
            tv.setText(text.name);
            fl_special.addView(normView);
        }
//        int measuredHeight = fl_special.getMeasuredHeight();
//        LogUtil.e("measuredHeight = "+measuredHeight);
//        LogUtil.e("measuredHeight = "+fl_special.getHeight());

        tv_sure.setOnClickListener(v -> {
            listener.onFinish(specialItem.id+"",cart_view.getTv_number().getText().toString().trim());
            dialog.dismiss();
            dialog = null;
        });
        iv_close.setOnClickListener(v -> {
            dialog.dismiss();
            dialog = null;
        });
    }


    public  static void showReceiveCouponDialog(Context context, List<CouponsBean> data) {
        View view = View.inflate(context, R.layout.pop_bottom_selete_coupon, null);
        dialog = DialogUtils.getBottomDialog(context,view);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        RecyclerView rv_coupon = view.findViewById(R.id.rv_coupon);
        rv_coupon.setLayoutManager(new LinearLayoutManager(context));
        CouponReceiveDialogAdapter adpter = new CouponReceiveDialogAdapter();
        rv_coupon.setAdapter(adpter);
        adpter.setNewData(data);

        View empty_view = View.inflate(context, com.tozzais.baselibrary.R.layout.base_empty_view, null);
        TextView tv_content = empty_view.findViewById(com.tozzais.baselibrary.R.id.tv_content);
        tv_content.setText("暂无优惠券");
        adpter.setEmptyView(empty_view);


        iv_close.setOnClickListener(v -> {
            dialog.dismiss();
            dialog = null;
        });
    }

    public  static void showServiceCouponDialog(Context context, List<LifeCoupon> data) {
        View view = View.inflate(context, R.layout.pop_bottom_selete_coupon, null);
        dialog = DialogUtils.getBottomDialog(context,view);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        RecyclerView rv_coupon = view.findViewById(R.id.rv_coupon);
        rv_coupon.setLayoutManager(new LinearLayoutManager(context));
        CouponReceiveDialogForServiceAdapter adapter = new CouponReceiveDialogForServiceAdapter();
        rv_coupon.setAdapter(adapter);
        adapter.setNewData(data);
        iv_close.setOnClickListener(v -> {
            dialog.dismiss();
            dialog = null;
        });
    }

    //isShield 是否已拉黑
    public static void showReportDialog(Context context, boolean isShield,
                                        onSelectListener listener) {
        View view = View.inflate(context, R.layout.pop_bottom_report, null);
        dialog = DialogUtils.getBottomDialog(context, view);
        LinearLayout ll_pullback = view.findViewById(R.id.ll_pullback);
        LinearLayout ll_report = view.findViewById(R.id.ll_report);
        TextView tv_shield = view.findViewById(R.id.tv_shield);
        if(isShield)
            tv_shield.setText("解除拉黑");
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(v -> {
            dialog.dismiss();
            dialog = null;
        });
        ll_pullback.setOnClickListener(v -> {
            listener.onFinish("1");
            dialog.dismiss();
            dialog = null;
        });
        ll_report.setOnClickListener(v -> {
            listener.onFinish("0");
            dialog.dismiss();
            dialog = null;
        });

    }

    public static void showPublishDialog(Context context) {
        View view = View.inflate(context, R.layout.pop_bottom_publish, null);
        dialog = DialogUtils.getBottomDialog(context, view);
        LinearLayout ll_pullback = view.findViewById(R.id.ll_pullback);
        LinearLayout ll_report = view.findViewById(R.id.ll_report);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(v -> {
            dialog.dismiss();
            dialog = null;
        });
        ll_pullback.setOnClickListener(v -> {
            PublishTopicActivity.launch(context);
            dialog.dismiss();
            dialog = null;
        });
        ll_report.setOnClickListener(v -> {
            PublishPostActivity.launch(context);

            dialog.dismiss();
            dialog = null;
        });

    }


    public static void showSexDialog(Context context, onSelectListener listener) {
        View view = View.inflate(context, R.layout.pop_bottom_sex, null);
        dialog = DialogUtils.getBottomDialog(context, view);
        ImageView iv_woman = view.findViewById(R.id.iv_woman);
        ImageView iv_man = view.findViewById(R.id.iv_man);
        iv_woman.setOnClickListener(v -> {
            listener.onFinish("2");
            dialog.dismiss();
            dialog = null;
        });
        iv_man.setOnClickListener(v -> {
            listener.onFinish("1");
            dialog.dismiss();
            dialog = null;
        });

    }

    public static void showCallPhoneDialog(Context context) {
        PhoneResult phoneBean = GlobalParam.getPhoneBean();
        if (phoneBean == null){
            return;
        }
        View view = View.inflate(context, R.layout.pop_bottom_callphone, null);
        dialog = DialogUtils.getBottomDialog(context, view);
        TextView tv_phone = view.findViewById(R.id.tv_phone);
        RelativeLayout ll_phone = view.findViewById(R.id.ll_phone);
        RelativeLayout tv_cancel = view.findViewById(R.id.tv_cancel);
        ll_phone.setOnClickListener(v -> {
            CommonUtils.callPhone(context,"69765809");
            dialog.dismiss();
            dialog = null;
        });
        tv_cancel.setOnClickListener(v -> {
            dialog.dismiss();
            dialog = null;
        });

    }

    /**
     * @param context
     * @param type 1 商品咨询电话 2平台客服电话 3生活服务
     */
    public static void showCallPhoneDialog(Context context,int type) {
        PhoneResult phoneBean = GlobalParam.getPhoneBean();
        if (phoneBean == null){
            return;
        }
        View view = View.inflate(context, R.layout.pop_bottom_callphone, null);
        dialog = DialogUtils.getBottomDialog(context, view);
        TextView tv_phone = view.findViewById(R.id.tv_phone);
        if (type == 1){
            tv_phone.setText("呼叫"+phoneBean.products_tel_phone);
        }else  if (type == 2){
            tv_phone.setText("呼叫"+phoneBean.platform_tel_phone);
        }else  if (type == 3){
            tv_phone.setText("呼叫"+phoneBean.service_tel_phone);
        }

        RelativeLayout ll_phone = view.findViewById(R.id.ll_phone);
        RelativeLayout tv_cancel = view.findViewById(R.id.tv_cancel);
        ll_phone.setOnClickListener(v -> {
            if (type == 1){
                CommonUtils.callPhone(context,phoneBean.products_tel_phone);
            }else  if (type == 2){
                CommonUtils.callPhone(context,phoneBean.platform_tel_phone);
            }else  if (type == 3){
                CommonUtils.callPhone(context,phoneBean.service_tel_phone);
            }

            dialog.dismiss();
            dialog = null;
        });
        tv_cancel.setOnClickListener(v -> {
            dialog.dismiss();
            dialog = null;
        });

    }
    public interface onSelectListener {
        void onFinish(String payString);
    }

    public interface onNormSelectListener {
        void onFinish(String norm_id,String number);
    }
}
