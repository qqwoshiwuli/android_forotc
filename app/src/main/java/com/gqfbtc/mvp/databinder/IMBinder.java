package com.gqfbtc.mvp.databinder;

import com.blankj.utilcode.util.LogUtils;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.mvp.databind.IDataBind;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GlobleContext;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfbtc.R;
import com.gqfbtc.base.Application;
import com.gqfbtc.mvp.delegate.IMDelegate;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by 郭青枫 on 2017/11/12.
 */

public abstract class IMBinder<T extends IMDelegate> extends BaseDataBind<T> implements IDataBind<T> {

    public IMBinder(T viewDelegate) {
        super(viewDelegate);
    }


    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    public void connect(String token) {
        LogUtils.i("--" + token);
        if (GlobleContext.getInstance().getApplicationContext().getApplicationInfo().packageName.equals(Application.getCurProcessName(GlobleContext.getInstance().getApplicationContext()))) {
            // RongIMClient.init(GlobleContext.getInstance().getApplicationContext());
            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {
                    LogUtils.e("--onTokenIncorrect");
                    ToastUtil.show("登陆信息过期，请重新登陆！");
                    viewDelegate.ImError();
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    LogUtils.e("--onSuccess---" + userid);
                    viewDelegate.ImSuccess();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtils.e("--onError" + errorCode);
                    ToastUtil.show(CommonUtils.getString(R.string.disconnect_server));
                    //跳转login 页面
                    viewDelegate.ImError();
                }
            });
        }
    }

}
