package com.gqfbtc.mvp.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BasePullFragment;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.adapter.TopUpRecordAdapter;
import com.gqfbtc.entity.bean.TopUp;
import com.gqfbtc.mvp.databinder.BaseFragmentPullBinder;
import com.gqfbtc.mvp.delegate.BaseFragentPullDelegate;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/21.
 */

public class AssetsBtcTopupFragment extends BasePullFragment<BaseFragentPullDelegate, BaseFragmentPullBinder> {

    List<TopUp> defDatas;
    TopUpRecordAdapter recordAdapter;
    HeaderAndFooterWrapper adapter;

    String topupAddress;
    boolean isShow = false;

    public void setTopupAddress(String topupAddress) {
        this.topupAddress = topupAddress;
        if (tv_btc_address != null) {
            tv_btc_address.setText(topupAddress);
        }
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initList();
        onRefresh();

    }

    private void initList() {
        defDatas = new ArrayList<>();
        viewDelegate.setNoDataTxt("暂无充值记录");
        recordAdapter = new TopUpRecordAdapter(getActivity(), defDatas);
        adapter = new HeaderAndFooterWrapper(recordAdapter);
        adapter.addHeaderView(initHeaderView());
        initRecycleViewPull(adapter, adapter.getHeadersCount(), new LinearLayoutManager(getActivity()));
        viewDelegate.setIsPullDown(false);

    }

    TextView tv_commit;
    TextView tv_btc_address;

    public TextView getTv_btc_address() {
        return tv_btc_address;
    }

    private View initHeaderView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_top_up, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv_btc_address = view.findViewById(R.id.tv_btc_address);
        tv_commit = view.findViewById(R.id.tv_commit);
        tv_btc_address.setText(topupAddress);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copy(topupAddress);
            }
        });
        return view;
    }

    private void copy(String txt) {
        ClipboardManager mClipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip = ClipData.newPlainText("text", txt);
        mClipboardManager.setPrimaryClip(myClip);
        ToastUtil.show("已复制地址到粘贴板：\n" + txt + "");
    }

    @Override
    public BaseFragmentPullBinder getDataBinder(BaseFragentPullDelegate viewDelegate) {
        return new BaseFragmentPullBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                List<TopUp> list = GsonUtil.getInstance().toList(data, "list", TopUp.class);
                getDataBack(defDatas, list, adapter);
                break;
        }
    }


    @Override
    protected Class<BaseFragentPullDelegate> getDelegateClass() {
        return BaseFragentPullDelegate.class;
    }


    @Override
    protected void refreshData() {
        addRequest(binder.recharge(this));
    }
}
