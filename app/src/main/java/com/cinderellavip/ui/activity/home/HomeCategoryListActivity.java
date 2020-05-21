package com.cinderellavip.ui.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cinderellavip.R;
import com.cinderellavip.bean.net.HotList;
import com.cinderellavip.global.RequestCode;
import com.cinderellavip.http.ApiManager;
import com.cinderellavip.http.BaseResult;
import com.cinderellavip.http.Response;
import com.cinderellavip.ui.fragment.home.CategoryFragment;
import com.tozzais.baselibrary.http.RxHttp;
import com.tozzais.baselibrary.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/9/8.
 * 首页分类
 */
public class HomeCategoryListActivity extends BaseActivity {


    @BindView(R.id.tv_hint)
    TextView tv_hint;

    public static void launch(Activity from) {
        Intent intent = new Intent(from, HomeCategoryListActivity.class);
        from.startActivityForResult(intent, RequestCode.request_service_coupon);
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        getSearchHint();

    }

    private void getSearchHint(){
        new RxHttp<BaseResult<HotList<String>>>().send(ApiManager.getService().getSearchWords(),
                new Response<BaseResult<HotList<String>>>(mActivity,Response.BOTH) {
                    @Override
                    public void onSuccess(BaseResult<HotList<String>> result) {
                        HotList<String> data = result.data;
                        tv_hint.setText(data.keyword);
                    }
                });
    }

    @Override
    public void loadData() {
        getSupportFragmentManager().beginTransaction().add(R.id.content_container1,
                new CategoryFragment()).commit();
    }

    @Override
    public int getToolbarLayout() {
        return -1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_category;
    }



    @Override
    public void initListener() {
        super.initListener();

    }

    @OnClick({R.id.iv_back, R.id.ll_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_search:
                SearchActivity.launch(mActivity);
                break;
        }
    }
}
