package com.gqfbtc.mvp.delegate;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;

/**
 * Created by 郭青枫 on 2017/11/12.
 */

public abstract class IMDelegate extends BaseDelegate {

    public interface IMLinsener{
          void ImError();

          void ImSuccess();
    }

    IMLinsener imLinsener;

    public void setImLinsener(IMLinsener imLinsener) {
        this.imLinsener = imLinsener;
    }

    public abstract void ImError();

    public abstract void ImSuccess();

}
