package com.gqfbtc.greenDB;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.gqfbtc.entity.bean.ImUser;
import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.entity.bean.UserLogin;

import com.gqfbtc.greenDB.ImUserDao;
import com.gqfbtc.greenDB.PaymentBTCETHAddressDao;
import com.gqfbtc.greenDB.UserLoginDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig imUserDaoConfig;
    private final DaoConfig paymentBTCETHAddressDaoConfig;
    private final DaoConfig userLoginDaoConfig;

    private final ImUserDao imUserDao;
    private final PaymentBTCETHAddressDao paymentBTCETHAddressDao;
    private final UserLoginDao userLoginDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        imUserDaoConfig = daoConfigMap.get(ImUserDao.class).clone();
        imUserDaoConfig.initIdentityScope(type);

        paymentBTCETHAddressDaoConfig = daoConfigMap.get(PaymentBTCETHAddressDao.class).clone();
        paymentBTCETHAddressDaoConfig.initIdentityScope(type);

        userLoginDaoConfig = daoConfigMap.get(UserLoginDao.class).clone();
        userLoginDaoConfig.initIdentityScope(type);

        imUserDao = new ImUserDao(imUserDaoConfig, this);
        paymentBTCETHAddressDao = new PaymentBTCETHAddressDao(paymentBTCETHAddressDaoConfig, this);
        userLoginDao = new UserLoginDao(userLoginDaoConfig, this);

        registerDao(ImUser.class, imUserDao);
        registerDao(PaymentBTCETHAddress.class, paymentBTCETHAddressDao);
        registerDao(UserLogin.class, userLoginDao);
    }
    
    public void clear() {
        imUserDaoConfig.clearIdentityScope();
        paymentBTCETHAddressDaoConfig.clearIdentityScope();
        userLoginDaoConfig.clearIdentityScope();
    }

    public ImUserDao getImUserDao() {
        return imUserDao;
    }

    public PaymentBTCETHAddressDao getPaymentBTCETHAddressDao() {
        return paymentBTCETHAddressDao;
    }

    public UserLoginDao getUserLoginDao() {
        return userLoginDao;
    }

}
