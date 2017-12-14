package com.fivefivelike.mybaselibrary.base;


import com.fivefivelike.mybaselibrary.mvp.databind.IDataBind;
import com.fivefivelike.mybaselibrary.mvp.view.IDelegate;
import com.fivefivelike.mybaselibrary.utils.SaveUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 郭青枫 on 2017/7/3.
 */

public abstract class BaseDataBind<T extends IDelegate> implements IDataBind<T> {
    protected T viewDelegate;
    protected Map<String, Object> baseMap;

    public BaseDataBind(T viewDelegate) {
        this.viewDelegate = viewDelegate;
    }

    protected Map<String, Object> getBaseMap() {
        baseMap = new HashMap<>();
        return baseMap;
    }

    protected Map<String, Object> getBaseMapWithUid() {
        getBaseMap();
        baseMap.put("uid", SaveUtil.getInstance().getString("uid"));
        baseMap.put("token", SaveUtil.getInstance().getString("token"));
        return baseMap;
    }

}
