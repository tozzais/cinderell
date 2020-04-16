package com.cinderellavip.ui.fragment.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.TextView;


import com.cinderellavip.R;
import com.cinderellavip.adapter.recycleview.MineAddressAdpter;
import com.cinderellavip.bean.net.NetCityBean;
import com.cinderellavip.global.GlobalParam;
import com.cinderellavip.http.ApiManager;
import com.cinderellavip.ui.activity.mine.EditAddressActivity;
import com.cinderellavip.ui.activity.mine.MineAddressActivity;
import com.tozzais.baselibrary.ui.BaseListFragment;
import com.tozzais.baselibrary.util.sign.SignUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.OnClick;

public class MineAddressFragment extends BaseListFragment<NetCityBean> {


    @BindView(R.id.tv_add)
    TextView tvAdd;
    private int type;
    private String addressID;

    @Override
    public int setLayout() {
        return R.layout.fragment_mine_address;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        type = getArguments().getInt("type");
        addressID = getArguments().getString("addressID");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new MineAddressAdpter(type);
        mRecyclerView.setAdapter(mAdapter);

        setEmptyView("暂无地址");

//        if (type == MineAddressActivity.SELETE) {
//            //积分兑换 和 确认订单 暂时没区分 统一用确认
//            tvAdd.setText("确认");
//        }
        if (type == MineAddressActivity.EXCHANGE) {
            //积分兑换
            tvAdd.setText("确认兑换");
        }
    }

    @Override
    public void loadData() {
        super.loadData();
        new Handler().postDelayed(()->{
            List<NetCityBean> list = new ArrayList<>();
            list.add(new NetCityBean(true));
            list.add(new NetCityBean(false));
            list.add(new NetCityBean(false));
            list.add(new NetCityBean(false));
            setData(list);
        },500);



    }

    //选中的item
    private NetCityBean netCityBean;

    @Override
    public void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(((baseQuickAdapter, view, position) -> {

            List<NetCityBean> data = mAdapter.getData();
            netCityBean = data.get(position);
            if (type == MineAddressActivity.SELETE) {
                Intent intent = new Intent();
                intent.putExtra("netCityBean", netCityBean);
                mActivity.setResult(Activity.RESULT_OK, intent);
                mActivity.finish();
            } else if (type == MineAddressActivity.LOOK) {
//                EditAddressActivity.launch(mActivity, EditAddressActivity.EDIT, data.get(position));
            } else if (type == MineAddressActivity.EXCHANGE) {
                for (int i = 0; i < data.size(); i++) {
                    NetCityBean netCityBean = data.get(i);
                    netCityBean.isCheck = (position == i);
                }
                mAdapter.notifyDataSetChanged();
            }
        }));
    }


    @OnClick(R.id.tv_add)
    public void onClick() {
        if (type == MineAddressActivity.LOOK || type == MineAddressActivity.SELETE ) {
            EditAddressActivity.launch(mActivity, EditAddressActivity.ADD);
        } else if (type == MineAddressActivity.EXCHANGE) {

            if (netCityBean == null) {
                tsg("请选择地址");
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("netCityBean", netCityBean);
            mActivity.setResult(Activity.RESULT_OK, intent);
            mActivity.finish();
        }
//
    }

    @Override
    public void onEvent(Object o) {
        super.onEvent(o);
//        if (o instanceof UpdateAddress) {
//            onRefresh();
//        }
    }
}