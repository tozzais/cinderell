package com.cinderellavip.ui.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cinderellavip.R;
import com.cinderellavip.ui.fragment.mine.BlackListFragment;
import com.cinderellavip.ui.fragment.order.RefundFragment;
import com.cinderellavip.util.Utils;
import com.tozzais.baselibrary.ui.BaseActivity;


/**
 * Created by Administrator on 2016/9/8.
 */
public class BlackListActivity extends BaseActivity {



    public static void launch(Context from) {
        if (!Utils.isFastClick()){
            return;
        }
        Intent intent = new Intent(from, BlackListActivity.class);
        from.startActivity(intent);
    }


    @Override
    public void initView(Bundle savedInstanceState) {

        setBackTitle("黑名单");



    }


    @Override
    public void loadData() {
        BlackListFragment fragment = new BlackListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_container, fragment).commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_content;
    }


}
