package com.gqfbtc.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.view.dialog.BaseDialog;
import com.gqfbtc.R;
import com.gqfbtc.adapter.DialogAddressAdapter;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.widget.MaxHeightLinearLayoutMAnager;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/28.
 */

public class AddressDialog extends BaseDialog {

    DialogAddressAdapter dialogAddressAdapter;
    private TextView tv_title;
    private RecyclerView rv_address;
    private TextView tv_commit;
    DefaultClickLinsener defaultClickLinsener;
    String dimessStr;
    String titleStr;

    public AddressDialog setTitleStr(String titleStr) {
        this.titleStr = titleStr;
        return this;
    }

    public AddressDialog setDimessStr(String dimessStr) {
        this.dimessStr = dimessStr;
        return this;
    }

    public AddressDialog setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
        return this;
    }

    public AddressDialog(Context context) {
        super(context);
    }

    public AddressDialog(Context context, int style) {
        super(context, style);
    }


    @Override
    protected int getLayout() {
        return R.layout.dialog_address;
    }

    @Override
    protected void startInit() {
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        rv_address = (RecyclerView) this.findViewById(R.id.rv_address);
        tv_commit = (TextView) this.findViewById(R.id.tv_commit);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public void showWithData(List<PaymentBTCETHAddress> datas) {
        dialogAddressAdapter = new DialogAddressAdapter(mContext, datas);
        dialogAddressAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (defaultClickLinsener != null) {
                    defaultClickLinsener.onClick(view, position, dialogAddressAdapter.getDatas().get(position));
                }
                dismiss();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv_address.setLayoutManager(new MaxHeightLinearLayoutMAnager(mContext));
        rv_address.setAdapter(dialogAddressAdapter);
        if (!TextUtils.isEmpty(titleStr)) {
            tv_title.setText(titleStr);
            tv_title.setVisibility(View.VISIBLE);
        } else {
            tv_title.setVisibility(View.GONE);
        }
        show();
    }

}
