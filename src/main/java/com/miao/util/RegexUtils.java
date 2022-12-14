package com.miao.util;
import cn.hutool.core.util.StrUtil;
/**
 * @author Dhx_
 * @className RegexUtils
 * @description TODO
 * @date 2022/10/22 16:43
 */
public class RegexUtils {
    /**
     * 是否是无效手机格式
     *
     * @param phone 要校验的手机号
     * @return true:符合，false：不符合
     */
    public static boolean isPhoneInvalid(String phone) {
        return mismatch(phone, RegexPatterns.PHONE_REGEX);
    }

    /**
     * 是否是无效邮箱格式
     *
     * @param email 要校验的邮箱
     * @return true:符合，false：不符合
     */
    public static boolean isEmailInvalid(String email) {
        return mismatch(email, RegexPatterns.EMAIL_REGEX);
    }

    /**
     * 是否是无效验证码格式
     *
     * @param code 要校验的验证码
     * @return true:符合，false：不符合
     */
    public static boolean isCodeInvalid(String code) {
        return mismatch(code, RegexPatterns.VERIFY_CODE_REGEX);
    }

    /**
     * 是否是无效账户格式
     *
     * @param code 要校验的账户名
     * @return true:符合，false：不符合
     */
    public static boolean isUserAccountInvalid(String code) {
        return mismatch(code, RegexPatterns.USER_ACCOUNT_REGEX);
    }
    /**
     * 是否是无效用户名格式
     *
     * @param code 要校验的账户名
     * @return true:符合，false：不符合
     */
    public static boolean isUserNameInvalid(String code) {
        return mismatch(code, RegexPatterns.USER_NAME_REGEX);
    }

    /**
     * 是否是无效密码格式
     *
     * @param code 要校验的密码
     * @return true:符合，false：不符合
     */
    public static boolean isPasswordInvalid(String code) {
        return mismatch(code, RegexPatterns.PASSWORD_REGEX);
    }

    // 校验是否不符合正则格式
    private static boolean mismatch(String str, String regex) {
        if (StrUtil.isBlank(str)) {
            return true;
        }
        return !str.matches(regex);
    }

    /**
     *是否是无效的学号格式
     * @param stuCode 要校验的密码
     * @return true:符合，false：不符合
     */
    public static boolean isStuCodeInvalid(String stuCode) {
        return mismatch(stuCode, RegexPatterns.STUDENT_CODE_REGEX);
    }
}
