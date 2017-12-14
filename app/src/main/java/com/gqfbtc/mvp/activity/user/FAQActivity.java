package com.gqfbtc.mvp.activity.user;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.fivefivelike.mybaselibrary.base.BasePullActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfbtc.R;
import com.gqfbtc.adapter.Adapter_FAQ;
import com.gqfbtc.base.Application;
import com.gqfbtc.mvp.databinder.BaseActivityPullBinder;
import com.gqfbtc.mvp.delegate.BaseActivityPullDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/28.
 */

public class FAQActivity extends BasePullActivity<BaseActivityPullDelegate, BaseActivityPullBinder> {

    List<String> defDatas;
    Adapter_FAQ adapter;


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("常见问题").setSubTitle("反馈"));
        initList();
        initSearch();
    }

    @Override
    protected void clickRightTv() {
        super.clickRightTv();
        Application.getInstance().startCustomerService(this);
    }

    private void initList() {

        defDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            defDatas.add("");
        }
        adapter = new Adapter_FAQ(this, defDatas);
        initRecycleViewPull(adapter, new LinearLayoutManager(this));
        viewDelegate.setIsLoadMore(false);
    }

    private void initSearch() {
        View view = getLayoutInflater().inflate(R.layout.layout_search, null);
        viewDelegate.viewHolder.lin_pull.addView(view, 1);
        EditText et_search = (EditText) view.findViewById(R.id.et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewDelegate.viewHolder.lin_pull.getChildAt(1).findViewById(R.id.lin_toast).setVisibility(s.toString().length() > 0 ? View.GONE : View.VISIBLE);
            }
        });
    }

    @Override
    public BaseActivityPullBinder getDataBinder(BaseActivityPullDelegate viewDelegate) {
        return new BaseActivityPullBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {

    }

    @Override
    protected void refreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    list.add("");
                }
                getDataBack(defDatas, list, adapter);
            }
        }, 2000);
    }

    @Override
    protected Class<BaseActivityPullDelegate> getDelegateClass() {
        return BaseActivityPullDelegate.class;
    }
}
