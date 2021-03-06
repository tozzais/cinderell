package com.cinderellavip.ui.fragment.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cinderellavip.R;
import com.cinderellavip.adapter.recycleview.SelectServiceAddressAdapter;
import com.cinderellavip.bean.ListData;
import com.cinderellavip.bean.eventbus.UpdateLifeAddress;
import com.cinderellavip.bean.net.LifeCityBean;
import com.cinderellavip.http.ApiManager;
import com.cinderellavip.http.BaseResult;
import com.cinderellavip.http.Response;
import com.cinderellavip.ui.activity.life.AddServiceAddressActivity;
import com.cinderellavip.ui.activity.life.SelectServiceAddressActivity;
import com.cinderellavip.ui.activity.mine.MineAddressActivity;
import com.tozzais.baselibrary.http.RxHttp;
import com.tozzais.baselibrary.ui.BaseListFragment;

import java.util.List;
import java.util.TreeMap;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.OnClick;

public class LifeAddressFragment extends BaseListFragment<LifeCityBean> {


    @BindView(R.id.tv_add)
    TextView tvAdd;
    private int type;

    @Override
    public int setLayout() {
        return R.layout.fragment_select_address_life;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        type = getArguments().getInt("type");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new SelectServiceAddressAdapter(type);
        mRecyclerView.setAdapter(mAdapter);

        setEmptyView("暂无服务地址信息");

    }

    @Override
    public void loadData() {
        super.loadData();
        TreeMap<String, String> hashMap = new TreeMap<>();
        hashMap.put("page", ""+page);
        new RxHttp<BaseResult<ListData<LifeCityBean>>>().send(ApiManager.getService().lifeAddress(hashMap),
                new Response<BaseResult<ListData<LifeCityBean>>>(isLoad,getContext()) {
                    @Override
                    public void onSuccess(BaseResult<ListData<LifeCityBean>> result) {
                        setData(result.data.data);
                    }
                    @Override
                    public void onError(Throwable e) {
                        onErrorResult(e);
                    }
                    @Override
                    public void onErrorShow(String s) {
                        showError(s);
                    }
                });



    }



    @Override
    public void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(((baseQuickAdapter, view, position) -> {

            List<LifeCityBean> data = mAdapter.getData();
            LifeCityBean netCityBean = data.get(position);
            if (type == SelectServiceAddressActivity.SELECT) {
                Intent intent = new Intent();
                intent.putExtra("netCityBean", netCityBean);
                mActivity.setResult(Activity.RESULT_OK, intent);
                mActivity.finish();
            } else if (type == SelectServiceAddressActivity.LOOK) {
                AddServiceAddressActivity.launch(mActivity, AddServiceAddressActivity.EDIT);
            }
        }));
    }


    @OnClick(R.id.tv_add)
    public void onClick() {
        if (type == MineAddressActivity.LOOK || type == MineAddressActivity.SELETE ) {
            AddServiceAddressActivity.launch(mActivity, AddServiceAddressActivity.ADD);
        }
    }

    @Override
    public void onEvent(Object o) {
        super.onEvent(o);
        if (o instanceof UpdateLifeAddress){
            onRefresh();
        }
    }
}
