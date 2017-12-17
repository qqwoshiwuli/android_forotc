package com.gqfbtc.entity.bean;


import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/11/14.
 */

public class AdvertisingDetails {
    /**
     * private Long id;//广告主键
     * private String code;//广告编号
     * private Integer currency;//币种
     * private Long owerId;//发布人的id
     * private Boolean isSale;//指示此广告是买或者是卖,1表示是卖，0 表示为买
     * private Boolean isSafe;//安全或者是普通，1表示安全，0表示普通
     * private Integer status;//当前的状态,0 表示正常，1表示下架
     * private Boolean isFixed;//是固定的还是溢价模式，1表示固定，0表示溢价
     * private BigDecimal price;//单价
     * private String priceStr;//单价 带千分符
     * private BigDecimal quantity;//广告的出售或购买总数量
     * private BigDecimal lockedAmount;//锁定数量
     * private BigDecimal succeedAmount;//已成功交易的数量
     * private BigDecimal deposit;//广告保证金
     * private String remark;//广告的备注信息
     * //    private Date startTime;//交易开始时间
     * //    private Date endTime;//交易结束时间
     * private BigDecimal threshold;//最小购买金额
     * private BigDecimal floatPercent;//波动比例
     * private Date createTime;//创建时间
     * private Date updateTime;//更新时间
     * private String collectionCashAddr;//广告的收款地址，如有多个账户，使用逗号隔开
     * private String collectionCoinAddr;//收币地址
     * private Integer ceil;//价格上限
     * private Integer floor;//价格下限
     * private Long userId;//用户id
     * private String nickName;//用户昵称
     * private String avatar;//用户头像图片
     * private Boolean tagsKa;//是否大户
     * private Boolean tagsExpress;//是否是极速
     * private String title;//头部标题
     * private String grade;//评分等级
     * private Long dealCount;//交易次数
     * private String dealCountStr;//交易次数 带千分符
     * private BigDecimal dealQuantity;//已在平台交易过(交易币总数量)
     * private String dealQuantityStr;//已在平台交易过(交易币总数量) 带千分符
     * private BigDecimal leftMoney;//剩余可交易金额
     * private String leftMoneyStr;//剩余可交易金额  带千分符
     * private String dealRange;//可交易区间
     * //中介列表
     * private List<IntermediaryVO> intermediaryList;
     * //广告留言列表
     * private List<AdMessageVO> adMessageList;
     */

    private String id;
    private String code;
    private String currency;
    private boolean isSale;
    private boolean isSafe;
    private String status;
    private boolean isFixed;
    private BigDecimal price;
    private String priceStr;
    private String remark;
    private long createTime;
    private long updateTime;
    private String userId;
    private String nickName;
    private String avatar;
    private boolean tagsKa;
    private boolean tagsExpress;
    private String grade;
    private String dealCountStr;
    private String dealQuantityStr;
    private String dealRange;
    private List<Intermediary> intermediaryList;//中介列表
    private List<AdvertisingMessage> adMessageList;

    private String score;//成交次数
    private String title;//标题
    private String dealTypeStr;//币种
    private String respondTime; //响应时间
    private String respondChance; //响应概率


    //预计买入数量
    private String quantityDealPro;
    //系统自动帮我选托管人
    private String selectInterPro;
    /**
     * helpVO : {"prompt":"","cointypePro":"","quantityAdPro":"","thresholdPro":"","timePro":"","bankInfoPro":"","quantityDealPro":"您希望卖出的数量。出售链克需要支付交易额1%作为手续费。交易担保人会在交易过程中收取。","selectInterPro":"您可以选择一个您信任的交易担保人协助您完成订单。","dealPoundagePro":"* 发布广告免手续费，主动成交手续费为交易额的1%"}
     */

    private HelpVOBean helpVO;

    public String getQuantityDealPro() {
        return quantityDealPro;
    }

    public void setQuantityDealPro(String quantityDealPro) {
        this.quantityDealPro = quantityDealPro;
    }

    public String getSelectInterPro() {
        return selectInterPro;
    }

    public void setSelectInterPro(String selectInterPro) {
        this.selectInterPro = selectInterPro;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isTagsKa() {
        return tagsKa;
    }

    public void setTagsKa(boolean tagsKa) {
        this.tagsKa = tagsKa;
    }

    public boolean isTagsExpress() {
        return tagsExpress;
    }

    public void setTagsExpress(boolean tagsExpress) {
        this.tagsExpress = tagsExpress;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public String getDealCountStr() {
        return dealCountStr;
    }

    public void setDealCountStr(String dealCountStr) {
        this.dealCountStr = dealCountStr;
    }


    public String getDealQuantityStr() {
        return dealQuantityStr;
    }

    public void setDealQuantityStr(String dealQuantityStr) {
        this.dealQuantityStr = dealQuantityStr;
    }

    public String getDealRange() {
        return dealRange;
    }

    public void setDealRange(String dealRange) {
        this.dealRange = dealRange;
    }

    public List<Intermediary> getIntermediaryList() {
        return intermediaryList;
    }

    public void setIntermediaryList(List<Intermediary> intermediaryList) {
        this.intermediaryList = intermediaryList;
    }

    public List<AdvertisingMessage> getAdMessageList() {
        return adMessageList;
    }

    public void setAdMessageList(List<AdvertisingMessage> adMessageList) {
        this.adMessageList = adMessageList;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDealTypeStr() {
        return dealTypeStr;
    }

    public void setDealTypeStr(String dealTypeStr) {
        this.dealTypeStr = dealTypeStr;
    }

    public String getRespondTime() {
        return respondTime;
    }

    public void setRespondTime(String respondTime) {
        this.respondTime = respondTime;
    }

    public String getRespondChance() {
        return respondChance;
    }

    public void setRespondChance(String respondChance) {
        this.respondChance = respondChance;
    }

    public HelpVOBean getHelpVO() {
        return helpVO;
    }

    public void setHelpVO(HelpVOBean helpVO) {
        this.helpVO = helpVO;
    }

    public static class HelpVOBean {
        /**
         * quantityDealPro : 您希望卖出的数量。出售链克需要支付交易额1%作为手续费。交易担保人会在交易过程中收取。
         * selectInterPro : 您可以选择一个您信任的交易担保人协助您完成订单。
         * dealPoundagePro : * 发布广告免手续费，主动成交手续费为交易额的1%
         */

        private String quantityDealPro;
        private String selectInterPro;
        private String dealPoundagePro;

        public String getQuantityDealPro() {
            return quantityDealPro;
        }

        public void setQuantityDealPro(String quantityDealPro) {
            this.quantityDealPro = quantityDealPro;
        }

        public String getSelectInterPro() {
            return selectInterPro;
        }

        public void setSelectInterPro(String selectInterPro) {
            this.selectInterPro = selectInterPro;
        }

        public String getDealPoundagePro() {
            return dealPoundagePro;
        }

        public void setDealPoundagePro(String dealPoundagePro) {
            this.dealPoundagePro = dealPoundagePro;
        }
    }
}