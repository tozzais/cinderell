package com.cinderellavip.ui.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinderellavip.R;
import com.cinderellavip.global.Constant;
import com.cinderellavip.http.ApiManager;
import com.cinderellavip.http.BaseResult;
import com.cinderellavip.http.Response;
import com.cinderellavip.ui.web.AgreementWebViewActivity;
import com.tozzais.baselibrary.http.RxHttp;
import com.tozzais.baselibrary.ui.BaseActivity;
import com.tozzais.baselibrary.util.CommonUtils;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_recomment_code)
    EditText et_recomment_code;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.iv_agreement)
    ImageView ivAgreement;


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivityForResult(intent, 100);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setBackTitle("注册");
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }

    private boolean isSelete = false;

    private void register() {
        String phone = etPhone.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String invite_code = et_recomment_code.getText().toString().trim();
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
        if (TextUtils.isEmpty(pass)) {
            tsg("请输入登录密码密码");
            return;
        }
        if (!isSelete){
            tsg("请勾选《用户协议》和《隐私条款》");
            return;
        }
        TreeMap<String, String> hashMap = new TreeMap<>();
        hashMap.put("mobile", phone);
        hashMap.put("sms_code", code);
        hashMap.put("password", pass);
        hashMap.put("invite_code", invite_code);
        hashMap.put("from", "1");

        new RxHttp<BaseResult>().send(ApiManager.getService().getRegister(hashMap),
                new Response<BaseResult>(mActivity) {
                    @Override
                    public void onSuccess(BaseResult result) {
                        tsg("注册成功");
                        setResult(phone,pass);
                    }
                });



    }


    private void setResult(String phone, String pass) {
        Intent intent = new Intent();
        intent.putExtra("phone", phone);
        intent.putExtra("pass", pass);
        setResult(RESULT_OK, intent);
        finish();
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
        TreeMap<String, String> hashMap = new TreeMap<>();
        hashMap.put("mobile", phone);
            hashMap.put("type", "1");

        new RxHttp<BaseResult>().send(ApiManager.getService().getCode(hashMap),
                new Response<BaseResult>(mActivity) {
                    @Override
                    public void onSuccess(BaseResult result) {
                        tsg("验证码发送成功");
                        mHandler.sendEmptyMessage(1);
                    }
                });


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


    @OnClick({R.id.tv_code, R.id.iv_agreement, R.id.tv_register_agreement, R.id.tv_privacy, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_code:
                getCode();
                break;
            case R.id.iv_agreement:
                isSelete = !isSelete;
                ivAgreement.setImageResource(isSelete?R.mipmap.agreement_selete_yes:R.mipmap.agreement_selete_no);
                break;
            case R.id.tv_register_agreement:
                AgreementWebViewActivity.launch(mActivity, Constant.H5_SERVICE);
                break;
            case R.id.tv_privacy:
                AgreementWebViewActivity.launch(mActivity, Constant.H5_PRIVACY);
                break;
            case R.id.tv_register:
                register();
                break;
        }
    }
}
