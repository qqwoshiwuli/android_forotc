package com.gqfbtc.mvp.activity.user;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.blankj.utilcode.util.FileUtils;
import com.bumptech.glide.Glide;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.logger.KLog;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.dialog.ImgToastDialog;
import com.gqfbtc.entity.event.FilePathsEvent;
import com.gqfbtc.mvp.databinder.RealNameAuthenticationBinder;
import com.gqfbtc.mvp.delegate.RealNameAuthenticationDelegate;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class RealNameAuthenticationActivity extends BaseDataBindActivity<RealNameAuthenticationDelegate, RealNameAuthenticationBinder> {

    int nowChooseImgId = 0;
    File fountCard;
    File reverseCard;
    File userWithCard;

    @Override
    protected Class<RealNameAuthenticationDelegate> getDelegateClass() {
        return RealNameAuthenticationDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("实名认证"));
        viewDelegate.setOnClickListener(this, R.id.iv_front_idcard, R.id.iv_reverse_idcard, R.id.iv_people_idcard, R.id.tv_commit, R.id.tv_toast);
    }

    private void chooseTypeDialog() {
        if (nowChooseImgId != 0) {
            String[] items = {"拍照", "相册"};
            UiHeplUtils.initDefaultItemDialog(this, items, new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        camera();
                    } else if (position == 1) {
                        image();
                    }
                }
            }).show();
        } else {
            ToastUtil.show(CommonUtils.getString(R.string.str_permission_default));
        }
    }

    //相机拍照
    private void camera() {
        Album.camera(RealNameAuthenticationActivity.this) // 相机功能。
                .image() // 拍照。
                .requestCode(0x123)
                .onResult(new Action<String>() {
                    @Override
                    public void onAction(int requestCode, @NonNull String result) {
                        setImg(result);
                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(int requestCode, @NonNull String result) {

                    }
                })
                .start();
    }

    //图库选图
    private void image() {
        Album.image(RealNameAuthenticationActivity.this) // Image selection.
                .singleChoice()
                .requestCode(0x123)
                .camera(true)
                .columnCount(3)
                .onResult(new com.yanzhenjie.album.Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(int requestCode, @android.support.annotation.NonNull ArrayList<AlbumFile> result) {
                        if (result.size() > 0) {
                            if ("image/gif".equals(result.get(0).getMimeType())) {
                                ToastUtil.show("不能上传gif图片");
                                return;
                            }
                            for (AlbumFile item : result) {
                                setImg(item.getPath());
                            }
                        }
                    }
                })
                .start();
    }

    private void setImg(String result) {
        File file = null;
        switch (nowChooseImgId) {
            case R.id.iv_front_idcard:
                fountCard = new File(result);
                file = fountCard;
                break;
            case R.id.iv_reverse_idcard:
                reverseCard = new File(result);
                file = reverseCard;
                break;
            case R.id.iv_people_idcard:
                userWithCard = new File(result);
                file = userWithCard;
                break;
        }

        Glide.with(this).asBitmap().load(file).into(((ImageView) viewDelegate.viewHolder.rootView.findViewById(nowChooseImgId)));
    }


    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == 0x123) {
                chooseTypeDialog();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 0x123) {
                nowChooseImgId = 0;
            }
            ToastUtil.show("没有相关权限，请打开或在设置中心中开启");
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_front_idcard:
            case R.id.iv_reverse_idcard:
            case R.id.iv_people_idcard:
                nowChooseImgId = v.getId();
                AndPermission.with(this)
                        .requestCode(0x123)
                        .permission(Permission.CAMERA, Permission.STORAGE)
                        .rationale(new RationaleListener() {
                            @Override
                            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                AndPermission.rationaleDialog(RealNameAuthenticationActivity.this, rationale).show();
                            }
                        })
                        .callback(listener)
                        .start();
                break;
            case R.id.tv_commit:
                commit();
                break;
            case R.id.tv_toast:
                //查看示例
                new ImgToastDialog(this).show();
                break;
        }
    }

    FilePathsEvent filePathsEvent;

    private void commit() {
        if (fountCard == null) {
            ToastUtil.show("请上传身份证正面照片");
            return;
        }
        if (reverseCard == null) {
            ToastUtil.show("请上传身份证反面照片");
            return;
        }
        if (userWithCard == null) {
            ToastUtil.show("请上传手持身份证照片");
            return;
        }
        long fileLength1 = FileUtils.getFileLength(fountCard);
        long fileLength2 = FileUtils.getFileLength(reverseCard);
        long fileLength3 = FileUtils.getFileLength(userWithCard);
        KLog.i("上传文件大小：size1" + fileLength1 + "size2" + fileLength2 + "size3" + fileLength3);

        if (fileLength1 + fileLength2 + fileLength3 > 15 * 1024 * 1024) {
            ToastUtil.show("图片总大小不能超过15M");
            return;
        }

        filePathsEvent = new FilePathsEvent();
        List<String> paths = new ArrayList<>();
        paths.add(fountCard.getAbsolutePath());
        paths.add(reverseCard.getAbsolutePath());
        paths.add(userWithCard.getAbsolutePath());
        filePathsEvent.setPaths(paths);
        viewDelegate.viewHolder.tv_commit.setEnabled(false);
        UiHeplUtils.initDefaultDialog(this, "是否转入后台上传", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(filePathsEvent);
                onBackPressed();
            }
        }).setNegative("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.show("上传中请勿返回，否则会中断上传");
                addRequest(binder.batch(fountCard, reverseCard, userWithCard, RealNameAuthenticationActivity.this));
            }
        }).show();
    }


    @Override
    public RealNameAuthenticationBinder getDataBinder(RealNameAuthenticationDelegate viewDelegate) {
        return new RealNameAuthenticationBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:

                break;
        }
    }
}
