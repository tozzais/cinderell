package com.cinderellavip.ui.activity.mine;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cinderellavip.R;
import com.cinderellavip.global.ImageUtil;
import com.cinderellavip.toast.DialogUtil;
import com.cinderellavip.util.PhotoUtils;
import com.cinderellavip.weight.CircleImageView;
import com.tozzais.baselibrary.ui.CheckPermissionActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MineDataActivity extends CheckPermissionActivity {

    @BindView(R.id.iv_avater)
    CircleImageView ivAvater;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    public static void launch(Context activity) {
        Intent intent = new Intent(activity, MineDataActivity.class);
        activity.startActivity(intent);
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        setBackTitle("个人资料");
//        userInfo = GlobalParam.getUser();
//        if (userInfo != null){
//            setData();
//        }
    }


    @Override
    public void loadData() {
//


    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_minedata;
    }

    Dialog dialog;
    @OnClick({R.id.ll_avatar, R.id.ll_sex, R.id.ll_nickname})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_avatar:
                checkPermissions(PhotoUtils.needPermissions);
                break;
            case R.id.ll_sex:
                DialogUtil.showSexDialog(mContext, payString ->
                        tvSex.setText(payString)
                );
                break;
            case R.id.ll_nickname:
                ModifyNickNameActivity.launch(mActivity, tv_nickname.getText().toString().trim());
                break;
        }
    }

    @Override
    public void permissionGranted() {
        PhotoUtils.getInstance().selectPic(mActivity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ImageUtil.loadAvatar(mContext, ivAvater, (PhotoUtils.getInstance().getPath(mActivity, requestCode, data)));
        }
        if (requestCode == 100 && resultCode == 101) {
            tv_nickname.setText(data.getStringExtra("name"));
        }
    }






}