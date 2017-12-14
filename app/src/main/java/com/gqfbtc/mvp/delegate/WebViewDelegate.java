package com.gqfbtc.mvp.delegate;


import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;

public class WebViewDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }


    public static class ViewHolder {
        public View rootView;
        public WebView webview;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.webview = (WebView) rootView.findViewById(R.id.webview);
        }

    }
}
