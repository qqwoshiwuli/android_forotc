package com.gqfbtc.mvp.activity.transact;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.entity.bean.TransactAppraise;
import com.gqfbtc.mvp.activity.SuccessActivity;
import com.gqfbtc.mvp.databinder.TransactAppraiseBinder;
import com.gqfbtc.mvp.delegate.TransactAppraiseDelegate;
import com.hedgehog.ratingbar.RatingBar;


public class TransactAppraiseActivity extends BaseDataBindActivity<TransactAppraiseDelegate, TransactAppraiseBinder> {


    int firstStar = -1;
    int secondStar = -1;
    TransactAppraise transactAppraise;

    @Override
    protected Class<TransactAppraiseDelegate> getDelegateClass() {
        return TransactAppraiseDelegate.class;
    }

    public static void startAct(Activity activity,
                                String dealId,
                                String AdOwnerId,
                                String DealOwnerId,
                                String IntermediaryId,
                                String currency,
                                int requestCode) {
        Intent intent = new Intent(activity, TransactAppraiseActivity.class);
        intent.putExtra("dealId", dealId);
        intent.putExtra("AdOwnerId", AdOwnerId);
        intent.putExtra("DealOwnerId", DealOwnerId);
        intent.putExtra("currency", currency);
        activity.startActivityForResult(intent, requestCode);
    }

    private String dealId;
    private String AdOwnerId;
    private String DealOwnerId;
    private String IntermediaryId;
    private String currency;

    private void getIntentData() {
        Intent intent = getIntent();
        dealId = intent.getStringExtra("dealId");
        AdOwnerId = intent.getStringExtra("AdOwnerId");
        DealOwnerId = intent.getStringExtra("DealOwnerId");
        IntermediaryId = intent.getStringExtra("IntermediaryId");
        currency = intent.getStringExtra("currency");
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("交易评价").setSubTitle("帮助"));
        getIntentData();
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appraise();
            }
        });

        addRequest(binder.goDealMark(
                dealId,
                IntermediaryId,
                DealOwnerId,
                AdOwnerId,
                this));

    }

    private void appraise() {
        if (firstStar == -1) {
            ToastUtil.show("请选择评分");
            return;
        }
        if (secondStar == -1) {
            ToastUtil.show("请选择评分");
            return;
        }
        addRequest(binder.saveDealMark(dealId, transactAppraise.getFirstId(), firstStar + "",
                viewDelegate.viewHolder.et_seller.getText().toString(), transactAppraise.getSecondId(), secondStar + "", viewDelegate.viewHolder.et_guarantor.getText().toString(), this));
    }

    private void initView() {
        viewDelegate.viewHolder.tv_toast.setText(transactAppraise.getTitle());
        viewDelegate.viewHolder.tv_first.setText(transactAppraise.getFirstName());
        viewDelegate.viewHolder.tv_second.setText(transactAppraise.getSecondName());
        viewDelegate.viewHolder.tv_toast.setText("交易完成了，来评价一下" + transactAppraise.getFirstName() + "和" + transactAppraise.getSecondName() + "吧！您的评价将为其他用户提供参考~");
        viewDelegate.viewHolder.rb_seller.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                firstStar = (int) RatingCount;
            }
        });
        viewDelegate.viewHolder.rb_guarantor.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                secondStar = (int) RatingCount;
            }
        });
        initToolbar(new ToolbarBuilder().setTitle(transactAppraise.getTitle()).setSubTitle("帮助"));
    }

    @Override
    public TransactAppraiseBinder getDataBinder(TransactAppraiseDelegate viewDelegate) {
        return new TransactAppraiseBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //获取评价信息
                transactAppraise = GsonUtil.getInstance().toObj(data, TransactAppraise.class);
                initView();
                break;
            case 0x124:
                //评价成功
                setResult(RESULT_OK);
                SuccessActivity.startActWithId(TransactAppraiseActivity.this, dealId, SuccessActivity.INTENT_SUCCESS_EVALUATE,currency, 0x123);
                finish();
                break;
        }
    }
}
