package com.cinderellavip.ui.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.cinderellavip.R;
import com.cinderellavip.bean.eventbus.AddAddress;
import com.cinderellavip.bean.local.RequestSettlePara;
import com.cinderellavip.bean.net.NetCityBean;
import com.cinderellavip.http.ApiManager;
import com.cinderellavip.http.BaseResult;
import com.cinderellavip.http.Response;
import com.cinderellavip.toast.CenterDialogUtil;
import com.cinderellavip.util.PhotoUtils;
import com.cinderellavip.util.Utils;
import com.cinderellavip.util.address.LocalCityUtil3s;
import com.google.gson.Gson;
import com.tozzais.baselibrary.http.RxHttp;
import com.tozzais.baselibrary.ui.CheckPermissionActivity;
import com.tozzais.baselibrary.util.CommonUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

public class EditAddressActivity extends CheckPermissionActivity {
    public static final int ADD = 1, EDIT = 2;
    public static final int REQUESTCODE = 101;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.cb_default_address)
    CheckBox cbDefaultAddress;
    @BindView(R.id.tv_sava)
    TextView tvSava;

    private int type;

    //只有地址编辑的时候 才会有这个数据
    private NetCityBean item;


    public static void launch(Activity activity, int type) {
        if (!Utils.isFastClick()){
            return;
        }
        Intent intent = new Intent(activity, EditAddressActivity.class);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, REQUESTCODE);
    }

    public static void launch(Activity activity, int type, NetCityBean item) {
        if (!Utils.isFastClick()){
            return;
        }
        Intent intent = new Intent(activity, EditAddressActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("item", new Gson().toJson(item));
        activity.startActivityForResult(intent, REQUESTCODE);
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type", ADD);
        if (type == ADD) {
            tvSava.setText("保存");
            setBackTitle("新增收货地址");
        } else {
            tvSava.setText("保存");
            String para = getIntent().getStringExtra("item");
            item = new Gson().fromJson(para, NetCityBean.class);
            setBackTitle("修改收货地址");
            setRightText("删除");
            setData();
        }
    }

    @Override
    public void loadData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_address_edit;
    }


    private void setData() {
        etName.setText(item.name);
        etPhone.setText(item.mobile);
        tvAddress.setText(String.format("%s %s %s", item.province, item.city, item.area));
        etAddress.setText(item.address);
        cbDefaultAddress.setChecked(item.is_default);


    }

    @OnClick({R.id.ll_address, R.id.tv_sava})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                getCity();
                break;
            case R.id.tv_sava:
                commit();
                break;

        }
    }

    @Override
    public void initListener() {
        super.initListener();
        tv_right.setOnClickListener(v -> {
            CenterDialogUtil.showTwo(mContext, "确定要删除收货地址吗？",
                    "删除后不可恢复，请谨慎操作。", "暂不删除", "确认删除", type1 -> {
                        if (type1.equals("1")) {
                            deleteAddress();
                        }
                    });
        });
    }

    private void deleteAddress(){
        new RxHttp<BaseResult>().send(ApiManager.getService().deleteAddress(item.id+""),
                new Response<BaseResult>(mActivity) {
                    @Override
                    public void onSuccess(BaseResult result) {
                        tsg("删除成功");
                        EventBus.getDefault().post(new AddAddress());
                        finish();
                    }
                });

    }

    private void getCity() {
        LocalCityUtil3s.getInstance().showSelectDialog(mContext, ((province, city, county) -> {
            tvAddress.setText(String.format("%s %s %s", province.name, city.name, county.name));
        }));
    }


    @Override
    public void permissionGranted() {
        PhotoUtils.getInstance().selectPic(mActivity);
    }


    private void commit() {
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String address = tvAddress.getText().toString().trim();
        String addressDetail = etAddress.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            tsg("请输入真实姓名");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            tsg("请输入联系号码");
            return;
        } else if (!CommonUtils.isMobileNO(phone)) {
            tsg("手机号码格式不正确");
            return;
        }
        if (TextUtils.isEmpty(address)) {
            tsg("请选择所在城市");
            return;
        }
        if (TextUtils.isEmpty(addressDetail)) {
            tsg("请填写详细地址");
            return;
        }
        TreeMap<String, String> hashMap = new TreeMap<>();
        if (type == EDIT){
            hashMap.put("id", item.id+"");
        }
        hashMap.put("name", name);
        hashMap.put("mobile", phone);
        hashMap.put("province", address.split(" ")[0]);
        hashMap.put("city", address.split(" ")[1]);
        hashMap.put("area", address.split(" ")[2]);
        hashMap.put("address", addressDetail);
        hashMap.put("default", cbDefaultAddress.isChecked()?"1":"0");
        new RxHttp<BaseResult>().send(ApiManager.getService().getEditAddress(hashMap),
                new Response<BaseResult>(mActivity) {
                    @Override
                    public void onSuccess(BaseResult result) {
                        success();
                    }
                });



    }

    private void success() {
        if (type == ADD) {
            tsg("新增成功");
        } else {
            tsg("修改成功");
        }
        EventBus.getDefault().post(new AddAddress());
        finish();
    }




}
