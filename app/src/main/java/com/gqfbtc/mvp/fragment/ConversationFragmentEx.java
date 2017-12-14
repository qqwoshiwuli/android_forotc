package com.gqfbtc.mvp.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;

import io.rong.imkit.IExtensionClickListener;
import io.rong.imkit.fragment.ConversationFragment;

/**
 * 会话 Fragment 继承自ConversationFragment
 * onResendItemClick: 重发按钮点击事件. 如果返回 false,走默认流程,如果返回 true,走自定义流程
 * onReadReceiptStateClick: 已读回执详情的点击事件.
 * 如果不需要重写 onResendItemClick 和 onReadReceiptStateClick ,可以不必定义此类,直接集成 ConversationFragment 就可以了
 * Created by Yuejunhong on 2016/10/10.
 */
public class ConversationFragmentEx extends ConversationFragment implements IExtensionClickListener, AbsListView.OnScrollListener {

    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }


    public void onWarningDialog(String msg) {
        String typeStr = getUri().getLastPathSegment();
        if (!typeStr.equals("chatroom")) {
            super.onWarningDialog(msg);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onEmoticonToggleClick(View v, ViewGroup extensionBoard) {
        super.onEmoticonToggleClick(v, extensionBoard);
        defaultClickLinsener.onClick(extensionBoard, 1, null);
    }

    @Override
    public void onPluginToggleClick(View v, ViewGroup extensionBoard) {
        super.onPluginToggleClick(v, extensionBoard);
        defaultClickLinsener.onClick(extensionBoard, 1, null);
    }

    @Override
    public void onSwitchToggleClick(View v, ViewGroup inputBoard) {
        super.onSwitchToggleClick(v, inputBoard);
        defaultClickLinsener.onClick(inputBoard, 0, null);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        super.onScrollStateChanged(view, scrollState);
        defaultClickLinsener.onClick(view, 2, null);
    }
}
