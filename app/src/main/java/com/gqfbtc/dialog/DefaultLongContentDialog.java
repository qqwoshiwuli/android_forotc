package com.gqfbtc.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.AndroidUtil;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.fivefivelike.mybaselibrary.view.dialog.BaseDialog;
import com.gqfbtc.R;

/**
 * Created by 郭青枫 on 2017/11/17.
 */

public class DefaultLongContentDialog extends BaseDialog {
    private TextView tv_content;
    private ScrollView sv_txt;
    private TextView tv_commit;
    private TextView tv_cannel;

    int height;
    private TextView tv_title;

    DefaultClickLinsener defaultClickLinsener;

    String title;
    String content;

    public DefaultLongContentDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public DefaultLongContentDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public DefaultLongContentDialog setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
        return this;
    }

    public DefaultLongContentDialog(Context context) {
        super(context);
        height = AndroidUtil.getScreenW(context, true);
    }


    @Override
    protected int getLayout() {
        return R.layout.dialog_default_long_content;
    }

    @Override
    protected void startInit() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        tv_content = findViewById(R.id.tv_content);
        tv_title = findViewById(R.id.tv_title);
        sv_txt = findViewById(R.id.sv_txt);
        tv_commit = findViewById(R.id.tv_commit);
        tv_cannel = findViewById(R.id.tv_cannel);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (defaultClickLinsener != null) {
                    defaultClickLinsener.onClick(view, 1, null);
                }
                dismiss();
            }
        });
        tv_cannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void showDialog() {
        tv_title.setText(title);
        tv_content.setText(content);
        this.show();
    }


}
