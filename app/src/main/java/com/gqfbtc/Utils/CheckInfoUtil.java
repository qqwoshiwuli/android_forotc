package com.gqfbtc.Utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验信息的传入
 *
 * @author mr.wang
 */
public class CheckInfoUtil {

    /**
     * @param param 传入Object对象
     * @return boolean  true 不为空 false 为空
     * @throws
     * @Description: 校验前台传值是否为空
     * @author Kai
     */
    public static boolean checkParams(String param) {
        if (TextUtils.isEmpty(param)) {
            return false;
        }
        return true;
    }

    /**
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: checkMsgCode
     * @Description: 验证手机号格式是否正确
     * @author Kai
     * @date 2017年11月6日 上午9:52:00
     */
    public static boolean checkPhoneForMat(String phone) {
        //String regex = "^[1]+[3,5]+\\d{9}$";
        if (checkParams(phone) && phone.length() == 11) {
            return true;
        }
        return false;
    }

    /**
     * 校验密码格式是否正确
     *
     * @param password
     * @return kai
     * 2017年11月06日10:10:18
     */
    public static boolean checkPasswordFormat(String password) {
        if (!checkParams(password)) {
            return false;
        }
        String regexp = "^[a-zA-Z0-9]{6,20}$";
        return match(regexp, password);
    }

    /**
     * 校验邮箱格式是否正确
     *
     * @param email kai
     *              2017年11月06日10:20:59
     * @return 通过 true 不通过 false
     */
    public static boolean checkEmailFormat(String email) {
        if (!checkParams(email)) {
            return false;
        }
        String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return match(regex, email);
    }

    /**
     * 校验用户昵称格式是否正确
     *
     * @param nickName kai
     *                 2017年11月6日  上午11:19:27
     * @return
     */
    public static boolean checkNickName(String nickName) {
        if (checkParams(nickName) && nickName.length() < 9) {
            return true;
        }
        return false;
    }

    /**
     * 检查是否为中文汉字
     *
     * @param param kai
     *              2017年11月7日  上午11:02:03
     */
    public static boolean checkIsChinese(String param) {
        if (!checkParams(param)) {
            return false;
        }
        String regex = "[\\u4e00-\\u9fa5]+";
        return match(regex, param);
    }

    /**
     * 判断是否包含中文
     *
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return false;
        }
        return true;
    }

    /**
     * 检查是否为数字
     *
     * @param param kai
     *              2017年11月7日  上午11:27:57
     * @return
     */
    public static boolean checkIsNumber(String param) {
        String regex = "^[0-9]*$";
        return match(regex, param);
    }

    //    /**
    //     * 去除掉传入值内的空格
    //     *
    //     * kai
    //     * 2017年11月7日  上午11:14:12
    //     */
    //    public static String trimParam(String param) {
    //    		if(Objects.equals(null, param)) {
    //    			return "";
    //    		}
    //		return param.replaceAll("\\s*", "");
    //	}

    /**
     * 检查开户行 格式是否正确  长度小于等于12位 中文
     *
     * @param bankName kai
     *                 2017年11月7日  上午11:35:47
     */
    public static boolean checkBankName(String bankName) {
        if (checkParams(bankName) && bankName.length() < 13 && checkIsChinese(bankName)) {
            return true;
        }
        return false;
    }

    /**
     * 检查开户支行 格式是否正确  长度小于等于18位 中文
     * <p>
     * kai
     * 2017年11月7日  上午11:37:32
     *
     * @return
     */
    public static boolean checkBranchName(String branchName) {
        if (branchName.length() < 19) {
            return true;
        }
        return false;
    }

    /**
     * 检查开户人 格式是否正确  长度小于等于8位 中文
     * <p>
     * kai
     * 2017年11月7日  上午11:37:32
     *
     * @return
     */
    public static boolean checkOwnerName(String ownerName) {
        if (checkParams(ownerName) && ownerName.length() < 9 && checkIsChinese(ownerName)) {
            return true;
        }
        return false;
    }

    /**
     * 检查收款账号 格式是否正确  8-20位 全数字
     *
     * @param collectionAddr kai
     *                       2017年11月7日  上午11:51:17
     * @return
     */
    public static boolean checkCollectionAddr(String collectionAddr) {
        if (checkParams(collectionAddr) && collectionAddr.length() > 7 && collectionAddr.length() < 21 && checkIsNumber(collectionAddr)) {
            return true;
        }
        return false;
    }

    /**
     * 检查用户备注是否正确 长度为小于等于6位
     *
     * @param alias kai
     *              2017年11月7日  上午11:59:45
     * @return
     */
    public static boolean checkAlias(String alias) {
        if (checkParams(alias) && alias.length() < 7) {
            return true;
        }
        return false;
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        //全部匹配
        return matcher.matches();
        //部分匹配
        //return matcher.find();
    }

    /**
     * 判断两笔订单数量
     * 检查返回行数是否为1行  如果为1行就成功
     * 如果更新或保存不为1条的话，认为更新或保存失败，抛出异常以供事物捕获并回滚
     *
     * @return kai
     */
    public static boolean checkNum(int num1, int num2) {
        if (num1 == 1 && num2 == 1) {
            return true;
        }
        throw new RuntimeException();
    }

}
