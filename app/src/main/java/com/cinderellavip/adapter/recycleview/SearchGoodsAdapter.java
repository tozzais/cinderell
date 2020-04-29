package com.cinderellavip.adapter.recycleview;


import android.app.Activity;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cinderellavip.R;
import com.cinderellavip.bean.local.HomeGoods;
import com.cinderellavip.ui.activity.home.GoodsDetailActivity;
import com.cinderellavip.ui.activity.home.ShopDetailActivity;

import org.jetbrains.annotations.NotNull;


/**
 *
 */
public class SearchGoodsAdapter extends BaseQuickAdapter<HomeGoods, BaseViewHolder> implements LoadMoreModule {

    public SearchGoodsAdapter() {
        super(R.layout.item_search_goods, null);

    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @NotNull  HomeGoods homeGoods) {
        int position = baseViewHolder.getLayoutPosition();
       TextView tv_name =  baseViewHolder.getView(R.id.tv_name);
       TextView tv_price =  baseViewHolder.getView(R.id.tv_price);
       TextView tv_former_price =  baseViewHolder.getView(R.id.tv_former_price);
       if (homeGoods.getType() == HomeGoods.FEATURED){
           //精选
           tv_name.setMaxLines(2);
           tv_name.setLines(2);
           tv_price.setText("￥88");
           tv_former_price.setText("￥128");
           tv_former_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
       }else if (homeGoods.getType() == HomeGoods.FIGHT){
           //拼团
           tv_name.setMaxLines(1);
           tv_name.setLines(1);
           tv_price.setText("￥88");
           tv_former_price.setText("单买价￥128");
           tv_former_price.getPaint().setFlags(0); //取消划线
       }else if (homeGoods.getType() == HomeGoods.INLET){
           //进口
           tv_name.setMaxLines(2);
           tv_name.setLines(2);
           tv_price.setText("￥88");
           tv_former_price.setText("￥128");
           tv_former_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //取消划线
       }else if (homeGoods.getType() == HomeGoods.AFFORDABLE){
           //实惠
           tv_name.setMaxLines(2);
           tv_name.setLines(2);
           tv_price.setText("￥88");
           tv_former_price.setText("￥128");
           tv_former_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //取消划线
       }

        baseViewHolder.getView(R.id.ll_root).setOnClickListener(v -> {
            GoodsDetailActivity.launch((Activity) getContext(),0);
        });
        baseViewHolder.getView(R.id.tv_go_shop).setOnClickListener(v -> {
            ShopDetailActivity.launch(getContext());
        });
    }
}