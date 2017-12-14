package com.gqfbtc.mvp.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.Utils.CheckInfoUtil;
import com.gqfbtc.Utils.UiHeplUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.main.GetCodeActivity;
import com.gqfbtc.mvp.databinder.AddCollectionAddressBinder;
import com.gqfbtc.mvp.delegate.AddCollectionAddressDelegate;
import com.gqfbtc.mvp.popu.ListPopu;

import static com.gqfbtc.mvp.activity.main.GetCodeActivity.codeKey;


public class AddCollectionAddressActivity extends BaseDataBindActivity<AddCollectionAddressDelegate, AddCollectionAddressBinder> {

    String[] type = {"银行卡", "支付宝"};
    String addressType;

    @Override
    protected Class<AddCollectionAddressDelegate> getDelegateClass() {
        return AddCollectionAddressDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("添加收款地址").setSubTitle("帮助"));
        viewDelegate.initBank();
        addressType = "银行卡";
        viewDelegate.viewHolder.et_payment_mode.setText("银行卡");
        if (SingSettingDBUtil.isUser()) {
            viewDelegate.initUser();
        } else {
            viewDelegate.initIntervening();
        }
        viewDelegate.setOnClickListener(this, R.id.et_payment_mode, R.id.tv_commit);
    }

    @Override
    public AddCollectionAddressBinder getDataBinder(AddCollectionAddressDelegate viewDelegate) {
        return new AddCollectionAddressBinder(viewDelegate);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.et_payment_mode:
                new ListPopu(this).setSelectItem(type).setDefaultClickLinsener(new DefaultClickLinsener() {
                    @Override
                    public void onClick(View view, int position, Object item) {
                        addressType = (String) item;
                        viewDelegate.viewHolder.et_payment_mode.setText((String) item);
                        if (addressType.equals(type[0])) {
                            viewDelegate.initBank();
                        } else {
                            viewDelegate.initAplay();
                        }
                    }
                }).showAsDropDown(viewDelegate.viewHolder.et_payment_mode);
                break;
            case R.id.tv_commit:
                if (SingSettingDBUtil.isUser()) {
                    bankcommit();
                } else {
                    if (addressType.equals(type[0])) {
                        bankcommit();
                    } else {
                        aplycommit();
                    }
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String code = data.getStringExtra(codeKey);
            if (SingSettingDBUtil.isUser()) {
                addRequest(binder.addPaymentAddress(
                        "true",
                        type[0].equals(addressType) ? "1" : "2",
                        viewDelegate.viewHolder.et_content4.getText().toString(),
                        null,
                        viewDelegate.viewHolder.et_content1.getText().toString(),
                        viewDelegate.viewHolder.et_content2.getText().toString(),
                        viewDelegate.viewHolder.et_content3.getText().toString(),
                        code,
                        this
                ));
            } else {
                //判断 中介选择地址type
                if (addressType.equals(type[0])) {
                    addRequest(binder.ic_addIntermediaryBeneBankInfo(
                            "true",
                            type[0].equals(addressType) ? "1" : "2",
                            viewDelegate.viewHolder.et_content4.getText().toString(),
                            null,
                            viewDelegate.viewHolder.et_content1.getText().toString(),
                            viewDelegate.viewHolder.et_content2.getText().toString(),
                            viewDelegate.viewHolder.et_content3.getText().toString(),
                            code,
                            this
                    ));
                } else {
                    addRequest(binder.ic_addIntermediaryBeneBankInfo(
                            viewDelegate.viewHolder.et_content4.getText().toString(),
                            viewDelegate.viewHolder.et_content3.getText().toString(),
                            code, this
                    ));
                }
            }
        }
    }


    private void aplycommit() {
        if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_content3.getText().toString(), "请输入开户人姓名") &&
                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_content4.getText().toString(), "请输入支付宝账号")
                ) {
            //校验格式
            if (!CheckInfoUtil.checkOwnerName(viewDelegate.viewHolder.et_content3.getText().toString())) {
                ToastUtil.show("开户人姓名为中文或长度小于等于8位");
                return;
            }
            GetCodeActivity.startActBuyAct(this, 0x123);
        }
    }

    private void bankcommit() {
        if (!UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_content1.getText().toString(), "请输入银行名称") &&
                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_content3.getText().toString(), "请输入开户人姓名") &&
                !UiHeplUtils.judgeRequestContentIsNull(viewDelegate.viewHolder.et_content4.getText().toString(), "请输入收款账号")
                ) {
            if (!CheckInfoUtil.checkBankName(viewDelegate.viewHolder.et_content1.getText().toString())) {
                ToastUtil.show("银行名称为中文或长度小于等于12位");
                return;
            }
            if (!CheckInfoUtil.checkOwnerName(viewDelegate.viewHolder.et_content3.getText().toString())) {
                ToastUtil.show("开户人姓名为中文或长度小于等于8位");
                return;
            }
            if (!CheckInfoUtil.checkBranchName(viewDelegate.viewHolder.et_content2.getText().toString())) {
                ToastUtil.show("开户支行名称为中文或长度小于等于18位");
                return;
            }
            //后台验证
            if (SingSettingDBUtil.isUser()) {
                addRequest(binder.checkPaymentAddress(
                        "true",
                        type[0].equals(addressType) ? "1" : "2",
                        viewDelegate.viewHolder.et_content4.getText().toString(),
                        null,
                        viewDelegate.viewHolder.et_content1.getText().toString(),
                        viewDelegate.viewHolder.et_content2.getText().toString(),
                        viewDelegate.viewHolder.et_content3.getText().toString(),
                        this
                ));
            } else {
                //判断 中介选择地址type
                if (addressType.equals(type[0])) {
                    addRequest(binder.ic_checkInteBankInfo(
                            "true",
                            type[0].equals(addressType) ? "1" : "2",
                            viewDelegate.viewHolder.et_content4.getText().toString(),
                            null,
                            viewDelegate.viewHolder.et_content1.getText().toString(),
                            viewDelegate.viewHolder.et_content2.getText().toString(),
                            viewDelegate.viewHolder.et_content3.getText().toString(),
                            this
                    ));
                } else {
                    addRequest(binder.ic_checkInteBankInfo(
                            viewDelegate.viewHolder.et_content4.getText().toString(),
                            viewDelegate.viewHolder.et_content3.getText().toString(),
                            this
                    ));
                }
            }
        }
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                setResult(RESULT_OK);
                ToastUtil.show(info);
                onBackPressed();
                break;
            case 0x124:
                GetCodeActivity.startActBuyAct(this, 0x123);
                break;
        }
    }
}
