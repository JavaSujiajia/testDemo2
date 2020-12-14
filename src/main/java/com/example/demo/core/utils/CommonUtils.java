package com.example.demo.core.utils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @ClassName: CommonUtils
 * @Description: 工具类
 */
public class CommonUtils {

    /**
     * @param o
     * @return
     * @Title: isEmpty
     * @Description: 判断某个对象是否为空支持 String、数组、集合、map以及对象
     * @return boolean
     * @author MH
     * @date 2017年9月22日 下午5:02:04
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object o) {
        if (o == null) return true;
        //字符串
        if (o instanceof String) {
            return StringUtils.isBlank((String) o);
            //数组
        } else if (o instanceof Object[]) {
            Object[] _o = null;
            for (int i = 0; i < (_o = (Object[]) o).length; i++) {
                if (_o[i] != null && !"".equals(_o[i])) {
                    return false;
                }
            }
            //集合
        } else if (o instanceof Collection) {
            Collection c = null;
            return (c = (Collection) o).isEmpty() || c.size() <= 0;
            //map
        } else if (o instanceof Map) {
            return MapUtils.isEmpty((Map) o);
        }
        return false;
    }

    /**
     * @param o
     * @return
     * @Title: isNotEmpty
     * @Description: 返回 !isEmpty()
     * @return boolean
     * @author MH
     * @date 2017年9月22日 下午5:04:01
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * @param str
     * @return
     * @Title: camelhumpToUnderline
     * @Description: 驼峰转下划线，根据我们的数据库字段特点，转换后的字符以下划线开头
     * @return String
     * @author MH
     * @date 2017年8月4日 下午1:59:56
     */
    public static String camelhumpToUnderline(String str) {
        final int size;
        final char[] chars;
        final StringBuilder sb = new StringBuilder(
                (size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
        char c;
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (isUppercaseAlpha(c)) {
                sb.append('_').append(toLowerAscii(c));
            } else {
                sb.append(c);
            }
        }
        return sb.charAt(0) == '_' ? sb.toString() : "_" + sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     * @Title: underlineToCamelhump
     * @Description: 
     * @return String
     * @author MH
     * @date 2017年8月4日 下午2:08:36
     */
    public static String underlineToCamelhump(String str) {
        Matcher matcher = Pattern.compile("_[a-z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }

    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    public static boolean isLowercaseAlpha(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    public static char toUpperAscii(char c) {
        if (isLowercaseAlpha(c)) {
            c -= (char) 0x20;
        }
        return c;
    }

    public static char toLowerAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c += (char) 0x20;
        }
        return c;
    }


    /**
     * @param s
     * @return
     * @Title: isCellPhoneNumber
     * @Description: 验证手机号
     * @return boolean
     * @author MH
     * @date 2017年10月31日 上午11:19:54
     */
    public static boolean isCellPhoneNumber(String s) {
        if (StringUtils.isBlank(s)) return false;
        return s.matches("^(\\+?86\\s*)?1\\d{10}$");
    }

    /**
     * @param s
     * @return
     * @Title: isMail
     * @Description: 判断是否是邮箱
     * @return boolean
     * @author MH
     * @date 2017年10月31日 上午11:20:51
     */
    public static boolean isMail(String s) {
        if (StringUtils.isBlank(s)) return false;
        return s.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
    }

    /**
     * @param s
     * @return
     * @Title: isNumber
     * @Description:判断是否是数字包括整数跟浮点数 
     * @return boolean
     * @author MH
     * @date 2017年11月14日 下午2:36:43
     */
    public static boolean isNumber(String s) {
        if (StringUtils.isBlank(s)) return false;
        return s.matches("^\\d{1,}(\\.\\d{1,})?$");
    }

    /**
     * @param s
     * @return
     * @Title: isInteger
     * @Description: 判断是否是整数
     * @return boolean
     * @author MH
     * @date 2017年11月14日 下午2:37:30
     */
    public static boolean isInteger(String s) {
        if (StringUtils.isBlank(s)) return false;
        return s.matches("^\\d{1,}$");
    }


    public static void main(String[] args) {
        System.out.println(StringUtils.isBlank(""));
    }
}
