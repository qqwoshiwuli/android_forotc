package com.gqfbtc.mvp.activity.user;

import android.support.v7.widget.LinearLayoutManager;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfbtc.R;
import com.gqfbtc.adapter.SetListAdapter;
import com.gqfbtc.entity.SetMenuEntity;
import com.gqfbtc.mvp.delegate.SetDelegate;

import java.util.ArrayList;
import java.util.List;


public class SetActivity extends BaseActivity<SetDelegate> {

    String[] mTitle = {"登陆密码", "绑定邮箱"};
    List<SetMenuEntity> defDatas;
    SetListAdapter setListAdapter;

    @Override
    protected Class<SetDelegate> getDelegateClass() {
        return SetDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("设置").setSubTitle("帮助"));
        defDatas = new ArrayList<>();
        defDatas.add(new SetMenuEntity().setTitle(mTitle[0]).setHaveRightImg(true).setHavaContent(true).setContent("修改").setContentColorId(R.color.color_999999));
        defDatas.add(new SetMenuEntity().setTitle(mTitle[1]).setHaveRightImg(true).setHavaContent(true).setContent("已绑定").setContentColorId(R.color.color_999999));
        setListAdapter = new SetListAdapter(this, defDatas);
        viewDelegate.viewHolder.rv_set.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        viewDelegate.viewHolder.rv_set.setAdapter(setListAdapter);
        setListAdapter.setCheckClick(new SetListAdapter.CheckClick() {
            @Override
            public void checkBoxClick(int position) {

            }

            @Override
            public void onClick(int position) {
                if (position == 0) {
                    //修改密码
                    gotoActivity(ChangeUserPasswordActivity.class).startAct();
                } else if (position == 1) {
                    //修改邮箱
                    gotoActivity(ChangeUserEmailActivity.class).startAct();
                }
            }
        });
    }


}
