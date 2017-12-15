package com.gqfbtc.mvp.activity.posted;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.adapter.TransactAdvertisingAdapter;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.mvp.databinder.PostedBigDealBinder;
import com.gqfbtc.mvp.delegate.PostedBigDealDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

public class PostedBigDealSellActivity extends BaseDataBindActivity<PostedBigDealDelegate, PostedBigDealBinder> {
    List<PaymentBTCETHAddress> paymentBTCETHAddresses;
    TransactAdvertisingAdapter transactAdvertisingAdapter;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("出售其它币种").setSubTitle(CommonUtils.getString(R.string.str_subtitle_help)));
        viewDelegate.initSellView();
        addRequest(binder.getPaymentAddressList(this));

    }

    private void initAddress() {
        viewDelegate.viewHolder.rv_address.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        transactAdvertisingAdapter = new TransactAdvertisingAdapter(this, paymentBTCETHAddresses);
        viewDelegate.viewHolder.rv_address.setAdapter(transactAdvertisingAdapter);
        transactAdvertisingAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                transactAdvertisingAdapter.setSelectPositions(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void initCommit() {
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_unit_price.getText().toString(), "请输入" + (viewDelegate.isBuy ? "求购" : "出售") + "单价") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_amount.getText().toString(), "请输入" + (viewDelegate.isBuy ? "买入量" : "卖出量")) &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_starting_buy.getText().toString(), "请输入" + (viewDelegate.isBuy ? "最低买入" : "最低卖出"))
                        ) {
                    if (transactAdvertisingAdapter.getSelectPositions().size() == 0) {
                        ToastUtil.show("请选择收款地址");
                        return;
                    }
                    addRequest(binder.saveAd2(
                            viewDelegate.viewHolder.et_unit_price.getText().toString(),
                            viewDelegate.viewHolder.et_amount.getText().toString(),
                            viewDelegate.viewHolder.et_remark.getText().toString(),
                            viewDelegate.chooseday1 + "_" + viewDelegate.choosetime1,
                            viewDelegate.chooseday2 + "_" + viewDelegate.choosetime2,
                            viewDelegate.viewHolder.et_starting_buy.getText().toString(),
                            transactAdvertisingAdapter.getSelectId(),
                            PostedBigDealSellActivity.this
                    ));
                }
            }
        });
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        super.onServiceError(data, info, status, requestCode);
        switch (requestCode) {
            case 0x123:
                //发不成功

                break;
            case 0x124:
                //收款地址
                paymentBTCETHAddresses = GsonUtil.getInstance().toList(data, PaymentBTCETHAddress.class);
                if (paymentBTCETHAddresses == null) {
                    initToast();
                } else {
                    if (paymentBTCETHAddresses.size() == 0) {
                        initToast();
                    } else {
                        initAddress();
                        initCommit();
                    }
                }
                break;
        }
    }

    private void initToast() {
        UiHeplUtils.initDefaultToastDialog(this, "请在个人中心中添加收款地址", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }).show();
    }

    @Override
    protected Class<PostedBigDealDelegate> getDelegateClass() {
        return PostedBigDealDelegate.class;
    }

    @Override
    public PostedBigDealBinder getDataBinder(PostedBigDealDelegate viewDelegate) {
        return new PostedBigDealBinder(viewDelegate);
    }

}
