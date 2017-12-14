package com.gqfbtc.dialog;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.AndroidUtil;
import com.fivefivelike.mybaselibrary.view.dialog.BaseDialog;
import com.gqfbtc.R;

/**
 * Created by 郭青枫 on 2017/11/17.
 */

public class ProtocolDialog extends BaseDialog {
    private TextView tv_content;
    private ScrollView sv_txt;
    private TextView tv_commit;

    int height;
    private TextView tv_title;

    public ProtocolDialog(Context context) {
        super(context);
        height = AndroidUtil.getScreenW(context, true);
    }


    @Override
    protected int getLayout() {
        return R.layout.dialog_protocol;
    }

    @Override
    protected void startInit() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        tv_content = findViewById(R.id.tv_content);
        tv_title = findViewById(R.id.tv_title);
        sv_txt = findViewById(R.id.sv_txt);
        tv_commit = findViewById(R.id.tv_commit);
        tv_title.setText(Html.fromHtml("<font color=\"#007cd1\">FOROTC</font>  服务条款"));
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


}
