package com.gqfbtc.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.fivefivelike.mybaselibrary.view.dialog.BaseDialog;
import com.gqfbtc.R;
import com.gqfbtc.entity.bean.AppVersion;

/**
 * Created by 郭青枫 on 2017/11/28 0028.
 */

public class UpdateDialog extends BaseDialog implements DialogInterface.OnCancelListener {


    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_cannel;
    private TextView tv_confirm;
    AppVersion appVersion;
    DefaultClickLinsener defaultClickLinsener;

    public UpdateDialog setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public UpdateDialog setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
        return this;
    }

    public UpdateDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_updata;
    }

    @Override
    protected void startInit() {
        getWindow().setGravity(Gravity.CENTER);
        setWindowNoPadding();
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        tv_cannel = findViewById(R.id.tv_cannel);
        tv_confirm = findViewById(R.id.tv_confirm);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);

        tv_cannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取消
                click(0);
                dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //确认
                click(1);
                dismiss();
            }
        });

        this.setOnCancelListener(this);

    }

    public void showDialog() {
        tv_title.setText(appVersion.getVersionTitle());
        tv_content.setText(Html.fromHtml(appVersion.getVersionContent()));
        this.show();
    }

    private void click(int posotion) {
        if (defaultClickLinsener != null) {
            defaultClickLinsener.onClick(null, posotion, appVersion);
        }
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        //取消
        click(0);
    }
}
