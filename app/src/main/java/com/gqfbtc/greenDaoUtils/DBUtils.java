package com.gqfbtc.greenDaoUtils;

import com.gqfbtc.entity.bean.PaymentBTCETHAddress;
import com.gqfbtc.greenDB.PaymentBTCETHAddressDao;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/11/20.
 */

public class DBUtils {

    //BTC地址
    public static void setUserCoinBTCAddressList(List<PaymentBTCETHAddress> paymentBTCETHAddresses) {
        //先插入
        for (int i = 0; i < paymentBTCETHAddresses.size(); i++) {
            DaoManager.getInstance().getDaoSession().getPaymentBTCETHAddressDao().insertOrReplace(paymentBTCETHAddresses.get(i));
        }
        //再查询本地是否有 无效地址 并删除
        List<PaymentBTCETHAddress> DBBtcAddress = getAllCoinBTCAddress();
        for (int i = 0; i < DBBtcAddress.size(); i++) {
            for (int j = 0; j < paymentBTCETHAddresses.size(); j++) {
                boolean isHave = false;
                if (DBBtcAddress.get(i).getId() == paymentBTCETHAddresses.get(i).getId()) {
                    isHave = true;
                }
                if (!isHave) {
                    delectUserCoinBTCAddress(DBBtcAddress.get(i));
                }
            }
        }
    }

    public static void delectUserCoinBTCAddress(PaymentBTCETHAddress paymentBTCETHAddress) {
        DaoManager.getInstance().getDaoSession().getPaymentBTCETHAddressDao().delete(paymentBTCETHAddress);
    }


    public static List<PaymentBTCETHAddress> getAllCoinBTCAddress() {
        return DaoManager.getInstance().getDaoSession().getPaymentBTCETHAddressDao().queryBuilder().where(PaymentBTCETHAddressDao.Properties.CollectionType.gt("3")).list();
    }


}
