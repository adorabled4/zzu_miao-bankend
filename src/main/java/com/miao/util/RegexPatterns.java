package com.miao.util;

/**
 * @author Dhx_
 * @className RegexPatterns
 * @description TODO
 * @date 2022/10/22 16:44
 */
public abstract class RegexPatterns {
    /**
     * 手机号正则
     */
    public static final String PHONE_REGEX = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
    /**
     * 邮箱正则
     */
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /**
     * 密码正则。4~32位的字母、数字、下划线
     */
    public static final String PASSWORD_REGEX = "^\\w{4,32}$";
    /**
     * 验证码正则, 6位数字或字母
     */
    public static final String VERIFY_CODE_REGEX = "^[a-zA-Z\\d]{6}$";

    /**
     * 账户名正则 : 4-16位字母,数字,汉字,下划线 其中两个汉字是可以注册成功的,表示4个字符
     */
    public static final String USER_ACCOUNT_REGEX= "^([\\u4e00-\\u9fa5]{2,4})|([A-Za-z0-9_]{4,16})|([a-zA-Z0-9_\\u4e00-\\u9fa5]{3,16})$";

    /**
     * 学号正则   ^2012350101(?:0[1-9]|[1-2]\d|3[0-2])$
     */
    public static final String STUDENT_CODE_REGEX="^\\d{12,12}$";

    /**
     * 用户名正则 :  4-16位字母,数字,汉字,下划线 其中两个汉字是可以注册成功的,表示4个字符
     */
    public static final String USER_NAME_REGEX= "^([\\u4e00-\\u9fa5]{2,4})|([A-Za-z0-9_]{4,16})|([a-zA-Z0-9_\\u4e00-\\u9fa5]{3,16})$";

}

