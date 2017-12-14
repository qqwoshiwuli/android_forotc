package com.gqfbtc.Utils;

/**
 * Created by 郭青枫 on 2017/9/27.
 */

import android.webkit.URLUtil;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;
import com.yanzhenjie.album.task.DefaultAlbumLoader;

import java.io.File;

/**
 * Created by Yan Zhenjie on 2017/3/31.
 */
public class GlideAlbumLoader implements AlbumLoader {

    @Override
    public void loadAlbumFile(ImageView imageView, AlbumFile albumFile, int viewWidth, int viewHeight) {
        int mediaType = albumFile.getMediaType();
        if (mediaType == AlbumFile.TYPE_IMAGE) {
            Glide.with(imageView.getContext())
                    .load(albumFile.getPath())
                    .into(imageView);
        } else if (mediaType == AlbumFile.TYPE_VIDEO) {
            DefaultAlbumLoader.getInstance()
                    .loadAlbumFile(imageView, albumFile, viewWidth, viewHeight);
        }
    }

    @Override
    public void loadImage(ImageView imageView, String imagePath, int width, int height) {
        if (URLUtil.isNetworkUrl(imagePath)) {
            Glide.with(imageView.getContext())
                    .load(imagePath)
                    .into(imageView);
        } else {
            Glide.with(imageView.getContext())
                    .load(new File(imagePath))
                    .into(imageView);
        }
    }

}
