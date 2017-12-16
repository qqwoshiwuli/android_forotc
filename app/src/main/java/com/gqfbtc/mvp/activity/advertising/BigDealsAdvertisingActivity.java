package com.gqfbtc.mvp.activity.advertising;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.circledialog.view.listener.OnInputClickListener;
import com.fivefivelike.mybaselibrary.base.BasePullActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.adapter.MessageAdapter;
import com.gqfbtc.entity.bean.AdvertisingDetails;
import com.gqfbtc.entity.bean.AdvertisingMessage;
import com.gqfbtc.entity.bean.HomeAdvertising;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.SuccessActivity;
import com.gqfbtc.mvp.databinder.BaseActivityPullBinder;
import com.gqfbtc.mvp.delegate.BigDealsAdvertisingDelegate;
import com.gqfbtc.widget.FoucsLinearLayoutManager;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/12/15 0015.
 */

public class BigDealsAdvertisingActivity extends BasePullActivity<BigDealsAdvertisingDelegate, BaseActivityPullBinder<BigDealsAdvertisingDelegate>> {
    UserLogin userLogin;
    HeaderAndFooterWrapper adapter;
    MessageAdapter messageAdapter;
    AdvertisingDetails advertisingDetails;
    List<AdvertisingMessage> defDatas;
    String str;//提示文字

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        userLogin = SingSettingDBUtil.getUserLogin();
        getIntentData();
        if (homeAdvertising.isSale()) {
            initToolbar(new ToolbarBuilder().setTitle("买链克").setSubTitle("帮助"));
        } else {
            initToolbar(new ToolbarBuilder().setTitle("卖链克").setSubTitle("帮助"));
        }
        addRequest(binder.adwkc_getAdDetail(homeAdvertising.getAdId(),this));
    }

    public static void startAct(Activity activity,
                                HomeAdvertising homeAdvertising) {
        Intent intent = new Intent(activity, BigDealsAdvertisingActivity.class);
        intent.putExtra("homeAdvertising", homeAdvertising);
        activity.startActivity(intent);
    }

    private HomeAdvertising homeAdvertising;

    private void getIntentData() {
        Intent intent = getIntent();
        homeAdvertising = intent.getParcelableExtra("homeAdvertising");
    }

    private void initList() {
        viewDelegate.setShowNoData(false);
        messageAdapter = new MessageAdapter(this, defDatas);
        adapter = new HeaderAndFooterWrapper(messageAdapter);
        adapter.addHeaderView(viewDelegate.initHeader(advertisingDetails));
        initRecycleViewPull(adapter, adapter.getHeadersCount(), new FoucsLinearLayoutManager(this));
        viewDelegate.setIsLoadMore(false);
        viewDelegate.setIsPullDown(false);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //留言刷新
                List<AdvertisingMessage> list = GsonUtil.getInstance().toList(data, AdvertisingMessage.class);
                getDataBack(defDatas, list, adapter);
                viewDelegate.isHaveMessage(list.size() > 0);
                break;
            case 0x124:
                //订单详情
                advertisingDetails = GsonUtil.getInstance().toObj(data, AdvertisingDetails.class);
                defDatas = advertisingDetails.getAdMessageList();
                initList();
                viewDelegate.isHaveMessage(defDatas.size() > 0);
                initBottomView();
                if (userLogin != null) {
                    if ((userLogin.getId() + "").equals(advertisingDetails.getUserId())) {
                        //用户自己的广告，只能留言
                        viewDelegate.bottomView.findViewById(R.id.tv_commit).setVisibility(View.GONE);
                    }
                }
                break;
            case 0x125:
                //留言
                if (SingSettingDBUtil.isLogin(this)) {
                    onRefresh();
                    ToastUtil.show(info);
                }
                break;
            case 0x126:
                String transactId = GsonUtil.getInstance().getValue(data, "dealId");
                SuccessActivity.startActWithId(this, transactId, SuccessActivity.INTENT_SUCCESS_ORDER,HomeAdvertising.coin_type_ucx, 0x123);
                break;
        }
    }

    private void initBottomView() {
        viewDelegate.initBottomView(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SingSettingDBUtil.isLogin(BigDealsAdvertisingActivity.this)) {
                    UiHeplUtils.initDefaultDialog(BigDealsAdvertisingActivity.this, "确定要" + ((TextView) view).getText().toString() + "吗?", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //保存订单
                            if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.et_bigdeals_cny.getText().toString(), "请输入" + str + "数量")) {
                                String id;
                                if (viewDelegate.checkbox.isChecked()) {
                                    id = "0";
                                } else {
                                    if (viewDelegate.selectGuarantorAdapter.getSelectPositon() == -1) {
                                        ToastUtil.show("请选择中介，或交由系统分配");
                                        return;
                                    } else {
                                        id = advertisingDetails.getIntermediaryList().get(viewDelegate.selectGuarantorAdapter.getSelectPositon()).getIntermediaryId();
                                    }
                                }
                                addRequest(binder.dealwkc_saveDeal(homeAdvertising.getAdId(),
                                        advertisingDetails.getPrice() + "",
                                        viewDelegate.et_bigdeals_cny.getText().toString(),
                                        id,
                                        BigDealsAdvertisingActivity.this
                                ));
                            }

                        }
                    }).show();
                }
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userLogin != null) {
                    UiHeplUtils.initDefaultInputDialog(BigDealsAdvertisingActivity.this, "添加留言", "对广告有任何问题可在这里补充", "留言", new OnInputClickListener() {
                        @Override
                        public void onClick(String text, View v) {
                            if (!UiHeplUtils.judgeRequestContentIsNull(text, "留言不能为空")) {
                                addRequest(binder.saveMessage(homeAdvertising.getAdId(), text, BigDealsAdvertisingActivity.this));
                            }
                        }
                    }).show();
                } else {
                    ToastUtil.show("请先登录后再留言");
                }
            }
        });
        if (homeAdvertising.isSale()) {
            str = "购买";
        } else {
            str = "出售";
        }
        ((TextView) viewDelegate.bottomView.findViewById(R.id.tv_commit)).setText(str);
    }

    @Override
    protected Class<BigDealsAdvertisingDelegate> getDelegateClass() {
        return BigDealsAdvertisingDelegate.class;
    }

    @Override
    public BaseActivityPullBinder<BigDealsAdvertisingDelegate> getDataBinder(BigDealsAdvertisingDelegate viewDelegate) {
        return new BaseActivityPullBinder<BigDealsAdvertisingDelegate>(viewDelegate);
    }

    @Override
    protected void refreshData() {
        addRequest(binder.getMessage(homeAdvertising.getAdId(), this));
    }
}
