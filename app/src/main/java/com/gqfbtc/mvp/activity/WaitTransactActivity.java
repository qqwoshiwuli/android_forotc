package com.gqfbtc.mvp.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.AndroidUtil;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfbtc.R;
import com.gqfbtc.Utils.KeyboardChangeListener;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.dialog.AddressDialog;
import com.gqfbtc.dialog.DefaultLongContentDialog;
import com.gqfbtc.entity.bean.OrderDetails;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.databinder.WaitTransactBinder;
import com.gqfbtc.mvp.delegate.WaitTransactDelegate;
import com.gqfbtc.mvp.fragment.ConversationFragmentEx;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;


public class WaitTransactActivity extends BaseDataBindActivity<WaitTransactDelegate, WaitTransactBinder> implements KeyboardChangeListener.KeyBoardListener {

    OrderDetails orderDetails;
    PaymentBTCETHAddress paymentBTCETHAddress;
    private KeyboardChangeListener mKeyboardChangeListener;
    RongExtension rongExtension;
    int screenHeight;
    boolean isKeySorftShow = false;
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
        screenHeight = AndroidUtil.getScreenSize(this, 2) / 3;
        mKeyboardChangeListener = new KeyboardChangeListener(this);
        mKeyboardChangeListener.setKeyBoardListener(this);
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        initRefush();
    }

    private void initRefush() {
        //刷新
        refreshInfo();
        viewDelegate.viewHolder.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshInfo();
            }
        });
    }

    private void refreshInfo() {
        addRequest(binder.dealdt(id, false, WaitTransactActivity.this));
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        isKeySorftShow = isShow;
        viewDelegate.viewHolder.fl_top.setVisibility(!isShow ? View.VISIBLE : View.GONE);
        handler.sendEmptyMessageDelayed(1, 100);
    }

    private Handler handler = new Handler() {//进行延时跳转
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //界面上部显隐
                    linsenerLayout();
                    break;
                case 2:
                    //中介刷新
                    refreshInfo();
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
        super.onDestroy();
    }

    ConversationFragmentEx fragment;

    private void initServiceFragment() {
        setWindowManagerLayoutParams(0);

        fragment = new ConversationFragmentEx();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(Conversation.ConversationType.GROUP.getName().toLowerCase())
                .appendQueryParameter("targetId", orderDetails.getId() + "_" + orderDetails.getCode()).build();
        //设置传递用户数据
        if (SingSettingDBUtil.isUser()) {
            RongIM.getInstance().setCurrentUserInfo(new UserInfo(userLogin.getId() + "u", userLogin.getNickName(), Uri.parse(GlideUtils.getBaseUrl() + userLogin.getAvatar())));
        } else {
            RongIM.getInstance().setCurrentUserInfo(new UserInfo(userLogin.getId() + "i", userLogin.getName(), Uri.parse(userLogin.getAvatar())));
        }
        fragment.setUri(uri);
        /* 加载 ConversationFragment */
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_root, fragment);
        transaction.commit();
        fragment.setDefaultClickLinsener(new DefaultClickLinsener() {
            @Override
            public void onClick(View view, int position, Object item) {
                if (position == 1) {
                    rongExtension = (RongExtension) view;
                }
                linsenerLayout();
            }
        });
    }

    ViewTreeObserver vto;

    private void linsenerLayout() {
        //上部分显示判断
        if (rongExtension != null) {
            ViewGroup.LayoutParams layoutParams = rongExtension.getLayoutParams();
            int height = layoutParams.height;
            int width = layoutParams.width;
            Log.i("gqf", height + "---" + width);
            if (vto == null) {
                vto = rongExtension.getViewTreeObserver();
                vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int height = rongExtension.getHeight();
                        if (height > screenHeight || isKeySorftShow) {
                            viewDelegate.viewHolder.fl_top.setVisibility(View.GONE);
                        } else {
                            viewDelegate.viewHolder.fl_top.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }
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
                orderDetails = GsonUtil.getInstance().toObj(data, OrderDetails.class);
                initView();
                if (!SingSettingDBUtil.isUser()) {
                    handler.sendEmptyMessageDelayed(2, 3000);
                }
                break;
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
        viewDelegate.viewHolder.tv_total_price.setText(orderDetails.getDealMoneyStr());
        viewDelegate.viewHolder.tv_poundage.setText(orderDetails.getPoundageStr());
        viewDelegate.viewHolder.tv_unit_price.setText(orderDetails.getDealPriceStr());
        viewDelegate.viewHolder.tv_btc.setText(orderDetails.getDealQuantityStr());
        viewDelegate.viewHolder.tv_freeze.setText(orderDetails.getFrozenCoin());
        viewDelegate.viewHolder.tv_payment.setText(orderDetails.getDealRemark());
        viewDelegate.viewHolder.tv_collection.setText(orderDetails.getDealPushBtn());
        viewDelegate.viewHolder.tv_call_service.setText(orderDetails.getCustomJoin());
        viewDelegate.viewHolder.tv_paytype.setText(orderDetails.getPayType());

        ViewTreeObserver viewTreeObserver = viewDelegate.viewHolder.lin_top.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                viewDelegate.viewHolder.lin_top.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int height = viewDelegate.viewHolder.lin_top.getHeight();
                viewDelegate.viewHolder.swipeRefreshLayout.setHeight(height);
            }
        });

        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(false);

        viewDelegate.viewHolder.fl_collection.setVisibility(TextUtils.isEmpty(orderDetails.getDealPushBtn()) ? View.GONE : View.VISIBLE);
        viewDelegate.viewHolder.fl_call_service.setVisibility(TextUtils.isEmpty(orderDetails.getCustomJoin()) ? View.GONE : View.VISIBLE);
        viewDelegate.viewHolder.lin_my_pay_type.setVisibility(TextUtils.isEmpty(orderDetails.getPayType()) ? View.GONE : View.VISIBLE);
        viewDelegate.viewHolder.lin_my_pay_type.setVisibility(orderDetails.getBankInfoList().size() == 0 ? View.GONE : View.VISIBLE);

        viewDelegate.setOnClickListener(this, R.id.fl_collection, R.id.fl_call_service, R.id.lin_my_pay_type, R.id.tv_payment);
        if (fragment == null) {
            initServiceFragment();
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
                    isNeedAppraise();
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

    private void isNeedAppraise() {
        //去评价
        TransactAppraiseActivity.startAct(this,
                orderDetails.getId(),
                orderDetails.getAdOwnerId(),
                orderDetails.getDealOwnerId(),
                orderDetails.getIntermediaryId(),
                0x123
        );
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
