package com.gqfbtc.http;

import android.text.TextUtils;

import com.fivefivelike.mybaselibrary.utils.SaveUtil;

import static com.gqfbtc.base.AppConst.httpBaseUrl;
import static com.gqfbtc.base.AppConst.isEditUrl;

/**
 * Created by 郭青枫 on 2017/11/5.
 */

public class HttpUrl {
    private static final String TAG = "HttpUrl";
    private static final String serviceId = "HttpUrl";
    static HttpUrl httpUrl = new HttpUrl();

    public static HttpUrl getIntance() {
        if (httpUrl == null) {
            httpUrl = new HttpUrl();
        }
        return httpUrl;
    }

    public static void setNull() {
        httpUrl = null;
    }

    public void saveUidAndToken(String uid, String token) {
        SaveUtil.getInstance().saveString("uid", uid + "");
        SaveUtil.getInstance().saveString("token", token + "");
    }

    public void saveUid(String uid) {
        SaveUtil.getInstance().saveString("uid", uid + "");
    }

    public String getUid() {
        return SaveUtil.getInstance().getString("uid");
    }

    public void saveToken(String token) {
        SaveUtil.getInstance().saveString("token", token + "");
    }

    public String getToken() {
        return SaveUtil.getInstance().getString("token");
    }

    public void delectUidAndToken() {
        SaveUtil.getInstance().remove("uid");
        SaveUtil.getInstance().remove("token");
    }

    /**
     * 测试库
     */
    public String base82Url = "http://116.62.120.179:82";
    public String base80Url = "http://192.168.168.109:8080";
    //韩
    //public static final String getBAseUrl() = "http://192.168.168.114:8080";
    //public static final String getBAseUrl() = "http://forotc.com";
    public static final String baseUrl = httpBaseUrl;

    public static String getBAseUrl() {
        if (isEditUrl) {
            if (!TextUtils.isEmpty(SaveUtil.getInstance().getString("baseUrl"))) {
                return SaveUtil.getInstance().getString("baseUrl");
            }
        }
        return baseUrl;
    }

    public static void saveBaseUrl(String url) {
        SaveUtil.getInstance().saveString("baseUrl", url);
        httpUrl = null;
    }


    /**
     * btc订单列表
     */
    public String orderList = getBAseUrl() + "/deal/list";

    /**
     * ucx订单列表
     */
    public String dealwkcList = getBAseUrl() + "/dealwkc/list";


    /**
     * 用户
     */
    String userUrl = "/uc";

    /**
     * 登陆
     */
    public String doLogin = getBAseUrl() + userUrl + "/doLogin";
    /**
     * 注册
     */
    public String signup = getBAseUrl() + userUrl + "/signup";
    /**
     * 短信
     */
    public String getMsgCode = getBAseUrl() + userUrl + "/getMsgCode";
    /**
     * 找回密码
     */
    public String doFindUser = getBAseUrl() + userUrl + "/doFindUser";
    /**
     * 修改密码
     */
    public String editUserPassword = getBAseUrl() + userUrl + "/editUserPassword";
    /**
     * 更新邮箱
     */
    public String editUserEmail = getBAseUrl() + userUrl + "/editUserEmail";
    /**
     * 检查用户是否有昵称
     */
    public String checkUserNickName = getBAseUrl() + userUrl + "/checkUserNickName";
    /**
     * 保存用户昵称
     */
    public String editUserNickName = getBAseUrl() + userUrl + "/editUserNickName";
    /**
     * 设置新密码
     */
    public String editNewPassword = getBAseUrl() + userUrl + "/editNewPassword";
    /**
     * 获得收款地址列表
     */
    public String getPaymentAddressList = getBAseUrl() + userUrl + "/getPaymentAddressList";
    /**
     * 增加收款地址
     */
    public String addPaymentAddress = getBAseUrl() + userUrl + "/addPaymentAddress";
    /**
     * 获取收币地址
     */
    public String getCoinAddressList = getBAseUrl() + userUrl + "/getCoinAddressList";
    /**
     * 新增收币地址
     */
    public String addCoinAddress = getBAseUrl() + userUrl + "/addCoinAddress";
    /**
     * 获取imtoken
     */
    public String imtoken = getBAseUrl() + userUrl + "/imtoken";
    /**
     * 用户登出
     */
    public String doLoginOut = getBAseUrl() + userUrl + "/doLoginOut";
    /**
     * 个人中心
     */
    public String userCenter = getBAseUrl() + userUrl + "/userCenter";
    /**
     * 我的广告
     */
    public String ads = getBAseUrl() + userUrl + "/ads";
    /**
     * 删除用户地址
     */
    public String delUserAddress = getBAseUrl() + userUrl + "/delUserAddress";
    /**
     * 校验收款地址信息
     */
    public String checkPaymentAddress = getBAseUrl() + userUrl + "/checkPaymentAddress";
    /**
     * 校验比特币地址信息
     */
    public String checkCoinAddress = getBAseUrl() + userUrl + "/checkCoinAddress";
    /**
     * 链克 我的广告
     */
    public String adsucx = getBAseUrl() + userUrl + "/adsucx";


    /**
     *
     */
    String idxUrl = "/idx";
    //http://116.62.120.179:82/idx/listCarouselFigure
    /**
     * 检查用户是否被冻结
     */
    public String checkUserIsFrozen = getBAseUrl() + idxUrl + "/checkUserIsFrozen";
    /**
     * 首页广告信息
     */
    public String ad = getBAseUrl() + idxUrl + "/ad";
    /**
     * 资产
     */
    public String acc = getBAseUrl() + idxUrl + "/acc";
    /**
     * 提现记录
     */
    public String withdraw = getBAseUrl() + idxUrl + "/acc/withdraw/list";
    /**
     * 充值记录
     */
    public String recharge = getBAseUrl() + idxUrl + "/acc/recharge/list";
    /**
     * 提现
     */
    public String deposit_add = getBAseUrl() + idxUrl + "/deposit/add";
    /**
     * 首页轮播
     */
    public String listCarouselFigure = getBAseUrl() + idxUrl + "/listCarouselFigure";
    /**
     * 版本更新
     */
    public String getAPPVersion = getBAseUrl() + idxUrl + "/getAPPVersion/1";
    /**
     * ucx广告
     */
    public String adUcx = getBAseUrl() + idxUrl + "/adUcx";


    /**
     * 广告
     */
    String adUrl = "/ad";
    /**
     * 保存广告
     */
    public String saveAd = getBAseUrl() + adUrl + "/saveAd";
    /**
     * 点击挂单之前的验证操作
     */
    public String beforeSaveAd = getBAseUrl() + adUrl + "/beforeSaveAd";
    /**
     * 广告留言
     */
    public String getMessage = getBAseUrl() + adUrl + "/getMessage";
    /**
     * 广告详情
     */
    public String getAdDetail = getBAseUrl() + adUrl + "/getAdDetail";
    /**
     * 保存留言
     */
    public String saveMessage = getBAseUrl() + adUrl + "/saveMessage";
    /**
     * 广告上架
     */
    public String down = getBAseUrl() + adUrl + "/down";
    /**
     * 广告下架
     */
    public String up = getBAseUrl() + adUrl + "/up";
    /**
     * 广告下架
     */
    public String changetime = getBAseUrl() + adUrl + "/time/change";

    /**
     * 订单处理
     */
    String dealUrl = "/deal";
    /**
     * 保存订单前的校验并返回手续费
     */
    public String beforeSaveDeal = getBAseUrl() + dealUrl + "/beforeSaveDeal";
    /**
     * 保存订单
     */
    public String saveDeal = getBAseUrl() + dealUrl + "/saveDeal";
    /**
     * 订单详情
     */
    public String dealdt = getBAseUrl() + dealUrl + "/dealdt";
    /**
     * 修改订单状态
     */
    public String change = getBAseUrl() + dealUrl + "/change";
    /**
     * 评价页面数据
     */
    public String goDealMark = getBAseUrl() + dealUrl + "/goDealMark";
    /**
     * 保存评价
     */
    public String saveDealMark = getBAseUrl() + dealUrl + "/saveDealMark";


    /**
     * 中介
     */
    String icUrl = "/ic";
    /**
     * 中介登陆
     */
    public String ic_doLogin = getBAseUrl() + icUrl + "/doLogin";
    /**
     * 登出
     */
    public String ic_doLoginOut = getBAseUrl() + icUrl + "/doLoginOut";
    /**
     * 修改中介在线状态
     */
    public String ic_editIsOnline = getBAseUrl() + icUrl + "/editIsOnline";
    /**
     * 中介增加地址
     */
    public String ic_addIntermediaryBeneBankInfo = getBAseUrl() + icUrl + "/addIntermediaryBeneBankInfo";
    /**
     * 获取中介个人地址
     */
    public String ic_getIntermediaryBeneBankInfo = getBAseUrl() + icUrl + "/getIntermediaryBeneBankInfo";
    /**
     * 中介个人地址删除
     */
    public String ic_dealIntermediaryBeneBankInfo = getBAseUrl() + icUrl + "/dealIntermediaryBeneBankInfo";
    /**
     * 个人中心
     */
    public String ic_userCenter = getBAseUrl() + icUrl + "/interCenter";
    /**
     * 获取imtoken
     */
    public String ic_imtoken = getBAseUrl() + icUrl + "/imtoken";
    /**
     * 中介添加地址校验
     */
    public String ic_checkInteBankInfo = getBAseUrl() + icUrl + "/checkInteBankInfo";


    /**
     * 图片上传
     */
    String uploadUrl = "/file";
    /**
     * 实名认证
     */
    public String batch = getBAseUrl() + uploadUrl + "/upload/batch";
    /**
     * 检测实名认证
     */
    public String checkIsUpload = getBAseUrl() + uploadUrl + "/checkIsUpload";

    /**
     * 分享
     */
    String shareUrl = "/share";
    /**
     * 获取分享页面的数据
     */
    public String getSharePanelInfo = getBAseUrl() + shareUrl + "/getSharePanelInfo";
    /**
     * 获取用户邀请二维码地址
     */
    public String getQRCode = getBAseUrl() + shareUrl + "/getQRCode";

    /**
     * 大宗交易
     */
    String adwkcUrl = "/adwkc";
    /**
     * 保存大宗交易广告
     */
    public String adwkc_saveAd = getBAseUrl() + adwkcUrl + "/saveAd";
    /**
     * 大宗交易广告详情
     */
    public String adwkc_getAdDetail = getBAseUrl() + adwkcUrl + "/getAdDetail";
    /**
     * 发起广告前校验
     */
    public String adwkc_beforeSaveAd = getBAseUrl() + adwkcUrl + "/beforeSaveAd";
    /**
     * 链克 我的广告 下架前 调用
     */
    public String adwkc_beforedown = getBAseUrl() + adwkcUrl + "/beforedown";


    /**
     * 大宗交易
     */
    String dealwkcUrl = "/dealwkc";
    /**
     * 大宗交易订单推进
     */
    public String dealwkc_change = getBAseUrl() + dealwkcUrl + "/change";
    /**
     * 大宗交易订单 保存
     */
    public String dealwkc_saveDeal = getBAseUrl() + dealwkcUrl + "/saveDeal";
    /**
     * 大宗交易订单 详情
     */
    public String dealwkc_dealdt = getBAseUrl() + dealwkcUrl + "/dealdt";
    /**
     * 大宗交易订单 下架
     */
    public String dealwkc_dealCancle = getBAseUrl() + dealwkcUrl + "/dealCancle";


}
