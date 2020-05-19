package com.cinderellavip.ui.fragment.life;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinderellavip.R;
import com.cinderellavip.adapter.recycleview.ServiceListAdapter;
import com.cinderellavip.bean.local.CouponsBean;
import com.cinderellavip.bean.net.life.CategoryResult;
import com.cinderellavip.bean.net.life.CategoryService;
import com.cinderellavip.bean.net.life.LiftCategoryItem;
import com.cinderellavip.bean.net.life.LiftHomeCategory;
import com.cinderellavip.bean.net.life.LiftHomeServiceItem;
import com.cinderellavip.global.CinderellApplication;
import com.cinderellavip.global.ImageUtil;
import com.cinderellavip.http.ApiManager;
import com.cinderellavip.http.BaseResult;
import com.cinderellavip.http.ListResult;
import com.cinderellavip.http.Response;
import com.cinderellavip.toast.DialogUtil;
import com.cinderellavip.ui.activity.life.BuyLongServiceActivity;
import com.cinderellavip.ui.fragment.mine.OrderFragment;
import com.cinderellavip.util.DataUtil;
import com.tozzais.baselibrary.http.RxHttp;
import com.tozzais.baselibrary.ui.BaseListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.OnClick;


public class ServiceListFragment extends BaseListFragment<String> implements View.OnClickListener {


    @Override
    public int setLayout() {
        return R.layout.fragment_service_list;
    }


    public static ServiceListFragment newInstance(LiftHomeCategory service) {
        ServiceListFragment cartFragment = new ServiceListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("service",service);
        cartFragment.setArguments(bundle);
        return cartFragment;

    }

    private LiftHomeCategory service;



    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        service = getArguments().getParcelable("service");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new ServiceListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        View view = View.inflate(mActivity, R.layout.header_service_list, null);
        iv_banner = view.findViewById(R.id.iv_banner);
        ll_new_person = view.findViewById(R.id.ll_new_person);
        tv_coupon_name = view.findViewById(R.id.tv_coupon_name);
        iv_buy_long_service = view.findViewById(R.id.iv_buy_long_service);
        iv_buy_long_service.setOnClickListener(this);
        tv_receive_coupon = view.findViewById(R.id.tv_receive_coupon);
        tv_receive_coupon.setOnClickListener(this);
        mAdapter.addHeaderView(view);


    }
    private ImageView iv_banner;
    private LinearLayout ll_new_person;
    private TextView tv_coupon_name;
    private ImageView iv_buy_long_service;
    private TextView tv_receive_coupon;

    @Override
    public void loadData() {
        TreeMap<String, String> hashMap = new TreeMap<>();
        hashMap.put("city", CinderellApplication.name);
        hashMap.put("service",service.one+"");
        hashMap.put("four",service.three+"");
        new RxHttp<BaseResult<CategoryResult>>().send(ApiManager.getService().life_category(hashMap),
                new Response<BaseResult<CategoryResult>>(isLoad,mActivity) {
                    @Override
                    public void onSuccess(BaseResult<CategoryResult> result) {
                        CategoryResult data = result.data;
                        CategoryService service = data.service;
                        if (service == null && TextUtils.isEmpty(service.topimg)){
                            iv_banner.setVisibility(View.GONE);
                        }else {
                            iv_banner.setVisibility(View.VISIBLE);
                            ImageUtil.loadNet(mActivity,iv_banner,service.topimg);
                        }
//                        LiftHomeServiceItem lo_ng = data.lo_ng;
//                        if (lo_ng != null && )
                    }

                    @Override
                    public void onErrorShow(String s) {
                        showError(s);
                    }
                });


        setData(DataUtil.getData(2));

    }


    @OnClick(R.id.tv_service)
    public void onClick() {
        DialogUtil.showCallPhoneDialog(mActivity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_buy_long_service:
                BuyLongServiceActivity.launch(getContext());
                break;
            case R.id.tv_receive_coupon:
                List<CouponsBean> list = new ArrayList<>();
                list.add(new CouponsBean());
                list.add(new CouponsBean());
                list.add(new CouponsBean());
                DialogUtil.showServiceCouponDialog(mActivity, list);
                break;
        }

    }
}
