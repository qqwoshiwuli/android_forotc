package com.gqfbtc.Utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.fivefivelike.mybaselibrary.utils.FileUtil;
import com.fivefivelike.mybaselibrary.utils.GlobleContext;
import com.gqfbtc.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.gqfbtc.base.AppConst.httpBaseUrl;


/**
 * Created by 郭青枫 on 2016/11/29.
 */

public class GlideUtils {
    public static final String BASE_URL = httpBaseUrl;

    public static String getBaseUrl() {
        return BASE_URL + "/avatar/";
    }

    //http://forotc.com/avatar/23.png
    public static void loadImage(String url, ImageView icon) {
        loadImage(url, icon, R.drawable.loaderror, null);
    }

    public static void loadImage(String url, ImageView icon, int errorId) {
        loadImage(url, icon, errorId, null);
    }


    public static void loadImage(String url, ImageView icon, int errorId, RequestListener<Drawable> requestListener) {
        if (!TextUtils.isEmpty(url) && !url.startsWith("http") && !new File(url).exists()) {
            url = BASE_URL + "/avatar/" + url;
        }
        Glide.with(GlobleContext.getInstance().getApplicationContext())
                .load(url).
                apply(new RequestOptions()
                        .centerCrop()
                        .error(errorId)
                )
                .listener(requestListener)
                .into(icon);
    }

    public static void loadImage(Uri url, ImageView icon) {
        Glide.with(GlobleContext.getInstance().getApplicationContext())
                .load(url).
                apply(new RequestOptions().centerCrop().error(R.drawable.loaderror))
                .into(icon);
    }

    public static File saveBitmap(Context context, String name, Bitmap bitmap) {
        // 创建一个位于SD卡上的文件
        File file = new File(FileUtil.getIamgePath(context),
                name);
        if (file.exists()) {
            return file;
        }
        FileOutputStream out = null;
        try {
            // 打开指定文件输出流
            out = new FileOutputStream(file);
            // 将位图输出到指定文件
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    out);
            MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, null);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
