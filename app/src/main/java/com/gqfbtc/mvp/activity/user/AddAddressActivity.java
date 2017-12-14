package com.gqfbtc.mvp.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.Utils.CheckInfoUtil;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.main.GetCodeActivity;
import com.gqfbtc.mvp.databinder.AddAddressBinder;
import com.gqfbtc.mvp.delegate.AddAddressDelegate;

import static com.gqfbtc.mvp.activity.main.GetCodeActivity.codeKey;


public class AddAddressActivity extends BaseDataBindActivity<AddAddressDelegate, AddAddressBinder> {


    String[] title = {"BTC", "ETH"};
    public static final int type_btc = 0;
    public static final int type_eth = 1;

    @Override
    protected Class<AddAddressDelegate> getDelegateClass() {
        return AddAddressDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("添加" + title[type] + "地址").setSubTitle("帮助"));
        if (type == type_btc) {
            viewDelegate.initBtc();
        } else {
            viewDelegate.initEth();
        }
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });
    }

    private void commit() {
        if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_address.getText().toString(), "请输入" + title[type] + "地址") &&
                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_remark.getText().toString(), "请备注一下方便自己区分地址")
                ) {
            if (!CheckInfoUtil.checkAlias(viewDelegate.viewHolder.et_remark.getText().toString())) {
                ToastUtil.show("备注长度不能超过6位");
                return;
            }
            if (!CheckInfoUtil.isContainChinese(viewDelegate.viewHolder.et_address.getText().toString())) {
                ToastUtil.show("钱包地址不能包含中文");
                return;
            }
            //校验收币地址
            if (SingSettingDBUtil.isUser()) {
                addRequest(binder.checkCoinAddress(
                        "true",
                        (type + 3) + "",
                        viewDelegate.viewHolder.et_address.getText().toString(),
                        viewDelegate.viewHolder.et_remark.getText().toString(),
                        this
                ));
            } else {
                addRequest(binder.ic_checkInteBankInfo(viewDelegate.viewHolder.et_address.getText().toString(),
                        viewDelegate.viewHolder.et_remark.getText().toString(),
                        this));
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String code = data.getStringExtra(codeKey);
            //中介添加地址
            if (SingSettingDBUtil.isUser()) {
                addRequest(binder.addCoinAddress(
                        "true",
                        (type + 3) + "",
                        viewDelegate.viewHolder.et_address.getText().toString(),
                        viewDelegate.viewHolder.et_remark.getText().toString(),
                        code,
                        this
                ));
            } else {
                addRequest(binder.ic_addIntermediaryBeneBankInfo(viewDelegate.viewHolder.et_address.getText().toString(),
                        viewDelegate.viewHolder.et_remark.getText().toString(), code, this));
            }
        }
    }

    public static void startAct(Activity activity,
                                int type,
                                int requestCode) {
        Intent intent = new Intent(activity, AddAddressActivity.class);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, requestCode);
    }

    private int type;//0 btc 1 eth

    private void getIntentData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
    }

    @Override
    public AddAddressBinder getDataBinder(AddAddressDelegate viewDelegate) {
        return new AddAddressBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //添加地址成功
                setResult(RESULT_OK);
                ToastUtil.show(info);
                onBackPressed();
                break;
            case 0x124:
                //校验格式成功 ,跳转获取验证码
                GetCodeActivity.startActBuyAct(this, 0x123);
                break;
        }

    }
}
