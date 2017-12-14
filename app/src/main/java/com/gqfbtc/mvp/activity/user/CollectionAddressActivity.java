package com.gqfbtc.mvp.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfbtc.adapter.SlidWithFragmentPagerAdapter;
import com.gqfbtc.mvp.databinder.MyAddressBinder;
import com.gqfbtc.mvp.delegate.MyAddressDelegate;
import com.gqfbtc.mvp.fragment.EthAndBtcAddressFragment;

import java.util.ArrayList;


public class CollectionAddressActivity extends BaseDataBindActivity<MyAddressDelegate, MyAddressBinder> implements EthAndBtcAddressFragment.Linsener {

    private String[] mTitles = {"收款地址管理"};
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected Class<MyAddressDelegate> getDelegateClass() {
        return MyAddressDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("收款地址管理").setSubTitle("帮助"));
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(AddCollectionAddressActivity.class)
                        .startActResult(0x123);
            }
        });
        viewDelegate.viewHolder.tv_commit.setText("添加收款地址");
        initFragment();
    }


    private void initFragment() {
        fragments.add(EthAndBtcAddressFragment.newInstance(EthAndBtcAddressFragment.type_collection));
        SlidWithFragmentPagerAdapter slidWithFragmentPagerAdapter = new SlidWithFragmentPagerAdapter(getSupportFragmentManager(), mTitles, fragments);
        viewDelegate.viewHolder.vp_address.setAdapter(slidWithFragmentPagerAdapter);
        viewDelegate.viewHolder.tl_2.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0x123) {
                setResult(RESULT_OK);
                ((EthAndBtcAddressFragment) fragments.get(viewDelegate.viewHolder.vp_address.getCurrentItem())).onRefresh();
            }
        }
    }

    public static void startAct(Activity activity,
                                int requestCode) {
        Intent intent = new Intent(activity, CollectionAddressActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }


    @Override
    public MyAddressBinder getDataBinder(MyAddressDelegate viewDelegate) {
        return new MyAddressBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {

    }

    public void setResultOk() {
        setResult(RESULT_OK);
    }

}
