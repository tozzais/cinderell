package com.cinderellavip.adapter.recycleview;

import android.app.Activity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cinderellavip.R;
import com.cinderellavip.util.DataUtil;
import com.cinderellavip.util.ScreenUtil;
import com.cinderellavip.weight.FlowLayout;
import com.cinderellavip.weight.RatingBarView;
import com.tozzais.baselibrary.util.DpUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class DirectCommentAdapter extends BaseQuickAdapter<String, BaseViewHolder> implements LoadMoreModule {

    public DirectCommentAdapter() {
        super(R.layout.item_direct_comment, null);
    }


    @Override
    protected void convert( BaseViewHolder helper, String item) {
        int position = helper.getAdapterPosition();

        helper.getView(R.id.ll_root).setOnClickListener(v -> {
        });
        RatingBarView ratingBar = helper.getView(R.id.ratingBar);
        ratingBar.setStar(4,false);
        ratingBar.setClickable(false);

        FlowLayout fl_flag = helper.getView(R.id.fl_flag);
        List<String> list = new ArrayList<>();
        list.add("服务态度好");
        list.add("形象气质佳");
        list.add("个人卫生好");
        list.add("说话轻柔");
        addFlag(fl_flag,list);


        RecyclerView rv_image = helper.getView(R.id.rv_image);
        rv_image.setLayoutManager(new GridLayoutManager(getContext(),4));
        CommentImageAdapter adapter = new CommentImageAdapter();
        rv_image.setAdapter(adapter);
        adapter.setNewData(DataUtil.getData(4));


    }

    private void addFlag(FlowLayout flowLayout, List<String> list) {
//往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(DpUtil.dip2px(getContext(), 5), 0,
                0, DpUtil.dip2px(getContext(), 12));
        if (flowLayout != null) {
            flowLayout.removeAllViews();
        }
        for (String s : list) {
            TextView tv = new TextView(getContext());
            tv.setPadding(DpUtil.dip2px(getContext(), 12), DpUtil.dip2px(getContext(), 3),
                    DpUtil.dip2px(getContext(), 12), DpUtil.dip2px(getContext(), 3));
            tv.setText(s);
            tv.setMaxWidth(ScreenUtil.getScreenWidth((Activity) getContext()) - DpUtil.dip2px(getContext(), 24));
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setSingleLine();
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            tv.setTextColor(getContext().getResources().getColor(R.color.black_title_color));
            tv.setBackgroundResource(R.drawable.shape_yellow50_life_shadow);
            tv.setLayoutParams(layoutParams);
            flowLayout.addView(tv, layoutParams);
        }
    }







}