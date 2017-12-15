package com.gqfbtc.mvp.delegate;


import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.utils.AndroidUtil;
import com.fivefivelike.mybaselibrary.utils.GlobleContext;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfbtc.R;
import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.fragment.ConversationFragmentEx;
import com.gqfbtc.widget.SingleLineZoomTextView;
import com.gqfbtc.widget.WaitTransactSwipeRefreshLayout;

import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class WaitTransactDelegate extends BaseDelegate {
    public ViewHolder viewHolder;
    public ConversationFragmentEx fragment;
    public RongExtension rongExtension;
    public int screenHeight;
    public boolean isKeySorftShow = false;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
        screenHeight = AndroidUtil.getScreenSize((FragmentActivity) viewHolder.rootView.getContext(), 2) / 3;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wait_transact;
    }

    public void initChatView(FragmentTransaction transaction, UserLogin userLogin, String id, String code) {
        fragment = new ConversationFragmentEx();
        Uri uri = Uri.parse("rong://" + GlobleContext.getInstance().getApplicationContext().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(Conversation.ConversationType.GROUP.getName().toLowerCase())
                .appendQueryParameter("targetId", id + "_" + code).build();
        //设置传递用户数据
        if (SingSettingDBUtil.isUser()) {
            RongIM.getInstance().setCurrentUserInfo(new UserInfo(userLogin.getId() + "u", userLogin.getNickName(), Uri.parse(GlideUtils.getBaseUrl() + userLogin.getAvatar())));
        } else {
            RongIM.getInstance().setCurrentUserInfo(new UserInfo(userLogin.getId() + "i", userLogin.getName(), Uri.parse(userLogin.getAvatar())));
        }
        fragment.setUri(uri);
        /* 加载 ConversationFragment */
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

    public void linsenerLayout() {
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
                            viewHolder.fl_top.setVisibility(View.GONE);
                        } else {
                            viewHolder.fl_top.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }
    }


    public void initDefaultView() {
        viewHolder.tv_bigdeals_toast.setVisibility(View.GONE);
    }

    public void initBigDealsView() {
        viewHolder.tv_title1.setText("交易币种");
        viewHolder.tv_title3.setText("成交单价");
        viewHolder.lin_default.setVisibility(View.GONE);
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_title1;
        public SingleLineZoomTextView tv_total_price;
        public TextView tv_title2;
        public TextView tv_poundage;
        public TextView tv_title3;
        public SingleLineZoomTextView tv_unit_price;
        public TextView tv_title4;
        public TextView tv_btc;
        public SingleLineZoomTextView tv_freeze;
        public SingleLineZoomTextView tv_payment;
        public TextView tv_paytype;
        public LinearLayout lin_my_pay_type;
        public LinearLayout lin_default;
        public TextView tv_bigdeals_toast;
        public TextView tv_collection;
        public FrameLayout fl_collection;
        public TextView tv_call_service;
        public FrameLayout fl_call_service;
        public LinearLayout lin_top;
        public FrameLayout fl_top;
        public FrameLayout fl_root;
        public WaitTransactSwipeRefreshLayout swipeRefreshLayout;
        public LinearLayout lin_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title1 = (TextView) rootView.findViewById(R.id.tv_title1);
            this.tv_total_price = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_total_price);
            this.tv_title2 = (TextView) rootView.findViewById(R.id.tv_title2);
            this.tv_poundage = (TextView) rootView.findViewById(R.id.tv_poundage);
            this.tv_title3 = (TextView) rootView.findViewById(R.id.tv_title3);
            this.tv_unit_price = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_unit_price);
            this.tv_title4 = (TextView) rootView.findViewById(R.id.tv_title4);
            this.tv_btc = (TextView) rootView.findViewById(R.id.tv_btc);
            this.tv_freeze = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_freeze);
            this.tv_payment = (SingleLineZoomTextView) rootView.findViewById(R.id.tv_payment);
            this.tv_paytype = (TextView) rootView.findViewById(R.id.tv_paytype);
            this.lin_my_pay_type = (LinearLayout) rootView.findViewById(R.id.lin_my_pay_type);
            this.lin_default = (LinearLayout) rootView.findViewById(R.id.lin_default);
            this.tv_bigdeals_toast = (TextView) rootView.findViewById(R.id.tv_bigdeals_toast);
            this.tv_collection = (TextView) rootView.findViewById(R.id.tv_collection);
            this.fl_collection = (FrameLayout) rootView.findViewById(R.id.fl_collection);
            this.tv_call_service = (TextView) rootView.findViewById(R.id.tv_call_service);
            this.fl_call_service = (FrameLayout) rootView.findViewById(R.id.fl_call_service);
            this.lin_top = (LinearLayout) rootView.findViewById(R.id.lin_top);
            this.fl_top = (FrameLayout) rootView.findViewById(R.id.fl_top);
            this.fl_root = (FrameLayout) rootView.findViewById(R.id.fl_root);
            this.swipeRefreshLayout = (WaitTransactSwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
            this.lin_root = (LinearLayout) rootView.findViewById(R.id.lin_root);
        }

    }
}
