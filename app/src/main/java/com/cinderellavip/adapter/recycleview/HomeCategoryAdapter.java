package com.cinderellavip.adapter.recycleview;


import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cinderellavip.R;
import com.cinderellavip.bean.local.HomeCategoryItem;
import com.cinderellavip.ui.activity.home.CardSaleActivity;
import com.cinderellavip.util.ScreenUtil;
import com.cinderellavip.util.dialog.RightDialogUtil;


public class HomeCategoryAdapter extends BaseQuickAdapter<HomeCategoryItem, BaseViewHolder> {

    public HomeCategoryAdapter() {
        super(R.layout.item_home_category, null);
    }


    @Override
    protected void convert( BaseViewHolder helper,  HomeCategoryItem item) {
        int position = helper.getAdapterPosition();
//        helper.setText(R.id.tv_number,item);
        ImageView iv_image = helper.getView(R.id.iv_image);
        TextView tv_number = helper.getView(R.id.tv_number);
        iv_image.setImageResource(item.res);
        tv_number.setText(item.name);

        LinearLayout rl_root = helper.getView(R.id.rl_root);
        ViewGroup.LayoutParams linearParams = rl_root.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        int screenWidth = ScreenUtil.getScreenWidth((Activity) getContext());
        linearParams.width = screenWidth/5;// 控件的宽强制设成30
        linearParams.height = screenWidth/5;// 控件的宽强制设成30
        rl_root.setLayoutParams(linearParams); //使设置好的布局参数应用到控件


        helper.getView(R.id.rl_root).setOnClickListener(v -> {
            if (item.name.equals("更多")){
                RightDialogUtil.showDialog(getContext(),s -> {});
            }if (item.name.equals("大牌特卖")){
                CardSaleActivity.launch(getContext());
            }
        });



    }



}