package com.gqfbtc.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.fivefivelike.mybaselibrary.view.dialog.BaseDialog;
import com.gqfbtc.R;
import com.gqfbtc.Utils.glide.GlideUtils;

/**
 * Created by 郭青枫 on 2017/11/20.
 */

public class ImgToastDialog extends BaseDialog {
    private ImageView iv_pic;

    String path;
    int imgId = 0;

    public ImgToastDialog setImgId(int imgId) {
        this.imgId = imgId;
        return this;
    }

    public ImgToastDialog setPath(String path) {
        this.path = path;
        return this;
    }


    public ImgToastDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_img;
    }

    @Override
    protected void startInit() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setWindowNoPadding();
        iv_pic = findViewById(R.id.iv_pic);
        if (!TextUtils.isEmpty(path)) {
            GlideUtils.loadImage(path, iv_pic);
        }
        if (imgId != 0) {
            iv_pic.setImageResource(imgId);
        }
        iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


}
