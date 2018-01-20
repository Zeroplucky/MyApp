package com.example.utils;

/**
 * Created by Administrator on 2017/12/14.
 */

public class StringUtils {

    private static final String USER_NAME_REGEX = "^[a-zA-Z]\\w{2,19}$";

    private static final String PASSWORD_REGEX = "^[0-9]{3,20}$";


    public static boolean checkUserName(String userName) {
        return userName.matches(USER_NAME_REGEX);
    }

    public static boolean checkPassword(String pwd) {
        return pwd.matches(PASSWORD_REGEX);
    }

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        return !(value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim()));
    }
}
