package com.gqfbtc.mvp.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.circledialog.CircleDialog;
import com.circledialog.params.ProgressParams;
import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfbtc.Utils.UiHeplUtils;
import com.gqfbtc.mvp.delegate.WebViewDelegate;

/**
 * Created by 郭青枫 on 2017/11/19.
 */

public class WebVeiwActivity extends BaseActivity<WebViewDelegate> {
    DialogFragment dialogFragment;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("详情").setSubTitle("帮助"));
        getIntentData();
        UiHeplUtils.webviewRegister(viewDelegate.viewHolder.webview);
        viewDelegate.viewHolder.webview.loadUrl(type);
        viewDelegate.viewHolder.webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                initToolbar(new ToolbarBuilder().setTitle(title).setSubTitle("帮助"));
            }
        });
        showDialog();
        viewDelegate.viewHolder.webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                dimessDialog();
            }
        });
    }

    private void dimessDialog() {
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    private void showDialog() {
        dialogFragment = new CircleDialog.Builder(this)
                .setProgressText("加载中...")
                .setProgressStyle(ProgressParams.STYLE_SPINNER)
                .setCanceledOnTouchOutside(false)
                .setCancelable(true)
                .setCircleDialogLinsener(this)
                //                        .setProgressDrawable(R.drawable.bg_progress_s)
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && viewDelegate.viewHolder.webview.canGoBack()) {
            viewDelegate.viewHolder.webview.goBack();
            return true;
        } else {
            onBackPressed();
        }
        return true;
    }

    public static void startAct(Activity activity,
                                String type) {
        Intent intent = new Intent(activity, WebVeiwActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    private String type;

    private void getIntentData() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
    }

    @Override
    protected void onDestroy() {
        viewDelegate.viewHolder.webview.setWebChromeClient(null);
        viewDelegate.viewHolder.webview.setWebViewClient(null);
        viewDelegate.viewHolder.webview.setEnabled(false);
        viewDelegate.viewHolder.webview.removeAllViews();
        viewDelegate.viewHolder.webview.destroy();
        super.onDestroy();
    }

    @Override
    protected Class<WebViewDelegate> getDelegateClass() {
        return WebViewDelegate.class;
    }
}
