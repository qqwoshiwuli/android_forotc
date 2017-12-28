package com.gqfbtc.mvp.activity.main;

import android.Manifest;
import android.app.Notification;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.circledialog.CircleDialogHelper;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.utils.ActUtil;
import com.fivefivelike.mybaselibrary.utils.AppUtil;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.dialog.UpdateDialog;
import com.gqfbtc.entity.TabEntity;
import com.gqfbtc.entity.bean.AppVersion;
import com.gqfbtc.entity.bean.CheckFrozen;
import com.gqfbtc.entity.bean.ImUser;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.ChooseBuyBtcModeActivity;
import com.gqfbtc.mvp.activity.posted.PostedBigDealBuyActivity;
import com.gqfbtc.mvp.activity.posted.PostedBigDealSellActivity;
import com.gqfbtc.mvp.databinder.MainBinder;
import com.gqfbtc.mvp.delegate.IMDelegate;
import com.gqfbtc.mvp.delegate.MainDelegate;
import com.gqfbtc.mvp.fragment.AssetsFragment;
import com.gqfbtc.mvp.fragment.HomeFragment;
import com.gqfbtc.mvp.fragment.MajorsFrgment;
import com.gqfbtc.mvp.fragment.OrderFragment;
import com.gqfbtc.mvp.fragment.UserFragment;
import com.gqfbtc.server.NotificationHelper;
import com.gqfbtc.server.UpdateService;
import com.gqfbtc.widget.BoomButtomIconText;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;


public class MainActivity extends BaseDataBindActivity<MainDelegate, MainBinder> implements UserFragment.Linsener, HomeFragment.Linsener, MajorsFrgment.Linsener {

    private String[] mTitles = {"交易", "订单", "", "资产", "我的"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconSelectIds = {
            R.string.ic_jiaoyi, R.string.ic_dingdan, 0,
            R.string.ic_qianbao, R.string.ic_wode};

    private int[] mIconBoomIds = {
            R.string.ic_btc, R.string.ic_btc
            , R.string.ic_eth, R.string.ic_eth
    };

    private int[] mIconBoomColorIds = {
            R.color.white, R.color.color_f5a623
            , R.color.white, R.color.color_555555
    };

    private int[] mIconBoomBgColorIds = {
            R.drawable.btc_buy_icon, R.drawable.btc_sell_icon
            , R.drawable.ucx_buy_icon, R.drawable.ucx_sell_icon
    };

    private String[] mBoomTitles = {"买BTC", "卖BTC"
            , "买链克", "卖链克"
    };
    private String[] mBoomSubtitles = {"广告", "广告"
            , "广告", "广告"
    };

    List<BoomButtomIconText.BoomBtnEntity> entities;
    List<View> views;
    HomeFragment homeFragment;
    OrderFragment orderFragment;
    AssetsFragment assetsFragment;
    UserFragment userFragment;
    MajorsFrgment majorsFrgment;


    UserLogin userLogin;
    AppVersion appVersion;
    MainEventBusHelper mainEventBusHelper;

    int chooseType;//选择发广告类型
    public static final int buy_btc = 0;
    public static final int sell_btc = 1;
    public static final int buy_ucx = 2;
    public static final int sell_ucx = 3;

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        mainEventBusHelper = new MainEventBusHelper(this, viewDelegate, binder);
        userLogin = SingSettingDBUtil.getUserLogin();
        initFragment();
        if (SingSettingDBUtil.getUserLogin() != null) {
            if (SingSettingDBUtil.isUser()) {
                //初始化用户底部按钮
                initBottomBtn();
            } else {
                //初始化中介底部按钮
                initBottomBtnIntervening();
            }
            addRequest(binder.userCenter(SingSettingDBUtil.isUser(), this));
        } else {
            //没有登录
            initBottomBtn();
        }
        addRequest(binder.getAPPVersion(this));
    }


    public void initFragment() {
        viewDelegate.initAddFragment(R.id.fl_root, getSupportFragmentManager());
        orderFragment = new OrderFragment();
        assetsFragment = new AssetsFragment();
        userFragment = new UserFragment();
        if (SingSettingDBUtil.isUser() || SingSettingDBUtil.getUserLogin() == null) {
            homeFragment = new HomeFragment();
            majorsFrgment = new MajorsFrgment();
            viewDelegate.addFragment(homeFragment);
        }
        viewDelegate.addFragment(orderFragment);
        viewDelegate.addFragment(assetsFragment);
        viewDelegate.addFragment(userFragment);
        viewDelegate.showFragment(0);
        doubleClickActList.add(this.getClass().getName());//两次返回act注册
    }

    public void initBottomBtnIntervening() {
        for (int i = 1; i < mTitles.length; i++) {
            if (i != 2) {
                mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], 0));
            }
        }
        viewDelegate.viewHolder.tl_2.setIconVisible(false);
        viewDelegate.viewHolder.tl_2.setTabData(mTabEntities);
        viewDelegate.viewHolder.tl_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (SingSettingDBUtil.isLogin(MainActivity.this)) {
                    viewDelegate.showFragment(position);
                } else {
                    MainActivity.this.finish();
                }
                if (position == 0) {
                    viewDelegate.viewHolder.tl_2.hideMsg(0);
                }
                if (position == 2) {
                    viewDelegate.viewHolder.tl_2.hideMsg(1);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewDelegate.viewHolder.boom.setVisibility(View.GONE);
        viewDelegate.viewHolder.tv_center_btn.setVisibility(View.GONE);
    }

    public void initBottomBtn() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], 0));
        }
        viewDelegate.viewHolder.tl_2.setIconVisible(false);
        viewDelegate.viewHolder.tl_2.setTabData(mTabEntities);


        viewDelegate.viewHolder.tl_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position > 0) {
                    if (!SingSettingDBUtil.isLogin(MainActivity.this)) {
                        viewDelegate.viewHolder.tl_2.setCurrentTab(0);
                        return;
                    }
                }
                if (SingSettingDBUtil.getUserLogin() != null) {
                    if (position > 1) {
                        position = position - 1;
                    }
                    if (SingSettingDBUtil.isLogin(MainActivity.this)) {
                        viewDelegate.showFragment(position);
                    } else {
                        MainActivity.this.finish();
                    }
                    if (position == 1) {
                        viewDelegate.viewHolder.tl_2.hideMsg(1);
                    }
                    if (position == 4) {
                        viewDelegate.viewHolder.tl_2.hideMsg(1);
                    }
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewDelegate.viewHolder.tl_2.getmTabsContainer().getChildAt(2).setEnabled(false);
        entities = new ArrayList<>();
        views = new ArrayList<>();

        for (int i = 0; i < mBoomTitles.length; i++) {
            entities.add(new BoomButtomIconText.BoomBtnEntity().setmIcon(mIconBoomIds[i])
                    .setmTitle(mBoomTitles[i]).setmSubTitle(mBoomSubtitles[i])
                    .setBgIcon(mIconBoomBgColorIds[i])
                    .setTxtColor(mIconBoomColorIds[i])
                    .setPadding(getResources().getDimensionPixelOffset(R.dimen.trans_15px))
                    .setxPositions(
                            ((ScreenUtils.getScreenWidth() - getResources().getDimensionPixelOffset(R.dimen.trans_50px)) / mBoomTitles.length * (i)) +
                                    getResources().getDimensionPixelOffset(R.dimen.trans_25px)
                            , getResources().getDimensionPixelOffset(R.dimen.trans_160px))
                    .setWidth((ScreenUtils.getScreenWidth() - getResources().getDimensionPixelOffset(R.dimen.trans_40px)) / 4));
        }
        viewDelegate.viewHolder.boom.setEntities(entities);
        viewDelegate.viewHolder.boom.setCenter((ScreenUtils.getScreenWidth() / 2) - getResources().getDimensionPixelOffset(R.dimen.trans_60px), getResources().getDimensionPixelOffset(R.dimen.trans_10px));
        viewDelegate.viewHolder.boom.setBoomBtn(views);
        viewDelegate.viewHolder.boom.setControlView(viewDelegate.viewHolder.tv_center_btn);
        viewDelegate.viewHolder.boom.setClickable(false);
        viewDelegate.viewHolder.boom.setDefaultClickLinsener(new DefaultClickLinsener() {
            @Override
            public void onClick(View view, int position, Object item) {
                viewDelegate.viewHolder.boom.dimess();
                if (SingSettingDBUtil.isLogin(MainActivity.this)) {
                    if (position == 0) {
                        postAdvertising(buy_btc);
                    } else if (position == 1) {
                        postAdvertising(sell_btc);
                    } else if (position == 2) {
                        postAdvertising(buy_ucx);
                    } else if (position == 3) {
                        postAdvertising(sell_ucx);
                    }
                }
            }
        });
    }

    public void postAdvertising(int type) {
        if (SingSettingDBUtil.isLogin(this)) {
            chooseType = type;
            if (chooseType == sell_btc) {
                addRequest(binder.checkUserIsFrozen("1", true, this));
            } else if (chooseType == buy_btc) {
                addRequest(binder.checkUserIsFrozen("1", false, this));
            } else if (chooseType == sell_ucx) {
                addRequest(binder.adwkc_beforeSaveAd("3", true, this));
            } else if (chooseType == buy_ucx) {
                addRequest(binder.adwkc_beforeSaveAd("3", false, this));
            }
        }
    }

    IMDelegate.IMLinsener imLinsener = new IMDelegate.IMLinsener() {
        @Override
        public void ImError() {

        }

        @Override
        public void ImSuccess() {
            if (SingSettingDBUtil.isUser()) {
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(userLogin.getId() + "u", userLogin.getNickName(), Uri.parse(GlideUtils.getBaseUrl() + userLogin.getAvatar())));
            } else {
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(userLogin.getId() + "i", userLogin.getName(), Uri.parse(GlideUtils.getBaseUrl() + userLogin.getAvatar())));
            }
        }
    };

    private void initIm() {
        if (SingSettingDBUtil.isUser()) {
            if (!TextUtils.isEmpty(userLogin.getNickName()) && !TextUtils.isEmpty(userLogin.getImToken())) {
                viewDelegate.setImLinsener(imLinsener);
                binder.connect(userLogin.getImToken());
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(userLogin.getId() + "u", userLogin.getNickName(), Uri.parse(GlideUtils.getBaseUrl() + userLogin.getAvatar())));
            } else {
                addRequest(binder.imtoken(this));
            }
        } else {
            //中介
            if (!TextUtils.isEmpty(userLogin.getName()) && !TextUtils.isEmpty(userLogin.getImToken())) {
                viewDelegate.setImLinsener(imLinsener);
                binder.connect(userLogin.getImToken());
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(userLogin.getId() + "i", userLogin.getName(), Uri.parse(userLogin.getAvatar())));
            } else {
                addRequest(binder.ic_imtoken(this));
            }
        }
    }

    @Override
    public MainBinder getDataBinder(MainDelegate viewDelegate) {
        return new MainBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //是否冻结
                CheckFrozen checkFrozen = GsonUtil.getInstance().toObj(data, CheckFrozen.class);
                if (chooseType == buy_btc) {
                    ChooseBuyBtcModeActivity.startAct(this, ChooseBuyBtcModeActivity.type_buy, ChooseBuyBtcModeActivity.action_advertising, checkFrozen, 0x123);
                } else if (chooseType == sell_btc) {
                    ChooseBuyBtcModeActivity.startAct(this, ChooseBuyBtcModeActivity.type_sell, ChooseBuyBtcModeActivity.action_advertising, checkFrozen, 0x123);
                } else if (chooseType == buy_ucx) {
                    PostedBigDealBuyActivity.startAct(this, checkFrozen, 0x123);
                } else if (chooseType == sell_ucx) {
                    PostedBigDealSellActivity.startAct(this, checkFrozen, 0x123);
                }
                break;
            case 0x124:
                ImUser imUser = GsonUtil.getInstance().toObj(data, ImUser.class);
                userLogin.setImToken(imUser.getToken());
                SingSettingDBUtil.setNewUserLogin(userLogin);
                initIm();
                break;
            case 0x125:
                //用户token检测 同步用户数据
                userLogin = GsonUtil.getInstance().toObj(data, UserLogin.class);
                SingSettingDBUtil.setNewUserLogin(userLogin);
                break;
            case 0x126:
                //版本更新
                appVersion = GsonUtil.getInstance().toObj(data, AppVersion.class);
                version();
                break;
            case 0x127:
                ToastUtil.show(info);
                NotificationHelper.getInstence().systemNotification(this, info, "");
                break;
        }
    }

    private void updata() {
        UpdateService.
                Builder.create(appVersion.getDownloadAddr())
                .setStoreDir("update")
                .setIcoResId(R.drawable.artboard)
                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                .setAppVersion(appVersion)
                .build(mContext);
    }

    private void version() {
        AndPermission.with(this)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        if (UiHeplUtils.compareVersion(appVersion.getVersionCode(), AppUtils.getAppVersionName()) == 1) {
                            new UpdateDialog(MainActivity.this)
                                    .setAppVersion(appVersion)
                                    .setDefaultClickLinsener(new DefaultClickLinsener() {
                                        @Override
                                        public void onClick(View view, int position, Object item) {
                                            if (position == 0) {
                                                //取消
                                                if (appVersion.isIsNeedFresh()) {
                                                    ActUtil.getInstance().AppExit(MainActivity.this);
                                                }
                                            } else {
                                                //确认
                                                if (AppUtil.isWifi(mContext)) {
                                                    updata();
                                                } else {
                                                    CircleDialogHelper.initDefaultDialog(MainActivity.this, "当前处于非wifi模式，是否继续下载?", new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            updata();
                                                        }
                                                    }).setNegative("取消", new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            if (appVersion.isIsNeedFresh()) {
                                                                ActUtil.getInstance().AppExit(MainActivity.this);
                                                            }
                                                        }
                                                    }).show();
                                                }
                                            }
                                        }
                                    }).showDialog();
                        } else {
                            //新手引导
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        ToastUtil.show("没有读写权限,请开启权限");
                    }
                }).start();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SingSettingDBUtil.getUserLogin() != null) {
            userLogin = SingSettingDBUtil.getUserLogin();
            if (!TextUtils.isEmpty(userLogin.getImToken())) {
                initIm();
            } else {
                if (SingSettingDBUtil.isUser()) {
                    addRequest(binder.imtoken(this));
                } else {
                    addRequest(binder.ic_imtoken(this));
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        mainEventBusHelper.onDestroy();
        super.onDestroy();
    }


    @Override
    public void logout() {
        RongIMClient.getInstance().logout();
        RongIM.getInstance().logout();
    }

    public void goToPage(int positon) {
        viewDelegate.showFragment(positon);
        if (positon > 1) {
            viewDelegate.viewHolder.tl_2.setCurrentTab(positon - 1);
        } else {
            viewDelegate.viewHolder.tl_2.setCurrentTab(positon);
        }
    }

    public void changeMode(Class c) {
        if (c.equals(HomeFragment.class)) {
            //极速
            if (majorsFrgment != null) {
                viewDelegate.replaceFragment(0, majorsFrgment);
            }
        } else {
            //普通
            if (homeFragment != null) {
                viewDelegate.replaceFragment(0, homeFragment);
            }
        }
    }
}
