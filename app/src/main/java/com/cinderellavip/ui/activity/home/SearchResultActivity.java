package com.cinderellavip.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.cinderellavip.R;
import com.cinderellavip.listener.OnFilterListener;
import com.cinderellavip.ui.fragment.home.SearchResultFragment;
import com.cinderellavip.util.KeyboardUtils;
import com.cinderellavip.weight.FilterView;
import com.tozzais.baselibrary.ui.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/9/8.
 */
public class SearchResultActivity extends BaseActivity implements OnFilterListener {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.filter_view)
    FilterView filter_view;

    public static void launch(Context from, String keyword) {
        Intent intent = new Intent(from, SearchResultActivity.class);
        intent.putExtra("keyword", keyword);
        from.startActivity(intent);
    }


    private String keyword;
    @Override
    public int getLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        keyword = getIntent().getStringExtra("keyword");
        etSearch.setText(keyword);
    }

    @Override
    protected int getToolbarLayout() {
        return -1;
    }

    @Override
    public void loadData() {
        fragment = SearchResultFragment.newInstance(keyword);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment).commit();


    }


    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

        }
    }

    private SearchResultFragment fragment;

    @Override
    public void initListener() {
        super.initListener();
        etSearch.setOnKeyListener((v, keyCode, event) -> {
            //是否是回车键
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                KeyboardUtils.hideKeyboard(etSearch);
                String keyword = etSearch.getText().toString().trim();
                fragment.setKeyword(keyword);
            }
            return false;
        });
        filter_view.setOnFilterListener(this);
    }


    private String sort = "0";
    private String area = "0";
    @Override
    public void onComplex() {
        sort = "0";
        fragment.setSortAndArea(sort,area);
    }

    @Override
    public void onPrice(boolean isDown) {
        if (isDown){
            sort = "4";
        }else {
            sort = "3";
        }
        fragment.setSortAndArea(sort,area);
    }

    @Override
    public void onCategray(boolean isDown) {
    }

    @Override
    public void onSaleVolume(boolean isDown) {
        if (isDown){
            sort = "2";
        }else {
            sort = "1";
        }
        fragment.setSortAndArea(sort,area);
    }


}