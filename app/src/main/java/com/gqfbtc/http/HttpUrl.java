package com.gqfbtc.http;

import com.fivefivelike.mybaselibrary.utils.SaveUtil;

import static com.gqfbtc.base.AppConst.httpBaseUrl;

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
    //public static final String baseUrl = "http://192.168.168.114:8080";
    //public static final String baseUrl = "http://forotc.com";
    public static final String baseUrl = httpBaseUrl;
    /**
     * 订单列表
     */
    public String orderList = baseUrl + "/deal/list";

    /**
     * 用户
     */
    String userUrl = "/uc";

    /**
     * 登陆
     */
    public String doLogin = baseUrl + userUrl + "/doLogin";
    /**
     * 注册
     */
    public String signup = baseUrl + userUrl + "/signup";
    /**
     * 短信
     */
    public String getMsgCode = baseUrl + userUrl + "/getMsgCode";
    /**
     * 找回密码
     */
    public String doFindUser = baseUrl + userUrl + "/doFindUser";
    /**
     * 修改密码
     */
    public String editUserPassword = baseUrl + userUrl + "/editUserPassword";
    /**
     * 更新邮箱
     */
    public String editUserEmail = baseUrl + userUrl + "/editUserEmail";
    /**
     * 检查用户是否有昵称
     */
    public String checkUserNickName = baseUrl + userUrl + "/checkUserNickName";
    /**
     * 保存用户昵称
     */
    public String editUserNickName = baseUrl + userUrl + "/editUserNickName";
    /**
     * 设置新密码
     */
    public String editNewPassword = baseUrl + userUrl + "/editNewPassword";
    /**
     * 获得收款地址列表
     */
    public String getPaymentAddressList = baseUrl + userUrl + "/getPaymentAddressList";
    /**
     * 增加收款地址
     */
    public String addPaymentAddress = baseUrl + userUrl + "/addPaymentAddress";
    /**
     * 获取收币地址
     */
    public String getCoinAddressList = baseUrl + userUrl + "/getCoinAddressList";
    /**
     * 新增收币地址
     */
    public String addCoinAddress = baseUrl + userUrl + "/addCoinAddress";
    /**
     * 获取imtoken
     */
    public String imtoken = baseUrl + userUrl + "/imtoken";
    /**
     * 用户登出
     */
    public String doLoginOut = baseUrl + userUrl + "/doLoginOut";
    /**
     * 个人中心
     */
    public String userCenter = baseUrl + userUrl + "/userCenter";
    /**
     * 我的广告
     */
    public String ads = baseUrl + userUrl + "/ads";
    /**
     * 删除用户地址
     */
    public String delUserAddress = baseUrl + userUrl + "/delUserAddress";
    /**
     * 校验收款地址信息
     */
    public String checkPaymentAddress = baseUrl + userUrl + "/checkPaymentAddress";
    /**
     * 校验比特币地址信息
     */
    public String checkCoinAddress = baseUrl + userUrl + "/checkCoinAddress";


    /**
     *
     */
    String idxUrl = "/idx";
    //http://116.62.120.179:82/idx/listCarouselFigure
    /**
     * 检查用户是否被冻结
     */
    public String checkUserIsFrozen = baseUrl + idxUrl + "/checkUserIsFrozen";
    /**
     * 首页广告信息
     */
    public String ad = baseUrl + idxUrl + "/ad";
    /**
     * 资产
     */
    public String acc = baseUrl + idxUrl + "/acc";
    /**
     * 提现记录
     */
    public String withdraw = baseUrl + idxUrl + "/acc/withdraw/list";
    /**
     * 充值记录
     */
    public String recharge = baseUrl + idxUrl + "/acc/recharge/list";
    /**
     * 提现
     */
    public String deposit_add = baseUrl + idxUrl + "/deposit/add";
    /**
     * 首页轮播
     */
    public String listCarouselFigure = baseUrl + idxUrl + "/listCarouselFigure";
    /**
     * 版本更新
     */
    public String getAPPVersion = baseUrl + idxUrl + "/getAPPVersion/1";


    /**
     * 广告
     */
    String adUrl = "/ad";
    /**
     * 保存广告
     */
    public String saveAd = baseUrl + adUrl + "/saveAd";
    /**
     * 点击挂单之前的验证操作
     */
    public String beforeSaveAd = baseUrl + adUrl + "/beforeSaveAd";
    /**
     * 广告留言
     */
    public String getMessage = baseUrl + adUrl + "/getMessage";
    /**
     * 广告详情
     */
    public String getAdDetail = baseUrl + adUrl + "/getAdDetail";
    /**
     * 保存留言
     */
    public String saveMessage = baseUrl + adUrl + "/saveMessage";
    /**
     * 广告上架
     */
    public String down = baseUrl + adUrl + "/down";
    /**
     * 广告下架
     */
    public String up = baseUrl + adUrl + "/up";
    /**
     * 广告下架
     */
    public String changetime = baseUrl + adUrl + "/time/change";

    /**
     * 订单处理
     */
    String dealUrl = "/deal";
    /**
     * 保存订单前的校验并返回手续费
     */
    public String beforeSaveDeal = baseUrl + dealUrl + "/beforeSaveDeal";
    /**
     * 保存订单
     */
    public String saveDeal = baseUrl + dealUrl + "/saveDeal";
    /**
     * 订单详情
     */
    public String dealdt = baseUrl + dealUrl + "/dealdt";
    /**
     * 修改订单状态
     */
    public String change = baseUrl + dealUrl + "/change";
    /**
     * 评价页面数据
     */
    public String goDealMark = baseUrl + dealUrl + "/goDealMark";
    /**
     * 保存评价
     */
    public String saveDealMark = baseUrl + dealUrl + "/saveDealMark";


    /**
     * 中介
     */
    String icUrl = "/ic";
    /**
     * 中介登陆
     */
    public String ic_doLogin = baseUrl + icUrl + "/doLogin";
    /**
     * 登出
     */
    public String ic_doLoginOut = baseUrl + icUrl + "/doLoginOut";
    /**
     * 修改中介在线状态
     */
    public String ic_editIsOnline = baseUrl + icUrl + "/editIsOnline";
    /**
     * 中介增加地址
     */
    public String ic_addIntermediaryBeneBankInfo = baseUrl + icUrl + "/addIntermediaryBeneBankInfo";
    /**
     * 获取中介个人地址
     */
    public String ic_getIntermediaryBeneBankInfo = baseUrl + icUrl + "/getIntermediaryBeneBankInfo";
    /**
     * 中介个人地址删除
     */
    public String ic_dealIntermediaryBeneBankInfo = baseUrl + icUrl + "/dealIntermediaryBeneBankInfo";
    /**
     * 个人中心
     */
    public String ic_userCenter = baseUrl + icUrl + "/interCenter";
    /**
     * 获取imtoken
     */
    public String ic_imtoken = baseUrl + icUrl + "/imtoken";
    /**
     * 中介添加地址校验
     */
    public String ic_checkInteBankInfo = baseUrl + icUrl + "/checkInteBankInfo";


    /**
     * 图片上传
     */
    String uploadUrl = "/file";
    /**
     * 实名认证
     */
    public String batch = baseUrl + uploadUrl + "/upload/batch";
    /**
     * 检测实名认证
     */
    public String checkIsUpload = baseUrl + uploadUrl + "/checkIsUpload";

    /**
     * 分享
     */
    String shareUrl = "/share";
    /**
     * 获取分享页面的数据
     */
    public String getSharePanelInfo = baseUrl + shareUrl + "/getSharePanelInfo";
    /**
     * 获取用户邀请二维码地址
     */
    public String getQRCode = baseUrl + shareUrl + "/getQRCode";


}
