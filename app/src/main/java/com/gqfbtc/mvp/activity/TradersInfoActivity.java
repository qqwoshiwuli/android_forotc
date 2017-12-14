package com.gqfbtc.mvp.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.adapter.DialogAddressAdapter;
import com.gqfbtc.entity.bean.InterNeedInfoVO;
import com.gqfbtc.mvp.delegate.TradersInfoDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


public class TradersInfoActivity extends BaseActivity<TradersInfoDelegate> {


    @Override
    protected Class<TradersInfoDelegate> getDelegateClass() {
        return TradersInfoDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle(CommonUtils.getString(R.string.str_title_tradersinfo)));
        getIntentData();
        initView();
    }

    public static void startAct(Activity activity,
                                String interNeedInfoVOJson) {
        Intent intent = new Intent(activity, TradersInfoActivity.class);
        intent.putExtra("interNeedInfoVOJson", interNeedInfoVOJson);
        activity.startActivity(intent);
    }

    String interNeedInfoVOJson;
    InterNeedInfoVO interNeedInfoVO;

    private void getIntentData() {
        Intent intent = getIntent();
        interNeedInfoVOJson = intent.getStringExtra("interNeedInfoVOJson");
        interNeedInfoVO = GsonUtil.getInstance().toObj(interNeedInfoVOJson, InterNeedInfoVO.class);
    }

    private void initView() {
        viewDelegate.viewHolder.tv_seller_phone.setText(interNeedInfoVO.getSellUser().getPhone());
        viewDelegate.viewHolder.tv_buyer_phone.setText(interNeedInfoVO.getBuyUser().getPhone());
        viewDelegate.viewHolder.tv_seller_title.setText(CommonUtils.getString(R.string.str_txt_sellerphone, interNeedInfoVO.getSellUser().getNickName()));
        viewDelegate.viewHolder.tv_buyer_title.setText(CommonUtils.getString(R.string.str_txt_buyerphone, interNeedInfoVO.getBuyUser().getNickName()));
        viewDelegate.viewHolder.tv_seller_coin_add.setText(interNeedInfoVO.getBuyPlatformCoinAddr().getCollectionAddr());

        viewDelegate.viewHolder.tv_seller_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call(interNeedInfoVO.getSellUser().getPhone());
            }
        });
        viewDelegate.viewHolder.tv_buyer_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call(interNeedInfoVO.getBuyUser().getPhone());
            }
        });
        viewDelegate.viewHolder.tv_seller_coin_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copy(interNeedInfoVO.getBuyPlatformCoinAddr().getCollectionAddr());
            }
        });

        DialogAddressAdapter sellerAddress = new DialogAddressAdapter(this, interNeedInfoVO.getSellCashAddrList());
        viewDelegate.viewHolder.rv_seller_add.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        viewDelegate.viewHolder.rv_seller_add.setAdapter(sellerAddress);
        sellerAddress.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                copy(interNeedInfoVO.getSellCashAddrList().get(position).getBankName() + "\n" +
                        (TextUtils.isEmpty(interNeedInfoVO.getSellCashAddrList().get(position).getBranchName()) ? "" : (interNeedInfoVO.getSellCashAddrList().get(position).getBranchName() + "\n")) +
                        interNeedInfoVO.getSellCashAddrList().get(position).getCollectionAddr() + "\n" +
                        interNeedInfoVO.getSellCashAddrList().get(position).getOwnerName()
                );
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        DialogAddressAdapter buyerAddress = new DialogAddressAdapter(this, interNeedInfoVO.getBuyCoinAddrList());
        viewDelegate.viewHolder.rv_buyer_add.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        viewDelegate.viewHolder.rv_buyer_add.setAdapter(buyerAddress);
        buyerAddress.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                copy(interNeedInfoVO.getBuyCoinAddrList().get(position).getCollectionAddr());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void copy(String txt) {
        ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip = ClipData.newPlainText("text", txt);
        mClipboardManager.setPrimaryClip(myClip);
        ToastUtil.show("已复制地址到粘贴板：\n" + txt);
    }
}
