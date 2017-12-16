package com.gqfbtc.entity.bean;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/11/14.
 */

public class OrderDetails {
    /**
     * private Long id;//订单主键
     * <p>
     * private String code;//订单编号
     * <p>
     * private Long adId;//所属广告ID
     * <p>
     * private Long intermediaryId;//中介ID
     * <p>
     * private Long dealOwnerId;//订单方用户ID  主动方
     * <p>
     * private Long adOwnerId;//发布广告人 的id
     * <p>
     * private Boolean dealIsSafe;//订单是否为安全模式，1表示安全，0表示普通
     * <p>
     * private BigDecimal dealPrice;//订单单价
     * <p>
     * private BigDecimal dealQuantity;//订单的出售或购买总数量
     * <p>
     * private BigDecimal adPoundage;//广告方手续费
     * <p>
     * private BigDecimal dealPoundage;//订单方手续费
     * <p>
     * private BigDecimal intermediaryPoundage;//中介手续费
     * <p>
     * private BigDecimal randomCoin;//安全买时生成的随机数字
     * <p>
     * //    private Boolean adIsDone;//广告方是否已完成,1表示已完成,0表示未完成
     * <p>
     * //    private Boolean dealIsDone;//订单方是否已完成,1表示已完成,0表示未完成
     * <p>
     * //    private Boolean intermediaryIsDone;//中介是否已完成,1表示已完成,0表示未完成
     * //用户收款地址(此处为最终打款地址,此处存放的可能是广告方的也可能是订单方的)
     * private Long userCollCashAddr;
     * //中介收款地址(此处为最终打款地址)
     * private Long interCollCashAddr;
     * //订单收币地址(此处只有广告是卖,订单是安全买时才会使用)
     * private Long dealCollCoinAddr;
     * <p>
     * private Integer dealStatus;//订单状态
     * <p>
     * //    private Date createTime;//创建时间(即下单时间)
     * <p>
     * //    private Date updateTime;//更新时间
     * <p>
     * private Boolean isSale;//广告 是否为卖
     * private Boolean isSafe;//广告 是否为安全
     * private Integer currency;//币别
     * private String collCashAddr;//广告的收款地址，如有多个账户，使用逗号隔开
     * private String collCoinAddr;//收币地址
     * <p>
     * private String title;//页面 头部标题(a)
     * private String frozenCoin;//冻结币情况(b)
     * private String payType;//我的付款方式(c)
     * private String dealRemark;//付款详情备注(d)
     * private String customJoin="要求客服介入";//要求客服介入(e)
     * private String dealPushBtn;//订单推进按钮(f)
     * <p>
     * private String dealMoneyStr;//交易金额 字符串
     * private String dealPriceStr;//成交单价 字符串
     * private String poundageStr;//手续费 字符串
     * private String dealMoneyStr;//交易金额 字符串
     * private String dealPriceStr;//成交单价 字符串
     * private String poundageStr;//手续费 字符串
     * private String dealQuantityStr;//交易数量 字符串
     * private List<BeneBankInfo> BankInfoList;//收款账户
     */


    private String id;//订单主键

    private String code;//订单编号


    private String intermediaryId;//中介ID

    private String dealOwnerId;//订单方用户ID  主动方

    private String adOwnerId;//发布广告人 的id

    private Boolean isEv;//是否评价


    private String dealStatus;//订单状态

    //    private Date createTime;//创建时间(即下单时间)

    //    private Date updateTime;//更新时间

    private String currency;//币别

    /**
     * 页面 需要展示的主要数据
     */
    private String title;//页面 头部标题(a)
    private String frozenCoin;//冻结币情况(b)
    private String payType;//我的付款方式(c)
    private String dealRemark;//付款详情备注(d)
    private String customJoin = "要求客服介入";//要求客服介入(e)
    private String dealPushBtn;//订单推进按钮(f)


    private String dealMoneyStr;//交易金额 字符串
    private String dealPriceStr;//成交单价 字符串
    private String poundageStr;//手续费 字符串
    private String dealQuantityStr;//交易数量 字符串
    private String showMsg;//地址标题
    private String payTypeTitle;
    private List<PaymentBTCETHAddress> BankInfoList;//收款账户
    private InterNeedInfoVO interNeedInfoVO;

    /**
     * id : 256
     * adId : 298
     * intermediaryId : 21
     * dealOwnerId : 102
     * adOwnerId : 99
     * dealPrice : 100039
     * dealQuantity : 1
     * adPoundage : 500.2
     * dealPoundage : 500.2
     * intermediaryPoundage : 0
     * dealStatus : 0
     * currency : 1
     * dealTypeStr : 链克
     * interNeedInfoVO : {"sellUser":"","buyUser":"","sellCashAddrList":[],"buyPlatformCoinAddr":{"id":"","ownerId":"","ownerIsUser":"","collectionType":"","collectionAddr":"","alias":"","bankName":"","branchName":"","ownerName":"","remark":"","createTime":"","updateTime":"","isDel":""},"buyCoinAddrList":[]}
     * isComplete : false
     * isCancel : false
     * isRefuseAndDown : true
     */

    private boolean isRefuseAndDown;//有拒绝
    private int leftSecond;//ucx订单剩余操作时间

    public int getLeftSecond() {
        return leftSecond;
    }

    public void setLeftSecond(int leftSecond) {
        this.leftSecond = leftSecond;
    }


    public boolean isRefuseAndDown() {
        return isRefuseAndDown;
    }

    public void setRefuseAndDown(boolean refuseAndDown) {
        isRefuseAndDown = refuseAndDown;
    }

    public InterNeedInfoVO getInterNeedInfoVO() {
        return interNeedInfoVO;
    }

    public void setInterNeedInfoVO(InterNeedInfoVO interNeedInfoVO) {
        this.interNeedInfoVO = interNeedInfoVO;
    }

    public String getPayTypeTitle() {
        return payTypeTitle;
    }

    public void setPayTypeTitle(String payTypeTitle) {
        this.payTypeTitle = payTypeTitle;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public Boolean getEv() {
        return isEv;
    }

    public void setEv(Boolean ev) {
        isEv = ev;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getIntermediaryId() {
        return intermediaryId;
    }

    public void setIntermediaryId(String intermediaryId) {
        this.intermediaryId = intermediaryId;
    }

    public String getDealOwnerId() {
        return dealOwnerId;
    }

    public void setDealOwnerId(String dealOwnerId) {
        this.dealOwnerId = dealOwnerId;
    }

    public String getAdOwnerId() {
        return adOwnerId;
    }

    public void setAdOwnerId(String adOwnerId) {
        this.adOwnerId = adOwnerId;
    }


    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrozenCoin() {
        return frozenCoin;
    }

    public void setFrozenCoin(String frozenCoin) {
        this.frozenCoin = frozenCoin;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDealRemark() {
        return dealRemark;
    }

    public void setDealRemark(String dealRemark) {
        this.dealRemark = dealRemark;
    }

    public String getCustomJoin() {
        return customJoin;
    }

    public void setCustomJoin(String customJoin) {
        this.customJoin = customJoin;
    }

    public String getDealPushBtn() {
        return dealPushBtn;
    }

    public void setDealPushBtn(String dealPushBtn) {
        this.dealPushBtn = dealPushBtn;
    }

    public String getDealMoneyStr() {
        return dealMoneyStr;
    }

    public void setDealMoneyStr(String dealMoneyStr) {
        this.dealMoneyStr = dealMoneyStr;
    }

    public String getDealPriceStr() {
        return dealPriceStr;
    }

    public void setDealPriceStr(String dealPriceStr) {
        this.dealPriceStr = dealPriceStr;
    }

    public String getPoundageStr() {
        return poundageStr;
    }

    public void setPoundageStr(String poundageStr) {
        this.poundageStr = poundageStr;
    }

    public String getDealQuantityStr() {
        return dealQuantityStr;
    }

    public void setDealQuantityStr(String dealQuantityStr) {
        this.dealQuantityStr = dealQuantityStr;
    }

    public List<PaymentBTCETHAddress> getBankInfoList() {
        return BankInfoList;
    }

    public void setBankInfoList(List<PaymentBTCETHAddress> bankInfoList) {
        BankInfoList = bankInfoList;
    }

    public static class UserMsg {
        private Long id;
        private String name;//用户名
        private String nickName;//昵称
        private String phone;//电话

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

}
