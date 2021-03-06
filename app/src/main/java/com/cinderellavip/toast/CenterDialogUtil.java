package com.cinderellavip.toast;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinderellavip.R;
import com.cinderellavip.bean.eventbus.UpdateMineInfo;
import com.cinderellavip.http.ApiManager;
import com.cinderellavip.http.BaseResult;
import com.cinderellavip.http.Response;
import com.cinderellavip.listener.OnGetStringListener;
import com.cinderellavip.listener.OnSureClickListener;
import com.cinderellavip.service.SobotUtils;
import com.tozzais.baselibrary.http.RxHttp;
import com.tozzais.baselibrary.util.toast.ToastCommom;

import org.greenrobot.eventbus.EventBus;


public class CenterDialogUtil {


    private static Dialog cityDialog;


    public static void showBulletin(Context context) {
        View messageView = View.inflate(context, R.layout.pop_bottom_realname_auth, null);
        cityDialog = DialogUtils.getCenterDialog(context, messageView, false);
        ImageView iv_close = messageView.findViewById(R.id.iv_close);
        EditText et_name = messageView.findViewById(R.id.et_name);
        TextView tv_commit = messageView.findViewById(R.id.tv_commit);
        TextView tv_add = messageView.findViewById(R.id.tv_add);
        iv_close.setOnClickListener(v -> {
            cityDialog.dismiss();
            cityDialog = null;
        });
        tv_commit.setOnClickListener(v -> {
            String code = et_name.getText().toString().trim();
            if (TextUtils.isEmpty(code)){
                ToastCommom.createToastConfig().ToastShow(context,"请输入推荐码");
                return;
            }
            new RxHttp<BaseResult>().send(ApiManager.getService().applyVip(code),
                    new Response<BaseResult>(context) {
                        @Override
                        public void onSuccess(BaseResult result) {
                            EventBus.getDefault().post(new UpdateMineInfo());
                            ToastCommom.createToastConfig().ToastShow(context,"申请成功");
                            cityDialog.dismiss();
                            cityDialog = null;

                        }
                    });

        });
        tv_add.setOnClickListener(v -> {
            SobotUtils.start(context);
//            DialogUtil.showCallPhoneDialog(context,2);
        });

    }

    public static void showApplySuccess(Context context,OnSureClickListener listener) {
        View messageView = View.inflate(context, R.layout.pop_apply_sueecss, null);
        cityDialog = DialogUtils.getCenterDialog(context, messageView, false);
        TextView tv_commit = messageView.findViewById(R.id.tv_commit);

        tv_commit.setOnClickListener(v -> {
            if (listener != null){
                listener.onSure();
            }
            cityDialog.dismiss();
            cityDialog = null;
        });

    }

    public static void showApplySuccess1(Context context,OnSureClickListener listener) {
        View messageView = View.inflate(context, R.layout.pop_apply_success, null);
        cityDialog = DialogUtils.getCenterDialog(context, messageView, false);
        TextView tv_commit = messageView.findViewById(R.id.tv_login);

        tv_commit.setOnClickListener(v -> {
            if (listener != null){
                listener.onSure();
            }
            cityDialog.dismiss();
            cityDialog = null;
        });

    }

    public static void showShare(Context context, OnSureClickListener listener) {
        View messageView = View.inflate(context, R.layout.pop_center_share, null);
        cityDialog = DialogUtils.getCenterDialog(context, messageView, false);
        TextView tv_commit = messageView.findViewById(R.id.tv_login);
        ImageView iv_close = messageView.findViewById(R.id.iv_close);

        tv_commit.setOnClickListener(v -> {
            if (listener != null){
                listener.onSure();
            }
            cityDialog.dismiss();
            cityDialog = null;
        });iv_close.setOnClickListener(v -> {
            cityDialog.dismiss();
            cityDialog = null;
        });

    }
    public static void showApplyService(Context context,OnSureClickListener listener) {
        View messageView = View.inflate(context, R.layout.pop_apply_service, null);
        cityDialog = DialogUtils.getCenterDialog(context, messageView, false);
        TextView tv_commit = messageView.findViewById(R.id.tv_commit);
        TextView tv_cancel = messageView.findViewById(R.id.tv_cancel);

        tv_commit.setOnClickListener(v -> {
            if (listener != null){
                listener.onSure();
            }
            cityDialog.dismiss();
            cityDialog = null;
        }); tv_cancel.setOnClickListener(v -> {
            cityDialog.dismiss();
            cityDialog = null;
        });

    }

    public static void showTwo(Context context,
                               String title, String content, String btnCancel, String btnSure
            , final OnGetStringListener listener) {
        View messageView = View.inflate(context, R.layout.pop_one_btn2, null);
        cityDialog = DialogUtils.getCenterDialog(context, messageView);
        TextView tv_title = messageView.findViewById(R.id.tv_title);
        TextView tv_content = messageView.findViewById(R.id.tv_content);
        TextView tv_cancel = messageView.findViewById(R.id.tv_cancel);
        TextView tv_sure = messageView.findViewById(R.id.tv_sure);
        tv_title.setText(title);
        tv_content.setText(content);
        tv_cancel.setText(btnCancel);
        tv_sure.setText(btnSure);
        tv_sure.setOnClickListener(v -> {
            listener.getString("1");
            cityDialog.dismiss();
            cityDialog = null;
        });
        tv_cancel.setOnClickListener(v -> {
            listener.getString("0");
            cityDialog.dismiss();
            cityDialog = null;

        });
    }

    public static void showCostExplain(Context context,final OnGetStringListener listener) {
        View messageView = View.inflate(context, R.layout.pop_cost_explain, null);
        cityDialog = DialogUtils.getCenterDialog(context, messageView);
        TextView tv_title = messageView.findViewById(R.id.tv_title);
        TextView tv_content = messageView.findViewById(R.id.tv_content);
        TextView tv_sure = messageView.findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(v -> {
            listener.getString("1");
            cityDialog.dismiss();
            cityDialog = null;
        });
    }

    public static void showServiceOrder(Context context,
                               String title, String content, String btnCancel, String btnSure
            , final OnGetStringListener listener) {
        View messageView = View.inflate(context, R.layout.pop_btn2_service, null);
        cityDialog = DialogUtils.getCenterDialog(context, messageView);
        TextView tv_title = messageView.findViewById(R.id.tv_title);
        TextView tv_content = messageView.findViewById(R.id.tv_content);
        TextView tv_cancel = messageView.findViewById(R.id.tv_cancel);
        TextView tv_sure = messageView.findViewById(R.id.tv_sure);
        tv_title.setText(title);
        tv_content.setText(content);
        tv_cancel.setText(btnCancel);
        tv_sure.setText(btnSure);
        tv_sure.setOnClickListener(v -> {
            listener.getString("1");
            cityDialog.dismiss();
            cityDialog = null;
        });
        tv_cancel.setOnClickListener(v -> {
            cityDialog.dismiss();
            cityDialog = null;

        });
    }


    public static void showCommitSuccess(Context context,String title,
                                         OnSureClickListener listener) {
        View messageView = View.inflate(context, R.layout.pop_commit_success, null);
        TextView tv_title = messageView.findViewById(R.id.tv_title);
        tv_title.setText(title);
        TextView tv_login = messageView.findViewById(R.id.tv_login);
        tv_login.setOnClickListener(v -> {
            if (listener != null){
                listener.onSure();
            }
            cityDialog.dismiss();
            cityDialog = null;
        });
        cityDialog = DialogUtils.getCenterDialog(context, messageView, false);
    }

    public static void showSignSuccess(Context context,String title,
                                         OnSureClickListener listener) {
        View messageView = View.inflate(context, R.layout.pop_sign_success, null);
        TextView tv_title = messageView.findViewById(R.id.tv_title);
        tv_title.setText(title);
        TextView tv_login = messageView.findViewById(R.id.tv_login);
        tv_login.setOnClickListener(v -> {
            if (listener != null){
                listener.onSure();
            }
            cityDialog.dismiss();
            cityDialog = null;
        });
        cityDialog = DialogUtils.getCenterDialog(context, messageView, false);
    }



    public interface onSelectListener {
        void onFinish(String payString);
    }

}
