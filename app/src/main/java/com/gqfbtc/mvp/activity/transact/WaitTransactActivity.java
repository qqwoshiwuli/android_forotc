package com.gqfbtc.mvp.activity.transact;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfbtc.R;
import com.gqfbtc.Utils.KeyboardChangeListener;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.dialog.AddressDialog;
import com.gqfbtc.dialog.DefaultLongContentDialog;
import com.gqfbtc.entity.bean.OrderDetails;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.TradersInfoActivity;
import com.gqfbtc.mvp.databinder.WaitTransactBinder;
import com.gqfbtc.mvp.delegate.WaitTransactDelegate;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import static com.gqfbtc.mvp.delegate.WaitTransactDelegate.refushTime;


public class WaitTransactActivity extends BaseDataBindActivity<WaitTransactDelegate, WaitTransactBinder> implements KeyboardChangeListener.KeyBoardListener {

    OrderDetails orderDetails;
    PaymentBTCETHAddress paymentBTCETHAddress;
    private KeyboardChangeListener mKeyboardChangeListener;
    UserLogin userLogin;

    @Override
    protected Class<WaitTransactDelegate> getDelegateClass() {
        return WaitTransactDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("交易详情"));
        userLogin = SingSettingDBUtil.getUserLogin();
        mKeyboardChangeListener = new KeyboardChangeListener(this);
        mKeyboardChangeListener.setKeyBoardListener(this);
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        viewDelegate.initDefaultView();
        initRefush();
    }

    private void initRefush() {
        //刷新
        refreshInfo(true);
        viewDelegate.viewHolder.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshInfo(false);
            }
        });
    }

    private void refreshInfo(boolean isShowDialog) {
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(isShowDialog);
        addRequest(binder.dealdt(id, false, WaitTransactActivity.this));
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        viewDelegate.isKeySorftShow = isShow;
        viewDelegate.viewHolder.fl_top.setVisibility(!isShow ? View.VISIBLE : View.GONE);
        handler.removeMessages(1);
        handler.sendEmptyMessageDelayed(1, 100);
    }

    private Handler handler = new Handler() {//进行延时跳转
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //界面上部显隐
                    viewDelegate.linsenerLayout();
                    break;
                case 2:
                    //中介刷新
                    refreshInfo(false);
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void clickRightTv() {
        super.clickRightTv();
        //跳转双方信息详情页面
        TradersInfoActivity.startAct(this, GsonUtil.getInstance().toJson(orderDetails.getInterNeedInfoVO()));
    }

    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);//清空消息方便gc回收
        viewDelegate.onDestory();
        super.onDestroy();
    }

    public static void startAct(Activity activity,
                                String id) {
        Intent intent = new Intent(activity, WaitTransactActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    private String id;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    @Override
    public WaitTransactBinder getDataBinder(WaitTransactDelegate viewDelegate) {
        return new WaitTransactBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //获取订单详情
            case 0x124:
                //订单修改成功
                orderDetails = GsonUtil.getInstance().toObj(data, OrderDetails.class);
                initView();
                break;
        }
    }

    private void initView() {
        if (SingSettingDBUtil.isUser()) {
            initToolbar(new ToolbarBuilder().setTitle(orderDetails.getTitle()));
        } else {
            initToolbar(new ToolbarBuilder().setTitle(orderDetails.getTitle()).setSubTitle(CommonUtils.getString(R.string.str_subtitle_tradersinfo)));
        }
        viewDelegate.initDataView(orderDetails);
        viewDelegate.setOnClickListener(this, R.id.fl_collection, R.id.fl_call_service, R.id.lin_my_pay_type, R.id.tv_payment);
        if (viewDelegate.fragment == null) {
            setWindowManagerLayoutParams(WindowManagerLayoutParamsNone);
            viewDelegate.initChatView(getSupportFragmentManager().beginTransaction(), userLogin, orderDetails.getId(), orderDetails.getCode());
        }
        if (!SingSettingDBUtil.isUser()) {
            handler.removeMessages(2);
            handler.sendEmptyMessageDelayed(2, refushTime);
        }
    }

    private void pushOrder() {
        if (SingSettingDBUtil.isUser()) {
            if (orderDetails.getBankInfoList() != null && orderDetails.getBankInfoList().size() != 0) {
                //需要选择地址
                showSelectAddressDialog();
            } else {
                addRequest(binder.change(orderDetails.getId(), orderDetails.getDealStatus(), null, null, this));
            }
        } else {
            if (orderDetails.getBankInfoList() != null && orderDetails.getBankInfoList().size() != 0) {
                //需要选择地址
                showSelectAddressDialog();
            } else {
                addRequest(binder.change(orderDetails.getId(), orderDetails.getDealStatus(), null, null, this));
            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fl_collection:
                //推进订单
                if (!orderDetails.getEv()) {
                    UiHeplUtils.initDefaultDialog(this, orderDetails.getShowMsg(), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            pushOrder();
                        }
                    }).show();
                } else {
                    viewDelegate.isNeedAppraise(this, orderDetails);
                }
                break;
            case R.id.fl_call_service:
                //申请客服
                RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE, "10u", "");
                break;
            case R.id.lin_my_pay_type:
                //纯查看
                if (orderDetails != null && orderDetails.getBankInfoList() != null && orderDetails.getBankInfoList().size() > 0) {
                    if (!((TextUtils.isEmpty(orderDetails.getBankInfoList().get(0).getCollectionType()) && ("3".equals(orderDetails.getBankInfoList().get(0).getCollectionType()) || "4".equals(orderDetails.getBankInfoList().get(0).getCollectionType()))))) {
                        showAddress();
                    } else {
                        new DefaultLongContentDialog(this)
                                .setTitle("温馨提示")
                                .setContent(CommonUtils.getString(R.string.str_wait_toast))
                                .setDefaultClickLinsener(new DefaultClickLinsener() {
                                    @Override
                                    public void onClick(View view, int position, Object item) {
                                        showAddress();
                                    }
                                })
                                .showDialog();
                    }
                }
                break;
            case R.id.tv_payment:
                copy(viewDelegate.viewHolder.tv_payment.getText().toString());
                break;
        }
    }

    private void showAddress() {
        new AddressDialog(this)
                .setDimessStr("取消")
                .setDefaultClickLinsener(new DefaultClickLinsener() {
                    @Override
                    public void onClick(View view, int position, Object item) {
                        if (!TextUtils.isEmpty(orderDetails.getBankInfoList().get(position).getBankName())) {
                            copy(orderDetails.getBankInfoList().get(position).getBankName() + "\n" +
                                    (TextUtils.isEmpty(orderDetails.getBankInfoList().get(position).getBranchName()) ? "" : (orderDetails.getBankInfoList().get(position).getBranchName() + "\n")) +
                                    orderDetails.getBankInfoList().get(position).getCollectionAddr() + "\n" +
                                    orderDetails.getBankInfoList().get(position).getOwnerName()
                            );
                        } else {
                            copy(orderDetails.getBankInfoList().get(position).getCollectionAddr());
                        }
                    }
                })
                .setTitleStr(orderDetails.getPayTypeTitle())
                .showWithData(orderDetails.getBankInfoList());
    }

    private void copy(String txt) {
        ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip = ClipData.newPlainText("text", txt);
        mClipboardManager.setPrimaryClip(myClip);
        ToastUtil.show("已复制地址到粘贴板：\n" + txt);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            finish();
        }
    }

    boolean isCoinAndBank = false;

    private void showSelectAddressDialog() {
        //判断是否是比特币地址
        if (orderDetails.getBankInfoList().size() == 1 && !TextUtils.isEmpty(orderDetails.getBankInfoList().get(0).getCollectionType()) && ("3".equals(orderDetails.getBankInfoList().get(0).getCollectionType()) || "4".equals(orderDetails.getBankInfoList().get(0).getCollectionType()))) {
            //比特币地址
            addRequest(binder.change(orderDetails.getId(), orderDetails.getDealStatus(), orderDetails.getBankInfoList().get(0).getId() + "", orderDetails.getBankInfoList().get(0).getId() + "", WaitTransactActivity.this));
        } else {
            List<String> address = new ArrayList<>();
            //判断是否包含比特币地址
            for (int i = 0; i < orderDetails.getBankInfoList().size(); i++) {
                if (("3".equals(orderDetails.getBankInfoList().get(i).getCollectionType()) || "4".equals(orderDetails.getBankInfoList().get(i).getCollectionType()))) {
                    isCoinAndBank = true;
                } else {
                    address.add(orderDetails.getBankInfoList().get(i).getOwnerName() + orderDetails.getBankInfoList().get(i).getCollectionAddr());
                }
            }
            if (!isCoinAndBank) {
                //选择付款地址
                UiHeplUtils.initDefaultItemDialogWithTitle(this, "请选择一个付款地址", address, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        paymentBTCETHAddress = orderDetails.getBankInfoList().get(i - 1);
                        addRequest(binder.change(orderDetails.getId(), orderDetails.getDealStatus(), paymentBTCETHAddress.getId() + "", paymentBTCETHAddress.getId() + "", WaitTransactActivity.this));
                    }
                }).show();
            } else {
                addRequest(binder.change(orderDetails.getId(), orderDetails.getDealStatus(), null, null, this));
            }
            isCoinAndBank = false;
        }

    }

}
