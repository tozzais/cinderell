package com.cinderellavip.ui.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cinderellavip.MainActivity;
import com.cinderellavip.R;
import com.cinderellavip.bean.eventbus.LoginFinishSuccess;
import com.cinderellavip.http.ApiManager;
import com.cinderellavip.http.BaseResult;
import com.tozzais.baselibrary.http.RxHttp;
import com.tozzais.baselibrary.ui.BaseActivity;
import com.tozzais.baselibrary.util.CommonUtils;
import com.tozzais.baselibrary.util.sign.SignUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class FastLoginActivity extends BaseActivity {


    /**
     * 快捷登录
     */
    public static final int FAST_LOGIN = 0;
    /**
     * 绑定手机号
     */
    public static final int BIND_PHONE = 1;
    /**
     * 忘记密码
     */
    public static final int FORGET_PASS = 2;


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_switch)
    TextView tvSwitch;

    private int type;

    public static void launch(Activity activity, int type) {
        Intent intent = new Intent(activity, FastLoginActivity.class);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, 100);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_fast_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        type = getIntent().getIntExtra("type", FAST_LOGIN);
        if (type == FAST_LOGIN) {
            setBackTitle("快捷登录");
            tvRegister.setText("登录");
            etPass.setVisibility(View.GONE);
        } else if (type == BIND_PHONE) {
            setBackTitle("绑定手机号");
            tvRegister.setText("确定");
            tvSwitch.setVisibility(View.GONE);
        } else if (type == FORGET_PASS) {
            setBackTitle("忘记密码");
            tvRegister.setText("确定");
            etPass.setHint("输入新登录密码");
            tvSwitch.setVisibility(View.GONE);
        }

    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }



    private int time = 60;
    private Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (time > 0) {
                time--;
                tvCode.setText("还剩"+time + "秒");
                tvCode.setTextColor(getResources().getColor(R.color.grayText));
                mHandler.sendEmptyMessageDelayed(1, 1000);
                tvCode.setEnabled(false);
            } else {
                time = 60;
                tvCode.setTextColor(getResources().getColor(R.color.red));
                tvCode.setText("获取验证码");
                tvCode.setEnabled(true);
            }
            return false;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeMessages(1);
        }
        mHandler = null;

    }

    private void getCode() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            tsg("请输入手机号");
            return;
        } else if (!CommonUtils.isMobileNO(phone)) {
            tsg("请输入正确的手机号");
            return;
        }

//        TreeMap<String, String> hashMap = new TreeMap<>();
//        hashMap.put("phone", phone);
//        String category = "1";
//        if (type == REGISTER){
//            category = "1";
//        }else if (type == FORGET){
//            category = "2";
//        }else if (type == MODIFY){
//            category = "5";
//        }
//        hashMap.put("category",  category);
//        hashMap.put("tag",  tag);
//        String area_code = "86";
//        if (tag.equals("1")){
//            area_code = "86";
//        }else {
//            area_code = "852";
//        }
//        hashMap.put("area_code",  area_code);
////        hashMap.put("sign", SignUtil.getMd5(area_code+category+phone+tag));
//        hashMap.put("sign", SignUtil.getMd5(hashMap));
//        new RxHttp<BaseResult>().send(ApiManager.getService().getCode(hashMap),
//                new Response<BaseResult>(mActivity) {
//                    @Override
//                    public void onSuccess(BaseResult result) {
                        tsg("验证码发送成功");
                        mHandler.sendEmptyMessage(1);
//                    }
//                });
    }


    @OnClick({R.id.tv_code, R.id.tv_register, R.id.tv_switch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_code:
                getCode();
                break;
            case R.id.tv_register:
                sure();
                break;
            case R.id.tv_switch:
                finish();
                break;
        }
    }

    private void sure(){
        String phone = etPhone.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            tsg("请输入手机号");
            return;
        } else if (!CommonUtils.isMobileNO(phone)) {
            tsg("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            tsg("请输入短信验证码");
            return;
        }
        if (type == BIND_PHONE && TextUtils.isEmpty(pass)) {
            tsg("请输入登录密码");
            return;

        }if (type == FORGET_PASS && TextUtils.isEmpty(pass)) {
            tsg("请输入新 登录密码");
            return;

        }
        if (type == FAST_LOGIN) {
            EventBus.getDefault().post(new LoginFinishSuccess());
            tsg("登录成功");
            MainActivity.launch(mActivity);

        } else if (type == BIND_PHONE) {
            EventBus.getDefault().post(new LoginFinishSuccess());
            tsg("绑定成功");
            MainActivity.launch(mActivity);
        } else if (type == FORGET_PASS) {
            tsg("修改成功");
            setResult();
        }

    }

    private void setResult() {
        String phone = etPhone.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra("phone", phone);
        intent.putExtra("pass", pass);
        setResult(RESULT_OK, intent);
        finish();
    }
}