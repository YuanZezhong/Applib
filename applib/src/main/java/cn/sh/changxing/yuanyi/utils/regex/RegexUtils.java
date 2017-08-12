package cn.sh.changxing.yuanyi.utils.regex;

import java.util.regex.Pattern;

/**
 * @class：RegexUtils
 * @des:使用正则表达式验证信息
 * @author：yanxiaosa
 * @email：15555994291@163.com
 * @date：Administrator on 2017/8/12
 */

public class RegexUtils {

    /**
     * 验证手机号（简单）
     *
     * @param input 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isMobileSimple(CharSequence input) {
        return isMatch(RegexConstants.REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 验证手机号（精确）
     *
     * @param input 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isMobileExact(CharSequence input) {
        return isMatch(RegexConstants.REGEX_MOBILE_EXACT, input);
    }

    /**
     * 验证电话号码
     *
     * @param input 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isTel(CharSequence input) {
        return isMatch(RegexConstants.REGEX_TEL, input);
    }

    /**
     * 验证身份证号码15位
     *
     * @param input 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isIDCard15(CharSequence input) {
        return isMatch(RegexConstants.REGEX_ID_CARD15, input);
    }

    /**
     * 验证身份证号码18位
     *
     * @param input 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isIDCard18(CharSequence input) {
        return isMatch(RegexConstants.REGEX_ID_CARD18, input);
    }

    /**
     * 验证邮箱
     *
     * @param input 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isEmail(CharSequence input) {
        return isMatch(RegexConstants.REGEX_EMAIL, input);
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

}
