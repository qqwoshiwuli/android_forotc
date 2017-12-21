package com.gqfbtc.dialog;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.circledialog.CircleDialogHelper;
import com.fivefivelike.mybaselibrary.http.SingleRequest;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.FileUtil;
import com.fivefivelike.mybaselibrary.utils.GlobleContext;
import com.fivefivelike.mybaselibrary.utils.StringUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.logger.KLog;
import com.fivefivelike.mybaselibrary.view.dialog.BaseDialog;
import com.fivefivelike.mybaselibrary.view.dialog.NetConnectDialog;
import com.gqfbtc.R;
import com.gqfbtc.adapter.ShareAdapter;
import com.gqfbtc.entity.ShareItemEntity;
import com.gqfbtc.entity.bean.ShareEntity;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


/**
 * Created by 郭青枫 on 2017/9/30.
 */

public class ShareDialog extends BaseDialog {
    private RecyclerView recycleview;
    private TextView tv_cancel;
    public ShareAdapter adapter;
    public List<ShareItemEntity> list;
    private SharePlatformChooseListener sharePlatformChooseListener;
    public static final String SINA_PACKAGE_NAME = "com.sina.weibo";
    public static final String QQ_PACKAGE_NAME = "com.tencent.mobileqq";
    public static final String WECHAT_PACKAGE_NAME = "com.tencent.mm";
    public static final String SINA_ACT_NAME = "com.sina.weibo.composerinde.ComposerDispatchActivity";
    public static final String QQ_ACT_NAME = "com.tencent.mobileqq.activity.JumpActivity";
    public static final String WECHAT_ACT_NAME = "com.tencent.mm.ui.tools.ShareImgUI";
    public static final String WECHAT_MONMENT_ACT_NAME = "com.tencent.mm.ui.tools.ShareToTimeLineUI";

    public ShareDialog(Activity context, SharePlatformChooseListener sharePlatformChooseListener) {
        super(context);
        this.sharePlatformChooseListener = sharePlatformChooseListener;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_share;
    }

    @Override
    protected void startInit() {
        getWindow().setGravity(Gravity.BOTTOM);
        setWindowNoPadding();
        recycleview = (RecyclerView) findViewById(R.id.recycleview);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        list = new ArrayList<>();
        adapter = new ShareAdapter(mContext, list);
        recycleview.setLayoutManager(new GridLayoutManager(mContext, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recycleview.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (sharePlatformChooseListener != null) {
                    sharePlatformChooseListener.onPlatformChoose(ShareDialog.this, adapter.getDatas().get(position));
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface SharePlatformChooseListener {
        void onPlatformChoose(Dialog dialog, ShareItemEntity shareObj);
    }


    public void refreshData(List<ShareItemEntity> list) {
        this.list.clear();
        if (list != null) {
            this.list.addAll(list);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 获取本地分享选项
     *
     * @param context
     * @return
     */
    public static List<ShareItemEntity> getSystemList(Context context) {
        List<ShareItemEntity> list = new ArrayList<>();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.setType("image/*");
        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(shareIntent, 0);
        for (ResolveInfo info : resInfo) {
            ActivityInfo activityInfo = info.activityInfo;
            String packageName = activityInfo.packageName;
            String className = activityInfo.name;
            if (packageName.equals(WECHAT_PACKAGE_NAME) && className.equals(WECHAT_ACT_NAME)) {
                list.add(new ShareItemEntity(CommonUtils.getString(R.string.str_share_wechat), Wechat.NAME, R.drawable.sharewx, WECHAT_PACKAGE_NAME, WECHAT_ACT_NAME));
            } else if (packageName.equals(WECHAT_PACKAGE_NAME) && className.equals(WECHAT_MONMENT_ACT_NAME)) {
                list.add(new ShareItemEntity(CommonUtils.getString(R.string.str_share_wechat_circle), WechatMoments.NAME, R.drawable.sharewxpy, WECHAT_PACKAGE_NAME, WECHAT_MONMENT_ACT_NAME));
            } else if (packageName.equals(QQ_PACKAGE_NAME) && className.equals(QQ_ACT_NAME)) {
                list.add(new ShareItemEntity(CommonUtils.getString(R.string.str_share_qq), QQ.NAME, R.drawable.shareqq, QQ_PACKAGE_NAME, QQ_ACT_NAME));
            } else if (packageName.equals(SINA_PACKAGE_NAME) && className.equals(SINA_ACT_NAME)) {
                list.add(new ShareItemEntity(CommonUtils.getString(R.string.str_share_sina), SinaWeibo.NAME, R.drawable.sharewb, SINA_PACKAGE_NAME, SINA_ACT_NAME));
            }
        }
        return list;
    }

    /**
     * 获取正常走sharesdk分享的选项
     *
     * @return
     */
    public static List<ShareItemEntity> getNormalList() {
        List<ShareItemEntity> list = new ArrayList<>();
        list.add(new ShareItemEntity(CommonUtils.getString(R.string.str_share_wechat), Wechat.NAME, R.drawable.sharewx));
        list.add(new ShareItemEntity(CommonUtils.getString(R.string.str_share_wechat_circle), WechatMoments.NAME, R.drawable.sharewxpy));
        //list.add(new ShareItemEntity("QQ好友", QQ.NAME, R.drawable.shareqq));
        //list.add(new ShareItemEntity("QQ空间", QZone.NAME, R.drawable.shareqqkj));
        //list.add(new ShareItemEntity("新浪微博", SinaWeibo.NAME, R.drawable.sharewb));
        return list;
    }

    /**
     * 调用系统分享
     *
     * @param context
     * @param itemEntity
     * @param fileImages
     */
    public static void shareWithSystem(Context context, ShareItemEntity itemEntity, List<File> fileImages, String shareTxt) {
        try {
            Intent intent = new Intent();
            ComponentName comp = new ComponentName(itemEntity.getPackageName(), itemEntity.getActivityName());
            intent.setComponent(comp);
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("image/*");
            intent.putExtra("Kdescription", shareTxt);
            //            intent.putExtra("Kdescription", "11111111");
            if (fileImages == null || fileImages.size() == 0) {
                ToastUtil.show(CommonUtils.getString(R.string.str_share_nocontent));
                return;
            }
            ArrayList<Uri> uris = new ArrayList<>();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                for (File file : fileImages) {
                    uris.add(Uri.fromFile(file));
                }
            } else {
                for (File file : fileImages) {
                    uris.add(Uri.parse(android.provider.MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), null)));
                }
            }
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
            ((Activity) context).startActivityForResult(intent, 1000);
        } catch (Throwable e) {
        }
    }


    /**
     * 调用sharesdk分享
     *
     * @param context
     * @param obj
     */
    public static void showShare(final Context context, final ShareEntity obj, PlatformActionListener platformActionListener) {
        final OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        if (obj.getPlatform() != null) {
            oks.setPlatform(obj.getPlatform());
        }
        String title = obj.getTitle();
        String content = obj.getContent();
        final String url = obj.getUrl();
        String imageUrl = obj.getPic();
        if (StringUtil.isBlank(title)) {
            title = CommonUtils.getString(R.string.str_share_default_title);
        }
        if (StringUtil.isBlank(content)) {
            content = CommonUtils.getString(R.string.str_share_default_content);
        }
        oks.setUrl(url);
        oks.setTitleUrl(url);
        oks.setTitle(title);
        oks.setText(content);
        oks.setVenueName(CommonUtils.getString(R.string.app_name));
        oks.setImageUrl(imageUrl);
        oks.setCallback(platformActionListener);
        oks.show(GlobleContext.getInstance().getApplicationContext());
    }


    /**
     * bitmap保存本地
     */
    public static File saveMyBitmap(String bitName, Bitmap mBitmap) {
        File f = new File(bitName);// new File("/sdcard/" + bitName + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            MediaStore.Images.Media.insertImage(GlobleContext.getInstance().getApplicationContext().getContentResolver(),
                    f.getAbsolutePath(),bitName , null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GlobleContext.getInstance().getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(f.getPath()))));
        return f;
    }

    /**
     * 下载文件
     *
     * @param context
     * @param pathList
     */
    public static void downBitmapToFile(final Context context, final List<Bitmap> pathList, final List<String> names, final boolean isShowDialog) {
        AndPermission.with(context)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        for (int i = 0; i < pathList.size(); i++) {
                            File file=saveMyBitmap(names.get(i), pathList.get(i));
                        }
                        if (isShowDialog) {
                            CircleDialogHelper.initDefaultToastDialog((FragmentActivity) context, CommonUtils.getString(R.string.str_file_save), null).show();
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        ToastUtil.show(CommonUtils.getString(R.string.str_permission_read_write));
                    }
                }).start();
    }

    /**
     * 下载文件
     *
     * @param context
     * @param pathList
     * @param downLoadBack
     */
    public static void downFile(final Context context, final List<String> pathList, final DownLoadBack downLoadBack) {
        AndPermission.with(context)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        if (pathList == null || pathList.size() == 0)
                            return;
                        final List<File> fileList = new ArrayList<>();
                        final int[] count = new int[]{pathList.size()};
                        final NetConnectDialog dialog = new NetConnectDialog(context);
                        DownloadListener downloadListener = new DownloadListener() {
                            @Override
                            public void onDownloadError(int what, Exception exception) {
                                KLog.i();
                                count[0] = count[0] - 1;
                                if (fileList.size() == count[0]) {
                                    downLoadBack.onBack(fileList);
                                    dialog.dismiss();
                                }
                            }

                            @Override
                            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                                dialog.show();
                            }

                            @Override
                            public void onProgress(int what, int progress, long fileCount, long speed) {
                            }

                            @Override
                            public void onFinish(int what, String filePath) {
                                fileList.add(new File(filePath));
                                if (fileList.size() == count[0]) {
                                    downLoadBack.onBack(fileList);
                                    dialog.dismiss();
                                }
                            }

                            @Override
                            public void onCancel(int what) {
                                count[0] = count[0] - 1;
                                if (fileList.size() == count[0]) {
                                    downLoadBack.onBack(fileList);
                                    dialog.dismiss();
                                }
                            }
                        };
                        for (int i = 0; i < pathList.size(); i++) {
                            DownloadRequest downloadRequest = NoHttp.createDownloadRequest(pathList.get(i), FileUtil.getShareImagePath(context), false);
                            SingleRequest.getInstance().download(i, downloadRequest, downloadListener);
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        ToastUtil.show(CommonUtils.getString(R.string.str_permission_read_write));
                    }
                }).start();

    }

    public interface DownLoadBack {
        void onBack(List<File> list);
    }

}
