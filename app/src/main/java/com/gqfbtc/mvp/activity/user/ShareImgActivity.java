package com.gqfbtc.mvp.activity.user;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.biv.loader.ImageLoader;
import com.biv.loader.glide.GlideImageLoader;
import com.bumptech.glide.Glide;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GlobleContext;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.dialog.ShareDialog;
import com.gqfbtc.entity.ShareItemEntity;
import com.gqfbtc.mvp.databinder.ShareImgBinder;
import com.gqfbtc.mvp.delegate.ShareImgDelegate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class ShareImgActivity extends BaseDataBindActivity<ShareImgDelegate, ShareImgBinder> {
    ShareDialog shareDialog;

    List<String> imgs;
    String imgUrl;

    public static void startAct(Activity activity,
                                String type) {
        Intent intent = new Intent(activity, ShareImgActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    private String type;

    private void getIntentData() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
    }

    @Override
    protected Class<ShareImgDelegate> getDelegateClass() {
        return ShareImgDelegate.class;
    }

    @Override
    public ShareImgBinder getDataBinder(ShareImgDelegate viewDelegate) {
        return new ShareImgBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle(CommonUtils.getString(R.string.str_title_shareimgactivity)));
        getIntentData();
        initShareDialog();
        addRequest(binder.getQRCode(this));
    }

    private void initShareDialog() {
        shareDialog = new ShareDialog(this, new ShareDialog.SharePlatformChooseListener() {
            @Override
            public void onPlatformChoose(Dialog dialog, final ShareItemEntity shareObj) {
                dialog.dismiss();
                if (shareObj.isSystemShare()) {//系统分享
                    List<String> list = new ArrayList<>();
                    list.add(imgs.get(viewDelegate.viewHolder.banner.getCurrentItem()));
                    if (list == null || list.size() == 0) {
                        ToastUtil.show(CommonUtils.getString(R.string.str_share_no_img));
                        return;
                    }
                    List<String> names = new ArrayList<>();
                    View view = viewDelegate.viewHolder.banner.getViews().get(viewDelegate.viewHolder.banner.getCurrentItem());
                    if (view instanceof ImageView) {
                        if (((ImageView) view).getDrawable() == null) {
                            ToastUtil.show(CommonUtils.getString(R.string.str_share_no_load));
                            return;
                        }
                        Bitmap image = ((BitmapDrawable) ((ImageView) view).getDrawable()).getBitmap();
                        List<Bitmap> bitmaps = new ArrayList<>();
                        bitmaps.add(image);
                        names.add("/sdcard/" + "forotc" + viewDelegate.viewHolder.banner.getCurrentItem() + ".png");
                        ShareDialog.downBitmapToFile(ShareImgActivity.this, bitmaps, names, false);
                    }
                    List<File> files = new ArrayList<>();
                    for (int i = 0; i < names.size(); i++) {
                        files.add(new File(names.get(i)));
                    }
                    ShareDialog.shareWithSystem(ShareImgActivity.this, shareObj, files, type);
                } else {//sharesdk分享

                }
            }
        });
    }

    private void doShare() {
        List<ShareItemEntity> systemList = ShareDialog.getSystemList(this);
        if (systemList == null || systemList.size() == 0) {
            ToastUtil.show(CommonUtils.getString(R.string.str_share_no));
            return;
        }
        shareDialog.refreshData(ShareDialog.getSystemList(this));
        shareDialog.show();
    }

    private void doSave() {
        //保存当前展示图片到手机
        View view = viewDelegate.viewHolder.banner.getViews().get(viewDelegate.viewHolder.banner.getCurrentItem());
        if (view instanceof ImageView) {
            Bitmap image = ((BitmapDrawable) ((ImageView) view).getDrawable()).getBitmap();
            List<Bitmap> bitmaps = new ArrayList<>();
            List<String> names = new ArrayList<>();
            bitmaps.add(image);
            names.add("/sdcard/" + "forotc" + viewDelegate.viewHolder.banner.getCurrentItem() + ".png");
            ShareDialog.downBitmapToFile(this, bitmaps, names, true);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_commit:
                doShare();
                break;
            case R.id.tv_save:
                doSave();
                break;
            case R.id.tv_change:
                viewDelegate.viewHolder.banner.getViewPager().arrowScroll(View.FOCUS_RIGHT);
                break;
        }

    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        super.onServiceError(data, info, status, requestCode);
        switch (requestCode) {
            case 0x123:
                //获取图片
                imgs = GsonUtil.getInstance().toList(data, String.class);
                List<String> titles = new ArrayList<>();
                for (int i = 0; i < imgs.size(); i++) {
                    titles.add("");
                }
                viewDelegate.setOnClickListener(this, R.id.tv_commit, R.id.tv_save, R.id.tv_change);
                viewDelegate.viewHolder.banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
                    @Override
                    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {

                    }
                });
                viewDelegate.viewHolder.banner.setData(imgs, titles);
                setImgPath(0);
                viewDelegate.viewHolder.banner.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        viewDelegate.viewHolder.iv_progress.setVisibility(View.GONE);
                        setImgPath(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                break;
        }
    }

    private void setImgPath(final int position) {
        //加载进度监听
        View view = viewDelegate.viewHolder.banner.getViews().get(position);
        if (((ImageView) view).getDrawable() == null) {
            ViewCompat.setElevation(view, 0.5f);
            ViewCompat.setElevation(view, 0.5f);
            ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_CENTER);
            GlideImageLoader.with(this)
                    .loadImage(imgs.get(position), new ImageLoader.Callback() {
                        @Override
                        public void onCacheHit(File image) {
                            //imgLoadStatu(image, "onCacheHit", position, 0);
                        }

                        @Override
                        public void onCacheMiss(File image) {
                            //imgLoadStatu(image, "onCacheMiss", position, 0);
                        }

                        @Override
                        public void onStart() {
                            imgLoadStatu(null, "onStart", position, 0);
                        }

                        @Override
                        public void onProgress(int progress) {
                            imgLoadStatu(null, "onProgress", position, progress);
                        }

                        @Override
                        public void onFinish() {
                            imgLoadStatu(null, "onFinish", position, 0);
                        }

                        @Override
                        public void onSuccess(File image) {
                            imgLoadStatu(image, "onSuccess", position, 0);
                        }

                        @Override
                        public void onFail(Exception error) {
                            imgLoadStatu(null, "onFail", position, 0);
                        }
                    });
        }
    }

    int imgLoadProgress = 0;
    //进度展示
    private Handler handler = new Handler() {//进行延时跳转
        public void handleMessage(Message msg) {
            if (viewDelegate != null) {
                if (viewDelegate.viewHolder != null) {
                    Bundle bundle = msg.getData();
                    if ("onCacheHit".equals(bundle.getString("type"))) {
                        initLoadImg(bundle.getString("file"), msg.what);
                    } else if ("onCacheMiss".equals(bundle.getString("type"))) {
                        initLoadImg(bundle.getString("file"), msg.what);
                    } else if ("onStart".equals(bundle.getString("type"))) {
                        if (msg.what == viewDelegate.viewHolder.banner.getViewPager().getCurrentItem()) {
                            viewDelegate.viewHolder.iv_progress.setVisibility(View.VISIBLE);
                        }
                    } else if ("onProgress".equals(bundle.getString("type"))) {
                        if (msg.what == viewDelegate.viewHolder.banner.getViewPager().getCurrentItem()) {
                            viewDelegate.viewHolder.iv_progress.setProgress(imgLoadProgress);
                            viewDelegate.viewHolder.iv_progress.setVisibility(View.VISIBLE);
                        }
                    } else if ("onFinish".equals(bundle.getString("type"))) {
                        if (msg.what == viewDelegate.viewHolder.banner.getViewPager().getCurrentItem()) {
                            viewDelegate.viewHolder.iv_progress.setVisibility(View.GONE);
                        }
                    } else if ("onSuccess".equals(bundle.getString("type"))) {
                        initLoadImg(bundle.getString("file"), msg.what);
                    } else if ("onFail".equals(bundle.getString("type"))) {
                        if (msg.what == viewDelegate.viewHolder.banner.getViewPager().getCurrentItem()) {
                            viewDelegate.viewHolder.iv_progress.setVisibility(View.GONE);
                        }
                        View view = viewDelegate.viewHolder.banner.getViews().get(msg.what);
                        Glide.with(GlobleContext.getInstance().getApplicationContext()).asBitmap().load(R.drawable.loaderror).into(((ImageView) view));
                    }
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);//清空消息方便gc回收
        super.onDestroy();
    }

    private void imgLoadStatu(File file, String type, int position, int progress) {
        //发送进度
        imgLoadProgress = progress;
        Message imgLoadMsg = new Message();
        imgLoadMsg.what = position;
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        if (file != null) {
            bundle.putString("file", file.getAbsolutePath());
        }
        imgLoadMsg.setData(bundle);
        handler.sendMessage(imgLoadMsg);
    }

    private void initLoadImg(String path, int position) {
        //加载图片
        View view = viewDelegate.viewHolder.banner.getViews().get(position);
        if (((ImageView) view).getDrawable() == null) {
            Glide.with(GlobleContext.getInstance().getApplicationContext()).asBitmap().load(new File(path)).into(((ImageView) view));
        }
        viewDelegate.viewHolder.iv_progress.setVisibility(View.GONE);
    }

}
