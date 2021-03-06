package com.cinderellavip.ui.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cinderellavip.R;
import com.cinderellavip.adapter.viewpager.GoodsDetailPagerAdapter;
import com.cinderellavip.ui.fragment.mine.OrderFragment;
import com.cinderellavip.util.Utils;
import com.google.android.material.tabs.TabLayout;
import com.tozzais.baselibrary.ui.BaseActivity;
import com.tozzais.baselibrary.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class MineOrderActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;

    private GoodsDetailPagerAdapter adapter;
    private List<BaseFragment> fragmentList = new ArrayList<>();

    public static void launch(Activity activity, int type) {
        if (!Utils.isFastClick()){
            return;
        }
        Intent intent = new Intent(activity, MineOrderActivity.class);
        intent.putExtra("type",type);
        activity.startActivity(intent);
    }




    @Override
    public int getLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setBackTitle("我的订单");
        tablayout.setTabTextColors(getColor(R.color.black_title_color),getColor(R.color.baseColor));
        tablayout.setSelectedTabIndicatorColor(getColor(R.color.baseColor));
    }

    @Override
    public void loadData() {

        fragmentList.add(OrderFragment.newInstance(OrderFragment.ALL));
        fragmentList.add(OrderFragment.newInstance(OrderFragment.UNPAY));
        fragmentList.add(OrderFragment.newInstance(OrderFragment.UNSEND));
        fragmentList.add(OrderFragment.newInstance(OrderFragment.UNRECEIVE));
        fragmentList.add(OrderFragment.newInstance(OrderFragment.FINISH));
        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("待付款");
        list.add("待发货");
        list.add("待收货");
        list.add("已完成");
        adapter = new GoodsDetailPagerAdapter(getSupportFragmentManager(), fragmentList,list);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);

        viewpager.setCurrentItem(getIntent().getIntExtra("type",0));
        viewpager.setOffscreenPageLimit(4);

    }

    @Override
    public void initListener() {

    }


}
