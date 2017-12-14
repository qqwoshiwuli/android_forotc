package com.gqfbtc.mvp.delegate;

import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;
import com.gqfbtc.widget.SingleLineZoomTextView;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class LoginAndRegisterDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }


    public void initLogin() {
        viewHolder.lin_login.setVisibility(View.VISIBLE);
        viewHolder.tv_bottom_toast.setVisibility(View.VISIBLE);
        viewHolder.tv_forget_password.setVisibility(View.VISIBLE);
        String str = "还没有账号?立即<font color='" + viewHolder.rootView.getContext().getResources().getColor(R.color.color_blue) + "'>注册</font>";
        viewHolder.tv_bottom_toast.setText(Html.fromHtml(str));
        viewHolder.tv_commit.setText("登录");
        viewHolder.et_login_password.setHint("请输入密码");
        viewHolder.tv_login_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.et_login_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });

    }

    public void initRegister() {
        viewHolder.lin_register.setVisibility(View.VISIBLE);
        viewHolder.tv_bottom_toast.setVisibility(View.VISIBLE);
        String str = "已有账号?立即<font color='" + viewHolder.rootView.getContext().getResources().getColor(R.color.color_blue) + "'>登录</font>";
        viewHolder.tv_bottom_toast.setText(Html.fromHtml(str));
        viewHolder.tv_commit.setText("注册");

        viewHolder.et_register_code.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        viewHolder.et_register_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        viewHolder.et_register_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        viewHolder.et_register_password.setHint("6-20位数字或字母");
        viewHolder.lin_protocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.cb_check.toggle();
            }
        });
        viewHolder.tv_register_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.et_register_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });
    }

    public void initSetUserName() {
        viewHolder.lin_other_code.setVisibility(View.GONE);
        viewHolder.lin_other.setVisibility(View.VISIBLE);
        viewHolder.tv_other_toast.setText("请设置一个用户名, 用户名作为您对外展示的身份标识, 设置成功后不可修改");
        viewHolder.tv_ettitle.setText("用户名");
        viewHolder.et_other_phone.setHint("不超过8位字符");
        viewHolder.et_other_phone.setInputType(InputType.TYPE_CLASS_TEXT);
        viewHolder.et_other_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});//15
        viewHolder.tv_commit.setText("完成设置");
    }

    public void initFindPassword() {
        viewHolder.tv_ettitle.setText("手机号");
        viewHolder.lin_other.setVisibility(View.VISIBLE);
        viewHolder.tv_commit.setText("继续");
        viewHolder.et_other_code.setHint("请输入验证码");
        viewHolder.et_other_phone.setInputType(InputType.TYPE_CLASS_NUMBER);
        viewHolder.et_other_code.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    public void initFindPasswordNext() {
        viewHolder.et_other_phone.setEnabled(true);
        viewHolder.lin_other.setVisibility(View.VISIBLE);
        viewHolder.tv_other_toast.setText("请设置一个新密码");
        viewHolder.et_other_phone.setText("");
        viewHolder.et_other_phone.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        viewHolder.et_other_phone.setHint("6-20位数字或字母");
        viewHolder.tv_ettitle.setText("密码");
        viewHolder.et_other_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        viewHolder.lin_other_code.setVisibility(View.GONE);
        viewHolder.tv_commit.setText("完成设置");
    }

    public void initGetCode() {
        viewHolder.lin_other.setVisibility(View.VISIBLE);
        viewHolder.tv_commit.setText("确定");
        viewHolder.tv_other_toast.setText("该操作涉及到资金安全我们需要验证您的身份,请点击右下方按钮获取验证码。");
        viewHolder.lin_other.setVisibility(View.VISIBLE);
        viewHolder.lin_phone.setVisibility(View.GONE);
        viewHolder.et_other_phone.setInputType(InputType.TYPE_CLASS_NUMBER);
        viewHolder.et_other_code.setInputType(InputType.TYPE_CLASS_NUMBER);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_register;
    }


    public static class ViewHolder {
        public View rootView;
        public IconFontTextview tv_logo;
        public EditText et_login_phone;
        public EditText et_login_password;
        public IconFontTextview tv_login_show;
        public LinearLayout lin_login;
        public EditText et_register_email;
        public EditText et_register_phone;
        public EditText et_register_code;
        public SingleLineZoomTextView tv_register_get_code;
        public EditText et_register_password;
        public IconFontTextview tv_register_show;
        public CheckBox cb_check;
        public LinearLayout lin_protocol;
        public TextView tv_protocol;
        public LinearLayout lin_register;
        public TextView tv_other_toast;
        public TextView tv_ettitle;
        public EditText et_other_phone;
        public LinearLayout lin_phone;
        public EditText et_other_code;
        public SingleLineZoomTextView tv_other_get_code;
        public LinearLayout lin_other_code;
        public LinearLayout lin_other;
        public TextView tv_commit;
        public TextView tv_forget_password;
        public TextView tv_bottom_toast;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_logo = (IconFontTextview) rootView.findViewById(R.id.tv_logo);
            this.et_login_phone = (EditText) rootView.findViewById(R.id.et_login_phone);
            this.et_login_password = (EditText) rootView.findViewById(R.id.et_login_password);
            this.tv_login_show = (IconFontTextview) rootView.findViewById(R.id.tv_login_show);
            this.lin_login = (LinearLayout) rootView.findViewById(R.id.lin_login);
            this.et_register_email = (EditText) rootView.findViewById(R.id.et_register_email);
            this.et_register_phone = (EditText) rootView.findViewById(R.id.et_register_phone);
            this.et_register_code = (EditText) rootView.findViewById(R.id.et_register_code);
            this.tv_register_get_code = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_register_get_code);
            this.et_register_password = (EditText) rootView.findViewById(R.id.et_register_password);
            this.tv_register_show = (IconFontTextview) rootView.findViewById(R.id.tv_register_show);
            this.cb_check = (CheckBox) rootView.findViewById(R.id.cb_check);
            this.lin_protocol = (LinearLayout) rootView.findViewById(R.id.lin_protocol);
            this.tv_protocol = (TextView) rootView.findViewById(R.id.tv_protocol);
            this.lin_register = (LinearLayout) rootView.findViewById(R.id.lin_register);
            this.tv_other_toast = (TextView) rootView.findViewById(R.id.tv_other_toast);
            this.tv_ettitle = (TextView) rootView.findViewById(R.id.tv_ettitle);
            this.et_other_phone = (EditText) rootView.findViewById(R.id.et_other_phone);
            this.lin_phone = (LinearLayout) rootView.findViewById(R.id.lin_phone);
            this.et_other_code = (EditText) rootView.findViewById(R.id.et_other_code);
            this.tv_other_get_code = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_other_get_code);
            this.lin_other_code = (LinearLayout) rootView.findViewById(R.id.lin_other_code);
            this.lin_other = (LinearLayout) rootView.findViewById(R.id.lin_other);
            this.tv_commit = (TextView) rootView.findViewById(R.id.tv_commit);
            this.tv_forget_password = (TextView) rootView.findViewById(R.id.tv_forget_password);
            this.tv_bottom_toast = (TextView) rootView.findViewById(R.id.tv_bottom_toast);
        }

    }
}
