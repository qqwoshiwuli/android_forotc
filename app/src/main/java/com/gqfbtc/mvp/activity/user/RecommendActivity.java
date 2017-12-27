package com.gqfbtc.mvp.activity.user;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.CacheUtils;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.dialog.ShareDialog;
import com.gqfbtc.entity.ShareItemEntity;
import com.gqfbtc.entity.bean.ShareBean;
import com.gqfbtc.entity.bean.ShareEntity;
import com.gqfbtc.mvp.databinder.RecommendBinder;
import com.gqfbtc.mvp.delegate.RecommendDelegate;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


public class RecommendActivity extends BaseDataBindActivity<RecommendDelegate, RecommendBinder> {

    public static final String action = "forotc://share";


    ShareBean shareBean;
    ShareDialog shareDialog;
    ShareEntity shareEntity;

    @Override
    protected Class<RecommendDelegate> getDelegateClass() {
        return RecommendDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("分享给朋友").setSubTitle("帮助"));
        String shareBeanJson = CacheUtils.getInstance().getString("ShareBean");
        if (!TextUtils.isEmpty(shareBeanJson)) {
            shareBean = GsonUtil.getInstance().toObj(shareBeanJson, ShareBean.class);
            initView();
        }
        addRequest(binder.getSharePanelInfo(this.shareBean == null, this));
        viewDelegate.viewHolder.iv_pic.setImageResource(R.drawable.ic_share_group);
    }

    public static boolean startAct(Activity activity,
                                   String url) {
        boolean equals = action.equals(url);
        if (equals) {
            Intent intent = new Intent(activity, RecommendActivity.class);
            activity.startActivity(intent);
        }
        return equals;
    }


    private void initClick() {
        viewDelegate.viewHolder.lin_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareImgActivity.startAct(RecommendActivity.this,
                        shareBean.getShareText());
            }
        });
        viewDelegate.viewHolder.lin_literals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copy(shareBean.getShareText());
            }
        });
        viewDelegate.viewHolder.lin_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //链接分享
                shareDialog.refreshData(ShareDialog.getNormalList());
                shareDialog.show();
            }
        });
    }

    private void initShareDialog() {
        shareEntity = new ShareEntity();
        shareEntity.setTitle(shareBean.getTitle());
        shareEntity.setPic(shareBean.getImgUrl());
        shareEntity.setContent(shareBean.getShareText());
        shareEntity.setUrl(shareBean.getShareLink());
        shareDialog = new ShareDialog(this, new ShareDialog.SharePlatformChooseListener() {
            @Override
            public void onPlatformChoose(Dialog dialog, final ShareItemEntity shareObj) {
                dialog.dismiss();
                shareEntity.setPlatform(shareObj.getPlatfornName());
                ShareDialog.showShare(RecommendActivity.this, shareEntity, new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        String target = "";
                        if (platform.getName().equals(Wechat.NAME)) {
                            target = "wx";
                        } else if (platform.getName().equals(WechatMoments.NAME)) {
                            target = "wxfriend";
                        } else if (platform.getName().equals(SinaWeibo.NAME)) {
                            target = "micro";
                        } else if (platform.getName().equals(QQ.NAME)) {
                            target = "qq";
                        } else if (platform.getName().equals(QZone.NAME)) {
                            target = "qqfriend";
                        }
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                    }
                });
            }
        });
    }

    private void initView() {
        viewDelegate.viewHolder.sv_root.setVisibility(View.VISIBLE);
        viewDelegate.viewHolder.tv_num.setText(shareBean.getCountNum());
        viewDelegate.viewHolder.tv_rewards.setText(shareBean.getSumAmount());
        initShareDialog();
        initClick();
    }

    private void copy(String txt) {
        ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip = ClipData.newPlainText("text", txt);
        mClipboardManager.setPrimaryClip(myClip);
        ToastUtil.show("已复制地址到粘贴板：\n" + shareBean.getShareText() + " \n感谢您的分享！");
    }

    @Override
    public RecommendBinder getDataBinder(RecommendDelegate viewDelegate) {
        return new RecommendBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                shareBean = GsonUtil.getInstance().toObj(data, ShareBean.class);
                if (shareBean != null) {
                    CacheUtils.getInstance().put("ShareBean", data, 60 * 60 * 24);
                    initView();
                }
                break;
        }
    }
}
