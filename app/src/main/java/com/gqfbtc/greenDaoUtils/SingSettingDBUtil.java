package com.gqfbtc.greenDaoUtils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.gqfbtc.entity.bean.ImUser;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.activity.main.LoginActivity;
import com.gqfbtc.mvp.activity.main.SetUserNameActivity;

import java.util.List;


/**
 * Created by 郭青枫 on 2017/10/13.
 */

public class SingSettingDBUtil {

    private static String isUser = "";

    private static String isLogin = "";//0 未登陆 //1 已登录

    public static void setIsUser(String isUser) {
        SingSettingDBUtil.isUser = isUser;
    }

    public static boolean isUser() {
        if (TextUtils.isEmpty(isUser) && getUserLogin() != null) {
            isUser = TextUtils.isEmpty(getUserLogin().getName()) ? "0" : "1";//0用户
        }
        return isUser.equals("0");
    }

    public static boolean isLogin(Activity activity) {
        //if (TextUtils.isEmpty(isLogin)) {
        checkIsLogin(activity);
        // }
        if ("0".equals(isLogin)) {
            return false;
        } else {
            return true;
        }
    }

    private static void checkIsLogin(Activity activity) {
        if (getUserLogin() == null) {
            isLogin = "0";
            activity.startActivity(new Intent(activity, LoginActivity.class));
            return;
        } else if (TextUtils.isEmpty(getUserLogin().getNickName())) {
            if (isUser()) {
                isLogin = "0";
                activity.startActivity(new Intent(activity, SetUserNameActivity.class));
                return;
            }
        }
        isLogin = "1";
    }

    public static void logout() {
        delectUserLogin();
        delectImUser();
        isUser = "";
        isLogin = "";
        HttpUrl.getIntance().delectUidAndToken();
    }


    //登录信息保存
    public static void setNewUserLogin(UserLogin userLogin) {
        //获取到用户基本信息,保存在数据库
        if (userLogin != null) {
            if (!TextUtils.isEmpty(userLogin.getPhone())) {
                if (DaoManager.getInstance().getDaoSession().getUserLoginDao().queryBuilder().list().size() != 0) {
                    //如果有先删除
                    delectUserLogin();
                }
                //插入
                DaoManager.getInstance().getDaoSession().getUserLoginDao().insert(userLogin);
                HttpUrl.getIntance().saveUid(userLogin.getId() + "");
            }
        }
    }

    //登录信息获取
    public static UserLogin getUserLogin() {
        if (DaoManager.getInstance().getDaoSession().getUserLoginDao().queryBuilder().list() != null) {
            if (DaoManager.getInstance().getDaoSession().getUserLoginDao().queryBuilder().list().size() != 0) {
                List<UserLogin> userLogins = DaoManager.getInstance().getDaoSession().getUserLoginDao().queryBuilder().list();
                for (int i = 0; i < userLogins.size(); i++) {
                    if ((userLogins.get(i).getId() + "").equals(SaveUtil.getInstance().getString("uid"))) {
                        return userLogins.get(i);
                    }
                }
            }
        }
        return null;
    }


    //登录信息删除
    public static void delectUserLogin() {
        if (DaoManager.getInstance().getDaoSession().getUserLoginDao().queryBuilder().list().size() != 0) {
            DaoManager.getInstance().getDaoSession().getUserLoginDao().deleteAll();
        }
    }


    //im信息保存
    public static void setNewImUser(ImUser imUser) {
        if (imUser != null) {
            if (!TextUtils.isEmpty(imUser.getToken())) {
                if (DaoManager.getInstance().getDaoSession().getImUserDao().queryBuilder().list().size() != 0) {
                    //如果有先删除
                    delectImUser();
                }
                //插入
                DaoManager.getInstance().getDaoSession().getImUserDao().insert(imUser);
            }
        }
    }

    //im信息获取
    public static ImUser getImUser() {
        if (DaoManager.getInstance().getDaoSession().getImUserDao().queryBuilder().list() != null) {
            if (DaoManager.getInstance().getDaoSession().getImUserDao().queryBuilder().list().size() != 0) {
                List<ImUser> imUsers = DaoManager.getInstance().getDaoSession().getImUserDao().queryBuilder().list();
                for (int i = 0; i < imUsers.size(); i++) {
                    if ((imUsers.get(i).getId() + "").equals(SaveUtil.getInstance().getString("uid"))) {
                        return imUsers.get(i);
                    }
                }
            }
        }
        return null;
    }


    //im信息删除
    public static void delectImUser() {
        if (DaoManager.getInstance().getDaoSession().getImUserDao().queryBuilder().list().size() != 0) {
            DaoManager.getInstance().getDaoSession().getImUserDao().deleteAll();
        }
    }

}
