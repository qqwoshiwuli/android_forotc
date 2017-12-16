package com.gqfbtc.mvp.activity.posted;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.gqfbtc.R;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.entity.bean.CheckFrozen;
import com.gqfbtc.entity.bean.HomeAdvertising;
import com.gqfbtc.mvp.activity.SuccessActivity;
import com.gqfbtc.mvp.databinder.PostedBigDealBinder;
import com.gqfbtc.mvp.delegate.PostedBigDealDelegate;

public class PostedBigDealBuyActivity extends BaseDataBindActivity<PostedBigDealDelegate, PostedBigDealBinder> {
    HomeAdvertising advertising;

    @Override
    protected Class<PostedBigDealDelegate> getDelegateClass() {
        return PostedBigDealDelegate.class;
    }

    @Override
    public PostedBigDealBinder getDataBinder(PostedBigDealDelegate viewDelegate) {
        return new PostedBigDealBinder(viewDelegate);
    }

    public static void startAct(Activity activity,
                                CheckFrozen checkFrozen,
                                int requestCode) {
        Intent intent = new Intent(activity, PostedBigDealBuyActivity.class);
        intent.putExtra("checkFrozen", checkFrozen);
        activity.startActivity(intent);
    }

    CheckFrozen checkFrozen;

    private void getIntentData() {
        Intent intent = getIntent();
        checkFrozen = intent.getParcelableExtra("checkFrozen");
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("求购其它币种").setSubTitle(CommonUtils.getString(R.string.str_subtitle_help)));
        getIntentData();
        viewDelegate.initBuyView(checkFrozen);
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (
                        !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_unit_price.getText().toString(), "请输入" + (viewDelegate.isBuy ? "求购" : "出售") + "单价") &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_amount.getText().toString(), "请输入" + (viewDelegate.isBuy ? "买入量" : "卖出量")) &&
                                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_starting_buy.getText().toString(), "请输入" + (viewDelegate.isBuy ? "最低买入" : "最低卖出"))
                        ) {
                    addRequest(binder.saveAd1(
                            viewDelegate.viewHolder.et_unit_price.getText().toString(),
                            viewDelegate.viewHolder.et_amount.getText().toString(),
                            viewDelegate.viewHolder.et_remark.getText().toString(),
                            viewDelegate.chooseday1 + "_" + viewDelegate.choosetime1,
                            viewDelegate.chooseday2 + "_" + viewDelegate.choosetime2,
                            viewDelegate.viewHolder.et_starting_buy.getText().toString(),
                            PostedBigDealBuyActivity.this
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
                //广告发布成功
                advertising = GsonUtil.getInstance().toObj(data, HomeAdvertising.class);
                SuccessActivity.startActWithAdvertising(PostedBigDealBuyActivity.this, advertising, SuccessActivity.INTENT_SUCCESS_ADVERTISING, 0x123);
                break;
        }
    }

}
